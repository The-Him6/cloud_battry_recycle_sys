package com.br.seckill.service.impl;

import com.br.api.client.UserPointsClient;
import com.br.api.dto.UserPointsDTO;
import com.br.common.constants.RedisConstants;
import com.br.common.constants.SystemConstants;
import com.br.common.domain.Result;
import com.br.common.exception.BadRequestException;
import com.br.common.exception.DbException;
import com.br.common.utils.CacheClient;
import com.br.seckill.entity.SeckillActivity;
import com.br.seckill.entity.UserSeckillCoupon;
import com.br.seckill.mapper.SeckillActivityMapper;
import com.br.seckill.mapper.UserSeckillCouponMapper;
import com.br.seckill.mq.producer.SeckillCouponProducer;
import com.br.seckill.service.ISeckillActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 秒杀活动服务类
 */
@Slf4j
@Service("seckillActivityService")
@RequiredArgsConstructor
public class SeckillActivityServiceImpl implements ISeckillActivityService {

    private final SeckillActivityMapper seckillActivityMapper;

    private final UserSeckillCouponMapper userSeckillCouponMapper;

    private final UserPointsClient userPointsClient;

    private final StringRedisTemplate stringRedisTemplate;

    private final CacheClient cacheClient;

    private final SeckillCouponProducer seckillCouponProducer;

    /**
     * 秒杀Lua脚本，保证Redis库存和重复抢购判断原子执行
     */
    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;

    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("seckill_coupon.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }

    /**
     * 根据ID查询活动，使用互斥锁缓存避免热点活动缓存击穿
     */
    public SeckillActivity getById(Long id) {
        SeckillActivity activity = cacheClient.queryWithMutex(
                RedisConstants.CACHE_SECKILL_ACTIVITY_KEY,
                id,
                SeckillActivity.class,
                seckillActivityMapper::getById,
                RedisConstants.CACHE_NORMAL_TTL,
                TimeUnit.MINUTES
        );
        if (activity == null) {
            throw new DbException(SystemConstants.SECKILL_ACTIVITY_NOT_FOUND);
        }
        return activity;
    }

    /**
     * 管理员查询全部活动
     */
    public List<SeckillActivity> listAll() {
        return seckillActivityMapper.listAll();
    }

    /**
     * 用户查询已上架活动
     */
    public List<SeckillActivity> listOnline() {
        return seckillActivityMapper.listOnline();
    }

    /**
     * 管理员创建活动，默认500积分、100库存、草稿状态
     */
    public void add(SeckillActivity activity, Long adminId) {
        fillDefaultValue(activity, adminId);
        validateActivityTime(activity);
        seckillActivityMapper.insert(activity);
    }

    /**
     * 管理员更新活动，同时删除活动缓存
     */
    public void update(SeckillActivity activity) {
        if (activity.getId() == null || seckillActivityMapper.getById(activity.getId()) == null) {
            throw new DbException(SystemConstants.SECKILL_ACTIVITY_NOT_FOUND);
        }
        validateActivityTime(activity);
        seckillActivityMapper.update(activity);
        deleteActivityCache(activity.getId());
    }

    /**
     * 管理员上架活动，上架时把库存预热到Redis
     */
    public void online(Long id) {
        SeckillActivity activity = seckillActivityMapper.getById(id);
        if (activity == null) {
            throw new DbException(SystemConstants.SECKILL_ACTIVITY_NOT_FOUND);
        }
        activity.setStatus(SystemConstants.SECKILL_STATUS_ONLINE);
        seckillActivityMapper.update(activity);
        preheat(id);
        deleteActivityCache(id);
    }

    /**
     * 管理员下架活动，同时清理缓存和秒杀运行期Redis数据
     */
    public void offline(Long id) {
        SeckillActivity activity = seckillActivityMapper.getById(id);
        if (activity == null) {
            throw new DbException(SystemConstants.SECKILL_ACTIVITY_NOT_FOUND);
        }
        activity.setStatus(SystemConstants.SECKILL_STATUS_OFFLINE);
        seckillActivityMapper.update(activity);
        deleteSeckillRuntimeKeys(id);
    }

    /**
     * 手动预热活动库存到Redis，保证秒杀前Redis有库存Key
     */
    public void preheat(Long id) {
        SeckillActivity activity = seckillActivityMapper.getById(id);
        if (activity == null) {
            throw new DbException(SystemConstants.SECKILL_ACTIVITY_NOT_FOUND);
        }
        int remainStock = Math.max(activity.getStock() - activity.getSold(), 0);
        String stockKey = RedisConstants.SECKILL_STOCK_KEY + id;
        stringRedisTemplate.opsForValue().set(stockKey, String.valueOf(remainStock), 8, TimeUnit.DAYS);
        cacheClient.set(RedisConstants.CACHE_SECKILL_ACTIVITY_KEY + id, activity, RedisConstants.CACHE_NORMAL_TTL, TimeUnit.MINUTES);
    }

    /**
     * 用户抢券入口，先做业务校验，再交给Lua做Redis原子预扣，成功后投递RabbitMQ异步发券
     */
    public void seckill(Long activityId, Long userId) {
        SeckillActivity activity = getById(activityId);
        validateSeckillActivity(activity);
        validateUserCanSeckill(activity, userId);

        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Arrays.asList(
                        RedisConstants.SECKILL_STOCK_KEY + activityId,
                        RedisConstants.SECKILL_USERS_KEY + activityId
                ),
                activityId.toString(),
                userId.toString()
        );
        int code = result == null ? -1 : result.intValue();
        if (code == 1) {
            throw new BadRequestException(SystemConstants.SECKILL_STOCK_NOT_ENOUGH);
        }
        if (code == 2) {
            throw new BadRequestException(SystemConstants.SECKILL_REPEAT_ORDER);
        }
        if (code != 0) {
            throw new BadRequestException(SystemConstants.OPERATION_FAILED);
        }

        try {
            seckillCouponProducer.sendSeckillCouponMessage(activityId, userId);
        } catch (Exception e) {
            compensateRedis(activityId, userId);
            throw e;
        }
    }

    /**
     * Stream消费者调用的核心处理逻辑，后续换RabbitMQ时也复用这个方法
     */
        @Transactional(rollbackFor = Exception.class)
    public void handleSeckillMessage(Long activityId, Long userId) {
        SeckillActivity activity = seckillActivityMapper.getById(activityId);
        if (activity == null) {
            throw new DbException(SystemConstants.SECKILL_ACTIVITY_NOT_FOUND);
        }
        if (userSeckillCouponMapper.getByActivityAndUser(activityId, userId) != null) {
            return;
        }

        // 扣减积分（远程调用，本地事务无法回滚，后续失败需要显式补偿）
        Result<Boolean> deduct = userPointsClient.deduct(userId, activity.getPointsCost());
        if (deduct == null || !Boolean.TRUE.equals(deduct.getData())) {
            throwPointsServiceException(deduct);
        }

        try {
            int soldRows = seckillActivityMapper.increaseSold(activityId);
            if (soldRows == 0) {
                throw new BadRequestException(SystemConstants.SECKILL_STOCK_NOT_ENOUGH);
            }

            UserSeckillCoupon coupon = new UserSeckillCoupon();
            coupon.setActivityId(activityId);
            coupon.setUserId(userId);
            coupon.setStatus(SystemConstants.COUPON_STATUS_UNUSED);
            coupon.setEffectiveTime(activity.getCouponStartTime());
            coupon.setExpireTime(activity.getCouponEndTime());
            userSeckillCouponMapper.insert(coupon);
            deleteActivityCache(activityId);
        } catch (Exception e) {
            // 积分已扣但后续落库失败，退还积分，避免用户损失
            try {
                userPointsClient.add(userId, activity.getPointsCost());
            } catch (Exception ex) {
                log.error("秒杀失败退还积分失败，userId={}, points={}，需人工处理", userId, activity.getPointsCost(), ex);
            }
            throw e;
        }
    }

    /**
     * 秒杀落库或消息投递失败时补偿Redis预扣库存和已抢用户Set。
     * 先删除用户Set再恢复库存，可以避免重复补偿导致库存被多加。
     */
    public void compensateRedis(Long activityId, Long userId) {
        Long removed = stringRedisTemplate.opsForSet()
                .remove(RedisConstants.SECKILL_USERS_KEY + activityId, userId.toString());
        if (removed != null && removed > 0) {
            stringRedisTemplate.opsForValue().increment(RedisConstants.SECKILL_STOCK_KEY + activityId);
        }
    }

    /**
     * 活动创建时填充默认值
     */
    private void fillDefaultValue(SeckillActivity activity, Long adminId) {
        if (activity.getStock() == null) {
            activity.setStock(100);
        }
        if (activity.getSold() == null) {
            activity.setSold(0);
        }
        if (activity.getPointsCost() == null) {
            activity.setPointsCost(500);
        }
        if (activity.getStatus() == null) {
            activity.setStatus(SystemConstants.SECKILL_STATUS_DRAFT);
        }
        activity.setCreateAdminId(adminId);
    }

    /**
     * 校验活动时间完整性
     */
    private void validateActivityTime(SeckillActivity activity) {
        if (activity.getStartTime() != null && activity.getEndTime() != null
                && !activity.getStartTime().isBefore(activity.getEndTime())) {
            throw new BadRequestException(SystemConstants.SECKILL_END_AFTER_START);
        }
        if (activity.getCouponStartTime() != null && activity.getCouponEndTime() != null
                && !activity.getCouponStartTime().isBefore(activity.getCouponEndTime())) {
            throw new BadRequestException(SystemConstants.SECKILL_COUPON_EXPIRE_AFTER_EFFECTIVE);
        }
    }

    /**
     * 校验秒杀活动当前是否允许抢券
     */
    private void validateSeckillActivity(SeckillActivity activity) {
        if (!SystemConstants.SECKILL_STATUS_ONLINE.equals(activity.getStatus())) {
            throw new BadRequestException(SystemConstants.SECKILL_ACTIVITY_OFFLINE);
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(activity.getStartTime())) {
            throw new BadRequestException(SystemConstants.SECKILL_ACTIVITY_NOT_STARTED);
        }
        if (now.isAfter(activity.getEndTime())) {
            throw new BadRequestException(SystemConstants.SECKILL_ACTIVITY_ENDED);
        }
    }

    /**
     * 用户抢券前做快速校验，最终仍由MySQL事务兜底
     */
    private void validateUserCanSeckill(SeckillActivity activity, Long userId) {
        if (userSeckillCouponMapper.getByActivityAndUser(activity.getId(), userId) != null) {
            throw new BadRequestException(SystemConstants.SECKILL_REPEAT_ORDER);
        }
        Result<UserPointsDTO> userPointsDTO = userPointsClient.getByUserId(userId);
        boolean pointsServiceDown = userPointsDTO == null
                || (userPointsDTO.getCode() != null && userPointsDTO.getCode() == 500);
        if (pointsServiceDown || userPointsDTO.getData() == null) {
            // 积分服务不可用（fallback 返回 code=500）时提示重试，而不是误报"积分不足"
            throw new BadRequestException(pointsServiceDown ? "积分服务不可用，请重试" : SystemConstants.SECKILL_POINTS_NOT_ENOUGH);
        }
        if (userPointsDTO.getData().getAvailablePoints() < activity.getPointsCost()) {
            throw new BadRequestException(SystemConstants.SECKILL_POINTS_NOT_ENOUGH);
        }
    }

    /**
     * 积分服务调用失败的统一处理：
     * code=500（fallback 触发，服务不可用）→ 提示重试；
     * 否则（业务失败，如积分不足）→ 抛积分不足。
     */
    private void throwPointsServiceException(Result<?> result) {
        if (result == null || (result.getCode() != null && result.getCode() == 500)) {
            throw new BadRequestException("积分服务不可用，请重试");
        }
        throw new BadRequestException(SystemConstants.SECKILL_POINTS_NOT_ENOUGH);
    }

    /**
     * 删除活动缓存
     */
    private void deleteActivityCache(Long id) {
        stringRedisTemplate.delete(RedisConstants.CACHE_SECKILL_ACTIVITY_KEY + id);
    }

    /**
     * 清理秒杀活动运行期Redis数据，下架后不保留库存Key和已抢用户Set。
     */
    private void deleteSeckillRuntimeKeys(Long id) {
        stringRedisTemplate.delete(Arrays.asList(
                RedisConstants.CACHE_SECKILL_ACTIVITY_KEY + id,
                RedisConstants.SECKILL_STOCK_KEY + id,
                RedisConstants.SECKILL_USERS_KEY + id
        ));
    }
}

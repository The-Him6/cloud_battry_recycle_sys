package com.br.seckill.service.impl;
import lombok.RequiredArgsConstructor;

import com.br.common.constants.SystemConstants;
import com.br.common.exception.BadRequestException;
import com.br.common.exception.DbException;
import com.br.common.exception.ForbiddenException;
import com.br.seckill.entity.UserSeckillCoupon;
import com.br.seckill.mapper.UserSeckillCouponMapper;
import com.br.seckill.service.IUserSeckillCouponService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户秒杀券服务类
 */
@Service("userSeckillCouponService")
@RequiredArgsConstructor
public class UserSeckillCouponServiceImpl implements IUserSeckillCouponService {

    private final UserSeckillCouponMapper userSeckillCouponMapper;

    /**
     * 根据ID查询用户券
     */
    public UserSeckillCoupon getById(Long id) {
        UserSeckillCoupon coupon = userSeckillCouponMapper.getById(id);
        if (coupon == null) {
            throw new DbException(SystemConstants.SECKILL_COUPON_NOT_FOUND);
        }
        refreshExpiredStatus(coupon);
        return coupon;
    }

    /**
     * 查询用户全部秒杀券，并懒更新过期状态
     */
    public List<UserSeckillCoupon> listByUserId(Long userId) {
        List<UserSeckillCoupon> list = userSeckillCouponMapper.listByUserId(userId);
        for (UserSeckillCoupon coupon : list) {
            refreshExpiredStatus(coupon);
        }
        return list;
    }

    /**
     * 查询用户可用于兑换的秒杀券
     */
    public List<UserSeckillCoupon> listUsableByUserId(Long userId) {
        return userSeckillCouponMapper.listUsableByUserId(userId);
    }

    /**
     * 校验一张券是否可以用于兑换
     */
    public UserSeckillCoupon validateCouponForExchange(Long couponId, Long userId) {
        UserSeckillCoupon coupon = getById(couponId);
        if (!coupon.getUserId().equals(userId)) {
            throw new ForbiddenException(SystemConstants.PERMISSION_DENIED);
        }
        if (SystemConstants.COUPON_STATUS_USED.equals(coupon.getStatus())) {
            throw new BadRequestException(SystemConstants.SECKILL_COUPON_USED);
        }
        if (SystemConstants.COUPON_STATUS_EXPIRED.equals(coupon.getStatus())) {
            throw new BadRequestException(SystemConstants.SECKILL_COUPON_EXPIRED);
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(coupon.getEffectiveTime())) {
            throw new BadRequestException(SystemConstants.SECKILL_COUPON_NOT_EFFECTIVE);
        }
        if (now.isAfter(coupon.getExpireTime())) {
            userSeckillCouponMapper.markExpired(coupon.getId());
            coupon.setStatus(SystemConstants.COUPON_STATUS_EXPIRED);
            throw new BadRequestException(SystemConstants.SECKILL_COUPON_EXPIRED);
        }
        return coupon;
    }

    /**
     * 标记秒杀券已使用
     */
    public Boolean markUsed(Long couponId, Long productId, Long exchangeRecordId) {
        return userSeckillCouponMapper.markUsed(couponId, productId, exchangeRecordId) > 0;
    }

    /**
     * 查询时懒更新过期券状态
     */
    private void refreshExpiredStatus(UserSeckillCoupon coupon) {
        if (SystemConstants.COUPON_STATUS_UNUSED.equals(coupon.getStatus())
                && coupon.getExpireTime() != null
                && LocalDateTime.now().isAfter(coupon.getExpireTime())) {
            userSeckillCouponMapper.markExpired(coupon.getId());
            coupon.setStatus(SystemConstants.COUPON_STATUS_EXPIRED);
        }
    }
}

package com.br.seckill.mapper;

import com.br.seckill.entity.UserSeckillCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户秒杀券Mapper接口
 */
public interface UserSeckillCouponMapper {

    /**
     * 根据ID查询用户券
     */
    UserSeckillCoupon getById(@Param("id") Long id);

    /**
     * 根据活动和用户查询用户券
     */
    UserSeckillCoupon getByActivityAndUser(@Param("activityId") Long activityId, @Param("userId") Long userId);

    /**
     * 查询用户的全部秒杀券
     */
    List<UserSeckillCoupon> listByUserId(@Param("userId") Long userId);

    /**
     * 查询用户可用的秒杀券
     */
    List<UserSeckillCoupon> listUsableByUserId(@Param("userId") Long userId);

    /**
     * 新增用户券
     */
    int insert(UserSeckillCoupon coupon);

    /**
     * 更新用户券
     */
    int update(UserSeckillCoupon coupon);

    /**
     * 标记秒杀券已使用
     */
    int markUsed(@Param("id") Long id,
                 @Param("productId") Long productId,
                 @Param("exchangeRecordId") Long exchangeRecordId);

    /**
     * 标记秒杀券已过期
     */
    int markExpired(@Param("id") Long id);
}

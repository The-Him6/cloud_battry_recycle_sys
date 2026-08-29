package com.br.seckill.controller;

import com.br.common.constants.SystemConstants;
import com.br.common.domain.Result;
import com.br.common.exception.ForbiddenException;
import com.br.common.utils.AuthUtil;
import com.br.common.utils.UserContext;
import com.br.seckill.entity.UserSeckillCoupon;
import com.br.seckill.service.IUserSeckillCouponService;
import com.br.seckill.vo.UserSeckillCouponVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户秒杀券控制器
 */
@Tag(name = "用户秒杀券", description = "查询当前用户的秒杀优惠券")
@RestController
@RequestMapping("/seckill-coupon")
@RequiredArgsConstructor
public class UserSeckillCouponController {

    private final IUserSeckillCouponService userSeckillCouponService;


    /**
     * 校验秒杀券是否可用于兑换（Feign 内部调用）
     */
    @Operation(summary = "校验秒杀券")
    @PostMapping("/validate")
    public Result<UserSeckillCouponVO> validate(@RequestParam Long couponId, @RequestParam Long userId) {
        checkSelfOrAdmin(userId);
        UserSeckillCoupon coupon = userSeckillCouponService.validateCouponForExchange(couponId, userId);
        UserSeckillCouponVO vo = new UserSeckillCouponVO();
        BeanUtils.copyProperties(coupon, vo);
        return Result.success(vo);
    }

    /**
     * 核销秒杀券（Feign 内部调用）
     */
    @Operation(summary = "核销秒杀券")
    @PostMapping("/markUsed")
    public Result<Boolean> markUsed(@RequestParam Long couponId,
                                 @RequestParam Long productId,
                                 @RequestParam Long exchangeRecordId) {
        // 校验券归属，防止核销他人的秒杀券
        UserSeckillCoupon coupon = userSeckillCouponService.getById(couponId);
        checkSelfOrAdmin(coupon.getUserId());
        boolean result = userSeckillCouponService.markUsed(couponId, productId, exchangeRecordId);
        return Result.success(result);
    }
    /**
     * 查询我的全部秒杀券
     */
    @Operation(summary = "查询我的全部秒杀券")
    @GetMapping("/my")
    public Result<List<UserSeckillCouponVO>> listMyCoupons() {
        List<UserSeckillCoupon> list = userSeckillCouponService.listByUserId(AuthUtil.getUserId());
        List<UserSeckillCouponVO> voList = new ArrayList<>();
        for (UserSeckillCoupon item : list) {
            UserSeckillCouponVO vo = new UserSeckillCouponVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        }
        return Result.success(voList);
    }

    /**
     * 查询我的可用秒杀券
     */
    @Operation(summary = "查询我的可用秒杀券")
    @GetMapping("/usable")
    public Result<List<UserSeckillCouponVO>> listUsableCoupons() {
        List<UserSeckillCoupon> list = userSeckillCouponService.listUsableByUserId(AuthUtil.getUserId());
        List<UserSeckillCouponVO> voList = new ArrayList<>();
        for (UserSeckillCoupon item : list) {
            UserSeckillCouponVO vo = new UserSeckillCouponVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        }
        return Result.success(voList);
    }

    /**
     * 校验当前调用是否有权操作目标用户的券：
     * 无用户上下文视为内部系统调用（Feign）放行；有上下文时仅允许本人或管理员。
     */
    private void checkSelfOrAdmin(Long targetUserId) {
        Long callerUserId = UserContext.getUserId();
        if (callerUserId == null) {
            return; // 内部系统调用
        }
        Integer callerRole = UserContext.getRole();
        if (SystemConstants.ROLE_ADMIN.equals(callerRole)) {
            return;
        }
        if (!callerUserId.equals(targetUserId)) {
            throw new ForbiddenException(SystemConstants.PERMISSION_DENIED);
        }
    }
}
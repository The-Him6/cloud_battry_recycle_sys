package com.br.points.controller;
import lombok.RequiredArgsConstructor;

import com.br.common.constants.SystemConstants;
import com.br.common.domain.Result;
import com.br.common.exception.ForbiddenException;
import com.br.common.utils.UserContext;
import com.br.points.entity.UserPoints;
import com.br.points.service.IUserPointsService;
import com.br.points.vo.UserPointsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 积分控制器
 */
@Tag(name = "积分管理", description = "用户积分查询、增加、扣减")
@RestController
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointsController {

    private final IUserPointsService userPointsService;

    /**
     * 查询用户积分
     */
    @Operation(summary = "查询用户积分", description = "根据用户ID查询积分，无记录自动创建初始积分")
    @GetMapping("/{userId}")
    public Result<UserPointsVO> getByUserId(@PathVariable Long userId) {
        checkSelfOrAdmin(userId);
        UserPoints userPoints = userPointsService.getByUserId(userId);
        UserPointsVO vo = new UserPointsVO();
        BeanUtils.copyProperties(userPoints, vo);
        return Result.success(vo);
    }

    /**
     * 增加积分
     */
    @Operation(summary = "增加积分", description = "给指定用户增加积分")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestParam Long userId, @RequestParam Integer points) {
        checkSelfOrAdmin(userId);
        return Result.success(userPointsService.addPoints(userId, points));
    }

    /**
     * 扣减积分
     */
    @Operation(summary = "扣减积分", description = "扣减指定用户积分，积分不足返回false")
    @PostMapping("/deduct")
    public Result<Boolean> deduct(@RequestParam Long userId, @RequestParam Integer points) {
        checkSelfOrAdmin(userId);
        return Result.success(userPointsService.deductPoints(userId, points));
    }

    /**
     * 积分接口仅允许服务间调用（Feign）或操作本人/管理员：
     * 无用户上下文视为内部系统调用（如MQ消费者）直接放行；
     * 有上下文时仅允许本人或管理员操作。
     */
    private void checkSelfOrAdmin(Long targetUserId) {
        Long callerUserId = UserContext.getUserId();
        if (callerUserId == null) {
            return; // 内部系统调用（Feign且无请求上下文，如MQ消费者）
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
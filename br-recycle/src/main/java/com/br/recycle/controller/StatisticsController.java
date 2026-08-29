package com.br.recycle.controller;

import com.br.common.constants.SystemConstants;
import com.br.common.domain.Result;
import com.br.common.utils.AuthUtil;
import com.br.recycle.service.IStatisticsService;
import com.br.recycle.vo.DashboardOverviewVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计控制器
 */
@Tag(name = "数据统计", description = "管理员数据概览与回收数据统计图表")
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final IStatisticsService statisticsService;

    /**
     * 获取管理员首页概览数字。
     */
    @Operation(summary = "获取管理员首页概览数字", description = "仅管理员可操作")
    @GetMapping("/dashboard")
    public Result<DashboardOverviewVO> getDashboardOverview() {
        AuthUtil.requireAdmin();
        DashboardOverviewVO data = statisticsService.getDashboardOverview();
        return Result.success(SystemConstants.STATISTICS_QUERY_SUCCESS, data);
    }

    /**
     * 统计每种电池的回收数量
     */
    @Operation(summary = "统计每种电池的回收数量")
    @GetMapping("/battery-type")
    public Result<List<Map<String, Object>>> countByBatteryType() {
        AuthUtil.requireAdmin();
        List<Map<String, Object>> data = statisticsService.countByBatteryType();
        return Result.success(SystemConstants.STATISTICS_QUERY_SUCCESS, data);
    }

    /**
     * 统计每日回收数量
     */
    @Operation(summary = "统计每日回收数量", description = "days 表示最近天数，默认统计全部")
    @GetMapping("/date")
    public Result<List<Map<String, Object>>> countByDate(@RequestParam(required = false) Integer days) {
        AuthUtil.requireAdmin();
        List<Map<String, Object>> data = statisticsService.countByDate(days);
        return Result.success(SystemConstants.STATISTICS_QUERY_SUCCESS, data);
    }

    /**
     * 按月统计回收数量（近12个月）
     */
    @Operation(summary = "按月统计回收数量", description = "近 12 个月")
    @GetMapping("/monthly")
    public Result<List<Map<String, Object>>> countByMonth() {
        AuthUtil.requireAdmin();
        List<Map<String, Object>> data = statisticsService.countByMonth();
        return Result.success(SystemConstants.STATISTICS_QUERY_SUCCESS, data);
    }

    /**
     * 按年统计回收数量（全部年份）
     */
    @Operation(summary = "按年统计回收数量")
    @GetMapping("/yearly")
    public Result<List<Map<String, Object>>> countByYear() {
        AuthUtil.requireAdmin();
        List<Map<String, Object>> data = statisticsService.countByYear();
        return Result.success(SystemConstants.STATISTICS_QUERY_SUCCESS, data);
    }

    /**
     * 统计订单状态分布
     */
    @Operation(summary = "统计订单状态分布")
    @GetMapping("/order-status")
    public Result<List<Map<String, Object>>> countByOrderStatus() {
        AuthUtil.requireAdmin();
        List<Map<String, Object>> data = statisticsService.countByOrderStatus();
        return Result.success(SystemConstants.STATISTICS_QUERY_SUCCESS, data);
    }

    /**
     * 统计地区回收排行
     */
    @Operation(summary = "统计地区回收排行", description = "limit 表示返回排行条数，默认全部")
    @GetMapping("/city-rank")
    public Result<List<Map<String, Object>>> countByCity(@RequestParam(required = false) Integer limit) {
        AuthUtil.requireAdmin();
        List<Map<String, Object>> data = statisticsService.countByCity(limit);
        return Result.success(SystemConstants.STATISTICS_QUERY_SUCCESS, data);
    }

    /**
     * 获取综合统计数据
     */
    @Operation(summary = "获取综合统计数据", description = "聚合电池类型、近7天、订单状态、地区排行统计")
    @GetMapping("/overview")
    public Result<Map<String, Object>> getOverview() {
        AuthUtil.requireAdmin();
        Map<String, Object> overview = new HashMap<>();
        overview.put("batteryTypeStats", statisticsService.countByBatteryType());
        overview.put("dateStats", statisticsService.countByDate(7));
        overview.put("orderStatusStats", statisticsService.countByOrderStatus());
        overview.put("cityRankStats", statisticsService.countByCity(5));
        return Result.success(SystemConstants.STATISTICS_QUERY_SUCCESS, overview);
    }

}

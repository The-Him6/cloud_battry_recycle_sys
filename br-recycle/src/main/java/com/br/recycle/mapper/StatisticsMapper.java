package com.br.recycle.mapper;
import com.br.recycle.vo.DashboardOverviewVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 统计Mapper接口
 */
public interface StatisticsMapper {

    /**
     * 查询管理员首页需要的概览数字。
     */
    DashboardOverviewVO selectDashboardOverview();

    /**
     * 统计每种电池的回收数量
     */
    List<Map<String, Object>> countByBatteryType();

    /**
     * 统计每日回收数量
     */
    List<Map<String, Object>> countByDate(@Param("days") Integer days);

    /**
     * 按月统计回收数量（近12个月）
     */
    List<Map<String, Object>> countByMonth();

    /**
     * 按年统计回收数量（全部年份）
     */
    List<Map<String, Object>> countByYear();

    /**
     * 统计订单状态分布
     */
    List<Map<String, Object>> countByOrderStatus();

    /**
     * 统计地区回收排行
     */
    List<Map<String, Object>> countByCity(@Param("limit") Integer limit);
}

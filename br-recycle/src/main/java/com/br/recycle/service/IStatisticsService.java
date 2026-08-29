package com.br.recycle.service;


import com.br.recycle.vo.DashboardOverviewVO;

import java.util.List;
import java.util.Map;

/**
 * 数据统计服务接口。
 */
public interface IStatisticsService {

    DashboardOverviewVO getDashboardOverview();

    List<Map<String, Object>> countByBatteryType();

    List<Map<String, Object>> countByDate(Integer days);

    List<Map<String, Object>> countByMonth();

    List<Map<String, Object>> countByYear();

    List<Map<String, Object>> countByOrderStatus();

    List<Map<String, Object>> countByCity(Integer limit);
}

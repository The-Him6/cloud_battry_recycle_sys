package com.br.recycle.service.impl;

import com.br.api.client.UserClient;
import com.br.common.constants.RedisConstants;
import com.br.common.domain.Result;
import com.br.recycle.mapper.StatisticsMapper;
import com.br.recycle.service.IStatisticsService;
import com.br.recycle.vo.DashboardOverviewVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 统计服务类
 */
@Service("statisticsService")
@RequiredArgsConstructor
public class StatisticsServiceImpl implements IStatisticsService {

    private final StatisticsMapper statisticsMapper;

    private final UserClient userClient;

    private final StringRedisTemplate stringRedisTemplate;

    private final ObjectMapper objectMapper;

    /**
     * 查询管理员首页数据概览，优先读Redis缓存，未命中再查库，避免每次重复跨服务调用。
     */
    public DashboardOverviewVO getDashboardOverview() {
        // 1. 先查缓存
        String json = stringRedisTemplate.opsForValue().get(RedisConstants.CACHE_STATISTICS_DASHBOARD_KEY);
        if (json != null && !json.isEmpty()) {
            return readValue(json);
        }

        // 2. 缓存未命中，查库 + 跨服务获取用户总数
        DashboardOverviewVO vo = statisticsMapper.selectDashboardOverview();
        // 用户总数在 br_user 库，跨服务调 UserClient.count() 获取
        Result<Long> result = userClient.count();
        vo.setUserCount(result == null || result.getData() == null ? 0L : result.getData());

        // 3. 写缓存
        try {
            stringRedisTemplate.opsForValue().set(
                    RedisConstants.CACHE_STATISTICS_DASHBOARD_KEY,
                    objectMapper.writeValueAsString(vo),
                    RedisConstants.CACHE_NORMAL_TTL, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("统计缓存序列化失败", e);
        }
        return vo;
    }

    /**
     * 统计每种电池的回收数量
     */
    public List<Map<String, Object>> countByBatteryType() {
        return queryListWithCache(RedisConstants.CACHE_STATISTICS_BATTERY_TYPE_KEY,
                statisticsMapper::countByBatteryType);
    }

    /**
     * 统计每日回收数量
     */
    public List<Map<String, Object>> countByDate(Integer days) {
        if (days == null || days <= 0) {
            days = 7; // 默认最近7天
        }
        Integer finalDays = days;
        return queryListWithCache(RedisConstants.CACHE_STATISTICS_DATE_KEY + days,
                () -> statisticsMapper.countByDate(finalDays));
    }

    /**
     * 按月统计回收数量（近12个月）
     */
    public List<Map<String, Object>> countByMonth() {
        return queryListWithCache(RedisConstants.CACHE_STATISTICS_MONTHLY_KEY,
                statisticsMapper::countByMonth);
    }

    /**
     * 按年统计回收数量（全部年份）
     */
    public List<Map<String, Object>> countByYear() {
        return queryListWithCache(RedisConstants.CACHE_STATISTICS_YEARLY_KEY,
                statisticsMapper::countByYear);
    }

    /**
     * 统计订单状态分布
     */
    public List<Map<String, Object>> countByOrderStatus() {
        return queryListWithCache(RedisConstants.CACHE_STATISTICS_ORDER_STATUS_KEY,
                statisticsMapper::countByOrderStatus);
    }

    /**
     * 统计地区回收排行
     */
    public List<Map<String, Object>> countByCity(Integer limit) {
        if (limit == null || limit <= 0) {
            limit = 5; // 默认前5名
        }
        Integer finalLimit = limit;
        return queryListWithCache(RedisConstants.CACHE_STATISTICS_CITY_RANK_KEY + limit,
                () -> statisticsMapper.countByCity(finalLimit));
    }

    /**
     * 反序列化缓存中的概览数据
     */
    private DashboardOverviewVO readValue(String json) {
        try {
            return objectMapper.readValue(json, DashboardOverviewVO.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("统计缓存反序列化失败", e);
        }
    }

    /**
     * 通用的图表数据缓存查询：先读缓存，未命中查库并写回缓存
     */
    private List<Map<String, Object>> queryListWithCache(String key, Supplier<List<Map<String, Object>>> dbFallback) {
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json != null && !json.isEmpty()) {
            return readListValue(json);
        }

        List<Map<String, Object>> data = dbFallback.get();
        try {
            stringRedisTemplate.opsForValue().set(key,
                    objectMapper.writeValueAsString(data),
                    RedisConstants.CACHE_NORMAL_TTL, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("统计缓存序列化失败", e);
        }
        return data;
    }

    /**
     * 反序列化缓存中的图表数据
     */
    private List<Map<String, Object>> readListValue(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("统计缓存反序列化失败", e);
        }
    }
}

package com.br.seckill.service;

import com.br.seckill.entity.SeckillActivity;

import java.util.List;

/**
 * 秒杀活动服务接口。
 */
public interface ISeckillActivityService {

    SeckillActivity getById(Long id);

    List<SeckillActivity> listAll();

    List<SeckillActivity> listOnline();

    void add(SeckillActivity activity, Long adminId);

    void update(SeckillActivity activity);

    void online(Long id);

    void offline(Long id);

    void preheat(Long id);

    void seckill(Long activityId, Long userId);

    void handleSeckillMessage(Long activityId, Long userId);

    void compensateRedis(Long activityId, Long userId);
}

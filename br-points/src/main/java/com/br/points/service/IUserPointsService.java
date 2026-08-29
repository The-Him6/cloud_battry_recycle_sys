package com.br.points.service;


import com.br.points.entity.UserPoints;

/**
 * 用户积分服务接口。
 */
public interface IUserPointsService {

    UserPoints getByUserId(Long userId);

    boolean addPoints(Long userId, Integer points);

    boolean deductPoints(Long userId, Integer points);

    void update(UserPoints userPoints);
}

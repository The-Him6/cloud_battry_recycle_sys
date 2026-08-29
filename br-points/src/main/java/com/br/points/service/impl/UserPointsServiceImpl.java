package com.br.points.service.impl;
import lombok.RequiredArgsConstructor;


import com.br.points.entity.UserPoints;
import com.br.points.mapper.UserPointsMapper;
import com.br.points.service.IUserPointsService;
import org.springframework.stereotype.Service;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户积分服务类
 */
@Service("userPointsService")
@RequiredArgsConstructor
public class UserPointsServiceImpl implements IUserPointsService {

    private final UserPointsMapper userPointsMapper;

    /**
     * 根据用户ID查询积分
     */
    @Transactional(rollbackFor = Exception.class)
    public UserPoints getByUserId(Long userId) {
        UserPoints userPoints = userPointsMapper.getByUserId(userId);
        if (userPoints == null) {
            // 如果不存在，创建初始积分记录（user_id 需建唯一索引，并发下靠唯一索引兜底）
            try {
                userPoints = new UserPoints();
                userPoints.setUserId(userId);
                userPoints.setTotalPoints(0);
                userPoints.setAvailablePoints(0);
                userPoints.setUsedPoints(0);
                userPointsMapper.insert(userPoints);
            } catch (DuplicateKeyException e) {
                // 并发下他人已插入，直接重新查询
                userPoints = userPointsMapper.getByUserId(userId);
            }
        }
        return userPoints;
    }

    /**
     * 增加积分
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addPoints(Long userId, Integer points) {
        // 确保用户积分记录存在
        getByUserId(userId);
        // 增加积分
        return userPointsMapper.addPoints(userId, points) > 0;
    }

    /**
     * 扣减积分
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deductPoints(Long userId, Integer points) {
        UserPoints userPoints = getByUserId(userId);
        if (userPoints.getAvailablePoints() < points) {
            return false;
        }
        int result = userPointsMapper.deductPoints(userId, points);
        return result > 0;
    }

    /**
     * 更新积分
     */
    public void update(UserPoints userPoints) {
        userPointsMapper.update(userPoints);
    }
}





































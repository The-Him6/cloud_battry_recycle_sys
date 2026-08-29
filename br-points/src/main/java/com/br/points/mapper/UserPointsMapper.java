package com.br.points.mapper;

import com.br.points.entity.UserPoints;
import org.apache.ibatis.annotations.Param;

/**
 * 用户积分Mapper接口
 */
public interface UserPointsMapper {

    /**
     * 根据用户ID查询积分
     */
    UserPoints getByUserId(@Param("userId") Long userId);

    /**
     * 插入积分记录
     */
    int insert(UserPoints userPoints);

    /**
     * 更新积分
     */
    int update(UserPoints userPoints);

    /**
     * 增加积分
     */
    int addPoints(@Param("userId") Long userId, @Param("points") Integer points);

    /**
     * 扣减积分
     */
    int deductPoints(@Param("userId") Long userId, @Param("points") Integer points);
}





































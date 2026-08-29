package com.br.recycle.mapper;

import com.br.recycle.entity.RecycleOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 回收订单Mapper接口
 */
public interface RecycleOrderMapper {

    /**
     * 根据ID查询订单
     */
    RecycleOrder getById(@Param("id") Long id);

    /**
     * 根据订单编号查询订单
     */
    RecycleOrder getByOrderNumber(@Param("orderNumber") String orderNumber);

    /**
     * 查询所有订单
     */
    List<RecycleOrder> listAll();

    /**
     * 根据用户ID查询订单
     */
    List<RecycleOrder> listByUserId(@Param("userId") Long userId);

    /**
     * 分页查询订单
     */
    List<RecycleOrder> listByPage(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 统计订单总数
     */
    Integer count();

    /**
     * 插入订单
     */
    int insert(RecycleOrder order);

    /**
     * 更新订单
     */
    int update(RecycleOrder order);

    /**
     * 删除订单
     */
    int deleteById(@Param("id") Long id);

    /**
     * 按条件搜索订单
     */
    List<RecycleOrder> searchOrders(@Param("address") String address,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("orderStatus") Integer orderStatus,
            @Param("userId") Long userId,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    /**
     * 统计搜索结果总数
     */
    Integer countBySearch(@Param("address") String address,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("orderStatus") Integer orderStatus,
            @Param("userId") Long userId);

    /**
     * 统计用户当天订单数量
     */
    Integer countUserOrdersByDate(@Param("userId") Long userId,
            @Param("orderDate") String orderDate);
}

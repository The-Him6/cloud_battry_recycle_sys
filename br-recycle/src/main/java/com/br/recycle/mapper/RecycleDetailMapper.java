package com.br.recycle.mapper;

import com.br.recycle.entity.RecycleDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 回收明细Mapper接口
 */
public interface RecycleDetailMapper {
    
    /**
     * 根据ID查询明细
     */
    RecycleDetail getById(@Param("id") Long id);
    
    /**
     * 根据订单ID查询明细列表
     */
    List<RecycleDetail> listByOrderId(@Param("orderId") Long orderId);
    
    /**
     * 批量插入明细
     */
    int batchInsert(@Param("list") List<RecycleDetail> list);
    
    /**
     * 插入明细
     */
    int insert(RecycleDetail detail);
    
    /**
     * 删除订单的所有明细
     */
    int deleteByOrderId(@Param("orderId") Long orderId);
}


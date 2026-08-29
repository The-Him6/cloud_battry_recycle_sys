package com.br.recycle.mapper;

import com.br.recycle.entity.BatteryType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 电池种类Mapper接口
 */
public interface BatteryTypeMapper {
    
    /**
     * 根据ID查询电池种类
     */
    BatteryType getById(@Param("id") Long id);
    
    /**
     * 根据名称查询电池种类
     */
    BatteryType getByTypeName(@Param("typeName") String typeName);
    
    /**
     * 查询所有电池种类
     */
    List<BatteryType> listAll();
    
    /**
     * 查询启用的电池种类
     */
    List<BatteryType> listEnabled();
    
    /**
     * 插入电池种类
     */
    int insert(BatteryType batteryType);
    
    /**
     * 更新电池种类
     */
    int update(BatteryType batteryType);
    
    /**
     * 删除电池种类
     */
    int deleteById(@Param("id") Long id);
}


package com.br.recycle.service.impl;

import com.br.common.constants.SystemConstants;
import com.br.common.exception.BadRequestException;
import com.br.common.exception.DbException;
import com.br.recycle.entity.BatteryType;
import com.br.recycle.mapper.BatteryTypeMapper;
import com.br.recycle.service.IBatteryTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电池种类服务类
 */
@Service("batteryTypeService")
@RequiredArgsConstructor
public class BatteryTypeServiceImpl implements IBatteryTypeService {
    
    private final BatteryTypeMapper batteryTypeMapper;
    
    /**
     * 根据ID查询电池种类
     */
    public BatteryType getById(Long id) {
        BatteryType batteryType = batteryTypeMapper.getById(id);
        if (batteryType == null) {
            throw new DbException(SystemConstants.BATTERY_TYPE_NOT_FOUND);
        }
        return batteryType;
    }
    
    /**
     * 查询所有电池种类
     */
    public List<BatteryType> listAll() {
        return batteryTypeMapper.listAll();
    }
    
    /**
     * 查询启用的电池种类
     */
    public List<BatteryType> listEnabled() {
        return batteryTypeMapper.listEnabled();
    }
    
    /**
     * 添加电池种类
     */
    public void add(BatteryType batteryType) {
        // 检查名称是否已存在
        BatteryType existType = batteryTypeMapper.getByTypeName(batteryType.getTypeName());
        if (existType != null) {
            throw new BadRequestException(SystemConstants.BATTERY_TYPE_NAME_EXISTS);
        }
        
        batteryTypeMapper.insert(batteryType);
    }
    
    /**
     * 更新电池种类
     */
    public void update(BatteryType batteryType) {
        BatteryType existType = batteryTypeMapper.getById(batteryType.getId());
        if (existType == null) {
            throw new DbException(SystemConstants.BATTERY_TYPE_NOT_FOUND);
        }
        
        // 如果修改了名称，检查新名称是否已存在
        if (batteryType.getTypeName() != null && !batteryType.getTypeName().equals(existType.getTypeName())) {
            BatteryType nameExist = batteryTypeMapper.getByTypeName(batteryType.getTypeName());
            if (nameExist != null) {
                throw new BadRequestException(SystemConstants.BATTERY_TYPE_NAME_EXISTS);
            }
        }
        
        batteryTypeMapper.update(batteryType);
    }
    
    /**
     * 删除电池种类
     */
    public void deleteById(Long id) {
        BatteryType batteryType = batteryTypeMapper.getById(id);
        if (batteryType == null) {
            throw new DbException(SystemConstants.BATTERY_TYPE_NOT_FOUND);
        }
        batteryTypeMapper.deleteById(id);
    }
}


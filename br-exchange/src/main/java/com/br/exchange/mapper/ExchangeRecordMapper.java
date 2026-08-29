package com.br.exchange.mapper;

import com.br.exchange.entity.ExchangeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 兑换记录Mapper接口
 */
public interface ExchangeRecordMapper {

    /**
     * 根据ID查询记录
     */
    ExchangeRecord getById(@Param("id") Long id);

    /**
     * 查询所有记录
     */
    List<ExchangeRecord> listAll();

    /**
     * 根据用户ID查询记录
     */
    List<ExchangeRecord> listByUserId(@Param("userId") Long userId);

    /**
     * 分页查询记录
     */
    List<ExchangeRecord> listByPage(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 统计记录总数
     */
    Integer count();

    /**
     * 插入记录
     */
    int insert(ExchangeRecord record);

    /**
     * 更新记录
     */
    int update(ExchangeRecord record);
}





































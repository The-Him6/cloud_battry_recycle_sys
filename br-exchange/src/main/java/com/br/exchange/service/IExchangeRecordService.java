package com.br.exchange.service;

import com.br.exchange.entity.ExchangeRecord;

import java.util.List;

/**
 * 商品兑换记录服务接口。
 */
public interface IExchangeRecordService {

    ExchangeRecord getById(Long id);

    List<ExchangeRecord> listAll();

    List<ExchangeRecord> listByUserId(Long userId);

    List<ExchangeRecord> listByPage(Integer page, Integer size);

    Integer count();

    void createExchange(ExchangeRecord record);

    void updateStatus(Long id, Integer status);
}

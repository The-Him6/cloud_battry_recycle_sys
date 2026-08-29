package com.br.exchange.service;


import com.br.exchange.entity.ExchangeProduct;

import java.util.List;

/**
 * 积分商品服务接口。
 */
public interface IExchangeProductService {

    ExchangeProduct getById(Long id);

    List<ExchangeProduct> listAll();

    List<ExchangeProduct> listAvailable();

    List<ExchangeProduct> listByBrand(String brand);

    void add(ExchangeProduct product);

    void update(ExchangeProduct product);

    void deleteById(Long id);

    boolean updateStock(Long id, Integer quantity);
}

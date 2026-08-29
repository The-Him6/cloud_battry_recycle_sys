package com.br.exchange.service.impl;

import com.br.common.constants.RedisConstants;
import com.br.common.exception.DbException;
import com.br.common.utils.CacheClient;
import com.br.common.constants.SystemConstants;
import com.br.exchange.entity.ExchangeProduct;
import com.br.exchange.mapper.ExchangeProductMapper;
import com.br.exchange.service.IExchangeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 兑换商品服务类
 */
@Service("exchangeProductService")
@RequiredArgsConstructor
public class ExchangeProductServiceImpl implements IExchangeProductService {

    private final ExchangeProductMapper exchangeProductMapper;

    private final CacheClient cacheClient;

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 根据ID查询商品
     */
    public ExchangeProduct getById(Long id) {
        ExchangeProduct product = cacheClient.queryWithMutex(
                RedisConstants.CACHE_PRODUCT_KEY,
                id,
                ExchangeProduct.class,
                exchangeProductMapper::getById,
                RedisConstants.CACHE_NORMAL_TTL,
                TimeUnit.MINUTES
        );
        if (product == null) {
            throw new DbException(SystemConstants.EXCHANGE_PRODUCT_NOT_FOUND);
        }
        return product;
    }

    /**
     * 查询所有商品
     */
    public List<ExchangeProduct> listAll() {
        return exchangeProductMapper.listAll();
    }

    /**
     * 查询上架的商品
     */
    public List<ExchangeProduct> listAvailable() {
        return exchangeProductMapper.listAvailable();
    }

    /**
     * 根据品牌查询
     */
    public List<ExchangeProduct> listByBrand(String brand) {
        return exchangeProductMapper.listByBrand(brand);
    }

    /**
     * 添加商品
     */
    public void add(ExchangeProduct product) {
        if (product.getStatus() == null) {
            product.setStatus(1);
        }
        if (product.getPointsRequired() == null) {
            product.setPointsRequired(1000);
        }
        exchangeProductMapper.insert(product);
        deleteProductCache(product.getId());
    }

    /**
     * 更新商品
     */
    public void update(ExchangeProduct product) {
        ExchangeProduct existProduct = exchangeProductMapper.getById(product.getId());
        if (existProduct == null) {
            throw new DbException(SystemConstants.EXCHANGE_PRODUCT_NOT_FOUND);
        }
        exchangeProductMapper.update(product);
        deleteProductCache(product.getId());
    }

    /**
     * 删除商品
     */
    public void deleteById(Long id) {
        ExchangeProduct product = exchangeProductMapper.getById(id);
        if (product == null) {
            throw new DbException(SystemConstants.EXCHANGE_PRODUCT_NOT_FOUND);
        }
        exchangeProductMapper.deleteById(id);
        deleteProductCache(id);
    }

    /**
     * 更新库存
     */
    public boolean updateStock(Long id, Integer quantity) {
        int result = exchangeProductMapper.updateStock(id, quantity);
        if (result > 0) {
            deleteProductCache(id);
        }
        return result > 0;
    }

    /**
     * 删除商品缓存，保证库存和上下架状态更新后不会读到旧数据
     */
    private void deleteProductCache(Long id) {
        if (id != null) {
            stringRedisTemplate.delete(RedisConstants.CACHE_PRODUCT_KEY + id);
        }
    }
}





































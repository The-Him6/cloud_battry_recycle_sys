package com.br.exchange.mapper;

import com.br.exchange.entity.ExchangeProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 兑换商品Mapper接口
 */
public interface ExchangeProductMapper {

    /**
     * 根据ID查询商品
     */
    ExchangeProduct getById(@Param("id") Long id);

    /**
     * 查询所有商品
     */
    List<ExchangeProduct> listAll();

    /**
     * 查询上架的商品
     */
    List<ExchangeProduct> listAvailable();

    /**
     * 根据品牌查询
     */
    List<ExchangeProduct> listByBrand(@Param("brand") String brand);

    /**
     * 插入商品
     */
    int insert(ExchangeProduct product);

    /**
     * 更新商品
     */
    int update(ExchangeProduct product);

    /**
     * 删除商品
     */
    int deleteById(@Param("id") Long id);

    /**
     * 更新库存
     */
    int updateStock(@Param("id") Long id, @Param("quantity") Integer quantity);
}





































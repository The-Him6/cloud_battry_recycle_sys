package com.br.exchange.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 可兑换电池商品实体类
 */
@Data
@Schema(description = "可兑换电池商品实体")
public class ExchangeProduct implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long id;
    
    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String productName;
    
    /**
     * 品牌
     */
    @Schema(description = "品牌")
    private String brand;
    
    /**
     * 电池型号（5号/7号）
     */
    @Schema(description = "电池型号（5号/7号）")
    private String batteryModel;
    
    /**
     * 所需积分
     */
    @Schema(description = "所需积分")
    private Integer pointsRequired;
    
    /**
     * 库存数量
     */
    @Schema(description = "库存数量")
    private Integer stock;
    
    /**
     * 商品图片
     */
    @Schema(description = "商品图片")
    private String imageUrl;
    
    /**
     * 商品描述
     */
    @Schema(description = "商品描述")
    private String description;
    
    /**
     * 状态：0-下架，1-上架
     */
    @Schema(description = "状态：0-下架，1-上架")
    private Integer status;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}

package com.br.exchange.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 兑换商品VO
 */
@Data
@Schema(description = "兑换商品VO")
public class ExchangeProductVO {
    @Schema(description = "商品ID")
    private Long id;
    @Schema(description = "商品名称")
    private String productName;
    @Schema(description = "品牌")
    private String brand;
    @Schema(description = "电池型号（5号/7号）")
    private String batteryModel;
    @Schema(description = "所需积分")
    private Integer pointsRequired;
    @Schema(description = "库存数量")
    private Integer stock;
    @Schema(description = "商品图片")
    private String imageUrl;
    @Schema(description = "商品描述")
    private String description;
    @Schema(description = "状态：0-下架，1-上架")
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
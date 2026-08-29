package com.br.exchange.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 积分兑换记录实体类
 */
@Data
@Schema(description = "积分兑换记录实体")
public class ExchangeRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 兑换记录ID
     */
    @Schema(description = "兑换记录ID")
    private Long id;
    
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;
    
    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;
    
    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String productName;
    
    /**
     * 使用积分
     */
    @Schema(description = "使用积分")
    private Integer pointsUsed;
    
    /**
     * 兑换数量
     */
    @Schema(description = "兑换数量")
    private Integer quantity;
    
    /**
     * 兑换状态：0-待发货，1-已发货，2-已完成
     */
    @Schema(description = "兑换状态：0-待发货，1-已发货，2-已完成")
    private Integer exchangeStatus;
    
    /**
     * 收货地址
     */
    @Schema(description = "收货地址")
    private String shippingAddress;
    
    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String contactPhone;
    
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 兑换类型：0-普通积分兑换，1-秒杀券兑换
     */
    @Schema(description = "兑换类型：0-普通积分兑换，1-秒杀券兑换")
    private Integer exchangeType;

    /**
     * 使用的秒杀券ID
     */
    @Schema(description = "使用的秒杀券ID")
    private Long couponId;
    
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

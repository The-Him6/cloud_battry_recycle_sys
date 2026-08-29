package com.br.exchange.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 兑换记录VO
 */
@Data
@Schema(description = "兑换记录VO")
public class ExchangeRecordVO {
    @Schema(description = "兑换记录ID")
    private Long id;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "商品ID")
    private Long productId;
    @Schema(description = "商品名称")
    private String productName;
    @Schema(description = "使用积分")
    private Integer pointsUsed;
    @Schema(description = "兑换数量")
    private Integer quantity;
    @Schema(description = "兑换状态：0-待发货，1-已发货，2-已完成")
    private Integer exchangeStatus;
    @Schema(description = "收货地址")
    private String shippingAddress;
    @Schema(description = "联系电话")
    private String contactPhone;
    @Schema(description = "备注")
    private String remark;
    @Schema(description = "兑换类型：0-普通积分兑换，1-秒杀券兑换")
    private Integer exchangeType;
    @Schema(description = "使用的秒杀券ID")
    private Long couponId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
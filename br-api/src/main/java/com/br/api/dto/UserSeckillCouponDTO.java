package com.br.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户秒杀券实体类
 */
@Data
@Schema(description = "用户秒杀券DTO")
public class UserSeckillCouponDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户券ID
     */
    @Schema(description = "用户券ID")
    private Long id;

    /**
     * 秒杀活动ID
     */
    @Schema(description = "秒杀活动ID")
    private Long activityId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 状态：0-未使用，1-已使用，2-已过期
     */
    @Schema(description = "状态：0-未使用，1-已使用，2-已过期")
    private Integer status;

    /**
     * 生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "过期时间")
    private LocalDateTime expireTime;

    /**
     * 使用时兑换的商品ID
     */
    @Schema(description = "使用时兑换的商品ID")
    private Long usedProductId;

    /**
     * 使用时生成的兑换记录ID
     */
    @Schema(description = "使用时生成的兑换记录ID")
    private Long usedExchangeRecordId;

    /**
     * 使用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "使用时间")
    private LocalDateTime usedTime;

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
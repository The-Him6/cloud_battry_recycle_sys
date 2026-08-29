package com.br.seckill.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户秒杀券VO
 */
@Data
@Schema(description = "用户秒杀券VO")
public class UserSeckillCouponVO {
    @Schema(description = "用户券ID")
    private Long id;
    @Schema(description = "秒杀活动ID")
    private Long activityId;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "状态：0-未使用，1-已使用，2-已过期")
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "过期时间")
    private LocalDateTime expireTime;
    @Schema(description = "使用时兑换的商品ID")
    private Long usedProductId;
    @Schema(description = "使用时生成的兑换记录ID")
    private Long usedExchangeRecordId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "使用时间")
    private LocalDateTime usedTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
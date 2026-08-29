package com.br.seckill.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 秒杀活动VO
 */
@Data
@Schema(description = "秒杀活动VO")
public class SeckillActivityVO {
    @Schema(description = "活动ID")
    private Long id;
    @Schema(description = "活动标题")
    private String title;
    @Schema(description = "活动说明")
    private String description;
    @Schema(description = "秒杀券库存")
    private Integer stock;
    @Schema(description = "已售券数量")
    private Integer sold;
    @Schema(description = "秒杀所需积分")
    private Integer pointsCost;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "秒杀开始时间")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "秒杀结束时间")
    private LocalDateTime endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "优惠券生效时间")
    private LocalDateTime couponStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "优惠券过期时间")
    private LocalDateTime couponEndTime;
    @Schema(description = "状态：0-草稿，1-上架，2-下架，3-结束")
    private Integer status;
    @Schema(description = "创建管理员ID")
    private Long createAdminId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
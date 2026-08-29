package com.br.seckill.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 秒杀活动实体类
 */
@Data
@Schema(description = "秒杀活动实体")
public class SeckillActivity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @Schema(description = "活动ID")
    private Long id;

    /**
     * 活动标题
     */
    @Schema(description = "活动标题")
    private String title;

    /**
     * 活动说明
     */
    @Schema(description = "活动说明")
    private String description;

    /**
     * 秒杀券库存
     */
    @Schema(description = "秒杀券库存")
    private Integer stock;

    /**
     * 已售券数量
     */
    @Schema(description = "已售券数量")
    private Integer sold;

    /**
     * 秒杀所需积分
     */
    @Schema(description = "秒杀所需积分")
    private Integer pointsCost;

    /**
     * 秒杀开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "秒杀开始时间")
    private LocalDateTime startTime;

    /**
     * 秒杀结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "秒杀结束时间")
    private LocalDateTime endTime;

    /**
     * 优惠券生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "优惠券生效时间")
    private LocalDateTime couponStartTime;

    /**
     * 优惠券过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "优惠券过期时间")
    private LocalDateTime couponEndTime;

    /**
     * 状态：0-草稿，1-上架，2-下架，3-结束
     */
    @Schema(description = "状态：0-草稿，1-上架，2-下架，3-结束")
    private Integer status;

    /**
     * 创建管理员ID
     */
    @Schema(description = "创建管理员ID")
    private Long createAdminId;

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
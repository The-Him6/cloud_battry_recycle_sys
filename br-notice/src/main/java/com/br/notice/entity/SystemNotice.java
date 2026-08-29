package com.br.notice.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统弹窗公告实体类
 */
@Data
@Schema(description = "系统弹窗公告实体")
public class SystemNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告ID
     */
    @Schema(description = "公告ID")
    private Long id;

    /**
     * 公告标题
     */
    @Schema(description = "公告标题")
    private String title;

    /**
     * 公告内容
     */
    @Schema(description = "公告内容")
    private String content;

    /**
     * 关联秒杀活动ID
     */
    @Schema(description = "关联秒杀活动ID")
    private Long activityId;

    /**
     * 弹窗开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "弹窗开始时间")
    private LocalDateTime popupStartTime;

    /**
     * 弹窗结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "弹窗结束时间")
    private LocalDateTime popupEndTime;

    /**
     * 状态：0-草稿，1-已发布，2-已撤回
     */
    @Schema(description = "状态：0-草稿，1-已发布，2-已撤回")
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
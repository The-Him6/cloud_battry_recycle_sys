package com.br.notice.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 系统公告VO
 */
@Data
@Schema(description = "系统公告VO")
public class SystemNoticeVO {
    @Schema(description = "公告ID")
    private Long id;
    @Schema(description = "公告标题")
    private String title;
    @Schema(description = "公告内容")
    private String content;
    @Schema(description = "关联秒杀活动ID")
    private Long activityId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "弹窗开始时间")
    private LocalDateTime popupStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "弹窗结束时间")
    private LocalDateTime popupEndTime;
    @Schema(description = "状态：0-草稿，1-已发布，2-已撤回")
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
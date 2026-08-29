package com.br.notice.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户公告已读实体类
 */
@Data
@Schema(description = "用户公告已读实体")
public class UserNoticeRead implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @Schema(description = "记录ID")
    private Long id;

    /**
     * 公告ID
     */
    @Schema(description = "公告ID")
    private Long noticeId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 已读时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "已读时间")
    private LocalDateTime readTime;
}
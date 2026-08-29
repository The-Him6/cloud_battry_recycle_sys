package com.br.points.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户积分VO
 */
@Data
@Schema(description = "用户积分VO")
public class UserPointsVO {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "总积分")
    private Integer totalPoints;
    @Schema(description = "可用积分")
    private Integer availablePoints;
    @Schema(description = "已使用积分")
    private Integer usedPoints;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
package com.br.recycle.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 电池种类VO
 */
@Data
@Schema(description = "电池种类VO")
public class BatteryTypeVO {
    @Schema(description = "电池种类ID")
    private Long id;
    @Schema(description = "电池种类名称")
    private String typeName;
    @Schema(description = "种类描述")
    private String description;
    @Schema(description = "图标地址")
    private String icon;
    @Schema(description = "如何识别该类型电池")
    private String identificationGuide;
    @Schema(description = "回收积分（每个电池可获得的积分）")
    private Integer points;
    @Schema(description = "状态：0-禁用，1-启用")
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
package com.br.recycle.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 回收明细实体类
 */
@Data
@Schema(description = "回收明细实体")
public class RecycleDetail implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 明细ID
     */
    @Schema(description = "明细ID")
    private Long id;
    
    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    private Long orderId;
    
    /**
     * 电池种类ID
     */
    @Schema(description = "电池种类ID")
    private Long batteryTypeId;
    
    /**
     * 电池数量
     */
    @Schema(description = "电池数量")
    private Integer batteryCount;
    
    /**
     * 获得积分
     */
    @Schema(description = "获得积分")
    private Integer points;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}

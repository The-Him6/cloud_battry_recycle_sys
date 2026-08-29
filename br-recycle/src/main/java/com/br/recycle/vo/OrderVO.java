package com.br.recycle.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import com.br.recycle.entity.RecycleDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单详情VO
 */
@Data
@Schema(description = "订单详情VO")
public class OrderVO {
    
    @Schema(description = "订单ID")
    private Long id;
    
    @Schema(description = "订单编号")
    private String orderNumber;
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "回收电池总数量")
    private Integer totalCount;
    
    @Schema(description = "获得总积分")
    private Integer totalPoints;
    
    @Schema(description = "回收地址")
    private String recycleAddress;
    
    @Schema(description = "联系电话")
    private String contactPhone;
    
    @Schema(description = "订单状态：0-待处理，1-处理中，2-已完成，3-已取消")
    private Integer orderStatus;
    
    @Schema(description = "备注")
    private String remark;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    
    /**
     * 订单明细列表
     */
    @Schema(description = "订单明细列表")
    private List<RecycleDetail> details;
}

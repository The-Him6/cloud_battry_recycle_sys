package com.br.recycle.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.util.List;

/**
 * 创建订单DTO
 */
@Data
@Schema(description = "创建订单DTO")
public class CreateOrderDTO {
    
    /**
     * 回收地址
     */
    @Schema(description = "回收地址")
    private String recycleAddress;
    
    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String contactPhone;
    
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
    
    /**
     * 订单明细列表
     */
    @Schema(description = "订单明细列表")
    private List<OrderDetailDTO> details;
    
    @Data
    @Schema(description = "订单明细")
    public static class OrderDetailDTO {
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
    }
}

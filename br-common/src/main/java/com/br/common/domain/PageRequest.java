package com.br.common.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.io.Serializable;

/**
 * 分页请求参数
 */
@Data
@Schema(description = "分页请求参数")
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页码，默认第1页
     */
    @Schema(description = "当前页码，默认第1页")
    private Integer pageNum = 1;

    /**
     * 每页大小，默认10条
     */
    @Schema(description = "每页大小，默认10条")
    private Integer pageSize = 10;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private String orderBy;

    /**
     * 排序方向：asc/desc
     */
    private String orderDirection = "desc";

    /**
     * 获取有效的页码
     */
    public int getValidPageNum() {
        return pageNum != null && pageNum > 0 ? pageNum : 1;
    }

    /**
     * 获取有效的每页大小
     */
    public int getValidPageSize() {
        if (pageSize == null || pageSize <= 0) {
            return 10;
        }
        // 限制最大每页100条
        return Math.min(pageSize, 100);
    }
}




































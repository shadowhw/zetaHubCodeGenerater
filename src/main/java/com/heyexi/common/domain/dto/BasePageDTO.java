package com.heyexi.common.domain.dto;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Optional;

/**
 * @Author heyexi
 * @Date 2024-01-13 19:58:34
 * @Description 基础分页页面DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BasePageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间列名称
     */
    public static final String CREATED_TIME_COLUMN_NAME = "created_time";

    /**
     * desc列名称
     */
    public static final String DESC_COLUMN_NAME = "desc";

    /**
     * asc列名称
     */
    public static final String ASC_COLUMN_NAME = "asc";

    /**
     * 默认页面大小
     */
    public static final int DEFAULT_SIZE_OF_PAGES = 20;

    /**
     * 默认页数
     */
    public static final int DEFAULT_NUM_OF_PAGES = 1;

    @Min(1)
    private Integer pageIndex;

    @Min(1)
    private Integer pageSize;

    @Pattern(regexp = "^(DESC|ASC|desc|asc)$")
    private String sort;

    private String sortField;

    /**
     * 初始化参数值
     */
    public void init() {
        this.setPageIndex(Optional.ofNullable(getPageIndex())
                .orElse(DEFAULT_NUM_OF_PAGES));
        this.setPageSize(Optional.ofNullable(getPageSize())
                .orElse(DEFAULT_SIZE_OF_PAGES));
        this.setSortField(Optional.ofNullable(getSortField())
                .filter(StrUtil::isNotBlank)
                .orElse(CREATED_TIME_COLUMN_NAME));
        this.setSort(Optional.ofNullable(getSort())
                .filter(StrUtil::isNotBlank)
                .orElse(DESC_COLUMN_NAME));
    }
}

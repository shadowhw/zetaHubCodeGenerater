package com.heyexi.generater.dto.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author heyexi
 * @Date 2024-01-19 14:24:58
 * @Description 表索引
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableKeyInfo {

    /**
     * 索引类型
     */
    private String keyType;

    /**
     * 索引名称
     */
    private String keyName;

    /**
     * 组合字段名称
     */
    private String columnNames;

}

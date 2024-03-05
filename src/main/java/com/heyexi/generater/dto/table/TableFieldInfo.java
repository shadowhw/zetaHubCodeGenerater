package com.heyexi.generater.dto.table;

import com.heyexi.generater.constant.TableFieldTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author heyexi
 * @Date 2024-01-18 22:15:39
 * @Description 表字段配置
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableFieldInfo {

    /**
     * 字段类型
     */
    private TableFieldTypeEnum fieldTypeEnum;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 长度:10 或10,2
     */
    private String columnLength;

    /**
     * 是否为空
     */
    private Boolean isNull;

    /**
     * 默认值
     */
    private Object defaultValue;

    /**
     * 注释
     */
    private String comments;

    /**
     * 额外参数
     */
    private String extend;

}

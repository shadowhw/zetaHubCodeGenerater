package com.heyexi.generater.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author heyexi
 * @Date 2023-05-11 18:24:34
 * @Description 表字段信息
 */
@Data
@Accessors(chain = true)
public class TableFieldData implements Serializable {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 备注
     */
    private String columnComment;

    /**
     * 表字段对应的Java数据类型
     */
    private String javaDataType;

    /**
     * 实体字段名
     */
    private String entityFieldName;

}

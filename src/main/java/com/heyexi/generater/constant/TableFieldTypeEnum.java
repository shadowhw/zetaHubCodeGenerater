package com.heyexi.generater.constant;

/**
 * @Author heyexi
 * @Date 2024-01-18 22:21:35
 * @Description 表字段类型枚举
 */
public enum TableFieldTypeEnum {

    /**
     * 字段类型
     */
    VARCHAR("varchar"),
    INT("int"),
    DATETIME("datetime"),
    TINYINT("tinyint"),
    BIGINT("bigint"),
    DECIMAL("decimal"),
    TEXT("text");
    private final String typeCode;

    TableFieldTypeEnum(String typeCode) {
        this.typeCode = typeCode;
    }

    public String typeCode() {
        return typeCode;
    }
}

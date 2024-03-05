package com.heyexi.generater.constant;

/**
 * @Author heyexi
 * @Date 2024-01-18 22:21:35
 * @Description 表索引枚举
 */
public enum TableKeyTypeEnum {

    /**
     * 索引类型
     */
    PRIMARY_KEY("PRIMARY KEY"),
    UNIQUE_KEY("UNIQUE KEY"),
    KEY("KEY");
    private final String keyCode;

    TableKeyTypeEnum(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getKeyCode() {
        return keyCode;
    }
}

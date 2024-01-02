package com.heyexi.common.constants;

/**
 * @Author heyexi
 * @Date 2023-12-30 1:37:55
 * @Description 是否删除枚举值
 */
public enum DelFlagEnum {

    /**
     * 标记是否删除
     */
    DELETE_FALSE(0, "未删除"),
    DELETE_TRUE(1, "已删除");


    private final Integer code;
    private final String desc;

    DelFlagEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

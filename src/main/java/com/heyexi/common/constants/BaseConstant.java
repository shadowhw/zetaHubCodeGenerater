package com.heyexi.common.constants;

/**
 * @Author heyexi
 * @Date 2023-08-23 18:5:6
 * @Description
 * 通用常量信息
 */
public interface BaseConstant {

    /**
     * 数字
     */
    interface NUM {
        Integer NUM_0 = 0;
        Integer NUM_1 = 1;
        Integer NUM_2 = 2;
        Integer NUM_3 = 3;
        Integer NUM_4 = 4;
        Integer NUM_5 = 5;
        Integer NUM_6 = 6;
        Integer NUM_7 = 7;
        Integer NUM_8 = 8;
        Integer NUM_9 = 9;
        Integer NUM_200 = 200;
        Integer NUM_201 = 201;
        Integer NUM_202 = 202;
        Integer NUM_500 = 500;
    }

    /**
     * 字符串
     */
    interface STR {
        String SUCCESS = "success";
        String SERVER_ERROR = "service error";
        String BLANK = "";
        String COLON = ":";
        String COMMA = ",";
        String DASH = "-";
        String DOT = ".";
        String NULL = "null";
        String QUESTION_MARK = "?";
        String EXCLAMATION_MARK = "!";
    }

    /**
     * 逻辑运算类型
     */
    interface LOJIC_TYPE {
        String AND = "and";
        String OR = "or";
    }
}

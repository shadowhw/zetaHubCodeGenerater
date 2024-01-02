package com.heyexi.common.back;

import com.heyexi.common.constants.BaseConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;

/**
 * @Author heyexi
 * @Date 2023-08-23 17:34:46
 * @Description 返回参数
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Back<T> implements Serializable {

    private T data;
    private int code;
    private String msg;

    public static <T> Back<T> success(T data) {
        return builderBack(data, BaseConstant.NUM.NUM_200, BaseConstant.STR.SERVER_ERROR);
    }

    public static <T> Back<T> success(T data, String msg) {
        return builderBack(data, BaseConstant.NUM.NUM_200, msg);
    }

    public static <T> Back<T> fail() {
        return builderBack(null, BaseConstant.NUM.NUM_500, BaseConstant.STR.SERVER_ERROR);
    }

    public static <T> Back<T> fail(String msg) {
        return builderBack(null, BaseConstant.NUM.NUM_500, msg);
    }

    public static <T> Back<T> fail(String msg, int code) {
        return builderBack(null, code, msg);
    }


    private static <T> Back<T> builderBack(T data, int code, String msg) {
        Back<T> back = new Back<>();
        back.setCode(code);
        back.setMsg(msg);
        back.setData(data);
        return back;
    }
}

package com.heyexi.generater.service;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author heyexi
 * @Date 2023-07-28 15:17:39
 * @Description 公共方法
 */
public abstract class CommonServiceAbstract {

    /**
     * 下划线转驼峰
     *
     * @param str
     * @param isUpperFirst 首字母是否大写
     * @return
     */
    public String underScoreCaseToCamelCase(String str, Boolean isUpperFirst) {
        String regex = "_(\\w)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String replacement = matcher.group(1).toUpperCase();
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return isUpperFirst ? StrUtil.upperFirst(sb.toString()) : StrUtil.lowerFirst(sb.toString());
    }


}

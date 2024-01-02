package com.heyexi.generater.dto;

import freemarker.template.Template;
import lombok.Data;

/**
 * @Author heyexi
 * @Date 2023-12-24 17:57:34
 * @Description 模板文件信息
 */
@Data
public class TemplateInfoData {

    /**
     * 模板文件
     */
    private Template template;

    /**
     * 生成路径
     */
    private String url;

    /**
     * 代码文件决定地址
     */
    private String fileName;

    /**
     * 渲染所需数据
     */
    private RenderDataDTO renderDataDTO;
}

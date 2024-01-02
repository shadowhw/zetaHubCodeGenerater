package com.heyexi.generater.service;

import com.heyexi.generater.dto.ZetaHubCodeGenerator;

/**
 * @Author heyexi
 * @Date 2023-07-13 18:18:24
 * @Description 源码生成服务
 */
public interface SourceCodeGenerateService {

    /**
     * 生成代码
     *
     * @param zetaHubCodeGenerator
     */
    void generate(ZetaHubCodeGenerator zetaHubCodeGenerator);

}

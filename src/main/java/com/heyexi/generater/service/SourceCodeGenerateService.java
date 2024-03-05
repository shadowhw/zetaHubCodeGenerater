package com.heyexi.generater.service;

import com.heyexi.generater.dto.ZetaHubCodeGenerator;
import com.heyexi.generater.dto.table.TableGlobalConfig;

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

    /**
     * 生成表DDL
     *
     * @param tableGlobalConfig
     */
    void generateTableDDL(TableGlobalConfig tableGlobalConfig);

}

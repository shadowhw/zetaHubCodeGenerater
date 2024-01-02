package com.heyexi.generater.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author heyexi
 * @Date 2023-06-2 16:17:48
 * @Description 代码生成器
 */
@Data
@Accessors(chain = true)
public class ZetaHubCodeGenerator implements Serializable {

    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;

    /**
     * 包配置信息
     */
    private PackageConfig packageConfig;

    /**
     * 数据源配置信息
     */
    private DataSourceConfig dataSourceConfig;

}

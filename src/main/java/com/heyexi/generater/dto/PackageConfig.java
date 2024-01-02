package com.heyexi.generater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author heyexi
 * @Date 2023-06-2 16:17:48
 * @Description 包配置
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PackageConfig implements Serializable {

    /**
     * 父级包
     */
    private String parentPackage;

    /**
     * 模块名
     */
    private String moduleName;

}

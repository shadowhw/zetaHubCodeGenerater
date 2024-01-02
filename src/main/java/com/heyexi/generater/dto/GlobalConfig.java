package com.heyexi.generater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author heyexi
 * @Date 2023-06-2 16:24:23
 * @Description 全局配置类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GlobalConfig implements Serializable {

    /**
     * 作者
     */
    private String author;

    /**
     * 项目代码生成路径模式
     * 默认中台项目
     * 1：绝对路径
     * 2：相对路径:服务名称
     */
    private Integer projectPathModel;

    /**
     * 用户自定义项目根路径
     */
    private String customProjectRootPath;

    /**
     * 是否覆盖已生成文件,默认覆盖
     */
    private boolean isFileOverride;

    /**
     * 是否生成前端代码,默认生成
     */
    private boolean isFront;

    /**
     * 表前缀
     */
    private List<String> tablePrefix;

    /**
     * 要生成代码的表名称集合
     */
    private List<String> tablesName;

    /**
     * 主键实体字段名称:驼峰命名
     */
    private String primaryFieldName;

    /**
     * 是否删除实体字段名:驼峰命名
     */
    private String delFiledName;
}

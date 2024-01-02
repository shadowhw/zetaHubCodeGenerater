package com.heyexi.generater.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author heyexi
 * @Date 2023-12-24 17:37:37
 * @Description 渲染文件所需数据
 */
@Data
@Accessors(chain = true)
public class RenderDataDTO implements Serializable {

    /**
     * 全局配置
     */
    private GlobalConfig globalConfig;

    /**
     * 包配置信息
     */
    private PackageConfig packageConfig;

    /**
     * 当前时间
     */
    private Date date;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 驼峰命名实体名称
     */
    private String humpEntityName;

    /**
     * 表字段信息
     */
    private List<TableFieldData> tableFieldData;

    /**
     * 实体字段数据类型集合，用于导入包
     */
    private Set<String> javaDataTypeList;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 项目根目录
     */
    private String projectRootUrlPath;

    /**
     * 提供一个原型复制方法，给每个模板文件可以自定义设置信息
     *
     * @return
     */
    public RenderDataDTO cloneRenderDataDTO() {
        RenderDataDTO renderDataDTO = new RenderDataDTO();
        renderDataDTO.setDate(this.getDate())
                .setGlobalConfig(this.getGlobalConfig())
                .setPackageConfig(this.getPackageConfig())
                .setEntityName(this.getEntityName())
                .setHumpEntityName(this.getHumpEntityName())
                .setTableFieldData(this.getTableFieldData())
                .setJavaDataTypeList(this.getJavaDataTypeList())
                .setTableName(this.getTableName())
                .setProjectRootUrlPath(this.getProjectRootUrlPath());
        return renderDataDTO;
    }

}

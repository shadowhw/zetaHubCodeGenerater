package com.heyexi.generater.dto.table;

import com.heyexi.generater.constant.TableKeyTypeEnum;
import com.heyexi.generater.dto.DataSourceConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @Author heyexi
 * @Date 2024-01-18 22:13:56
 * @Description 表DDL生成配置
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TableGlobalConfig {

    /**
     * 数据源配置信息
     */
    private DataSourceConfig dataSourceConfig;


    /**
     * 表字段信息
     */
    private List<TableFieldInfo> tableFieldInfos;

    /**
     * 主键字段名
     */
    private String primaryKeyColumnName;

    /**
     * 索引集合
     */
    private List<TableKeyInfo> keyList;

    /**
     * 生成绝对文件目录地址
     */
    private String filePath;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 数据库名
     */
    private String schemeName;

    /**
     * 表注释
     */
    private String tableComments;

}

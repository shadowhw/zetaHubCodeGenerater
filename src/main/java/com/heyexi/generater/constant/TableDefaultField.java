package com.heyexi.generater.constant;

import com.heyexi.generater.dto.table.TableFieldInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author heyexi
 * @Date 2024-01-19 14:7:8
 * @Description 数据表默认字段
 */
public class TableDefaultField {

    public static List<TableFieldInfo> adCrm() {
        List<TableFieldInfo> list = new ArrayList<>();

        TableFieldInfo createdBy = new TableFieldInfo();
        createdBy.setColumnName("created_by");
        createdBy.setFieldTypeEnum(TableFieldTypeEnum.VARCHAR);
        createdBy.setColumnLength("255");
        createdBy.setIsNull(true);
        createdBy.setDefaultValue("'-1'");
        createdBy.setComments("创建人");

        TableFieldInfo createdTime = new TableFieldInfo();
        createdTime.setColumnName("created_time");
        createdTime.setFieldTypeEnum(TableFieldTypeEnum.DATETIME);
        createdTime.setIsNull(false);
        createdTime.setDefaultValue("CURRENT_TIMESTAMP");
        createdTime.setComments("创建时间");

        TableFieldInfo updatedBy = new TableFieldInfo();
        updatedBy.setColumnName("updated_by");
        updatedBy.setFieldTypeEnum(TableFieldTypeEnum.VARCHAR);
        updatedBy.setColumnLength("255");
        updatedBy.setIsNull(true);
        updatedBy.setDefaultValue("'-1'");
        updatedBy.setComments("更新人");

        TableFieldInfo updatedTime = new TableFieldInfo();
        updatedTime.setColumnName("updated_time");
        updatedTime.setFieldTypeEnum(TableFieldTypeEnum.DATETIME);
        updatedTime.setIsNull(false);
        updatedTime.setDefaultValue("CURRENT_TIMESTAMP");
        updatedTime.setExtend("ON UPDATE CURRENT_TIMESTAMP");
        updatedTime.setComments("更新时间");

        TableFieldInfo isDeleted = new TableFieldInfo();
        isDeleted.setColumnName("is_deleted");
        isDeleted.setFieldTypeEnum(TableFieldTypeEnum.TINYINT);
        isDeleted.setColumnLength("1");
        isDeleted.setIsNull(true);
        isDeleted.setDefaultValue("'0'");
        isDeleted.setComments("是否已删除0-否 1-是");


        list.add(createdBy);
        list.add(createdTime);
        list.add(updatedBy);
        list.add(updatedTime);
        list.add(isDeleted);
        return list;
    }


    public static List<TableFieldInfo> zataHub() {
        TableFieldInfo[] tableFieldInfos = new TableFieldInfo[]{
                new TableFieldInfo(TableFieldTypeEnum.VARCHAR, "create_by_id", "64", false, "'-1'", "创建人编码", null),
                new TableFieldInfo(TableFieldTypeEnum.VARCHAR, "create_by_name", "64", false, "'system'", "创建人姓名", null),
                new TableFieldInfo(TableFieldTypeEnum.DATETIME, "create_time", null, false, "CURRENT_TIMESTAMP", "创建时间", null),
                new TableFieldInfo(TableFieldTypeEnum.VARCHAR, "update_by_id", "64", false, "'-1'", "更新人编码", null),
                new TableFieldInfo(TableFieldTypeEnum.VARCHAR, "update_by_name", "64", false, "'system'", "更新人姓名", null),
                new TableFieldInfo(TableFieldTypeEnum.DATETIME, "update_time", null, false, "CURRENT_TIMESTAMP", "更新时间", "ON UPDATE CURRENT_TIMESTAMP"),
                new TableFieldInfo(TableFieldTypeEnum.TINYINT, "delFlag", "1", false, "'0'", "是否删除[0-否 1-是]", null),
        };
        return Arrays.asList(tableFieldInfos);
    }


}

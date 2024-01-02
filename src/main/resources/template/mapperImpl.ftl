<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageConfig.parentPackage}.${packageConfig.moduleName}.mapper.${entityName}Mapper">
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.entity.${entityName}">
    <#if tableFieldData?has_content>
     <#list tableFieldData as field>
      <#if field.columnName?has_content && field.columnName == 'id'>
        <id column="id" property="id" />
          <#else>
        <result column="${field.columnName}" property="${field.entityFieldName}"/>
      </#if>
     </#list>
    </#if>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        <#if tableFieldData?has_content>
            <#list tableFieldData as field>
                <#if field_has_next>
        ${field.columnName},
                    <#else >
        ${field.columnName}
                </#if>
            </#list>
        </#if>
    </sql>

    <select id="selectPageList" resultType="${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}PageListVO">
        select t.* from ${tableName} t
        <where>
            <#if tableFieldData?has_content>
                <#list tableFieldData as field>
                    <#if field.columnName == 'del_flag'>
            del_flag = 0
                    </#if>
                </#list>
            </#if>
            <#if tableFieldData?has_content>
                <#list tableFieldData as field>
                    <#if field.columnName != 'del_flag'>
                        <#if field.javaDataType == 'String'>
            <if test="cond.${field.entityFieldName} != null and cond.${field.entityFieldName} != ''">
                and ${field.columnName} = ${r'#'}${r'{'}cond.${field.entityFieldName}${r'}'}
            </if>
                            <#else>
            <if test="cond.${field.entityFieldName} != null">
                and ${field.columnName} = ${r'#'}${r'{'}cond.${field.entityFieldName}${r'}'}
            </if>
                        </#if>
                    </#if>
                </#list>
            </#if>
        </where>
    </select>

</mapper>
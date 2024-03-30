package ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.entity;

import lombok.experimental.Accessors;
import com.heyexi.common.domain.dto.BaseEntityDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#if javaDataTypeList?has_content>
    <#list javaDataTypeList as type>
        <#if type == 'BigDecimal'>
import java.math.BigDecimal;
        </#if>
        <#if type == 'LocalDateTime'>
import java.time.LocalDateTime;
        </#if>
        <#if type == 'LocalDate'>
import java.time.LocalDate;
        </#if>
    </#list>
</#if>

/**
 * @Author ${globalConfig.author}
 * @Date ${date?string('yyyy-MM-dd HH:mm:ss')}
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("${tableName}")
public class ${entityName} extends BaseEntityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

<#if tableFieldData?has_content>
    <#list tableFieldData as field>
        <#if field.columnComment?has_content>
    /**
     * ${field.columnComment}
     */
        </#if>
        <#if field.entityFieldName?has_content && field.entityFieldName == 'id'>
    @TableId(value = "id", type = IdType.AUTO)
        </#if>
        <#if field.javaDataType?has_content && field.entityFieldName?has_content>
    private ${field.javaDataType} ${field.entityFieldName};
        </#if>

    </#list>
</#if>
}

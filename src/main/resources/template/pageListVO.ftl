package ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo;

import lombok.experimental.Accessors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;
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
@ToString
public class ${entityName}PageListVO implements Serializable {

<#if tableFieldData?has_content>
    <#list tableFieldData as field>
        <#if field.columnComment?has_content>
    /**
     * ${field.columnComment}
     */
        </#if>
        <#if field.javaDataType?has_content && field.entityFieldName?has_content>
    private ${field.javaDataType} ${field.entityFieldName};
        </#if>

    </#list>
</#if>
}

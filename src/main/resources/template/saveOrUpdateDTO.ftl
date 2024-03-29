package ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.experimental.Accessors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;
<#assign dateFalg=false>
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
        <#if type == 'LocalDateTime' || type == 'LocalDate' && dateFalg == false>
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
        <#assign dateFalg=true>
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
public class ${entityName}SaveOrUpdateDTO implements Serializable {

<#if tableFieldData?has_content>
    <#list tableFieldData as field>
        <#if field.columnComment?has_content>
    /**
     * ${field.columnComment}
     */
        </#if>
        <#if field.javaDataType?has_content && field.javaDataType == 'LocalDateTime'>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        </#if>
        <#if field.javaDataType?has_content && field.javaDataType == 'LocalDate'>
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
        </#if>
        <#if field.javaDataType?has_content && field.javaDataType == 'String'>
    @NotBlank(message = "${field.entityFieldName} can not be blank")
        </#if>
        <#if field.javaDataType?has_content && (field.javaDataType == 'Integer' || field.javaDataType == 'Long')>
    @NotNull(message = "${field.entityFieldName} can not be null")
        </#if>
        <#if field.javaDataType?has_content && field.entityFieldName?has_content>
    private ${field.javaDataType} ${field.entityFieldName};
        </#if>

    </#list>
</#if>
}

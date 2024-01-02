package ${packageConfig.parentPackage}.${packageConfig.moduleName}.vo;

import lombok.experimental.Accessors;
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
        <#if type == 'String'>
import java.lang.String;
        </#if>
    <#if type == 'Long'>
import java.lang.Long;
    </#if>
    <#if type == 'Integer'>
import java.lang.Integer;
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
public class ${entityName} implements Serializable {

    private static final long serialVersionUID = 1L;

<#if tableFields?has_content>
    <#list tableFields as field>
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

package ${packageConfig.parentPackage}.${packageConfig.moduleName}.vo;

import com.heyexi.system.pojo.PageVO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class ComFileInfoVO {

@Data
@Accessors(chain = true)
@Description("分页请求参数")
public static class PageListReq extends PageVO.Req{
private Long id;

private String FileName;

/**
* 排序重要等级[DSysFileLevel]
*/
private String level;

private String createUserName;

private String remark;

private String bucketName;

}

@Data
@Accessors(chain = true)
@Description("上传文件参数")
public static class FileUploadReq {

private String level;//排序重要等级[DSysFileLevel]

private Long createUser;

private String createUserName;

private String remark;

@NotBlank(message = "桶名不能为空")
private String bucketName;

/**
* 上传密码
*/
@NotBlank(message = "密码不能为空")
private String pwd;
}

@Data
@Accessors(chain = true)
@Description("文件下载参数")
public static class FileDownloadReq {

@NotBlank(message = "文件名不能为空")
private String fileName;

private String pwd;

@NotBlank(message = "文件桶不能为空")
private String bucketName;
}

}

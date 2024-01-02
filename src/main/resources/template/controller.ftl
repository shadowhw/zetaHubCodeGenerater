package ${packageConfig.parentPackage}.${packageConfig.moduleName}.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Description;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.service.${entityName}Service;
import com.heyexi.common.back.Back;
import com.heyexi.common.domain.vo.PageVO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto.${entityName}PageListDTO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}PageListVO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto.${entityName}SaveOrUpdateDTO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}DetailVO;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;


/**
 * @Author ${globalConfig.author}
 * @Date ${date?string('yyyy-MM-dd HH:mm:ss')}
 * @Description
 */
@RestController
@RequestMapping("/${packageConfig.moduleName}/${humpEntityName}")
public class ${entityName}Controller {

    private final ${entityName}Service ${humpEntityName}Service;

    public ${entityName}Controller(${entityName}Service ${humpEntityName}Service) {
        this.${humpEntityName}Service = ${humpEntityName}Service;
    }

    @Description("分页查询")
    @PostMapping("/pageList")
    @ResponseBody
    public Back<PageVO.Resp<${entityName}PageListVO>> ${humpEntityName}PageList(@Valid @RequestBody ${entityName}PageListDTO dto) {
        return Back.success(${humpEntityName}Service.selectPageList(dto));
    }

    @Description("新增")
    @PostMapping("/create${entityName}")
    @ResponseBody
    public Back<String> create(@Valid @RequestBody ${entityName}SaveOrUpdateDTO dto) {
        String res = ${humpEntityName}Service.create${entityName}(dto);
        return Objects.isNull(res) ? Back.fail() : Back.success(res);
    }

    @Description("更新")
    @PostMapping("/update${entityName}")
    @ResponseBody
    public Back<String> update(@Valid @RequestBody ${entityName}SaveOrUpdateDTO dto) {
        String res = ${humpEntityName}Service.update${entityName}(dto);
        return Objects.isNull(res) ? Back.fail() : Back.success(res);
    }

    <#if globalConfig.primaryFieldName??>
    @Description("查询详情")
    @GetMapping("/query${entityName}Detail")
    @ResponseBody
    public Back<${entityName}DetailVO> query${entityName}Detail(@RequestParam("${globalConfig.primaryFieldName}") @NotBlank(message = "${globalConfig.primaryFieldName} can not be null") String ${globalConfig.primaryFieldName}) {
        return Back.success(${humpEntityName}Service.query${entityName}Detail(${globalConfig.primaryFieldName}));
    }
        <#else>
    @Description("查询详情")
    @GetMapping("/query${entityName}Detail")
    @ResponseBody
    public Back<${entityName}DetailVO> query${entityName}Detail(@RequestParam("id") @NotBlank(message = "id can not be null") String id) {
        return Back.success(${humpEntityName}Service.query${entityName}Detail(id));
    }
    </#if>

    <#if globalConfig.primaryFieldName??>
    @Description("删除")
    @DeleteMapping("/delete${entityName}")
    @ResponseBody
    public Back<String> delete${entityName}(@RequestParam("${globalConfig.primaryFieldName}") @NotBlank(message = "${globalConfig.primaryFieldName} can not be null") String ${globalConfig.primaryFieldName}) {
        String res = ${humpEntityName}Service.delete${entityName}(${globalConfig.primaryFieldName});
        return Objects.isNull(res) ? Back.fail() : Back.success(res);
    }
        <#else>
    @Description("删除")
    @GetMapping("/delete${entityName}")
    @ResponseBody
    public Back<String> delete${entityName}(@RequestParam("id") @NotBlank(message = "id can not be null") String id) {
        String res = ${humpEntityName}Service.delete${entityName}(id);
        return Objects.isNull(res) ? Back.fail() : Back.success(res);
    }
    </#if>

}
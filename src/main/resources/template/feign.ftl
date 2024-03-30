package ${packageConfig.parentPackage}.${packageConfig.moduleName}.feign;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import com.heyexi.common.back.Back;
import com.heyexi.common.domain.vo.PageVO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto.${entityName}PageListDTO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}PageListVO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto.${entityName}SaveOrUpdateDTO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}DetailVO;

import javax.validation.Valid;

/**
 * @Author ${globalConfig.author}
 * @Date ${date?string('yyyy-MM-dd HH:mm:ss')}
 * @Description
 */
@FeignClient(
        name = "admin",
        url = "${r'$'}{com.heyexi.feign.admin.gateway.url:}",
        contextId = "${entityName}Service",
        path = "/${packageConfig.moduleName}/${humpEntityName}")
public interface ${entityName}FeignClient {

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    @PostMapping("/pageList")
    Back<PageVO.Resp<${entityName}PageListVO>> ${humpEntityName}PageList(@Valid @RequestBody ${entityName}PageListDTO dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PostMapping("/create")
    Back<String> create(@Valid @RequestBody ${entityName}SaveOrUpdateDTO dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PostMapping("/update")
    Back<String> update(@Valid @RequestBody ${entityName}SaveOrUpdateDTO dto);

    <#if globalConfig.primaryFieldName??>
    /**
     * 查询详情
     *
     * @param ${globalConfig.primaryFieldName}
     * @return
     */
    @GetMapping("/queryDetail")
    Back<${entityName}DetailVO> query${entityName}Detail(@RequestParam("${globalConfig.primaryFieldName}") String ${globalConfig.primaryFieldName});
    <#else>
    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/queryDetail")
    Back<${entityName}DetailVO> query${entityName}Detail(@RequestParam("id") String id);
    </#if>

    <#if globalConfig.primaryFieldName??>
    /**
     * 删除
     *
     * @param ${globalConfig.primaryFieldName}
     * @return
     */
    @DeleteMapping("/delete")
    Back<String> delete${entityName}(@RequestParam("${globalConfig.primaryFieldName}") String ${globalConfig.primaryFieldName});
    <#else>
    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    Back<String> delete${entityName}(@RequestParam("id") String id);
    </#if>

}
package ${packageConfig.parentPackage}.${packageConfig.moduleName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto.${entityName}PageListDTO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.entity.${entityName};
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}PageListVO;
import com.heyexi.common.domain.vo.PageVO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto.${entityName}SaveOrUpdateDTO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}DetailVO;

/**
 * @Author ${globalConfig.author}
 * @Date ${date?string('yyyy-MM-dd HH:mm:ss')}
 * @Description
 */
public interface ${entityName}Service extends IService<${entityName}> {

    /**
     * 分页查询数据
     *
     * @param dto
     * @return
     */
    PageVO.Resp<${entityName}PageListVO> selectPageList(${entityName}PageListDTO dto);

    /**
     * 新增
     *
     * @param dto
     * @return 新增后的主键
     */
    String create${entityName}(${entityName}SaveOrUpdateDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return 更新后的主键
     */
    String update${entityName}(${entityName}SaveOrUpdateDTO dto);

    <#if globalConfig.primaryFieldName??>
    /**
     * 查询详情
     *
     * @param ${globalConfig.primaryFieldName}
     * @return
     */
    ${entityName}DetailVO query${entityName}Detail(String ${globalConfig.primaryFieldName});
        <#else>
    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    ${entityName}DetailVO query${entityName}Detail(String id);
    </#if>

    <#if globalConfig.primaryFieldName??>
    /**
     * 删除
     *
     * @param ${globalConfig.primaryFieldName}
     * @return
     */
    String delete${entityName}(String ${globalConfig.primaryFieldName});
    <#else>
    /**
     * 删除
     *
     * @param id
     * @return
     */
    String delete${entityName}(String id);
    </#if>

}

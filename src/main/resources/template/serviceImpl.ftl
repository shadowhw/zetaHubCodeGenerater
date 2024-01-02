package ${packageConfig.parentPackage}.${packageConfig.moduleName}.service.impl;

import org.springframework.stereotype.Service;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.service.${entityName}Service;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.entity.${entityName};
import com.heyexi.common.domain.vo.PageVO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto.${entityName}PageListDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.mapper.${entityName}Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}PageListVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.hutool.core.bean.BeanUtil;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto.${entityName}SaveOrUpdateDTO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}DetailVO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.convert.${entityName}Convert;
import com.heyexi.common.utils.BeanCommonUtils;
import java.util.Optional;
import com.heyexi.common.constants.BaseConstant;
import java.util.Objects;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
<#if globalConfig.delFiledName??>
import com.heyexi.common.constants.DelFlagEnum;
</#if>


/**
 * @Author ${globalConfig.author}
 * @Date ${date?string('yyyy-MM-dd HH:mm:ss')}
 * @Description
 */
@Service
public class ${entityName}ServiceImpl extends ServiceImpl<${entityName}Mapper, ${entityName}> implements ${entityName}Service {

    private final ${entityName}Mapper ${humpEntityName}Mapper;
    private final ${entityName}Convert ${humpEntityName}Convert;

    public ${entityName}ServiceImpl(${entityName}Mapper ${humpEntityName}Mapper, ${entityName}Convert ${humpEntityName}Convert) {
        this.${humpEntityName}Mapper = ${humpEntityName}Mapper;
        this.${humpEntityName}Convert = ${humpEntityName}Convert;
    }

    @Override
    public PageVO.Resp<${entityName}PageListVO> selectPageList(${entityName}PageListDTO dto) {
        Page<${entityName}PageListVO> page = new Page<>(dto.getPageIndex(), dto.getPageSize());
        IPage<${entityName}PageListVO> iPage = ${humpEntityName}Mapper.selectPageList(page, BeanUtil.beanToMap(dto));
        return new PageVO.Resp<>(iPage);
    }

    @Override
    public String create${entityName}(${entityName}SaveOrUpdateDTO dto) {
        ${entityName} ${humpEntityName} = ${humpEntityName}Convert.convertSaveOrUpdate${entityName}(dto);
        validSaveOrUpdate${entityName}(${humpEntityName}, false);
        BeanCommonUtils.setDefaultField(${humpEntityName}, false);
        int insert = baseMapper.insert(${humpEntityName});
        return insert > BaseConstant.NUM.NUM_0 ? String.valueOf(${humpEntityName}.getId()) : null;
    }

    @Override
    public String update${entityName}(${entityName}SaveOrUpdateDTO dto) {
        ${entityName} ${humpEntityName} = ${humpEntityName}Convert.convertSaveOrUpdate${entityName}(dto);
        validSaveOrUpdate${entityName}(${humpEntityName}, true);
        BeanCommonUtils.setDefaultField(${humpEntityName}, true);
        int update = baseMapper.updateById(${humpEntityName});
        return update > BaseConstant.NUM.NUM_0 ? String.valueOf(${humpEntityName}.getId()) : null;
    }

    <#if globalConfig.primaryFieldName??>
    @Override
    public ${entityName}DetailVO query${entityName}Detail(String ${globalConfig.primaryFieldName}) {
        ${entityName} ${humpEntityName} = ${humpEntityName}Mapper.selectOne(Wrappers.<${entityName}>lambdaQuery()
                .eq(${entityName}::get${globalConfig.primaryFieldName?cap_first}, ${globalConfig.primaryFieldName})
        <#if globalConfig.delFiledName??>
                .eq(${entityName}::get${globalConfig.delFiledName?cap_first}, DelFlagEnum.DELETE_FALSE.getCode())
        </#if>
        );
        return ${humpEntityName}Convert.convert${entityName}DetailVO(${humpEntityName});
    }
    <#else>
    @Override
    public ${entityName}DetailVO query${entityName}Detail(String id) {
        ${entityName} ${humpEntityName} = ${humpEntityName}Mapper.selectOne(Wrappers.<${entityName}>lambdaQuery()
                .eq(${entityName}::getId, id)
            <#if globalConfig.delFiledName??>
                .eq(${entityName}::get${globalConfig.delFiledName?cap_first}, DelFlagEnum.DELETE_FALSE.getCode())
            </#if>
        );
        return ${humpEntityName}Convert.convert${entityName}DetailVO(${humpEntityName});
    }
    </#if>

    <#if globalConfig.primaryFieldName??>
    @Override
    public String delete${entityName}(String ${globalConfig.primaryFieldName}) {
        int rows = ${humpEntityName}Mapper.delete(Wrappers.<${entityName}>lambdaQuery()
                .eq(${entityName}::get${globalConfig.primaryFieldName?cap_first}, ${globalConfig.primaryFieldName})
                        <#if globalConfig.delFiledName??>
                .eq(${entityName}::get${globalConfig.delFiledName?cap_first}, DelFlagEnum.DELETE_FALSE.getCode())
                        </#if>
        );
        return rows > BaseConstant.NUM.NUM_0 ? id : null;
    }
    <#else>
    @Override
    public String delete${entityName}(String id) {
        int rows = ${humpEntityName}Mapper.delete(Wrappers.<${entityName}>lambdaQuery()
                .eq(${entityName}::getId, id)
        <#if globalConfig.delFiledName??>
                .eq(${entityName}::get${globalConfig.delFiledName?cap_first}, DelFlagEnum.DELETE_FALSE.getCode())
        </#if>
        );
        return rows > BaseConstant.NUM.NUM_0 ? id : null;
    }
    </#if>

    /**
     * 新增或修改对数据进行校验
     *
     * @param entity
     * @param isUpdate 是否是更新
     */
    private void validSaveOrUpdate${entityName}(${entityName} entity, boolean isUpdate) {
        if (!isUpdate) {
            entity.setId(null);
            <#if globalConfig.delFiledName??>
            entity.set${globalConfig.delFiledName?cap_first}(DelFlagEnum.DELETE_FALSE.getCode());
            </#if>
        } else {
            <#if globalConfig.primaryFieldName??>
            Optional.ofNullable(entity).filter(t -> Objects.nonNull(entity.get${globalConfig.primaryFieldName?cap_first}()))
                    .map(${entityName}::get${globalConfig.primaryFieldName?cap_first}).map(code ->
                            ${humpEntityName}Mapper.selectOne(Wrappers.<${entityName}>lambdaQuery().select(${entityName}::get${globalConfig.primaryFieldName?cap_first})
                                    .eq(${entityName}::get${globalConfig.primaryFieldName?cap_first}, code)
                            <#if globalConfig.delFiledName??>
                                    .eq(${entityName}::get${globalConfig.delFiledName?cap_first}, DelFlagEnum.DELETE_FALSE.getCode())
                            </#if>
                            )
                <#else>
            Optional.ofNullable(entity).filter(t -> Objects.nonNull(entity.getId()))
                    .map(${entityName}::getId).map(code ->
                            ${humpEntityName}Mapper.selectOne(Wrappers.<${entityName}>lambdaQuery().select(${entityName}::getId)
                                    .eq(${entityName}::getId, code)
                            <#if globalConfig.delFiledName??>
                                    .eq(${entityName}::get${globalConfig.delFiledName?cap_first}, DelFlagEnum.DELETE_FALSE.getCode())
                            </#if>
                            )
            </#if>
                    ).orElseThrow(() -> {
                        throw new RuntimeException("数据不存在，更新失败");
                    });
        }
    }

}
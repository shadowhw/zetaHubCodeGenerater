package ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.convert;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapper;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.dto.${entityName}SaveOrUpdateDTO;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.entity.${entityName};
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}DetailVO;

/**
 * @Author ${globalConfig.author}
 * @Date ${date?string('yyyy-MM-dd HH:mm:ss')}
 * @Description ${entityName}对象转换
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ${entityName}Convert {

    /**
     * 更新或新增对象转换
     *
     * @param dto
     * @return
     */
    ${entityName} convertSaveOrUpdate${entityName}(${entityName}SaveOrUpdateDTO dto);

    /**
     * 查询详情转换
     *
     * @param dto
     * @return
     */
    ${entityName}DetailVO convert${entityName}DetailVO(${entityName} dto);

}

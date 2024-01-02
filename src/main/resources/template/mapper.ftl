package ${packageConfig.parentPackage}.${packageConfig.moduleName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.entity.${entityName};
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import ${packageConfig.parentPackage}.${packageConfig.moduleName}.domain.vo.${entityName}PageListVO;

/**
 * @Author ${globalConfig.author}
 * @Date ${date?string('yyyy-MM-dd HH:mm:ss')}
 * @Description
 */
public interface ${entityName}Mapper extends BaseMapper<${entityName}> {

    /**
     * 分页查询
     * @param page
     * @param cond
     * @return
     */
    IPage<${entityName}PageListVO> selectPageList(@Param("page") Page<${entityName}PageListVO> page, @Param("cond") Map<String, Object> cond);

}
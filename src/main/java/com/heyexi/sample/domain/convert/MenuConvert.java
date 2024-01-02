package com.heyexi.sample.domain.convert;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapper;
import com.heyexi.sample.domain.dto.MenuSaveOrUpdateDTO;
import com.heyexi.sample.domain.entity.Menu;
import com.heyexi.sample.domain.vo.MenuDetailVO;

/**
 * @Author heyexi
 * @Date 2024-01-02 21:48:53
 * @Description Menu对象转换
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MenuConvert {

    /**
     * 更新或新增对象转换
     *
     * @param dto
     * @return
     */
    Menu convertSaveOrUpdateMenu(MenuSaveOrUpdateDTO dto);

    /**
     * 查询详情转换
     *
     * @param dto
     * @return
     */
    MenuDetailVO convertMenuDetailVO(Menu dto);

}

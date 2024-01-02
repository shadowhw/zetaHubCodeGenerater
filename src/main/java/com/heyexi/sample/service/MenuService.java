package com.heyexi.sample.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heyexi.sample.domain.dto.MenuPageListDTO;
import com.heyexi.sample.domain.entity.Menu;
import com.heyexi.sample.domain.vo.MenuPageListVO;
import com.heyexi.common.domain.vo.PageVO;
import com.heyexi.sample.domain.dto.MenuSaveOrUpdateDTO;
import com.heyexi.sample.domain.vo.MenuDetailVO;

/**
 * @Author heyexi
 * @Date 2024-01-02 21:48:53
 * @Description
 */
public interface MenuService extends IService<Menu> {

    /**
     * 分页查询数据
     *
     * @param dto
     * @return
     */
    PageVO.Resp<MenuPageListVO> selectPageList(MenuPageListDTO dto);

    /**
     * 新增
     *
     * @param dto
     * @return 新增后的主键
     */
    String createMenu(MenuSaveOrUpdateDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return 更新后的主键
     */
    String updateMenu(MenuSaveOrUpdateDTO dto);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    MenuDetailVO queryMenuDetail(String id);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    String deleteMenu(String id);

}

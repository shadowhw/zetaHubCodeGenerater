package com.heyexi.sample.service.impl;

import org.springframework.stereotype.Service;
import com.heyexi.sample.service.MenuService;
import com.heyexi.sample.domain.entity.Menu;
import com.heyexi.common.domain.vo.PageVO;
import com.heyexi.sample.domain.dto.MenuPageListDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heyexi.sample.mapper.MenuMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyexi.sample.domain.vo.MenuPageListVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.hutool.core.bean.BeanUtil;
import com.heyexi.sample.domain.dto.MenuSaveOrUpdateDTO;
import com.heyexi.sample.domain.vo.MenuDetailVO;
import com.heyexi.sample.domain.convert.MenuConvert;
import com.heyexi.common.utils.BeanCommonUtils;
import java.util.Optional;
import com.heyexi.common.constants.BaseConstant;
import java.util.Objects;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heyexi.common.constants.DelFlagEnum;


/**
 * @Author heyexi
 * @Date 2024-01-02 21:48:53
 * @Description
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;
    private final MenuConvert menuConvert;

    public MenuServiceImpl(MenuMapper menuMapper, MenuConvert menuConvert) {
        this.menuMapper = menuMapper;
        this.menuConvert = menuConvert;
    }

    @Override
    public PageVO.Resp<MenuPageListVO> selectPageList(MenuPageListDTO dto) {
        Page<MenuPageListVO> page = new Page<>(dto.getPageIndex(), dto.getPageSize());
        IPage<MenuPageListVO> iPage = menuMapper.selectPageList(page, BeanUtil.beanToMap(dto));
        return new PageVO.Resp<>(iPage);
    }

    @Override
    public String createMenu(MenuSaveOrUpdateDTO dto) {
        Menu menu = menuConvert.convertSaveOrUpdateMenu(dto);
        validSaveOrUpdateMenu(menu, false);
        BeanCommonUtils.setDefaultField(menu, false);
        int insert = baseMapper.insert(menu);
        return insert > BaseConstant.NUM.NUM_0 ? String.valueOf(menu.getId()) : null;
    }

    @Override
    public String updateMenu(MenuSaveOrUpdateDTO dto) {
        Menu menu = menuConvert.convertSaveOrUpdateMenu(dto);
        validSaveOrUpdateMenu(menu, true);
        BeanCommonUtils.setDefaultField(menu, true);
        int update = baseMapper.updateById(menu);
        return update > BaseConstant.NUM.NUM_0 ? String.valueOf(menu.getId()) : null;
    }

    @Override
    public MenuDetailVO queryMenuDetail(String id) {
        Menu menu = menuMapper.selectOne(Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getId, id)
                .eq(Menu::getDelFlag, DelFlagEnum.DELETE_FALSE.getCode())
        );
        return menuConvert.convertMenuDetailVO(menu);
    }

    @Override
    public String deleteMenu(String id) {
        int rows = menuMapper.delete(Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getId, id)
                .eq(Menu::getDelFlag, DelFlagEnum.DELETE_FALSE.getCode())
        );
        return rows > BaseConstant.NUM.NUM_0 ? id : null;
    }

    /**
     * 新增或修改对数据进行校验
     *
     * @param entity
     * @param isUpdate 是否是更新
     */
    private void validSaveOrUpdateMenu(Menu entity, boolean isUpdate) {
        if (!isUpdate) {
            entity.setId(null);
            entity.setDelFlag(DelFlagEnum.DELETE_FALSE.getCode());
        } else {
            Optional.ofNullable(entity).filter(t -> Objects.nonNull(entity.getId()))
                    .map(Menu::getId).map(code ->
                            menuMapper.selectOne(Wrappers.<Menu>lambdaQuery().select(Menu::getId)
                                    .eq(Menu::getId, code)
                                    .eq(Menu::getDelFlag, DelFlagEnum.DELETE_FALSE.getCode())
                            )
                    ).orElseThrow(() -> {
                        throw new RuntimeException("数据不存在，更新失败");
                    });
        }
    }

}
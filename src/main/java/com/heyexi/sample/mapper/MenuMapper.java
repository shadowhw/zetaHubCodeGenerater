package com.heyexi.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyexi.sample.domain.entity.Menu;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.heyexi.sample.domain.vo.MenuPageListVO;

/**
 * @Author heyexi
 * @Date 2024-01-02 21:48:53
 * @Description
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 分页查询
     * @param page
     * @param cond
     * @return
     */
    IPage<MenuPageListVO> selectPageList(@Param("page") Page<MenuPageListVO> page, @Param("cond") Map<String, Object> cond);

}
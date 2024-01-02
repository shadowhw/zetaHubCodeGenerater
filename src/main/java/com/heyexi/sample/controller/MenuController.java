package com.heyexi.sample.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Description;
import com.heyexi.sample.service.MenuService;
import com.heyexi.common.back.Back;
import com.heyexi.common.domain.vo.PageVO;
import com.heyexi.sample.domain.dto.MenuPageListDTO;
import com.heyexi.sample.domain.vo.MenuPageListVO;
import com.heyexi.sample.domain.dto.MenuSaveOrUpdateDTO;
import com.heyexi.sample.domain.vo.MenuDetailVO;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;


/**
 * @Author heyexi
 * @Date 2024-01-02 21:48:53
 * @Description
 */
@RestController
@RequestMapping("/sample/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Description("分页查询")
    @PostMapping("/pageList")
    @ResponseBody
    public Back<PageVO.Resp<MenuPageListVO>> menuPageList(@Valid @RequestBody MenuPageListDTO dto) {
        return Back.success(menuService.selectPageList(dto));
    }

    @Description("新增")
    @PostMapping("/createMenu")
    @ResponseBody
    public Back<String> create(@Valid @RequestBody MenuSaveOrUpdateDTO dto) {
        String res = menuService.createMenu(dto);
        return Objects.isNull(res) ? Back.fail() : Back.success(res);
    }

    @Description("更新")
    @PostMapping("/updateMenu")
    @ResponseBody
    public Back<String> update(@Valid @RequestBody MenuSaveOrUpdateDTO dto) {
        String res = menuService.updateMenu(dto);
        return Objects.isNull(res) ? Back.fail() : Back.success(res);
    }

    @Description("查询详情")
    @GetMapping("/queryMenuDetail")
    @ResponseBody
    public Back<MenuDetailVO> queryMenuDetail(@RequestParam("id") @NotBlank(message = "id can not be null") String id) {
        return Back.success(menuService.queryMenuDetail(id));
    }

    @Description("删除")
    @DeleteMapping("/deleteMenu")
    @ResponseBody
    public Back<String> deleteMenu(@RequestParam("id") @NotBlank(message = "id can not be null") String id) {
        String res = menuService.deleteMenu(id);
        return Objects.isNull(res) ? Back.fail() : Back.success(res);
    }

}
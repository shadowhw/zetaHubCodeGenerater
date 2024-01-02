package com.heyexi.common.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.heyexi.common.domain.dto.BaseEntityDTO;
import com.heyexi.common.domain.dto.UserLoginInfoDTO;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @Author heyexi
 * @Date 2023-12-25 22:44:11
 * @Description
 * 自定义通用Bean工具
 */
public class BeanCommonUtils {

    private final static Long DEFAULT_USER_ID = 1L;
    private final static String DEFAULT_NAME = "system";


    /**
     * 设置默认字段值
     * @param entity 实体对象
     * @param isUpdate 是否属于更新
     * @return
     * @param <T>
     */
    public static <T> void setDefaultField(T entity, boolean isUpdate) {
        if (entity == null || ReflectionKit.isPrimitiveOrWrapper(entity.getClass()) || entity == String.class) {
            return;
        }
        // 获得当前登录用户信息，不存在则设置默认值
        UserLoginInfoDTO userLoginInfo = Optional.ofNullable(UserSessionHelp.getUser()).orElse(
                UserLoginInfoDTO.builder().id(DEFAULT_USER_ID).name(DEFAULT_NAME).build());
        BaseEntityDTO baseEntityDTO = new BaseEntityDTO();
        baseEntityDTO.setUpdateById(userLoginInfo.getId());
        baseEntityDTO.setUpdateByName(userLoginInfo.getName());
        baseEntityDTO.setUpdateTime(LocalDateTime.now());
        if (!isUpdate) {
            baseEntityDTO.setCreateById(userLoginInfo.getId());
            baseEntityDTO.setCreateByName(userLoginInfo.getName());
            baseEntityDTO.setCreateTime(LocalDateTime.now());
            baseEntityDTO.setOrgId(100L);
        }
        BeanUtil.copyProperties(baseEntityDTO, entity);
    }
}

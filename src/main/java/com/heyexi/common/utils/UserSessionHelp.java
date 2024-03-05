package com.heyexi.common.utils;

import com.heyexi.common.domain.dto.UserLoginInfoDTO;

/**
 * @Author heyexi
 * @Date 2023-12-28 22:26:15
 * @Description 用户信息获取工具类
 */
public class UserSessionHelp {

    /**
     * 获取用户登录信息
     *
     * @return
     */
    public static UserLoginInfoDTO getUser() {
        return UserLoginInfoDTO.builder().id(1L).name("heyexi").build();
    }

}

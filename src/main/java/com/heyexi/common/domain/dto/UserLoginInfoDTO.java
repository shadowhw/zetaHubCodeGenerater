package com.heyexi.common.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author heyexi
 * @Date 2023-12-28 22:27:28
 * @Description 用户登录信息
 */
@Data
@Accessors(chain = true)
@Builder
public class UserLoginInfoDTO implements Serializable {

    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 登录时间
     */
    private String loginTime;

    /**
     * 登录Ip
     */
    private String loginIp;

    /**
     * 所属角色
     */
    private List<UserLoginRoleInfoDTO> roles;

    /**
     * 权限集
     */
    private List<String> perms;


    /**
     * 用户角色信息
     */
    @Data
    public static class UserLoginRoleInfoDTO implements Serializable {

        /**
         * 编号
         */
        private Long id;

        /**
         * 角色名
         */
        private String roleName;

        /**
         * 标题
         */
        private String title;

        /**
         * 创建时间
         */
        private LocalDateTime createTime;

        /**
         * 状态
         */
        private Integer status;
    }
}

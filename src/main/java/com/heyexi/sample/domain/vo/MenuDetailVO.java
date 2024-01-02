package com.heyexi.sample.domain.vo;

import lombok.experimental.Accessors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * @Author heyexi
 * @Date 2024-01-02 21:48:53
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class MenuDetailVO implements Serializable {

    /**
     * 编号
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 组件路径
     */
    private String url;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 菜单类型[DIAM_MENU_TYPE]
     */
    private String menuType;

    /**
     * 排序号
     */
    private Integer sortNum;

    /**
     * 图标
     */
    private String icon;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    private Long createById;

    /**
     * 创建人名称
     */
    private String createByName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人id
     */
    private Long updateById;

    /**
     * 更新人名称
     */
    private String updateByName;

    /**
     * 组织编码
     */
    private Long orgId;

    private Integer delFlag;

    private LocalDate testDate;

    private LocalDateTime testDateTime;

}

package com.heyexi.sample.domain.dto;

import lombok.experimental.Accessors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
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
public class MenuSaveOrUpdateDTO implements Serializable {

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate testDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime testDateTime;

}

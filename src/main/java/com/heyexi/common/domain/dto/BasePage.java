package com.heyexi.common.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author heyexi
 * @Date 2023-12-4 22:15:13
 * @Description
 * 默认分页
 */
@Data
public class BasePage implements Serializable {

    /**
     * 页码
     */
    private Integer pageIndex = 1;

    /**
     *
     */
    private Integer pageSize = 10;

}

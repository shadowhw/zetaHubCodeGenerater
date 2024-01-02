package com.heyexi.common.domain.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author heyexi
 * @Date 2023-12-25 23:37:42
 * @Description
 * 实体默认字段
 */
@Data
public class BaseEntityDTO implements Serializable {

    /**
     * 创建人id
     */
    private Long createById;

    /**
     * 创建人名称
     */
    private String createByName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    private Long updateById;

    /**
     * 更新人名称
     */
    private String updateByName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 组织编码
     */
    private Long orgId;

    /**
     * 是否删除
     */
    private Integer delFlag;

}

package com.heyexi.generater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author heyexi
 * @Date 2023-06-2 16:24:23
 * @Description 数据源配置
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceConfig implements Serializable {
    private String url;

    private String username;

    private String password;
}

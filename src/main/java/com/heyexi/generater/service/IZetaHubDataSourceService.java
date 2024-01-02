package com.heyexi.generater.service;

import java.util.List;

/**
 * @Author heyexi
 * @Date 2023-07-28 17:26:9
 * @Description 数据库连接工具
 */
public interface IZetaHubDataSourceService {

    /**
     * 批量查询数据
     *
     * @param sql    参数使用占位符形式
     * @param clazz  映射类
     * @param params 占位符参数
     * @param <T>
     * @return
     */
    <T> List<T> executeQueryList(String sql, Class<T> clazz, List<Object> params);


}

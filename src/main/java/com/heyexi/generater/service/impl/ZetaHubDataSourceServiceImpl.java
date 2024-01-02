package com.heyexi.generater.service.impl;

import com.heyexi.generater.service.CommonServiceAbstract;
import com.heyexi.generater.service.IZetaHubDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author heyexi
 * @Date 2023-07-28 17:23:55
 * @Description 数据库连接工具
 */
@Slf4j
public class ZetaHubDataSourceServiceImpl extends CommonServiceAbstract implements IZetaHubDataSourceService {

    private final String driverName;
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;
    private PreparedStatement statement;

    public ZetaHubDataSourceServiceImpl(String driverName, String url, String username, String password) {
        this.driverName = driverName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取连接会话
     *
     * @return
     */
    private Connection getConnection() {
        if (Objects.nonNull(connection)) {
            return connection;
        }
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * 关闭连接信息
     */
    private void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> List<T> executeQueryList(String sql, Class<T> clazz, List<Object> params) {
        List<T> resultList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            if (!CollectionUtils.isEmpty(params)) {
                for (int i = 0; i < params.size(); i++) {
                    statement.setObject(i + 1, params.get(i));
                }
            }
            log.info("executeQuery:{}", statement.toString());
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                T instance = clazz.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i).toLowerCase();
                    Object columnValue = resultSet.getObject(i);
                    String propertyName = underScoreCaseToCamelCase(columnName, false);
                    try {
                        java.lang.reflect.Field field = clazz.getDeclaredField(propertyName);
                        field.setAccessible(true);
                        field.set(instance, columnValue);
                    } catch (NoSuchFieldException e) {
                        log.warn("{} Unable to mapping", columnName);
                    }
                }
                resultList.add(instance);
            }
            return resultList;
        } catch (SQLException e) {
            log.error("An exception occurred when executing executeQuery");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

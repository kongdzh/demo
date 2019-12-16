package com.wonders.utils;

import config.JdbcConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
//
//    @Resource(name = "dataSource")
//    private DataSource dataSource;

    public Connection getThreadConnection() {
        //1、从ThreadLocal内获取线程上的连接
        Connection connection = tl.get();
        //2、判断是否存在连接
        if (connection == null) {
            try {
                //3、如果线程上没有连接，从数据源获取连接放入tl中
                connection = dataSource.getConnection();
                tl.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
                //e.printStackTrace();
            }
        }
        return connection;
    }
}

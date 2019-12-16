package com.wonders.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 事务相关管理工具类：开启事务，提交事务，回滚事务，释放连接
 */
@Component(value = "txManager")
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 获取连接
     * @throws SQLException
     */
    public void beginTransaction() throws SQLException {
        connectionUtils.getThreadConnection().setAutoCommit(false);
    }

    /**
     * 提交
     * @throws SQLException
     */
    public void commit() throws SQLException {
        connectionUtils.getThreadConnection().commit();
    }

    /**
     * 回滚
     * @throws SQLException
     */
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放连接
     * @throws SQLException
     */
    public void release() {
        try {
            connectionUtils.getThreadConnection().close(); // 将连接还回连接池中
            connectionUtils.removeConnection(); // 解绑连接
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

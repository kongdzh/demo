package com.wonders.utils;

/**
 * 事务相关管理工具类：开启事务，提交事务，回滚事务，释放连接
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void beginTransaction() {

    }

    public void commit() {

    }

    public void rollback() {

    }

    public void release() {

    }
}

package com.wonders.dao.Impl;

import com.wonders.beans.Account;
import com.wonders.dao.IAccountDao;
import com.wonders.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Resource(name = "queryRunner")
    private QueryRunner queryRunner;

    @Resource(name = "connectionUtils")
    private ConnectionUtils connectionUtils;

    public List<Account> findAllAccount() {
        try {
            return queryRunner.query(connectionUtils.getThreadConnection(),"select * from account", new BeanListHandler<Account>(Account.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account findAccountById(Integer id) {
        try {
            return queryRunner.query(connectionUtils.getThreadConnection(),"select * from account where id = ?", new BeanHandler<Account>(Account.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(),"insert into account(name, money) values(?, ?)", account.getName(), account.getMoney());
            System.out.println("保存成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(),"update account set name=?, money=? where id=?", account.getName(), account.getMoney(), account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(),"delete from account where id=?", account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account findAccountByName(String accountName) {
        try {
            List<Account> accounts = queryRunner.query(connectionUtils.getThreadConnection(),"select * from account where name = ?", new BeanListHandler<Account>(Account.class), accountName);
            if (accounts.size() == 0 || accounts == null) {
                return null;
            }
            if (accounts.size() > 1) {
                throw new RuntimeException("结果不唯一，数据有问题");
            }
            return accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

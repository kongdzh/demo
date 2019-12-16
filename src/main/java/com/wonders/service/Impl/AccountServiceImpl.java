package com.wonders.service.Impl;

import com.wonders.beans.Account;
import com.wonders.dao.Impl.AccountDaoImpl;
import com.wonders.service.IAccountService;
import com.wonders.utils.TransactionManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Resource(name = "accountDao")
    private AccountDaoImpl accountDao;

    @Resource(name = "txManager")
    private TransactionManager transactionManager;

    public List<Account> findAllAccount() {
        try {
            //1、开启事务
            transactionManager.beginTransaction();
            //2、执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3、提交事务
            transactionManager.commit();
            //4、返回结果
            return accounts;
        }catch (Exception e){
            //5、回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6、释放连接
            transactionManager.release();
        }
        //return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Account account) {
        accountDao.deleteAccount(account);
    }

    public void transfer(String sourceName, String targetName, Float money) {

        //1、根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2、根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //3、转出账户减钱
        source.setMoney(source.getMoney() - money);
        //4、转入账户加钱
        target.setMoney(target.getMoney() + money);
        //5、更新转出账户
        accountDao.updateAccount(source);
        //6、更新转入账户
        accountDao.updateAccount(target);
    }
}

package com.wonders.service.Impl;

import com.wonders.beans.Account;
import com.wonders.dao.Impl.AccountDaoImpl;
import com.wonders.service.IAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Resource(name = "accountDao")
    private AccountDaoImpl accountDao;

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
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
}

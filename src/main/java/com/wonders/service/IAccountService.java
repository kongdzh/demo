package com.wonders.service;

import com.wonders.beans.Account;

import java.util.List;

public interface IAccountService {

    /*
    查询所有
     */
    List<Account> findAllAccount();

    /*
    按Id查询一个
     */
    Account findAccountById(Integer id);

    /*
    保存
     */
    void saveAccount(Account account);

    /*
    更新
     */
    void updateAccount(Account account);

    /*
    删除
     */
    void deleteAccount(Account account);
}

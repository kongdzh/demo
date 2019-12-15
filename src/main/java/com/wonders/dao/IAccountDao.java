package com.wonders.dao;

import com.wonders.beans.Account;

import java.util.List;

public interface IAccountDao {

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

    Account findAccountByName(String accountName);
}

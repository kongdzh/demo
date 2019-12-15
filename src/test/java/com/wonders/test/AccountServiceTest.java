package com.wonders.test;

import com.wonders.beans.Account;
import com.wonders.service.Impl.AccountServiceImpl;
import config.SpringConfiguration;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AccountServiceTest {

    private ApplicationContext applicationContext;
    private AccountServiceImpl accountService;

    @Before
    public void init() {
        applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        accountService = (AccountServiceImpl) applicationContext.getBean("accountService");
    }

//    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//    private AccountServiceImpl accountService = (AccountServiceImpl) applicationContext.getBean("accountService");

    @Test
    public void testFindAll() {
        List<Account> accountList = accountService.findAllAccount();
        for (Account a : accountList) {
            System.out.println(a);
        }
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test");
        account.setMoney(20000f);

        accountService.saveAccount(account);
    }

    @Test
    public void testTransfer() {
        accountService.transfer("aaa", "bbb", 100f);
    }
}

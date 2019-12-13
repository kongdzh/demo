package com.wonders.test;

import com.wonders.beans.Account;
import com.wonders.service.Impl.AccountServiceImpl;
import config.SpringConfiguration;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AccountServiceTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

    @Test
    public void testFindAll() {
        AccountServiceImpl accountService = (AccountServiceImpl) applicationContext.getBean("accountService");
        List<Account> accountList = accountService.findAllAccount();
        for (Account a : accountList) {
            System.out.println(a);
        }
    }
}

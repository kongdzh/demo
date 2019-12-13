package com.wonders.test;

import config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
测试QueryRunner是否单例
 */
public class QueryRunnerTest {

    @Test
    public void testQueryRunner() {
        ApplicationContext  applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        QueryRunner queryRunner1 = applicationContext.getBean("queryRunner", QueryRunner.class);
        QueryRunner queryRunner2 = applicationContext.getBean("queryRunner", QueryRunner.class);

        System.out.println(queryRunner1 == queryRunner2);
    }
}

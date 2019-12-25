package com.wonders.aop;

import com.wonders.utils.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Aspect
public class AopUtils {

    @Resource(name = "txManager")
    private TransactionManager transactionManager;

    @Around(value = "execution(* com.wonders.service.Impl.AccountServiceImpl.*(..))")
    public Object transaction(ProceedingJoinPoint proceedingJoinPoint) {
        Object results = null;
        try {
            //1、开启事务
            transactionManager.beginTransaction();
            System.out.println("开启事务");
            //2、执行操作
            Object[] args = proceedingJoinPoint.getArgs();
            results = proceedingJoinPoint.proceed(args);
            System.out.println("执行操作");
            //3、提交事务
            transactionManager.commit();
            System.out.println("提交事务");
            //4、返回结果
            return results;
        } catch (Throwable throwable) {
            //5、回滚操作
            transactionManager.rollback();
            System.out.println("进入回滚");
            throw new RuntimeException(throwable);
        } finally {
            //6、释放连接
            transactionManager.release();
            System.out.println("释放连接");
        }
    }
}

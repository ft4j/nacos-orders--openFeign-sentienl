package com.example.aop.aop.aop2;

import com.example.aop.aop.ser.PpService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component    //声明这是一个组件
@Aspect       ///声明这是一个切面Bean
public class MyAspect {
    //定义切点
    @Pointcut("execution(* com.example.aop.aop.aop2.MyService.*(..))")
    public void sayingssssssss(){}

    @Before("sayingssssssss()")
    public void beforeeeee(){
        System.out.println("注解类型Before前置通知");
    }

    //后置通知  after的具体实现在catch里，必然会走
    @After("sayingssssssss()")
    public void afterrrrr(){
        System.out.println("注解类型After后置通知");
    }
    //环绕通知。注意要有ProceedingJoinPoint参数传入。
    @Around("sayingssssssss()")
    public void arounddddd(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("注解类型Around环绕通知..环绕前");
        pjp.proceed();//执行方法
        System.out.println("注解类型Around环绕通知..环绕后");
    }

    @AfterReturning("sayingssssssss()")
    public void afterReturninggggg(){
        System.out.println("这是AfterReturning返回切面");
    }

    @AfterThrowing("sayingssssssss()")
    public void  afterThrowinggggg(){
        System.out.println("这是AfterThrowing抛出异常切面");
    }
}

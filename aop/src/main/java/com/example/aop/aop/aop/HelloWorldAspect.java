package com.example.aop.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component    //声明这是一个组件
@Aspect       ///声明这是一个切面Bean
public class HelloWorldAspect {
    //定义切点
    @Pointcut("execution(* com.example.aop.aop.aop.HelloWorldService.*(..))")
    public void sayings(){}

    @Before("sayings()")
    public void sayHello(){
        System.out.println("注解类型前置通知");
    }

    //后置通知
    @After("sayings()")
    public void sayGoodbey(){
        System.out.println("注解类型后置通知");
    }
    //环绕通知。注意要有ProceedingJoinPoint参数传入。
    @Around("sayings()")
    public void sayAround(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("注解类型环绕通知..环绕前");
        pjp.proceed();//执行方法
        System.out.println("注解类型环绕通知..环绕后");
    }
}

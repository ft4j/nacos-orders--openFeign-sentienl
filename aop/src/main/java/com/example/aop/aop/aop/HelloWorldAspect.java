package com.example.aop.aop.aop;

import com.example.aop.aop.ser.PpService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component    //声明这是一个组件
@Aspect       ///声明这是一个切面Bean
@Order(1)//这个值越小，切面的优先级越高
public class HelloWorldAspect {
    //定义切点
    @Pointcut("execution(* com.example.aop.aop.aop.IHelloWorldService.*(..))")
    public void sayings(){}

    @Before("sayings()")
    public void sayHello(){
        System.out.println("注解类型Before前置通知");
    }

    //后置通知
    @After("sayings()")
    public void sayGoodbey(){
        System.out.println("注解类型After后置通知");
    }
    //环绕通知。注意要有ProceedingJoinPoint参数传入。
    @Around("sayings()")
    public void sayAround(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("注解类型Around环绕通知..环绕前");
        pjp.proceed();//执行方法
        System.out.println("注解类型Around环绕通知..环绕后");
    }

    @AfterReturning("sayings()")
    public void dfdfdf(){
        System.out.println("这是AfterReturning返回切面");
    }

    @AfterThrowing("sayings()")
    public void  sdsds(){
        System.out.println("这是AfterThrowing抛出异常切面");
    }
}

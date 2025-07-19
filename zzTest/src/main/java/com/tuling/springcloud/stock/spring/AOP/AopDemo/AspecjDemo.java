package com.tuling.springcloud.stock.spring.AOP.AopDemo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspecjDemo {
    @Pointcut("@annotation(com.tuling.springcloud.stock.spring.AOP.AopDemo.DemoAnnonation)")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(){
        System.out.println("这是before方法");
    }
}

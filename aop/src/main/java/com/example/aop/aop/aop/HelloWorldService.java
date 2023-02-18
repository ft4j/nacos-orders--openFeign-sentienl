package com.example.aop.aop.aop;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldService implements IHelloWorldService{
    public void sayHello() {
        System.out.println("你好！Spring AOP——（即这个为主要业务）");
    }
}

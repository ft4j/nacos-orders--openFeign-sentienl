package com.example.aop.aop.aop2;

import org.springframework.stereotype.Component;

@Component
public class MyServiceImpl implements MyService {
    public void sayHello() {
//        int s= 1/0;
        System.out.println("你好MyServiceImpl！这是主方法");
    }
}

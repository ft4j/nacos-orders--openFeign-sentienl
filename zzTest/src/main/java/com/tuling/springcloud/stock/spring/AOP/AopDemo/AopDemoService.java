package com.tuling.springcloud.stock.spring.AOP.AopDemo;

import org.springframework.stereotype.Service;

@Service
public class AopDemoService {
    @DemoAnnonation
    public void dddd(){
        System.out.println("被代理的方法打印啦");
    }

}

package com.tuling.springcloud.stock.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
//@Scope(value = "prototype")
public class ScopeBean {

    public void soutA(){
        System.out.println("AAA");
    }

    public void soutB(){
        System.out.println("BBB");
    }
    @Autowired
    private ApplicationContext applicationContext;
    @PostConstruct
    public void dsdsd(){
        System.out.println("ScopeBean+PostConstruct");
        Object adaptBean = applicationContext.getBean("adaptBean");
        System.out.println(adaptBean);
    }
}

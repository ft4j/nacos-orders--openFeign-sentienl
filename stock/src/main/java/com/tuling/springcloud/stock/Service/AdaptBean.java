package com.tuling.springcloud.stock.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AdaptBean {
    @Autowired
    ScopeBean scopeBean;
    public void cc(){
        System.out.println(scopeBean);
        scopeBean.soutA();
    }

    public void dd(){
        System.out.println(scopeBean);
        scopeBean.soutB();
    }

    @PostConstruct
    public void dsdsd(){
        System.out.println("AdaptBean+PostConstruct");
    }
}

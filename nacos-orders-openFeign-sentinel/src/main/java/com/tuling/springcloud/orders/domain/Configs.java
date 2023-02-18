package com.tuling.springcloud.orders.domain;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Configs {
    public String name;
    public void print(){
        System.out.println("ddd");
    }

    @PostConstruct
    public void dsdffdfd(){
        System.out.println("PostConstruct");
    }

    @PreDestroy
    public void dsfsdfsdf(){
        System.out.println("PreDestroy");
    }
}

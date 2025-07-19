package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 注解注入ZhuRuComponent对象
 */
public class ZhuRuAtBean {
    public ZhuRuAtBean(){}
    public ZhuRuAtBean(String name,String age){
        this.name = name;
        this.age = age;
    }
    private String name;
    private String age;

    @Override
    public String toString() {
        return "ZhuRuComponent{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

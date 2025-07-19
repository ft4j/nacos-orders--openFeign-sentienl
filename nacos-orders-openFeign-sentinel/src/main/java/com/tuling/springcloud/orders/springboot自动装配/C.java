package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 注解注入ZhuRuComponent对象
 */
public class C {
    @Value("李四C")
    private String name;
    @Value("16C")
    private String age;

    @Override
    public String toString() {
        return "ZhuRuComponent{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

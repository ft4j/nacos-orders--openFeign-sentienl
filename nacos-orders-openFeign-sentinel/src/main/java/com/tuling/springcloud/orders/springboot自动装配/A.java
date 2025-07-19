package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 注解注入ZhuRuComponent对象
 */
@Component
public class A {
    @Value("张三")
    private String name;
    @Value("15")
    private String age;
    @Autowired
    private B b;

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", b=" + b +
                '}';
    }
}

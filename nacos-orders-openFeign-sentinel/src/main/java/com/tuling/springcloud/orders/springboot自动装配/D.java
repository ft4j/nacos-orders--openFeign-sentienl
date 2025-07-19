package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.beans.factory.annotation.Value;

/**
 * 注解注入ZhuRuComponent对象
 */
public class D {
    @Value("李四D")
    private String name;
    @Value("16D")
    private String age;

    @Override
    public String toString() {
        return "ZhuRuComponent{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

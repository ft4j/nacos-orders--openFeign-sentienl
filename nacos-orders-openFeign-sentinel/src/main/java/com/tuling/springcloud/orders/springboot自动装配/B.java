package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 注解注入ZhuRuComponent对象
 */
@Component
public class B {
    @Value("李四")
    private String name;
    @Value("16")
    private String age;

    @Override
    public String toString() {
        return "ZhuRuComponent{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

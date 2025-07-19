package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@MyEnableAutoConfig
@Configuration
public class ConfigurationClass无注解 {
    @Bean
    public E e(){
        return new E("Ename","Eage");
    }
}

package com.tuling.springcloud.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Configuration(proxyBeanMethods = false)  这俩一个意思
@Component
@Scope("singleton")
public class ConfigFalse {
    @Bean
    public BeanBean beanBeanF(){
        return new BeanBean();
    }
}

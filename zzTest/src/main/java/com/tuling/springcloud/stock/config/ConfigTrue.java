package com.tuling.springcloud.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigTrue {
    @Bean
    public BeanBean beanBeanT(){
        return new BeanBean();
    }
}

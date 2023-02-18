package com.tuling.springcloud.orders.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {
    @Bean
    public Configs getConfigs(){
        return new Configs();
    }

}

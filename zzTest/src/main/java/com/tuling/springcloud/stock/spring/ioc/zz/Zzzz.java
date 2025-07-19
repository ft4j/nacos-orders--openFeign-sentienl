package com.tuling.springcloud.stock.spring.ioc.zz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class Zzzz {
    @Bean
    public Zzz zzz(){
        return new Zzz();
    }
}

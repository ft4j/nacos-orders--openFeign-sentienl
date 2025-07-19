package com.example.aop.aop.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
@Configuration
public class ConfigurationBean {
    @Bean
    public Coll coll(@Autowired Map<String,Abstracttt> abstracttt){
        Coll ll = new Coll();
        ll.setMap(abstracttt);
        return ll;
    }
}

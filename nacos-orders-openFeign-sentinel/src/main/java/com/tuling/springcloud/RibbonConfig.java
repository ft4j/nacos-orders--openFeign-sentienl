package com.tuling.springcloud;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {
    //这个方法也必须叫irule
//    @Bean
//    public IRule irule() {
//        return new RandomRule();
//    }
}

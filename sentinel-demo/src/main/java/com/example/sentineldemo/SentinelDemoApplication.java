package com.example.sentineldemo;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//这是一个sentinel没有整合到springcloudalibaba的项目
@SpringBootApplication
public class SentinelDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelDemoApplication.class, args);
    }
    //支持注解开发sentinel的注解bean
    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }
}

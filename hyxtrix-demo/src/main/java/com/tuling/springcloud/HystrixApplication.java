package com.tuling.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient //这个注解可加可不加，加了表明已经使用了nacos，不加也可以
@EnableFeignClients
public class HystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);

    }

    @Bean
    public ExecutorService pool(){
        ExecutorService ex = Executors.newFixedThreadPool(2
//                ,new ThreadFactory(){
//
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread
//                return null;
//            }
//        }
        );
        return ex;
    }
}

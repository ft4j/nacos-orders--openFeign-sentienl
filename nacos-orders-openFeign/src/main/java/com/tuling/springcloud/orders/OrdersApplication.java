package com.tuling.springcloud.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

//    @Bean
//    @LoadBalanced//当服务被注册到nacos中之后，在互相调用需要加上这个负载均衡的注解
//    public RestTemplate restTemplate(RestTemplateBuilder builder){
//        RestTemplate restTemplate = builder.build();
//        return restTemplate;
//    }
}

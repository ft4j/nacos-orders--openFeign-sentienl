package com.tuling.springcloud.orders;

import com.tuling.springcloud.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//这样可以通过ribbon配置具体的负载均衡策略。name指定了服务，configuration指定了配置类，这个配置类会返回一个Irule类的实现类
//这里可以配置多个服务的具体的负载均衡策略，如果不想个性化配置，那么可以将配置类写在可以被@ComponentScan扫描到的地方
//@RibbonClients(value = {
//        @RibbonClient(name = "nacos-stock",configuration = RibbonConfig.class)
//})
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

    @Bean
    @LoadBalanced//当服务被注册到nacos中之后，在互相调用需要加上这个负载均衡的注解
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }
}

package com.tuling.springcloud.orders;

import com.netflix.loadbalancer.IRule;
import com.tuling.springcloud.MyRibbon;
import org.checkerframework.checker.units.qual.C;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
//@MapperScan("com.tuling.springcloud.orders.domain")
public class OrdersFeignSentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersFeignSentinelApplication.class, args);
    }

//    @Bean
//    @LoadBalanced//当服务被注册到nacos中之后，在互相调用需要加上这个负载均衡的注解
//    public RestTemplate restTemplate(RestTemplateBuilder builder){
//        RestTemplate restTemplate = builder.build();
//        return restTemplate;
//    }

    @Bean
    public IRule irule(){
        return new MyRibbon();
    }

    @Bean
    public Redisson redisson(){
        //此处的redisson设置为单击模式
        Config config = new Config();
        //设置为本地地址，使用第一个redis库
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        return (Redisson)Redisson.create(config);
    }
}

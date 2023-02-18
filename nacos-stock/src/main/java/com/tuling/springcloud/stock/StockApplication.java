package com.tuling.springcloud.stock;

import org.checkerframework.checker.units.qual.C;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@EnableDiscoveryClient //这个注解可加可不加，加了表明已经使用了nacos，不加也可以
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }

    //初始化redisson客户端
    @Bean
    public Redisson redisson(){
        //单机模式下的redisson
        Config config = new Config();
        //设置redisson地址和使用哪个库
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        return (Redisson)Redisson.create(config);
    }
}

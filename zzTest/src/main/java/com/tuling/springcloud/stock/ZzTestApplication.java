package com.tuling.springcloud.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import out.OutBean;

@SpringBootApplication
@MapperScan("com.tuling.springcloud.stock.spring.transaction.mapper")
public class ZzTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ZzTestApplication.class, args);
    }
}

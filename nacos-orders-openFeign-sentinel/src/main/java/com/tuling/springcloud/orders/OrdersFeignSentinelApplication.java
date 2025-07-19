package com.tuling.springcloud.orders;

import com.netflix.loadbalancer.IRule;
import com.tuling.springcloud.MyRibbon;
import com.tuling.springcloud.orders.springboot自动装配.ImportBean;
import com.tuling.springcloud.orders.springboot自动装配.MyEnableAutoConfig;
import com.tuling.springcloud.orders.springboot自动装配.MyEnableAutoRegisterConfig;
import com.tuling.springcloud.orders.springboot自动装配.ZhuRuAtBean;
import org.checkerframework.checker.units.qual.C;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableTransactionManagement
@MyEnableAutoConfig
@MyEnableAutoRegisterConfig
//@MapperScan("com.tuling.springcloud.orders.domain")
public class OrdersFeignSentinelApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(OrdersFeignSentinelApplication.class, args);
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

    //初始化Redisson
    @Bean
    public Redisson redisson(){
        //此处的redisson设置为单击模式
        Config config = new Config();
        //设置为本地地址，使用第一个redis库
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
//        config.setLockWatchdogTimeout(300000);//设置默认的加锁时间，着玩意用来给watchDog进行锁续命的时候读取配置
        return (Redisson)Redisson.create(config);
    }

    @Bean
    public ZhuRuAtBean zhuRuAtBean(){
        return new ZhuRuAtBean("王五","120");
    }

    @Bean
    @LoadBalanced//当服务被注册到nacos中之后，在互相调用需要加上这个负载均衡的注解
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }
}

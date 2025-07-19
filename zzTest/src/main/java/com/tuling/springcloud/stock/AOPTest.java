package com.tuling.springcloud.stock;

import com.tuling.springcloud.stock.spring.AOP.AopDemo.AopDemoService;
import com.tuling.springcloud.stock.spring.ioc.bean.MyAwareBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

@EnableAspectJAutoProxy
@ComponentScan("com.tuling.springcloud.stock.*")
public class AOPTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext aa = new AnnotationConfigApplicationContext();
        AopDemoService bean = aa.getBean(AopDemoService.class);
        bean.dddd();
    }
}

package com.tuling.springcloud.stock.spring.AOP;

import com.tuling.springcloud.stock.spring.AOP.AopDemo.AopDemoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@EnableAspectJAutoProxy
//@ComponentScan("com.tuling.springcloud.stock.*")
public class AOPTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext aa = new AnnotationConfigApplicationContext();
        AopDemoService bean = aa.getBean(AopDemoService.class);
        bean.dddd();
    }
}

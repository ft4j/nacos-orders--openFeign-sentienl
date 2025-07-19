package com.tuling.springcloud;

import com.tuling.springcloud.orders.domain.ConfigBean;
import com.tuling.springcloud.orders.domain.Configs;
import com.tuling.springcloud.orders.乱写包.IOCTestt;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {
    @Test
    public void sdsd(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(ConfigBean.class);
        annotationConfigApplicationContext.registerShutdownHook();
        Object configs = annotationConfigApplicationContext.getBean(Configs.class);
        System.out.println(configs);
    }


    @Test
    public void sfdsfggggg(){
        IOCTestt tt = new IOCTestt();
        tt.ddddx(System.out::println);
    }
}

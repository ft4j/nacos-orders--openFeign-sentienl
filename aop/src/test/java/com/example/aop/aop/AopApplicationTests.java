package com.example.aop.aop;

import com.example.aop.aop.aop.IHelloWorldService;
import com.example.aop.aop.aop2.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopApplicationTests {

    @Autowired
    IHelloWorldService helloWorldService;
    @Test
    void contextLoads() throws InterruptedException {
        Thread.sleep(5000);
        helloWorldService.sayHello();
    }

    @Autowired
    MyService myService;
    @Test
    void contextLoadssss() throws InterruptedException {
//        Thread.sleep(5000);
        myService.sayHello();
    }
}

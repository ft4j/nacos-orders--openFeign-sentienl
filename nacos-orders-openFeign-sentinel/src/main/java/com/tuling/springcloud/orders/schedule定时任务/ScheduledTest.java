package com.tuling.springcloud.orders.schedule定时任务;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTest {

//    @Scheduled(initialDelay = 1000,fixedDelay = 3000)
    public void dd(){
        System.out.println("我正在定时执行任务"+new Date());
    }
}

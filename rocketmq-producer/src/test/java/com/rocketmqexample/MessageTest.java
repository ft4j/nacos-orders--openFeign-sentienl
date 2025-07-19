package com.rocketmqexample;

import com.rocketmqexample.rocket.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ProducerApplication.class)
@RunWith(SpringRunner.class)
public class MessageTest {
    @Autowired
    ProducerService producerService;
    @Test
    public void 发送普通消息(){
        producerService.发送普通消息();
    }

    @Test
    public void 发生带key的消息(){
        producerService.sendSimpleHeaderMessage();
    }

    @Test
    public void 测试相同topic不同消费者组(){
        producerService.测试相同topic不同消费者组();
    }

    @Test
    public void 发送多个简单普通消息(){
        producerService.sendSimpleMessage();
    }

    @Test
    public void 发送sql92筛选的消息(){
        producerService.sql92();
    }

    @Test
    public void 发送List对象(){
        producerService.发送List对象();
    }

    @Test
    public void fasong(){
        producerService.sendGroupFirst_topic2();
    }

    @Test
    public void 一次发两个消息(){
        producerService.syncMessage();
    }

    @Test
    public void 顺序消息(){
        producerService.sendOrderly();
    }

    @Test
    public void 批量消息(){
        producerService.发送批量消息();
    }


    @Test
    public void 发送事务消息(){
        producerService.transactionMessage();
    }

    @Test
    public void 发送异步消息(){
        producerService.sendAsyncMessage();
    }

    @Test
    public void 测试springBeanDefinationProcessor(){
        System.out.println("dddddd");
    }

}

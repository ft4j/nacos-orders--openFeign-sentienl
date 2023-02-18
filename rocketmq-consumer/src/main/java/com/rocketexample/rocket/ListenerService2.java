package com.rocketexample.rocket;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "g2", topic = "ccc")
public class ListenerService2 implements RocketMQListener<String> {

    public void onMessage(String o) {
        System.out.println("g2"+o);
    }
}

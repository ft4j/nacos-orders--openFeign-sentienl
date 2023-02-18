package com.rocketexample.rocket;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "g1", topic = "ccc")
public class ListenerService implements RocketMQListener<String> {

    public void onMessage(String o) {
        System.out.println("g1"+o);
    }
}

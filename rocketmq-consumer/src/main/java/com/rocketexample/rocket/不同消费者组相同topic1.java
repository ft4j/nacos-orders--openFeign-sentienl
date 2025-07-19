package com.rocketexample.rocket;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 顺序消息的消费者
 */
@Service
@RocketMQMessageListener(consumerGroup = "g22x", topic = "sameTopicDiffConsumerGroup")
public class 不同消费者组相同topic1 implements RocketMQListener<String> {

    public void onMessage(String o) {
        System.out.println("不同消费者组相同topic1："+o);
    }
}

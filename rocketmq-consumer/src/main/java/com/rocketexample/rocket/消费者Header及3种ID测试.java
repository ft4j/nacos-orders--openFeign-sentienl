package com.rocketexample.rocket;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 顺序消息的消费者
 */
@Service
@RocketMQMessageListener(consumerGroup = "HeaderAnd3Id", topic = "getHeaderAndId")
public class 消费者Header及3种ID测试 implements RocketMQListener<String> {

    public void onMessage(String o) {
        System.out.println("不同消费者组相同topic2："+o);
    }
}

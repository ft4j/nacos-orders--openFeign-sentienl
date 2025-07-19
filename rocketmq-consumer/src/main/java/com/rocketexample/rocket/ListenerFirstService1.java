package com.rocketexample.rocket;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "zxczzz", topic = "firstSend")
public class ListenerFirstService1 implements RocketMQListener<String> {

    public void onMessage(String o) {

        System.out.println("只获取rocketMq的消息体部分::::::"+o);
    }
}

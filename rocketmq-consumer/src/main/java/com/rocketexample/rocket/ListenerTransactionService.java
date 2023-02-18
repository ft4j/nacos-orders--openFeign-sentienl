package com.rocketexample.rocket;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 顺序消息的消费者
 */
@Service
@RocketMQMessageListener(consumerGroup = "g3", topic = "transaction")
public class ListenerTransactionService implements RocketMQListener<MessageBody> {
    /**
     * 这个泛型可以跟生产者一样，就能直接接收到实体类数据
     * @param o
     */
    public void onMessage(MessageBody o) {
        System.out.println("g3"+o);
    }
}

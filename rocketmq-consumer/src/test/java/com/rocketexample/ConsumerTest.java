package com.rocketexample;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.util.List;

public class ConsumerTest {
    @Test
    public void dd(){
        System.out.println("dd");
    }


    @Test
    public void pullTest() throws MQClientException {
        //设置一个push消费者，push_group为当前消费者的消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("source_push_consumer_group");
        //设置nameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        //订阅对应的topic和tag
        consumer.subscribe("push_topic","*");
        consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context)->{
            for (MessageExt msg : msgs) {
                System.out.println(msg.getBody());
            }
            System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();

    }

    @Test
    public void sds() throws MQClientException {
        // 初始化consumer，并设置consumer group name
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("source_push_consumer_group");

        // 设置NameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        //订阅一个或多个topic，并指定tag过滤条件，这里指定*表示接收所有tag的消息
        consumer.subscribe("push_topic", "*");
        //注册回调接口来处理从Broker中收到的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 返回消息消费状态，ConsumeConcurrentlyStatus.CONSUME_SUCCESS为消费成功
                for (MessageExt msg : msgs) {
                    System.out.println(msg.getBody());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动Consumer
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}

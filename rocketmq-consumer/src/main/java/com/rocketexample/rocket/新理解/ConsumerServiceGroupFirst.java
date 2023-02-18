package com.rocketexample.rocket.新理解;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * 这是一个存在于消费组GroupFirst中的一个消费者，消费的topic为GroupFirst_topic
 */
@Component
@RocketMQMessageListener(topic = "GroupFirst_topic",consumerGroup = "GroupFirst")
//tag过滤：selectorType = SelectorType.TAG,selectorExpression="tag1||tag2"
//广播模式和集群模式:messageModel = MessageModel.BROADCASTING
/**
 * 注意哦，rocketMq的消费者是并发消费的，即使消息都在一个queue，被同一台服务消费也可能出现顺序不一致，所以需要这个参数保证消息的顺序消费
 */
//该模式可以实现顺序消费：consumeMode = ConsumeMode.ORDERLY
public class ConsumerServiceGroupFirst implements RocketMQListener<String>
{

    @Override
    public void onMessage(String s) {
        System.out.println(s+"消费者本体");
    }

//    @SneakyThrows
//    @Override
//    public void prepareStart(DefaultMQPushConsumer consumer) {
//        consumer.subscribe("GroupFirst_topic","tag2");
//        consumer.subscribe("GroupFirst_topiccc","tag2");
//        consumer.registerMessageListener((List<MessageExt> messages, ConsumeConcurrentlyContext context)->{
//            for (MessageExt message : messages) {
//                System.out.println(message.getTopic() + ":"+ message.getTags() + ":" +message.getBody());
//            }
//            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//        });
//    }
}

package com.rocketexample.rocket.新理解;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 这是一个存在于消费组GroupFirst中的一个消费者，消费的topic为GroupFirst_topic
 */
@Component
@RocketMQMessageListener(topic = "GroupFirst_topic1",consumerGroup = "GroupFirst1")
public class ConsumerServiceGroupFirst分身 implements RocketMQListener<String>
//        , RocketMQPushConsumerLifecycleListener
{

    @Override
    public void onMessage(String s) {
        System.out.println(s+"消费者分身");
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

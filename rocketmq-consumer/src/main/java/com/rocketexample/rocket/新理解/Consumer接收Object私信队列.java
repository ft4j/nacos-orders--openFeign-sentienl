package com.rocketexample.rocket.新理解;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 这是一个存在于消费组GroupFirst中的一个消费者，消费的topic为GroupFirst_topic
 */
@Component                         //GroupFirst2值得是原消费者组
@RocketMQMessageListener(topic = "%DLQ%GroupFirst2",consumerGroup = "DqlGroupFirst2")
public class Consumer接收Object私信队列 implements RocketMQListener<MessageExt>
, RocketMQPushConsumerLifecycleListener
{

    @Override
    public void onMessage(MessageExt me) {
        //发过来的消息
        String s = new String(me.getBody());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        System.out.println(format);
        System.out.println("ddd");
        System.out.println(s+"消费者分身");
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 设置消费者重试次数
        consumer.setMaxReconsumeTimes(2);
        // 实例名称-控制面板可以看到
        consumer.setInstanceName("消费者1号");
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

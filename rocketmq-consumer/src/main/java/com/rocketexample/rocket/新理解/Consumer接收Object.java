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
@Component
@RocketMQMessageListener(topic = "GroupFirst_topic2",consumerGroup = "GroupFirst2")
public class Consumer接收Object implements RocketMQListener<MessageExt>
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
        int sssss = 1/0;
        System.out.println(sssss);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 设置消费者重试次数
        consumer.setMaxReconsumeTimes(2);
        // 实例名称-控制面板可以看到
        consumer.setInstanceName("消费者1号");
    }
}

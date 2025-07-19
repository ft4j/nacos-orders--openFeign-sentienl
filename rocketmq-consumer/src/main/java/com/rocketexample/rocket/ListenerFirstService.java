package com.rocketexample.rocket;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "zxc", topic = "firstSend")
public class ListenerFirstService implements RocketMQListener<MessageExt> {

    public void onMessage(MessageExt o) {

        String msgId = o.getMsgId();
        String s = o.getProperties().get("UNIQ_KEY");
        String keys = o.getProperties().get(RocketMQHeaders.KEYS);
        String messageId = o.getProperties().get(RocketMQHeaders.MESSAGE_ID);
        System.out.println("UNIQ_KEY："+s);
        System.out.println("msgId："+msgId);
        System.out.println("keys："+keys);
        System.out.println("messageId："+messageId);
    }
}

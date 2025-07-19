package com.rocketexample.rocket;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "g1", topic = "ccc")
public class ListenerService implements RocketMQListener<MessageExt> {

    public void onMessage(MessageExt o) {
        String msgId = o.getMsgId();
        String s = o.getProperties().get("UNIQ_KEY");

        System.out.println("UNIQ_KEY:"+s);
        System.out.println("g1"+o);
    }
}

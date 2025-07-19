package com.rocketmqexample.rocket;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

@Component
@ExtRocketMQTemplateConfiguration(group = "kkkkk")
public class MyRocketMqTemplate extends RocketMQTemplate {
}

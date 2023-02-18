package com.rocketmqexample.rocket;

import lombok.Data;

@Data
public abstract class MessageBodyInterface {
    private String name;
    private String age;
    private String key;
    private MessageData messageData;
}

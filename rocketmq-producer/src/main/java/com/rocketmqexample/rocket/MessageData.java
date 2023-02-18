package com.rocketmqexample.rocket;

import lombok.Data;

@Data
public class MessageData {
    public MessageData(String data,String num){
        this.data = data;
        this.num = num;
    }
    private String data;
    private String num;
}

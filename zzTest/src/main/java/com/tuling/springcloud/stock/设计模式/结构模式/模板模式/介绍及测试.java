package com.tuling.springcloud.stock.设计模式.结构模式.模板模式;


import org.junit.Test;

/**
 * spring中的各种template就是模板模式
 * 封装不可变的部分，实现可变的部分
 * rocketMQTemplate.convertAndSend("firstSend", "hello world" + i);
 * 它提供了各种模板方法，使用者只管调用，不需要知道具体的实现  比如rocketMQTemplate.convertAndSend
 * 它值需要你告诉它发送的topic和消息体 他就发送出了，完全不考虑自己的组装一个Message
 * 简化了API的操作
 */
public class 介绍及测试 {
    @Test
    public void  dsdsdsd(){
        Game g = new Football();
        g.play();
    }
}

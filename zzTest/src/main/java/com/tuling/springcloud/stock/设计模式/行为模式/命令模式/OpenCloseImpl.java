package com.tuling.springcloud.stock.设计模式.行为模式.命令模式;

public class OpenCloseImpl implements Device {
    @Override
    public void volumeUp() {

    }

    @Override
    public void volumeDown() {

    }

    @Override
    public void channelUp() {

    }

    @Override
    public void channelDown() {

    }

    @Override
    public void on() {
        System.out.println("开关实现：开灯");
    }

    @Override
    public void off() {
        System.out.println("开关实现：关灯");
    }
}

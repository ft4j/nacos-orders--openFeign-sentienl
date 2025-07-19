package com.tuling.springcloud.stock.设计模式.行为模式.命令模式;

public class ChannelSwitchImpl implements Device {
    @Override
    public void volumeUp() {

    }

    @Override
    public void volumeDown() {

    }

    @Override
    public void channelUp() {
        System.out.println("开关实现：挡位增加+");
    }

    @Override
    public void channelDown() {
        System.out.println("开关实现：挡位减少-");
    }

    @Override
    public void on() {
        System.out.println("开关实现：风扇打开");
    }

    @Override
    public void off() {
        System.out.println("开关实现：风扇关闭");
    }
}

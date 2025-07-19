package com.tuling.springcloud.stock.设计模式.行为模式.命令模式;

public class DeviceImpl implements Device {
    @Override
    public void volumeUp() {
        System.out.println("音量++");
    }

    @Override
    public void volumeDown() {
        System.out.println("音量--");
    }

    @Override
    public void channelUp() {
        System.out.println("开关实现：频道增加+");
    }

    @Override
    public void channelDown() {
        System.out.println("开关实现：频道减少-");
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

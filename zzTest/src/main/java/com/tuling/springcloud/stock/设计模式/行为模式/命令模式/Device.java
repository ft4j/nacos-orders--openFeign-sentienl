package com.tuling.springcloud.stock.设计模式.行为模式.命令模式;

public interface Device{
    void on();//开
    void off();//关
    void channelUp(); // 频道+或是换挡或是切换模式
    void channelDown(); // 频道-或是减挡或是切换模式
    void volumeUp();//音量+
    void volumeDown();//音量-
}

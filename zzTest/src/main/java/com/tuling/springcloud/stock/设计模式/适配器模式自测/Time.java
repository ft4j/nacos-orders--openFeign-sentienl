package com.tuling.springcloud.stock.设计模式.适配器模式自测;

public class Time implements Radio{
    @Override
    public void play() {

    }

    @Override
    public void time() {
        System.out.println("现在是北京时间22：00");
    }
}

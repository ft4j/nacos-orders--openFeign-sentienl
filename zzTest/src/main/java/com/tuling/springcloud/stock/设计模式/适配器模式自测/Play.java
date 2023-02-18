package com.tuling.springcloud.stock.设计模式.适配器模式自测;

public class Play implements Radio{
    @Override
    public void play() {
        System.out.println("播放了一首千里之外");
    }

    @Override
    public void time() {

    }
}

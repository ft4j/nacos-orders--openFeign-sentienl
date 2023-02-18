package com.tuling.springcloud.stock.设计模式.适配器模式自测;

import org.junit.Test;

public class 手机测试 {
    @Test
    public void dd(){
        Phone p = new IPhone();
        p.use("Time");
        p.use("Play");
        p.use("hhhhhhh");
    }
}

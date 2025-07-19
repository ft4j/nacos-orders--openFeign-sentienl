package com.tuling.springcloud.stock.设计模式.结构模式.代理模式;

public class 客户 implements 房地产{


    @Override
    public void 买房子() {
        System.out.println("客户付钱");
    }
}

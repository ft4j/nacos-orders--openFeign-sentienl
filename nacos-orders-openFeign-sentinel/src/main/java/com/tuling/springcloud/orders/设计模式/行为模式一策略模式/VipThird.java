package com.tuling.springcloud.orders.设计模式.行为模式一策略模式;

public class VipThird implements Vip {
    @Override
    public Double totalPrice(Integer num, Integer price) {
        return num * price * 0.8;
    }
}

package com.tuling.springcloud.orders.设计模式.行为模式一策略模式;

public class Context {
    private Vip vip;
    public Context(Vip vip){
        this.vip = vip;
    }

    public Double getTotalPrice(int num,int price){
        return vip.totalPrice(num,price);
    }
}

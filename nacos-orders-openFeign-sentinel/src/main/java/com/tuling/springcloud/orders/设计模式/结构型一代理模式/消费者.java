package com.tuling.springcloud.orders.设计模式.结构型一代理模式;

public class 消费者 implements BuyHouse{
    public void buy(){
        System.out.println("付钱，我要买房子");
    }
}

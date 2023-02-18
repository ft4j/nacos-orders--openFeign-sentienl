package com.tuling.springcloud.orders.设计模式.结构型一代理模式;

public class 中介 implements BuyHouse{
    消费者 消费者 = new 消费者();
    public 中介(消费者 消费者){
        this.消费者 = 消费者;
    }
    public void preBuy(){
        System.out.println("看房");
    }

    public void after(){
        System.out.println("签合同");
    }


    public void buy(){
        preBuy();
        消费者.buy();
        after();
    }

}

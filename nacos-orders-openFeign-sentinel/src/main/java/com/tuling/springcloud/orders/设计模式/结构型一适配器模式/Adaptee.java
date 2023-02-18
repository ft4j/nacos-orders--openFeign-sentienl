package com.tuling.springcloud.orders.设计模式.结构型一适配器模式;

/**
 * 被适配对象
 */
public class Adaptee {
    public void fa(){
        System.out.println("fa方法");
    }

    public void fb(){
        System.out.println("fb方法");
    }

    //这个方法在Target中也存在
    public void fc(){
        System.out.println("fc方法");
    }
}

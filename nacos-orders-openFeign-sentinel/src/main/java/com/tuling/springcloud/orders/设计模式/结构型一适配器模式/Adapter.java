package com.tuling.springcloud.orders.设计模式.结构型一适配器模式;

public class Adapter extends Adaptee implements Target {
    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        System.out.println("f2方法");
    }

}

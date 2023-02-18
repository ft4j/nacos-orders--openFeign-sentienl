package com.tuling.springcloud.orders.设计模式.工厂模式.B工厂方法模式;

public class Bmw530Factory implements FactoryBmw {
    @Override
    public BmwB createBmw() {
        return new BmwB530();
    }
}

package com.tuling.springcloud.stock.设计模式.创建模式.建造者模式;

public class ConCreateBuilder extends AbstractBuilder {

    @Override
    public void buildPartA() {
        bp.setPartA("A部分在ConCreateBuilder中被设值");
    }

    @Override
    public void buildPartB() {
        bp.setPartB("B部分在ConCreateBuilder中被设值");
    }

    @Override
    public void buildPartC() {
        bp.setPartC("C部分在ConCreateBuilder中被设值");
    }
}

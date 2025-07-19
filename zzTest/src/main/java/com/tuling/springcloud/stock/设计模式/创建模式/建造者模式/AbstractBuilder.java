package com.tuling.springcloud.stock.设计模式.创建模式.建造者模式;

abstract class AbstractBuilder {
    protected BuildedProduct bp = new BuildedProduct();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();

    public BuildedProduct getResult(){
        return bp;
    }
}

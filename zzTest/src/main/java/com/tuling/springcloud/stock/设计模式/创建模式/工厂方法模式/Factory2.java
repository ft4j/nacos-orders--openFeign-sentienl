package com.tuling.springcloud.stock.设计模式.创建模式.工厂方法模式;

public class Factory2 implements Factory{

    @Override
    public Product createProduct() {
        return new Product2();
    }
}

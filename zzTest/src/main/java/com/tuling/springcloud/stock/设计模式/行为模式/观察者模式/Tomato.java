package com.tuling.springcloud.stock.设计模式.行为模式.观察者模式;

public class Tomato implements Food {
    @Override
    public void eaten() {
        System.out.println("我吃西红柿了");
    }
}

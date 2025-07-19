package com.tuling.springcloud.stock.设计模式.结构模式.外观模式;

public class Facade {
    private InterfaceA a = new InterfaceA();
    private InterfaceB b = new InterfaceB();
    public void method(){
        a.aM();
        b.bM();
    }
}

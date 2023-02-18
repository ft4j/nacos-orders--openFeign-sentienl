package com.tuling.springcloud.orders.设计模式.结构型一适配器模式;

/**
 * 这个接口是适配器需要适配的接口，别的对象将被转化为适配这个对象的对象
 */
public interface Target {
    void f1();
    void f2();
    //这个方法在adaptee中也存在
    void fc();
}

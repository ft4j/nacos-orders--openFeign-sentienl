package com.tuling.springcloud.stock.设计模式.创建模式.单例模式.线程安全的单例;

public class HungerSingleton饿汉线程安全 {
    private HungerSingleton饿汉线程安全(){

    }
    private static HungerSingleton饿汉线程安全 hungerSingleton = new HungerSingleton饿汉线程安全();
    public static HungerSingleton饿汉线程安全 getHungerSingleton(){
        return hungerSingleton;
    }
}

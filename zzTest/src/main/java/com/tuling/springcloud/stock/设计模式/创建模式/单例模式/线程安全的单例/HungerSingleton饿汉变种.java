package com.tuling.springcloud.stock.设计模式.创建模式.单例模式.线程安全的单例;

public class HungerSingleton饿汉变种 {
    private HungerSingleton饿汉变种(){

    }
    private static HungerSingleton饿汉变种 hungerSingleton = null;
    static{
        hungerSingleton = new HungerSingleton饿汉变种();
    }
    public static HungerSingleton饿汉变种 getHungerSingleton(){
        return hungerSingleton;
    }
}

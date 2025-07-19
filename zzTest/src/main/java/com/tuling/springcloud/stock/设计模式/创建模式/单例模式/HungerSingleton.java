package com.tuling.springcloud.stock.设计模式.创建模式.单例模式;

public class HungerSingleton {
    private HungerSingleton(){

    }
    private static HungerSingleton hungerSingleton = new HungerSingleton();
    public static HungerSingleton getHungerSingleton(){
        return hungerSingleton;
    }
}

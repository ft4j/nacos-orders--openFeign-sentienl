package com.tuling.springcloud.orders.设计模式.单例模式;

public class LanhanSingleton {
    private LanhanSingleton(){}

    private static LanhanSingleton lanhanSingleton = null;

    public static LanhanSingleton getLanhanSingleton(){
        if(lanhanSingleton == null){
            lanhanSingleton = new LanhanSingleton();
        }
        return lanhanSingleton;
    }
}

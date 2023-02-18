package com.tuling.springcloud.orders.设计模式.单例模式;

public class EhanSingleton {
    private EhanSingleton(){}
    private static EhanSingleton ehanSingleton = new EhanSingleton();

    public static EhanSingleton getEhanSingleton(){
        return ehanSingleton;
    }

}

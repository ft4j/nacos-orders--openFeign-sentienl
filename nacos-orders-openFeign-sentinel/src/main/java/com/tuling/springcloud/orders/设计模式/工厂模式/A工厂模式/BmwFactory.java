package com.tuling.springcloud.orders.设计模式.工厂模式.A工厂模式;

public class BmwFactory {

    public static Bmw createCar(String type){
        if("330".equals(type)){
            return new Bmw330();
        }else if("530".equals(type)){
            return new Bmw530();
        }
        return null;
    }

}

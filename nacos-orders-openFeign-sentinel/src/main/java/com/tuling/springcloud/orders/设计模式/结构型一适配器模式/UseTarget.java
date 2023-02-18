package com.tuling.springcloud.orders.设计模式.结构型一适配器模式;

public class UseTarget {
    private Target target;
    public UseTarget(Target target){
        this.target = target;
    }
    public void use(){
        target.f1();
    }
}

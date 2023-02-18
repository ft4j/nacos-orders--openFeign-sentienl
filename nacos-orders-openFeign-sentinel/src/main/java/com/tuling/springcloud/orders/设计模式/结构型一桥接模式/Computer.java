package com.tuling.springcloud.orders.设计模式.结构型一桥接模式;

public abstract class Computer {
    public Computer(Brand brand){
        this.brand = brand;
    }
    //电脑品牌
    private Brand brand;

    public String brandName(){
        return brand.brandName();
    }

    public abstract void showInfo();

}

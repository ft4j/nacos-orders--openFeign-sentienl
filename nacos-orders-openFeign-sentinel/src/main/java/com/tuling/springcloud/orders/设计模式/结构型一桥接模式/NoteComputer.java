package com.tuling.springcloud.orders.设计模式.结构型一桥接模式;

public class NoteComputer extends Computer {

    public NoteComputer(Brand brand) {
        super(brand);
    }

    @Override
    public void showInfo() {
        System.out.println(super.brandName()+"笔记本电脑");
    }
}

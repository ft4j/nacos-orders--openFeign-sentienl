package com.tuling.springcloud.stock.设计模式.创建模式.抽象工厂模式;

public class B海尔 implements AInterface电器厂 {
    @Override
    public Interface电视 create电视() {
        return new L海尔电视();
    }

    @Override
    public Interface冰箱 create冰箱() {
        return new L海尔冰箱();
    }

    @Override
    public Interface洗衣机 create洗衣机() {
        return new L海尔洗衣机();
    }

    @Override
    public Interface热水器 create热水器() {
        return new L海尔热水器();
    }
}

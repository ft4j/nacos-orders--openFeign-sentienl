package com.tuling.springcloud.orders.设计模式.工厂模式.C抽象工厂模式;

public class Bmw330FactoryC implements AbstractFactory {
    @Override
    public Engine createEngine() {
        return new EngineA();
    }

    @Override
    public Aircondition createAirCondition() {
        return new AirconditionA();
    }
}

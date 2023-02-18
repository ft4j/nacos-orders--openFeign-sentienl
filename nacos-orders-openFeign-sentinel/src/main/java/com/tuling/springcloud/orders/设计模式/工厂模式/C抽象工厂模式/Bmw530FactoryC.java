package com.tuling.springcloud.orders.设计模式.工厂模式.C抽象工厂模式;

public class Bmw530FactoryC implements AbstractFactory {
    @Override
    public Engine createEngine() {
        return new EngineB();
    }

    @Override
    public Aircondition createAirCondition() {
        return new AirconditionB();
    }
}

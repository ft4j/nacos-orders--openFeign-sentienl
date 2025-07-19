package com.tuling.springcloud.stock.spring.ioc.循环依赖;

public class A {
    B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}

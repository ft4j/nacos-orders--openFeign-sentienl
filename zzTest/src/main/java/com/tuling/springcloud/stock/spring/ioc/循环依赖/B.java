package com.tuling.springcloud.stock.spring.ioc.循环依赖;


public class B {
    A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}

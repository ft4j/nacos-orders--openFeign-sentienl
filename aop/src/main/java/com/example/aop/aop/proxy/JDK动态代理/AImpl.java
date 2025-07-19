package com.example.aop.aop.proxy.JDK动态代理;

public class AImpl implements A{
    @Override
    public void b() {
        System.out.println("B正在处理事件");
    }
}

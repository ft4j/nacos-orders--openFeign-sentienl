package com.tuling.springcloud.stock.spring.AOP.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyClass implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //代理方法before
        before();

        //被代理的方法执行
        methodProxy.invokeSuper(o, objects);

        //代理方法after
        after();
        return null;
    }

    private void before(){
        System.out.println("代理的before方法执行");
    }

    private void after(){
        System.out.println("代理的after方法执行");
    }
}

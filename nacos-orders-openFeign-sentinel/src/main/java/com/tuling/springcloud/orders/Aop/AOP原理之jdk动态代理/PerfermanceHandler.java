package com.tuling.springcloud.orders.Aop.AOP原理之jdk动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerfermanceHandler implements InvocationHandler {
    //被代理的对象
    private Object target;
    public PerfermanceHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理的前代码
        PerformanceMonitor.begin(target.getClass().getName()+"."+method.getName());

        //method被代理的方法，target被代理的对象
        Object object = method.invoke(target, args);

        //代理的后代码
        PerformanceMonitor.end();
        return object;
    }
}

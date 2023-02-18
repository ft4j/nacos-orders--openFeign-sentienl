package com.tuling.springcloud.orders.Aop.AOP原理之CGLIB;

import com.tuling.springcloud.orders.Aop.AOP原理之jdk动态代理.PerformanceMonitor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName()+"."+method.getName());
        Object o = proxy.invokeSuper(target, args);
        PerformanceMonitor.end();
        return o;
    }
}

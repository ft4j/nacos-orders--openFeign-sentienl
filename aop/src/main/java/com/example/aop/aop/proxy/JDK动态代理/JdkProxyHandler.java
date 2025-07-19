package com.example.aop.aop.proxy.JDK动态代理;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxyHandler implements InvocationHandler {
    private Object target;
    public JdkProxyHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        method.invoke(target,args);
        System.out.println("after");
        return null;
    }

}

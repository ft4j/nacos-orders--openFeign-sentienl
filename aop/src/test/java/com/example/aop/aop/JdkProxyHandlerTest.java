package com.example.aop.aop;

import com.example.aop.aop.proxy.JDK动态代理.A;
import com.example.aop.aop.proxy.JDK动态代理.AImpl;
import com.example.aop.aop.proxy.JDK动态代理.JdkProxyHandler;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class JdkProxyHandlerTest {
    @Test
    public void ddd(){
        A a = new AImpl();
        // 创建代理处理器
        JdkProxyHandler handler = new JdkProxyHandler(a);

        // 创建代理对象
        A proxy = (A) Proxy.newProxyInstance(
                a.getClass().getClassLoader(),
                a.getClass().getInterfaces(),
                handler);
        proxy.b();
    }

    @Test
    public void cglib(){
        Enhancer e = new Enhancer();//cglib的来自spring，否则需要引入
    }
}

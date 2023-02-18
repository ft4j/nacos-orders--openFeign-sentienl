package com.tuling.springcloud;

import com.tuling.springcloud.orders.Aop.AOP原理之CGLIB.CglibProxy;
import com.tuling.springcloud.orders.Aop.AOP原理之jdk动态代理.ForumService;
import com.tuling.springcloud.orders.Aop.AOP原理之jdk动态代理.ForumServiceImpl;
import com.tuling.springcloud.orders.Aop.AOP原理之jdk动态代理.PerfermanceHandler;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * spring默认使用的cglib方式进行aop代理
 */
public class Aop之Jdk动态代理和CGLIBTest {
    /**
     * jdk动态代理
     */
    @Test
    public void dddd(){
        ForumService target = new ForumServiceImpl();
        PerfermanceHandler ph = new PerfermanceHandler(target);

        ForumService fs =
                (ForumService)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), ph);
        fs.removeForum("");
        fs.removeTopic("");
    }

    /**
     * cglib代理
     */
    public void dsadasd(){
        CglibProxy cp = new CglibProxy();
        ForumServiceImpl fs = (ForumServiceImpl)cp.getProxy(ForumServiceImpl.class);
        fs.removeForum("");
        fs.removeTopic("");
    }


}

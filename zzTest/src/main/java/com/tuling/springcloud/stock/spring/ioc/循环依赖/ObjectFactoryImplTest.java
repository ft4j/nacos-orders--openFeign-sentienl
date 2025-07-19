package com.tuling.springcloud.stock.spring.ioc.循环依赖;

import org.junit.Test;
import org.springframework.beans.factory.ObjectFactory;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactoryImplTest {
    Map<String,ObjectFactory> m = new HashMap();
    @Test
    public void test(){
        ObjectFactory of = new ObjectFactoryImpl();
        addSingletonFactory("xx",()->{
            System.out.println("xxxddddddd");
            System.out.println("eeeddddd");
            System.out.println("aaaddddddd");
            return new String("这是返回的对ddddddd象");
        });
        Object xx = m.get("xx").getObject();
        System.out.println(xx);
    }


    void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory ){
        m.put(beanName,singletonFactory);
    }

    Object getEarlyBeanReference(){
//        System.out.println("xxx");
//        System.out.println("eee");
//        System.out.println("aaa");
//        return new String("这是返回的对象");
        return null;
    }
}

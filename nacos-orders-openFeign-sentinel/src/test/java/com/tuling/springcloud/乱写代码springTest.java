package com.tuling.springcloud;

import com.tuling.springcloud.orders.OrdersFeignSentinelApplication;
import com.tuling.springcloud.orders.springboot自动装配.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = OrdersFeignSentinelApplication.class)
@RunWith(SpringRunner.class)
public class 乱写代码springTest {

    @Autowired
    ZhuRuComponent zhuRuComponent;

    @Autowired
    ZhuRuAtBean zhuRuAtBean;

    /**
     * 测试用@Component和@Bean方式注入一个类
     */
    @Test
    public void dsdsd(){
        System.out.println(zhuRuComponent.toString());
        System.out.println(zhuRuAtBean.toString());
    }

    @Autowired(required = false)
    A a;
    @Autowired
    B b;
    @Test
    public void dsdfsdf(){
        System.out.println(a);
        System.out.println(b);
    }

    @Autowired
    C c;
    @Test
    public void sdfsdf(){
        System.out.println(c);
    }

    /**
     * 通过MyImportBeanDefinitionRegister类注册本类
     * 而MyImportBeanDefinitionRegister被注册在ConfigurationClass1下
     * 一个配置类只能存在一个
     */
    @Autowired
    @Qualifier("dddd")
    D d;
    @Test
    public void sdfssdfdf(){
        System.out.println(d);
    }

    /**
     *
     */
    @Autowired
    E e;
    @Test
    public void dfsdf(){
        System.out.println(e);
    }

    /**
     * 条件装配 F.class
     */
    @Autowired(required = false)
    F f;
    @Test
    public void ssasdssdsd(){
        System.out.println(f);
    }



    @Test
    public void sdddddddfsdf(){
    }



}

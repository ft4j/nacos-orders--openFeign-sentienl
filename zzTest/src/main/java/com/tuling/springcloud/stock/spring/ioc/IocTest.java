package com.tuling.springcloud.stock.spring.ioc;

import com.tuling.springcloud.stock.spring.AOP.springaop.BeProxy;
import com.tuling.springcloud.stock.spring.ioc.bean.MyAwareBean;
import com.tuling.springcloud.stock.spring.ioc.bean.扩展bean;
import com.tuling.springcloud.stock.spring.ioc.循环依赖.A;
import com.tuling.springcloud.stock.spring.ioc.循环依赖.B;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.function.RequestPredicates;

public class IocTest extends RequestPredicates {
    @Test
    public void dsdsdsdds(){
        ClassPathXmlApplicationContext co = new ClassPathXmlApplicationContext("applicationContext.xml");
        IocBean iocBean = (IocBean) co.getBean("iocBean");
        iocBean.beanMethod();
        System.out.println(iocBean.firstName);
        System.out.println(iocBean.secondName);

        扩展bean beannn = (扩展bean) co.getBean("扩展bean");
        System.out.println(beannn);
        MyAwareBean myAwareBean = (MyAwareBean) co.getBean("myAwareBean");
        ApplicationContext applicationContext = myAwareBean.getApplicationContext();
        System.out.println(applicationContext);
        System.out.println(myAwareBean.getSssss());
        System.out.println("-------------------------");
        /**
         * bean获取的方式注意一下，FactoryBean获取对象的方式注意一下？factoryBeanImp获取的是FactoryBeanStudent对象
         * &factoryBeanImp才能获取它原来的对象，被鸠占鹊巢了！
         */
        FactoryBeanStudent s = (FactoryBeanStudent) co.getBean("factoryBeanImp");
        System.out.println(s);
        FactoryBeanImp factoryBeanImp = (FactoryBeanImp)co.getBean("&factoryBeanImp");
        System.out.println(factoryBeanImp);

    }

    @Test
    public void factoryFactory(){
        ClassPathXmlApplicationContext co = new ClassPathXmlApplicationContext("applicationContext.xml");
        FactoryBeanStudent factoryBeanStudent = (FactoryBeanStudent)co.getBean("factoryBeanImp");
        System.out.println(factoryBeanStudent);
        FactoryBeanImp factoryBeanImp = (FactoryBeanImp)co.getBean("&factoryBeanImp");
        System.out.println(factoryBeanImp);
    }

    @Test
    public void 循环依赖(){
        ClassPathXmlApplicationContext co = new ClassPathXmlApplicationContext("cycle.xml");
        A a = (A)co.getBean("a");
        System.out.println(a);
        B b = (B)co.getBean("b");
        System.out.println(b);
    }

    @Test
    public void beanPostProcessorTest(){
        ClassPathXmlApplicationContext co = new ClassPathXmlApplicationContext("beanPostProcessor.xml");
        BeProxy beProxy = (BeProxy)co.getBean("beProxy");
        System.out.println(beProxy);
    }
}

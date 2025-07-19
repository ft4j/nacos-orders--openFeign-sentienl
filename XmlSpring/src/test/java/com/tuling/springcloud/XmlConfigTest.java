package com.tuling.springcloud;

import com.tuling.springcloud.cycle.Aaa;
import com.tuling.springcloud.cycle.Bbb;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class XmlConfigTest {
    @Test
    public void sdfsdfsdf(){
        ClassPathXmlApplicationContext co = new ClassPathXmlApplicationContext("cycleXmlSpring.xml");
        Aaa aaa = (Aaa)co.getBean("aaa");
        System.out.println(aaa);
        Bbb bbb = (Bbb)co.getBean("bbb");
        System.out.println(bbb);
    }
}

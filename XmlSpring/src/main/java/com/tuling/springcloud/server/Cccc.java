package com.tuling.springcloud.server;

import com.tuling.springcloud.cycle.Aaa;
import com.tuling.springcloud.cycle.Bbb;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Cccc {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext co = new ClassPathXmlApplicationContext("cycleXmlSpring.xml");
        Aaa a = (Aaa)co.getBean("a");
        System.out.println(a);
        Bbb b = (Bbb)co.getBean("b");
        System.out.println(b);
    }
}

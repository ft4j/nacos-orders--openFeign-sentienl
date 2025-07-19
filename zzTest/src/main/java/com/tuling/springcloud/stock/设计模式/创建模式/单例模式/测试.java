package com.tuling.springcloud.stock.设计模式.创建模式.单例模式;

import com.tuling.springcloud.stock.设计模式.创建模式.单例模式.线程安全的单例.Enum线程安全单例;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

public class 测试 {

    @Test
    public void lazy(){

    }

    @Test
    public void hunger(){

    }

    @Test
    public void dsd(){
        Random rd = new Random();
        for (int i = 0; i < 20; i++) {
            int s = rd.nextInt(2);
            System.out.println(s);
        }

        BigDecimal bd = new BigDecimal("20");
        String s = bd.toPlainString();
        System.out.println(s);
    }


    @Test
    public void dsdsdsd(){
        Enum线程安全单例 instance = Enum线程安全单例.INSTANCE;
        instance.enumMethod();
    }
}

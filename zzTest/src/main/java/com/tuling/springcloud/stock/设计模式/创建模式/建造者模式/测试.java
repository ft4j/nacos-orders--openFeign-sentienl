package com.tuling.springcloud.stock.设计模式.创建模式.建造者模式;

import org.junit.Test;

/**
 * 建造者模式主要可以实时监控类的创建过程
 * 存在一个被建造者建造的对象X，它有若干个属性
 * 又存在一个建造者Y，它存在建造方法，能够分别构造被建造者X的一个属性
 * 又存在一个指挥者Z，它又若干个方法，在方法中，它调用了Y中的若干个方法，控制了X属性哪些被构建，按照什么顺序构建
 * 使用者直接实例化Z，然后调用Z的方法就能控制X对象的构造！
 *
 */
public class 测试 {
    @Test
    public void 建造者模式(){
        Director dr = new Director(new ConCreateBuilder());
        BuildedProduct construct = dr.construct();
        System.out.println(construct);
    }
}

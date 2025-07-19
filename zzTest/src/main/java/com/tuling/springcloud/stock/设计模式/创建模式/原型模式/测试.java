package com.tuling.springcloud.stock.设计模式.创建模式.原型模式;

import jodd.util.CsvUtil;
import org.junit.Test;

import java.security.acl.AclEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式就是为了大量创建 相似对象时，需要采用的一种设计模式，它属于一种创建模式
 */
public class 测试 {
    /**
     * 浅克隆
     * @throws CloneNotSupportedException
     */
    @Test
    public void cloneSheep() throws CloneNotSupportedException {
        Sheep sp = new Sheep();
        sp.setAge(13);
        sp.setName("多莉");
        sp.setClassName("三班");
        System.out.println(sp);
        Sheep clone = sp.clone();
        System.out.println(clone);
        System.out.println(sp == clone);
    }

    @Test
    public void cloneBear() throws CloneNotSupportedException {
        Bear br = new Bear();
        br.setDu("毛肚");
        br.setJiao("牛角");
        br.setTizi(4);
        List l = new ArrayList();
        l.add("sansha");
        l.add("wusha");
        br.setList(l);

        Bear clone = br.clone();
        System.out.println(br == clone);
        System.out.println(br.getList() == clone.getList());
        //结果  这是前拷贝，不可以拷贝对象中的对象，br和clone中的List还是只想同一个对象
        //false
        //true

        //如过想得到一个全新的List对象，那么，需要使用流来实现  或者  在重写clone方法中也指定clone那个需要深度clone的对象
    }
}

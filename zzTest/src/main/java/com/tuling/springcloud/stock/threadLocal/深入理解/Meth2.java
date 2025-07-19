package com.tuling.springcloud.stock.threadLocal.深入理解;

import org.junit.Test;

public class Meth2 {
    public void dfsdf(){
        String s = Meth1.tl.get();
        System.out.println(s);
        String s2 = Meth1.tx.get();
        System.out.println(s2);
    }
    @Test
    public void xxxx() throws InterruptedException {
        Meth1 m1 = new Meth1();
        m1.dsdsd();
        Meth2 m2 = new Meth2();
        m2.dfsdf();
    }
}

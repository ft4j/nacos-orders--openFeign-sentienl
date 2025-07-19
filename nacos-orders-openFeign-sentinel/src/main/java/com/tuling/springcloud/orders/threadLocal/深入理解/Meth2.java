package com.tuling.springcloud.orders.threadLocal.深入理解;

public class Meth2 {
    public void dfsdf(){
        ThreadLocal<String> tl = new ThreadLocal<String>();
        String s = tl.get();
        System.out.println(s);
    }
}

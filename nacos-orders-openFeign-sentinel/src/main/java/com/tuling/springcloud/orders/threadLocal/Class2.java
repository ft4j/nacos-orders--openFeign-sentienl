package com.tuling.springcloud.orders.threadLocal;

public class Class2 {
//    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    public void ddd(){
        String s = Class1.threadLocal.get();
        System.out.println("当前在Class2，获取的ThreadLocal值为："+s);
        //防止内存泄露
        Class1.threadLocal.remove();
    }
}

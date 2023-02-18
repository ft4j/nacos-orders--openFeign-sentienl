package com.tuling.springcloud.orders.threadLocal;

public class Class1 {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    public void ddd(){
        System.out.println("这会在Class1，正在设值");
        threadLocal.set("class1笑了，哈哈哈哈");
    }
}

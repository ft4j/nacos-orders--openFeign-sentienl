package com.tuling.springcloud.orders.线程池;

import java.util.concurrent.Callable;

public class CallableImplementer implements Callable<String> {
    @Override
    public String call() throws Exception {
        String returnStr = "返回的str";
        System.out.println(Thread.currentThread().getName());
        int s = 1/0;
        System.out.println(Thread.currentThread().getName());
        System.out.println(returnStr);
        return returnStr;
    }
}

package com.tuling.springcloud.orders.线程池;

import java.util.concurrent.Callable;

public class CallableImp implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "callable完回归了";
    }
}

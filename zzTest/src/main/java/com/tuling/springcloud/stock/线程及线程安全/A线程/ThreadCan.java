package com.tuling.springcloud.stock.线程及线程安全.A线程;

public class ThreadCan implements Runnable{
    @Override
    public void run() {
        System.out.println("implement方式实现线程"+Thread.currentThread().getName());
    }
}

package com.tuling.springcloud.stock.线程及线程安全.A线程;

public class MyThread extends Thread {
    private String param;
    public MyThread(String param){
        this.param = param;
    }
    public MyThread(){

    }
    @Override
    public void run() {
        System.out.println("extends方式实现线程"+Thread.currentThread().getName());
        System.out.println("param");
    }
}

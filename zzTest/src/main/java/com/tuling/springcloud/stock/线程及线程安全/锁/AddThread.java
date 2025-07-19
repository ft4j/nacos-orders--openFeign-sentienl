package com.tuling.springcloud.stock.线程及线程安全.锁;

public class AddThread extends Thread{
    Integer s = 0;
    public AddThread(){}
    public AddThread(Integer s){
        this.s = s;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s++;
        }
    }
}

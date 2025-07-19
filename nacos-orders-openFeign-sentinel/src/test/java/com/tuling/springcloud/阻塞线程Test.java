package com.tuling.springcloud;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

public class 阻塞线程Test {
    @Test
    public void sdfsdf() throws InterruptedException {
        LinkedBlockingQueue lbq = new LinkedBlockingQueue(2);

        Thread d = new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lbq.offer("33333333");
        });
        d.start();
        Object take = lbq.take();
        System.out.println(take+"我获取到了take！");
    }
}

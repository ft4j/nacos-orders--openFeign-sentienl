package com.tuling.springcloud.stock.线程及线程安全.B线程池;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

public class 队列 {
    volatile static boolean b = false;
    @Test
    public void ddd() throws InterruptedException {
        SynchronousQueue<String> sq = new SynchronousQueue();

        //满了返回false
        sq.offer("");
        //塞满不阻塞
        sq.put("xxxx");
        //按顺序塞，塞满报错
        sq.add("");


        //获取最老的元素，但是不弹出
        String peek = sq.peek();
        //弹出最老的元素
        String poll = sq.poll();
        //获取不到就阻塞
        sq.take();


        //取最早放进去的
        sq.poll();
        System.out.println("ddd");
        Thread d1 = new Thread(()->{
            try {
                System.out.println("put前:"+System.currentTimeMillis());
                Thread.sleep(3000);
                sq.put("这是放进队列的数据");
                System.out.println("put后:"+System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread d2 = new Thread(()->{
            try {
//                System.out.println("sleep前:"+System.currentTimeMillis());

                System.out.println("take前:"+System.currentTimeMillis());
                String take = sq.take();
                System.out.println(take);
                System.out.println("take后:"+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        d1.start();
//        d2.start();
        Thread.sleep(5000);
    }


    @Test
    public void dsdsd() throws InterruptedException {
        LinkedBlockingDeque<String> qu = new LinkedBlockingDeque<String>(2);


        qu.offer("20");

        String peek = qu.peek();
        String peek1 = qu.peek();
        System.out.println(peek);
        System.out.println(peek1);
    }
}

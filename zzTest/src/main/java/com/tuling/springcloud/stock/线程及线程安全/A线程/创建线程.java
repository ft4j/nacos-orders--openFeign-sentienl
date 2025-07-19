package com.tuling.springcloud.stock.线程及线程安全.A线程;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class 创建线程 {
    @Test
    public void dsdsds(){
        Thread d = new MyThread("hhhhhhhh");
        d.start();

        Thread d1 = new Thread(new ThreadCan(),"Thread xxxxxx d1");
        d1.start();

        System.out.println(Thread.currentThread().getName()+"   这是主线程");
        Thread d2 = new Thread(()->{
            System.out.println("dsdasdasdasd");
        });
        d2.start();
    }

    @Test
    public void ddddddddddd() throws ExecutionException, InterruptedException {
        FutureTask<String> ft = new FutureTask(()->{
            System.out.println("callable被执行了");
            return "success";
        });
        Thread d = new Thread(ft);
        d.start();
        String s = ft.get();
        System.out.println(s);
    }

    /**
     * NEW
     * RUNNABLE
     * BLOCKED
     * WAITING
     * TIMED_WAITING
     * TERMINATED
     */
}

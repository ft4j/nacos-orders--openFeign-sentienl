package com.tuling.springcloud;

import com.tuling.springcloud.orders.线程池.CallableImplementer;
import com.tuling.springcloud.orders.线程池.Xiancheng;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.*;

public class 线程以及线程池Test {
    @Test
    public void sdsdsdsd() throws ExecutionException, InterruptedException {
        CallableImplementer call = new CallableImplementer();
        try{
            FutureTask<String> f = new FutureTask<String>(call);
            Thread d = new Thread(f);
            d.start();
            String s = f.get();
            System.out.println(s);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());

        System.out.println("---------------");

        try{
            ExecutorService es = Executors.newSingleThreadExecutor();
            Future submit = es.submit(call);
//            Object o = submit.get();
//            System.out.println(o);
            System.out.println("这是submit完了之后的拉");
            Future<?> submit1 = es.submit(() -> {
                System.out.println("??");
                int i = 1 / 0;
            });

            System.out.println("xxxxxxxxxxxxxxxx");
            es.execute(()->{
                try{
                    int i = 1 / 0;
                }catch(Exception e){
                    e.printStackTrace();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void sdasdfsdf() throws InterruptedException {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(10);
        System.out.println("dd");
        es.schedule(()->{
            System.out.println("dddsddd");
        },2, TimeUnit.SECONDS);
        es.shutdown();
    }


    @Test
    public  void maiwwerwern() {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(5);
        Xiancheng task = new Xiancheng();
        System.out.println("Created : ");
        es.schedule(task, 2, TimeUnit.SECONDS);// 只执行一次
        // executor.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS); //任务+延迟
//        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);//任延迟取最大值 稳定定时器
//        Thread d = new Thread(task);
//        d.start();
    }


    @Test
    public void sasd(){
        Xiancheng x = new Xiancheng();
        Thread d = new Thread(x);
        d.start();
    }
}

class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void run() {
        System.out.println("Executing : " + name + ", Current Seconds : " + new Date().getSeconds());
        try {
            // 模拟处理业务需要5秒钟
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

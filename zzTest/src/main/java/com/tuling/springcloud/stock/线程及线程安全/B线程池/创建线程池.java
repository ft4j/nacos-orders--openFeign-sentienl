package com.tuling.springcloud.stock.线程及线程安全.B线程池;

import org.junit.Test;

import java.util.concurrent.*;

public class 创建线程池 {
    @Test
    public void dsds() throws InterruptedException {
        ExecutorService e = Executors.newSingleThreadExecutor();
        ExecutorService es = Executors.newFixedThreadPool(20);
        ExecutorService ess = Executors.newScheduledThreadPool(1);
        ExecutorService esss = Executors.newCachedThreadPool();
        //正常就是这样创建，自带的创建方式都有失控的风险，这个拒绝策略即使策略模式
        ExecutorService ee = new ThreadPoolExecutor(2,2,2000
                ,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        e.execute(()->{

        });

    }
    /**
     * 线程池的执行顺序
     * 判断核心线程满了没有，没有一定是新建线程（addWorker），直到核心线程都被创建
     * 如果核心线程都满了，就往队列塞任务，此时判断队列满了没有
     * 队列没满就往队列塞任务，满了就创建临时线程（addWorker）
     * 此时再判断临时线程满了没有，满了执行拒绝策略，未满就继续创建临时线程（addWorker）
     *
     * 此时如果没有新的任务产生，那么核心线程和临时线程都是继续执行队列中的任务，
     *
     * 其中拒绝策略会在两种情况下启用，一是临时线程都被占满，二是execute的时候，线程池已经不是running状态
     *
     * 拒绝策略：
     *
     * 线程池的状态
     * RUNNING    可以正常使用的状态
     * SHUTDOWN   调用shutdown之后，不能接受新任务，可以处理已经存在的认为
     * STOP       调用shutdownNow之后，不能接受新任务，并将存在的任务也杀死，释放线程
     * TIDYING    任务为0，线程也释放完成
     * TERMINATED tidying状态下，调用terminated()方法之后，线程池彻底结束
     */
}

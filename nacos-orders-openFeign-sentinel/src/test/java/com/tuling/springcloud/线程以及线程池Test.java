package com.tuling.springcloud;

import com.tuling.springcloud.orders.线程池.CallableImplementer;
import com.tuling.springcloud.orders.线程池.Xiancheng;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 1、线程池状态running  shutdown stop（shutdownNow） tidying termianted
 * 2、拒绝策略，拒绝并报错，不做处理，调用线程处理，丢弃最老的并加入最新的任务
 * 3、callerRunsPolicy会阻塞主线程
 * 4、线程池执行顺序，execute->加入任务：核心 队列 缓存，消费任务：核心 缓存 队列，如果全都放不下，触发拒绝策略
 * 随着任务的加入，核心线程数量回不断变大，就算有核心线程空闲，如果添加新任务，依然会创建线程直到线程池的核心线程达到构造函数的值
 * 而缓存线程如果空闲则会被直接复用
 * 5、execute()做了什么？
 *  根据线程池的容量情况，将任务被 核心、缓存消费或者存入队列中。execute方法会调用一个addWorker方法，在该方法中，任务被封装未一个worker对象。
 *  addWorker方法继续执行，如果发现任务被成功添加，workerAdded此时未true没执行t.start()方法
 *  if (workerAdded) {
 *      t.start();
 *      workerStarted = true;
 *  }
 *  这个start方法就是执行的Worker对象的start()方法，此时会调用runWorker()方法
 *  runWorker()方法中有一个while()死循环，会不断调用getTask()方法
 *  getTask()方法中一个for死循环，死循环中会调用线程queue的take方法，如果队列有任务，就返回队列第一个任务，并被执行
 *  如果队列没用任务，则阻塞当前线程。因为execute执行之后有死循环，因此核心线程没任务也不会被回收而是永远阻塞。
 *  因此个人理解：如果队列有任务会第一时间被核心线程消费，所以看起来execute会首先将任务给核心线程。当核心线程满了，才将任务存入队列
 *
 *  ctl是用来根据线程池状态，判断系统是否还存在可用的线程的一个原子参数
 *
 * 5、execute和submit的区别
 *  》execute可以接受runnable任务，submit可以接受runnable和callable类型的接口
 *  》execute没返回值，submit可以有返回值
 *  》execute不能抛出线程中的异常，submit可以抛出线程中的异常
 *
 * 6、任务的执行顺序，核心线程和缓存线程会同步执行任务。如果队列未满，那么任务将被核心线程按照顺序执行
 * SynchronousQueue sq = new SynchronousQueue();
 * 无存储空间的队列，加入一个元素就需要有对应的take方法取出元素。
 * SynchronousQueue.put()，如果不存在一个对应的take方法，就会一直阻塞。
 * offer()方法则是，如果不存在一个对应的take就返回fasle，存在就返回true，并且立刻将元素传递到take()处，本身不存储数据
 * offer()方法如果指明了延迟事件，就阻塞对应的事件。阻塞的事件内元素被取走，返回true，否则返回false
 * ！！！它的传递速度是最快的！！！
 *
 * ArrayBlockingQueue和LinkedBlockedQueue都是可以指定大小的队列，
 * 但是ArrayBlockingQueue必须默认队列大小，LinkedBlockingQueue则默认Integer.max
 * ArrayBlockingQueue取值设值是一把锁，LinkedBlockingQueue是两把锁，所以后者并发更好，前者基本被后者替代
 *
 * DelayedWorkQueue是定时线程池默认的队列，具有定时功能。
 *
 *         //DelayedWorkQueue
 *         //ArrayBlockingQueue
 *         //LinkedBlockingQueue
 *         //LinkedBlockingQueue sq = new LinkedBlockingQueue();
 */
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
            ExecutorService ee = Executors.newScheduledThreadPool(2);
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

        ScheduledExecutorService esss = Executors.newScheduledThreadPool(10);
        esss.scheduleAtFixedRate(()->{
            System.out.println("dddd");
        },1,2,TimeUnit.SECONDS);
        Thread.sleep(10000);
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

    /**
     * ************************************************************************
     */
    /**
     * 回忆线程池
     */
    @Test
    public void wanwanxiancgengchi() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ThreadPoolExecutor tl = new ThreadPoolExecutor(1,2,
                10,TimeUnit.SECONDS,new LinkedBlockingQueue<>(2),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        CallSomething cs = new CallSomething();
        Future<String> submit = tl.submit(cs);
        String s = submit.get();
        System.out.println(s);

        tl.shutdown();
    }

    @Test
    public void sdsf(){
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.schedule(()->{
            System.out.println("5秒之后我才执行");
        },5,TimeUnit.SECONDS);
        ses.shutdown();
    }
    /**
     * 创建线程
     */
    @Test
    public void sdfsdf(){
        Thread d = new Thread(()->{
            System.out.println("ddddd");
        });
        d.start();
    }

    @Test
    public void sddfsd() throws InterruptedException {
        SynchronousQueue sq = new SynchronousQueue();
        //DelayedWorkQueue sq = new SynchronousQueue();
        //ArrayBlockingQueue sq = new ArrayBlockingQueue();
        //LinkedBlockingQueue
//        LinkedBlockingQueue sq = new LinkedBlockingQueue();
        Thread d1 = new Thread(()->{
            for (int i = 0; i < 200; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sq.offer("线程1放进去了"+i);
            }
        });
        Thread d2 = new Thread(()->{
            for (int i = 0; i < 200; i++) {
                try {
                    System.out.println("获取之前");
                    Object take = sq.take();
                    System.out.println(take);
                    System.out.println("获取之后----------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        d1.start();
        d2.start();
        Thread.sleep(2000000);
    }

    @Test
    public void dfdfg() throws InterruptedException {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(3);
        es.scheduleAtFixedRate(()->{
            System.out.println("dddddd");
        },3,3,TimeUnit.SECONDS);
        Thread.sleep(2000000000);
    }
    /**
     * 线程的创建
     */
    @Test
    public void dsdfsdfsd() throws ExecutionException, InterruptedException {
        CallSomething cs = new CallSomething();
        FutureTask<String> ft = new FutureTask(cs);
        new Thread(ft).start();
        String s = ft.get();
        System.out.println(Thread.currentThread().getName()+"    "+s);
    }

    @Test
    public void sdfsdfsdf() throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(1, 1, 3, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        });
        Thread.sleep(200000);
    }
}

class CallSomething implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"call一下");
        return "这是一个有返回值的线程";
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

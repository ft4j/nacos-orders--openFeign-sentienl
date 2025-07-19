package com.tuling.springcloud;

import com.tuling.springcloud.orders.乱写包.InttImpl;
import com.tuling.springcloud.orders.乱写包.Ob;
import com.tuling.springcloud.orders.乱写包.Stoc;
import com.tuling.springcloud.orders.线程池.CallableImp;
import com.tuling.springcloud.orders.线程池.Ddsdsaaaaa;
import com.tuling.springcloud.orders.线程池.Runnnnn;
import net.minidev.json.JSONUtil;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.*;

public class 线程及线程池test1 {
    @Test
    public void 创建线程() throws ExecutionException, InterruptedException {
        //lambda
        Thread d = new Thread(()->{
            System.out.println("ddd");
        });
        d.start();
        //继承Thread
        Ddsdsaaaaa ds = new Ddsdsaaaaa();
        ds.start();

        //实现runnable接口
        Runnnnn rn = new Runnnnn();
        Thread dss = new Thread(rn);
        dss.start();

        //Callable创建线程  这种线程可以有执行的返回值
        //这实现了callAble接口，Callable是一个顶层接口
        CallableImp cbi = new CallableImp();
        //这实现了RunnableFuture接口，RunnableFuture接口则继承了Runnable和Future接口，说干什么用的？不知道啊
        FutureTask<String> ft = new FutureTask(cbi);
        /**
         * 妈的居然有这种B东西写法，这样子ft.get()得到的就是"dsdsd"
         * FutureTask<String> ft = new FutureTask(rn,"dsdsd");
         */
        Thread thread = new Thread(ft);
        thread.start();
        String s = ft.get();
        System.out.println(s);
        //用线程池取出线程去执行
        CallableImp cbiiii = new CallableImp();
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<String> submit = es.submit(cbiiii);
        String s1 = submit.get();
        System.out.println("这是从线程池拿到的"+s1);
        es.shutdown();

        /**
         * 线程池的状态
         * NEW刚刚被new出来
         * RUNNABLE 调用了.start(),yield(),sleep(),join(),wait(),BLOCKING结束，但线程还未执行时的状态
         * BLOCK 阻塞状态，请求资源时发现被加了锁，阻塞了
         * waiting 等待状态，这个状态下，这个线程不会被分配CPU时间片
         * timed_waiting 超时等待，这个状态下，也不会被分配CPU时间片，但是他有具体的等待时间
         * terminated 线程结束
         */
    }

    @Test
    public void 线程API学习Join() throws InterruptedException {
        //join
        Thread d1 = new Thread(()->{
            System.out.println("d1睡着");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("d1醒来");

        });

        Thread d2 = new Thread(()->{
            System.out.println("d2开始执行");
            try {
                d1.join(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("被d2插队之后继续执行");
        });
        d2.start();
        d1.start();
        Thread.sleep(5000);
    }


    @Test
    public void 线程Api学习SleepYieldWait() throws InterruptedException {
        System.out.println("先sleep吧");
        //让线程暂停执行若干毫秒，这个暂停不会让当前线程交出锁
        Thread.sleep(3000);
        Dd dd = new Dd();
        Thread d = new Thread(()->{
            dd.打印方法();
        },"第一个线程");

        Thread d1= new Thread(()->{
            dd.打印方法();
        },"第二个线程");

        d.start();
        Thread.sleep(20);
        d1.start();
        Thread.sleep(8000);
    }

    class Dd{
        /**
         * 这可以证明sleep方法是不会交出锁的
         */
        public synchronized void 打印方法(){
            System.out.println("print:"+Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void sleep和wait的区别() throws InterruptedException {
        /**
         * sleep是暂停当前线程的执行，当前线程不会放弃锁
         * wait其实是一个让当前对象的锁在线程内部被放弃的一个办法
         * wait和notify必须在synchronized代码块中使用，否则报错
         * 对于这两个方法的理解是，wait实际上是一个让锁可以在synchronized内部被释放锁而不是必须等代码执行玩才能释放锁的办法
         * 而notify也必须在synchronized中执行，让一个已经被wait的线程被唤醒，但是notify不会放弃锁
         * 只有当执行玩synchronized代码块时，锁才被释放
         */

        String ddd = "";
        Thread d1 =  new Thread(()->{
            synchronized (ddd){
                System.out.println("线程d1现在正在执行，并已经获取了锁，并在此时选择放弃锁，进入等待状态");
                System.out.println("d1wait之前，睡3秒");
                try {
                    Thread.sleep(30);
                    ddd.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("d1wait之后运行的代码");
            }
        });
        Thread dd1 =  new Thread(()->{
            synchronized (ddd){
                System.out.println("线程dd1现在正在执行，并已经获取了锁，并在此时选择放弃锁，进入等待状态");
                System.out.println("dd1wait之前，睡3秒");
                try {
                    Thread.sleep(30);
                    ddd.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("dd1wait之后运行的代码");
            }
        });
        Thread ddd1 =  new Thread(()->{
            synchronized (ddd){
                System.out.println("线程ddd1现在正在执行，并已经获取了锁，并在此时选择放弃锁，进入等待状态");
                System.out.println("ddd1wait之前，睡3秒");
                try {
                    Thread.sleep(30);
                    ddd.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ddd1wait之后运行的代码");
            }
        });

        Thread d2 = new Thread(()->{
            synchronized (ddd){
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程2现在正在执行，并已经获取了锁");
                }
                ddd.notifyAll();
                System.out.println("notify之后的代码是个啥意思？看看");
            }
        });
        //这三个都wait住了
        d1.start();dd1.start();ddd1.start();
        Thread.sleep(200);
        d2.start();
        Thread.sleep(8000);


    }


    /**
     * 线程打断的方法
     * stop()现在不合时宜了
     * wait状态下调用interrupt，让线程报错退出
     * 正常退出
     * 给线程添加退出状态（自己或者外部添加都可以），在线程中添加退出判断以退出
     * 以下就是给线程添加退出状态的退出方式
     * @throws InterruptedException
     */
    @Test
    public void 线程api之打断() throws InterruptedException {
        Thread d1 = new Thread(()->{
            for (int i = 0; i < 1000000; i++) {
                System.out.println(Thread.currentThread().isInterrupted());
                try {
                    //一个waiting状态的线程被调用了interrupt()方法会报错，此时可以catch住并顺势结束线程
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("d1得到了被打断的状态，优雅推出");
                    break;
                }
            }
        });

        Thread d2 = new Thread(()->{
            System.out.println("d2要打断d1了哦");
            d1.interrupt();
        });
        d1.start();
        Thread.sleep(1);
        d2.start();
        Thread.sleep(1155);
        //当前线程时否被打断？判断完之后复位（复位后，这个方法总是返回false）
//        Thread.interrupted();
        //判断当前线程时否被打断？判断完之后不复位
//        Thread.currentThread().isInterrupted();
        //给当前线程一个打断标记
//        Thread.currentThread().interrupt();
        //给d1添加一个打断标记
//        d1.interrupt();



    }
    /**
     * 关于守护线程的理论
     * 守护线程的生命周期取决于普通线程
     * 当所有的普通线程执行完毕，守护线程也会推出
     * 守护线程不会组织jvm的推出
     * 守护线程适合做一些支持性的工作
     */
    @Test
    public void 守护线程(){
        Thread d = new Thread(()->{

        });
        //这是一个守护线程
        d.setDaemon(true);
        d.start();


    }

    @Test
    public void 线程池的创建() throws ExecutionException, InterruptedException {

        ExecutorService es = new ThreadPoolExecutor(1,2,3,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        
        es.execute(()->{
            System.out.println("dddd");
        });

        CallableImp ca = new CallableImp();
        FutureTask<String> tf = new FutureTask(ca);
        es.submit(tf);
        String s = tf.get();
        System.out.println(s);
//        es.shutdown();
//        es.awaitTermination(10,TimeUnit.SECONDS);
    }

    /**
     * 线程池5个状态
     * running 正常运行的状态
     * shutdown 调用了shutdown()的方法的状态，不可以接受新任务，但是已经存在的任务继续执行
     * stop 调用了shutdownNow()方法的状态，不接受新任务且停止执行现存的所有任务
     * tidying 整理状态，shutdown执行完所有任务或者stop之后进入tidying状态，这状态下会执行terminated()方法，如果想让他起作用，需要继承
     * ThreadPoolExecutor类，并重写terminated()方法
     * terminated 线程池彻地结束，此时调用isTerminated方法返回true
     *
     *
     * ***********线程池的的队列及其区别**********
     * 单例线程池和定长线程池：LinkedBlockingQueue 定长队列，没啥特别的
     * 缓存线程池：SynchronousQueue   容量为1，超出就创建缓存线程
     * 定时线程池：DelayedWorkQueue   带定时功能的队列
     * @throws InterruptedException
     */
    @Test
    public void 线程池() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newFixedThreadPool(3);
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        ExecutorService es = new ThreadPoolExecutor(1,2,3,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(()->{
            System.out.println(123);
        },3,TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();


        executorService.shutdown();
        System.out.println(executorService.isTerminated());
        Thread.sleep(5000);
    }

    @Test
    public void 线程池的执行原理(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()->{

        });
    }

    /**
     * lambda表达式 和 方法引用
     */
    @Test
    public void ob(){
        Ob b = new Ob();
        b.ggg(()->{
            System.out.println("dddd");
        });
        b.ggg(Stoc::ccc);
        b.ggg(new InttImpl());

        //按顺序塞，塞满不报错
        Queue q = new LinkedBlockingQueue(3);
        q.offer("1");q.offer("2");q.offer("3");q.offer("4");
        System.out.println(q);
        //按顺序塞，塞满报错
        Queue w = new LinkedBlockingQueue(3);
        w.add("1");w.add("2");w.add("3");
        System.out.println(w);
        //按顺序取，不会减少队列里的内容，可以用以取值判断，且不减少元素
        System.out.println(q.peek());
        System.out.println(q);
        //和poll一样，当队列为空 poll返回null  remove报错
        System.out.println(q.remove());
        System.out.println(q);
        //按照顺序取，取一个少一个
        System.out.println(w.poll());
        System.out.println(w);


    }

    @Test
    public void testQueue() throws InterruptedException {
//        SynchronousQueue s = new SynchronousQueue();
//        s.take();

    }

}

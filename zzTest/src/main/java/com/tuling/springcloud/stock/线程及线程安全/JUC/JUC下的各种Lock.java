package com.tuling.springcloud.stock.线程及线程安全.JUC;

import org.junit.Test;
import org.redisson.RedissonLockEntry;
import org.redisson.misc.RPromise;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JUC下的各种Lock {

    /**
     * 如果通过lockInterruptibly方法加锁，会让该加锁过程可以被打断，后续的代码直接按照没加锁的执行
     * @throws InterruptedException
     * ReentrantLock在解锁之后会将lock和unlock之间的变量刷新回主内存
     * 而加锁之后，会获取主内存的变量值
     */
    @Test
    public void TestReentrantLock() throws InterruptedException {
        ReentrantLock rl = new ReentrantLock();
        Thread d1 = new Thread(()->{
            try {
                rl.lockInterruptibly();
                System.out.println("d1线程枷锁成功，睡两秒");
                Thread.sleep(40000);
                System.out.println("d1线程睡醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rl.unlock();
        });
        d1.start();
        Thread.sleep(300);
        Thread d2 = new Thread(()->{
            try {
                rl.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("d2线程枷锁成功，睡两秒");
            try {
                Thread.sleep(4000);
                System.out.println("d2线程睡醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(rl.isLocked()){
                rl.unlock();
            }
        });
        d2.start();
        Thread.sleep(2000);
        System.out.println("睡2秒就打断");
//        d2.interrupt();
        Thread.sleep(20000);
    }


    /**
     * countDownLatch，阻挡的是主线程，主线程停在await的调用位置，当其他线程countDown次数达到入参的值时（案例中为3）
     * 主线程继续运行，countDownLatch在countdwon之后，count值不会恢复
     * @throws InterruptedException
     */
    @Test
    public void testCountDownLatch() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            int finalI = i+1;
            new Thread(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第:"+ finalI +"次countdown");
                cdl.countDown();
            }).start();
        }
        System.out.println("在这锁住了，等结束");
        cdl.await();
        System.out.println("结束堵塞");
        //cdl是不会回复挡板次数的
        System.out.println(cdl.getCount());
        //再减一次试试，啥也没发生
        cdl.countDown();
    }

    /**
     * CyclicBarrier会阻挡所有调用了await()的线程，当调用await的线程跟参数一致时，所有的线程都会被放开阻塞
     * 下面的线程3阻塞了5秒之后执行await，那么其他线程都得在他们各自的wait处等待5秒
     * 放开执行之后，CyclicBarrier的挡板数会恢复到构造函数给定的值，可复用
     * @throws InterruptedException
     */
    @Test
    public void testCyclicBarrier() throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(3);
        Thread d1 = new Thread(()->{
            System.out.println("d1进来了，等住了");
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("d1继续");
        });
        Thread d2 = new Thread(()->{
            System.out.println("d2进来了，等住了");
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("d2继续");
        });
        Thread d3 = new Thread(()->{
            System.out.println("d3进来了，等住了");
            try {
                Thread.sleep(5000);
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("d3继续");
        });
        d1.start();
        d2.start();
        d3.start();
        Thread.sleep(8000);
    }

    /**
     * 信号量挡板，在它的acquire()和release()，只允许构造函数中规定的线程执行
     * acquire()和release()写在子线程中，案例所示，semaphore参数为2，那么只有2个线程可以立刻执行
     * 第三个线程需要等前两个执行完release时，信号量才足够它执行
     */
    @Test
    public void testSemaphore() throws InterruptedException {
        Semaphore sp = new Semaphore(2);
        Thread d1 = new Thread(()->{
            try {
                sp.acquire();
                System.out.println("线程1在执行，sleep...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sp.release();
        });
        Thread d2=  new Thread(()->{
            try {
                sp.acquire();
                System.out.println("线程2在执行，sleep...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sp.release();
        });
        Thread d3=  new Thread(()->{
            try {
                sp.acquire();
                System.out.println("线程3在执行，sleep...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sp.release();
        });
        d1.start();
        d2.start();
        d3.start();

        Thread.sleep(50000);


    }

    /**
     * 写写互斥
     * 读写互斥
     * 读读不互斥
     * 读写锁可以通过锁降级，保证该线程对于数据的可见性
     * 但是读写锁不支持锁升级，会死锁
     */
    @Test
    public void testReadWriteLock() throws InterruptedException {
        ReentrantReadWriteLock rrw = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock readLock = rrw.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rrw.writeLock();
        readLock.lock();
        writeLock.lock();
        Thread d = new Thread(()->{
            writeLock.lock();
            try {
                System.out.println("写锁进来了，睡两秒");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
        });
        d.start();

        Thread d111 = new Thread(()->{
            readLock.lock();
            try {
                System.out.println("读锁进来了，睡两秒");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
        });
        d111.start();
        Thread.sleep(4000);
    }

    /**
     * exchange可以实现两个线程之前交换对象
     * 第一个线程调用exchange方法，它会被阻塞，等第二个线程去调用exchange方法
     * 那该函数会返回它的泛型类型的对象。两个线程交换了传入的对象
     * @throws InterruptedException
     */
    @Test
    public void testExchange() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            String s = "T1";
            try {
                System.out.println("t1开始暂停");
                s = exchanger.exchange(s);
                System.out.println("t1结束暂停");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t1").start();
        Thread.sleep(3000);
        new Thread(() -> {
            String s = "T2";
            try {
                System.out.println("t2开始暂停");
                s = exchanger.exchange(s);
                System.out.println("t2结束暂停");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t2").start();
    }



    /**
     * 下面演示了ReenTrantLock死锁，以及finally和return的的顺序（finally先）
     */
    @Test
    public void dsdsds(){
        System.out.println("ddddddddd");
        HashSet s = new HashSet<String>();
        s.add("d");
        s.add("c");
        Iterator iterator = s.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    ReentrantLock rl1 = new ReentrantLock();
    ReentrantLock rl2 = new ReentrantLock();
    //死锁
    @Test
    public void  ssdsds() throws InterruptedException {
        Thread d = new Thread(()->{
            rl1.lock();
            System.out.println("线程111111:rl1加锁了");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rl2.lock();
            System.out.println("线程111111:线程rl1加锁了");
            rl2.unlock();
            rl1.unlock();
        });
        Thread d1 = new Thread(()->{
            rl2.lock();
            System.out.println("线程22222:线程rl2加锁了");
            try {
                Thread.sleep(500);
                boolean b = rl1.tryLock(5, TimeUnit.SECONDS);
                if(!b){
                    System.out.println("线程2的rl1放弃加锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                rl2.unlock();
                rl1.unlock();
            }
        });
        d.start();
        d1.start();
        d.join();
        d1.join();

    }

    //IllegalMonitorStateException 没加锁就解锁：跟没在synchronized代码块中使用wait()报错一样
    @Test
    public void dddddd(){
        ReentrantLock rl2 = new ReentrantLock();
        rl2.unlock();

    }

    /**
     * finally执行先于return
     * 以下代码执行结果
     * fianlly了
     * / by zero
     * 并且，如果finally中由return，那么fianlly中return什么，这个方法就必然return什么
     */
    @Test
    public void fsdfsd(){
        String s = returnAndFinally();
        System.out.println(s);
    }

    public String returnAndFinally(){
        try{
            int d = 1/0;
            int i = 3;
            return i+"";
        }catch (Exception e){
            return e.getMessage();
        }finally {
            System.out.println("fianlly了");
            return "finally的return";
        }
    }

    private final Semaphore latch;
    public JUC下的各种Lock() {
        this.latch = new Semaphore(0);
    }
    @Test
    public void dsdsdsdsdsd(){
        //final的值只能直接初始化或者卸载构造器中，下列代码编译不通过
        //this.latch = new Semaphore(0);
    }


    @Test
    public void dsdadsdsdf(){
        try{
            int s = 1/0;
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("finall了");
        }

        System.out.println("哈哈哈哈继续执行代码");
    }
}

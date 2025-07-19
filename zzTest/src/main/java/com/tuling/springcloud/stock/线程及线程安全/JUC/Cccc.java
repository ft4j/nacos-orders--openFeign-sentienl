package com.tuling.springcloud.stock.线程及线程安全.JUC;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock原理分析
 */
public class Cccc {
    /**
     * 对尝试加锁的线程进行interrupt，会让线程放弃锁的竞争
     * 怎么测试，线程1先加上锁，让线程2和3竞争，在竞争状态，让线程2interrupt，发现线程2线程3一起执行了
     * @throws InterruptedException
     */
    @Test
    public void aaaxxx() throws InterruptedException {
        ReentrantLock rtl = new ReentrantLock();
        new Thread(()->{
            rtl.lock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rtl.unlock();
        }).start();
        Thread.sleep(300);
        Thread d2 = new Thread(()->{
            rtl.lock();
            while (true){
                System.out.println("线程2疯狂打印");
            }
        });
        d2.start();
        d2.interrupt();
        new Thread(()->{
            rtl.lock();
            while (true){
                System.out.println("33333333333333333333333333333333333333");
            }
        }).start();

        System.out.println("wandna ");
        Thread.sleep(500000000);
    }



    @Test
    public void dsdsdsd(){
        ReentrantLock rtl = new ReentrantLock(true);
        rtl.lock();

    }


    @Test
    public void fsfdsdfsdf() throws InterruptedException {
        ReentrantLock l = new ReentrantLock();
        Condition condition = l.newCondition();
        new Thread(()->{
            l.lock();
            System.out.println("线程1获取到锁了，await一下");
            try {
                condition.await();
                System.out.println("线程1await，结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            l.unlock();
        }).start();
        Thread.sleep(500);
        new Thread(()->{
            l.lock();
            System.out.println("线程2获取到锁");
            condition.signal();
//            condition.signalAll();
            l.unlock();
            System.out.println("线程2解锁");
        }).start();


        Thread.sleep(220000000);
    }

    ReentrantLock lx = new ReentrantLock();
    @Test
    public void 验证ReentrantLock的公平性() throws InterruptedException {
        createThread("线程1");
        Thread.sleep(10);
        createThread("线程2");
        Thread.sleep(10);
        createThread("线程3");
        Thread.sleep(10);
        createThread("线程4");
        Thread.sleep(10);
        createThread("线程5");
        Thread.sleep(10);
        createThread("线程6");
        Thread.sleep(10);
        createThread("线程7");
        Thread.sleep(10);
        createThread("线程8");
        Thread.sleep(10);
        createThread("线程9");
        Thread.sleep(10);
        createThread("线程10");
//        System.out.println("线程创建完毕");
        Thread.sleep(3000);
    }


    void createThread(String name){
        reenTrantLock(name);
//        synchronizedddddd(name);
    }

    void reenTrantLock(String name){
        new Thread(()->{
            System.out.println(name+"开始枷锁");
            lx.lock();
            System.out.println(name+"枷锁成功");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+"解锁成功");
            lx.unlock();
        },name).start();
    }
    String dd = "";
    void synchronizedddddd(String name){
        new Thread(()->{
            synchronized (dd){
                System.out.println(name+"枷锁成功");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name+"解锁成功");
            }
        },name).start();
    }
    ReentrantLock reentrantLock = new ReentrantLock();
    @Test
    public void dsdsdsdsdsdsd() throws InterruptedException {
        for(int i = 1; i < 51; i ++){
            int finalI = i;
            new Thread(() -> {
                System.out.println("开始启动线程" + finalI);
                running("t" + finalI);
            },"t" + finalI).start();
        }
        Thread.sleep(5000);
    }private void running(String threadName){
        reentrantLock.lock();
        System.out.println(threadName + "...");
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }



}

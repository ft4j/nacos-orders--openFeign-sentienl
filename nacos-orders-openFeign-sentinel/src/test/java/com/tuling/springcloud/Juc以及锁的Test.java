package com.tuling.springcloud;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Juc以及锁的Test {
    @Test
    public void fsdfsdf() throws InterruptedException {
        ReentrantLock rl = new ReentrantLock(true);
        rl.lock();
        rl.tryLock();
        rl.tryLock(10, TimeUnit.SECONDS);

        new Thread(()->{
            rl.lock();

        }).start();



    }

    /**
     * lock和tryLock
     * lock会持续等待，tryLock会尝试加锁之后成功就加锁成功，失败就不加锁
     * 使tryLock要注意用加锁结果判断要不要解锁，否则报错
     * @throws InterruptedException
     */
    @Test
    public void sfsdfsd() throws InterruptedException {
        ReentrantLock rl = new ReentrantLock(true);
//      rl.lock();
//      rl.tryLock();
//      rl.tryLock(10, TimeUnit.SECONDS);

        new Thread(()->{
            rl.lock();
            System.out.println("进入第一个线程，并获取锁");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rl.unlock();
        }).start();
        Thread.sleep(500);
        new Thread(()->{
            boolean b = false;
            try {
                b = rl.tryLock(6, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("进入第二个线程，并获取锁");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(b){
                rl.unlock();
            }
        }).start();
        //使用idea测试多线程时，不能让主线程直接停止，否则子线程的数据也不会被打印
        Thread.sleep(10000);
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void sdfsdf() throws InterruptedException {
        ReentrantReadWriteLock rrw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rrw.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rrw.writeLock();
        /**
         * 第一个线程打印1
         * 第一个线程打印2
         * 这是第二个线程1
         * 这是第二个线程2
         * 这是第三个线程
         */
        new Thread(()->{
            readLock.lock();
            System.out.println("第一个线程打印1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一个线程打印2");
            readLock.unlock();
        }).start();
        Thread.sleep(200);
        new Thread(()->{
            writeLock.lock();
            System.out.println("这是第二个线程1");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("这是第二个线程2");
            writeLock.unlock();
        }).start();
        Thread.sleep(200);
        new Thread(()->{
            readLock.lock();
            System.out.println("这是第三个线程");
            readLock.unlock();
        }).start();

        Thread.sleep(10000);
        System.out.println("结束");
    }

    @Test
    public void dddd() throws InterruptedException {
        ReentrantLock r = new ReentrantLock();
        Thread d = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"d开始加锁");
            r.lock();
            System.out.println(Thread.currentThread().getName()+"d加上锁了，刺激");
            try {
                Thread.sleep(5000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.unlock();
        });
        d.start();

        Thread d1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"d1开始加锁");
            r.lock();
            System.out.println(Thread.currentThread().getName()+"d1加上锁了，刺激11121212");
            try {
                Thread.sleep(5000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.unlock();
        });
        d1.start();
        Thread.sleep(500000000);
    }

}

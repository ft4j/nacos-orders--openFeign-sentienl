package com.tuling.springcloud;

import org.junit.Test;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JdkLockTest {
    /**
     * 这个锁的设计和分布式锁的设计非常相似
     * 都是可重入锁，可以实现公平锁
     * 都可以进行tryLock，可以给尝试加锁添加一个超时时间
     * 加锁成功之后，锁的时间都是无线的，不执行完不会交出锁
     * reentrantLock依靠jdk完成加锁，redisson则依靠锁续命保证锁的无限长
     * reentrantLock不可以添加锁的过期时间，redisson可以添加锁的过期时间，避免锁续命
     * @throws InterruptedException
     */
    @Test
    public void ReentrantLock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        lock.lock();
        lock.unlock();
        /**
         * tryLock会尝试去加锁，成功返回true，失败返回false
         * 程序员可以根据返回的数据，继续下一步操作
         */
        lock.tryLock();
        lock.unlock();
        /**
         * 根据以下参数，该加锁操作会在3秒内不断循环，直到加锁成功返回true
         * 如果3秒之后还没加锁成功，那么返回false
         */
        lock.tryLock(3, TimeUnit.SECONDS);
        lock.unlock();
        /**
         * 无限等待的加锁操作，加锁不成功就一直等待
         */
        lock.lock();
        lock.unlock();
    }

    /**
     * 这是测试读写锁使用的用来读写的参数i
     * 对于ReadWriteLock而言，一个资源能够被多个读线程访问，”或者“被一个写线程访问
     *
     */
    private Integer i = 0;
    @Test
    public void fdff() throws InterruptedException {
        ReentrantReadWriteLock rrw = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock readLock = rrw.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rrw.writeLock();
        //*********************读锁测试开始**********************************
        /**
         * 下面代码中，i会被两个线程同时
         */
        Thread d1 = new Thread(()->{
            readLock.lock();
            System.out.println(i+"d1");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                readLock.unlock();
            }
        });
        d1.start();

        Thread.sleep(500);

        Thread d2 = new Thread(()->{
            readLock.lock();
            System.out.println(i+"d2");
            readLock.unlock();
        });
        d2.start();
        //************************读锁测试结束***********************************************
    }

    /**
     * lock.lockInterruptibly();用这种方式加锁的数据可以被主线程用t1.interrupt();阻断，让其他线程获取锁
     * @throws InterruptedException
     */
    @Test
    public void testLockInterruptibly() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {

            try {
                lock.lockInterruptibly();
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            boolean locked = false;
            try {
                System.out.println("线程2尝试拿到锁");
                locked = lock.tryLock(4, TimeUnit.SECONDS);
                if (locked) {
                    System.out.println("线程2拿到锁");
                } else {
                    System.out.println("线程2没能拿到锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (locked) {
                    lock.unlock();
                }
            }
        });
        t2.start();
        Thread.sleep(1000);
        t1.interrupt(); // 中断t1线程，使t1线程可以释放锁
    }


    /**
     * 注意，读写锁指的是读锁和写锁两个代码块之前互相影响
     * 读锁锁住代码块1，那么写锁包含的代码块就hold住了，读写锁可以一把锁锁两块代码
     */
    private Map<String, Object> map = new ConcurrentHashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.WriteLock rw = readWriteLock.writeLock();
    ReentrantReadWriteLock.ReadLock rr = readWriteLock.readLock();

    /**
     * 打印结果
     * 线程1加锁并睡着
     * 线程2加锁并睡着
     * 线程2醒了
     * 线程1醒了
     * @throws InterruptedException
     */
    @Test
    public void 读读不互斥() throws InterruptedException {
        new Thread(()->{
            rr.lock();
            try {
                System.out.println("线程1加锁并睡着");
                Thread.sleep(5000);
                System.out.println("线程1醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rr.unlock();
        }).start();

        new Thread(()->{
            rr.lock();
            try {
                System.out.println("线程2加锁并睡着");
                Thread.sleep(5000);
                System.out.println("线程2醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rr.unlock();
        }).start();

        Thread.sleep(8000);

    }

    /**
     * 线程1加锁，线程二的代码块，被锁住了
     * 打印结果：
     * 线程1加锁并睡着
     * 线程1醒了
     * 线程2加锁并睡着
     * 线程2醒了
     * @throws InterruptedException
     */
    @Test
    public void 读写互斥() throws InterruptedException {
        new Thread(()->{
            rr.lock();
            try {
                System.out.println("线程1加锁并睡着");
                Thread.sleep(5000);
                System.out.println("线程1醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rr.unlock();
        }).start();
        Thread.sleep(200);
        new Thread(()->{
            rw.lock();
            try {
                System.out.println("线程2加锁并睡着");
                Thread.sleep(5000);
                System.out.println("线程2醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rw.unlock();
        }).start();

        Thread.sleep(200000);
    }

    @Test
    public void 写写互斥() throws InterruptedException {
        new Thread(()->{
            writeLock();
        }).start();
        new Thread(()->{
            writeLock();
        }).start();

        Thread.sleep(8000);
    }

    public void writeLock(){
        try {
            rw.lock();
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rw.unlock();
        }
    }



}

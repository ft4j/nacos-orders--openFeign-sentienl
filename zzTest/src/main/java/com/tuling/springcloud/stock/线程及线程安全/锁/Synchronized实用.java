package com.tuling.springcloud.stock.线程及线程安全.锁;

public class Synchronized实用 {
    /**
     * 加锁的对象是this  方法1和方法2、方法3不互斥
     * @throws InterruptedException
     */
    public synchronized void methodSync() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"进入普通方法，sleep5秒:"+System.currentTimeMillis());
        Thread.sleep(5000);
    }


    /**
     * 加锁的对象是 Synchronized实用.class 所以它跟方法3会互斥
     * @throws InterruptedException
     */
    public static synchronized void staticMethodSync() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"进入静态方法，sleep5秒:"+System.currentTimeMillis());
        Thread.sleep(5000);
    }

    public static  void methodClassSync() throws InterruptedException {
        synchronized(Synchronized实用.class){
            System.out.println(Thread.currentThread().getName()+"进入静态方法，sleep5秒:"+System.currentTimeMillis());
            Thread.sleep(5000);
        }
    }
}

package com.tuling.springcloud.stock.线程及线程安全.锁;

import org.junit.Test;

import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicReference;

public class Synchronized及volatileTest {
    /**
     * 线程安全三原则：可见性、一致性、顺序性
     * 可见性：内存可见性
     * 一致性：结果一致性
     * 顺序性：防止指令重排
     *
     * synchronized可以解决以上三个问题
     * volatile可以解决可见性和顺序性问题
     *
     * synchronized原理及锁升级：
     *  在jdk1.5之前，syn不存在锁升级过程，cyn的原理为获取加锁对象的monitor的持有权
     *  并使用monitorEnter指令和monitorExit指令来完成加锁和解锁的过程
     *
     *  在jdk1.5之后加入了，锁升级的过程，synchronized性能不在差劲
     *  锁升级将锁分为4个阶段  1、偏向锁  2、轻量级锁 3、自旋锁  4、重量级锁
     *  1、偏向锁：当一个锁不存在竞争的时候，锁被重入的时候，会校验对象头的线程ID，如果一致就省去加锁过程，直接重入，并在重入次数上+1，
     *  偏向锁只能被一个线程持有，当另外一个线程竞争这个对象并加锁时，这个线程继续去获取锁，就会匹配线程ID失败，此时取消偏向锁升级为轻量级锁
     *  2、轻量级锁：当线程交替获取锁，并且没发生锁竞争的时候，synchronized表现出轻量级锁，各个线程交替获取锁，放弃锁，其他线程恰好也不阻塞
     *  3、自旋锁：当发生锁竞争时，那个被阻塞线程会进入自选状态，消耗一定的CPU资源，但不会挂起，免去线程唤醒及切换的时间，提升性能
     *  4、重量级锁：当synchronized代码块性能一般，超出了自旋锁的自选次数，锁升级为重量级锁，重量级锁就跟JDK1.5之前的一样了，性能一般
     *
     *
     *  锁消除：当一个synch代码块被写在一个完全不可能出现并发的位置时，synch的功能会被消除
     */
    static int s = 0;
    //这里展示一个线程安全问题
    @Test
    public void dsdsds() throws InterruptedException {
        Integer dd = 0;
        Thread d1 = new Thread(()->{
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (dd){
                    s++;
                }
            }
        });
        Thread d2 = new Thread(()->{
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (dd){
                    s++;
                }
            }
        });
        d1.start();
        d2.start();
        d1.join();
        d2.join();
        System.out.println(s);
    }


    @Test
    public void 静态方法加锁测试() throws InterruptedException {
        //注意 静态方法的synchronized可以锁住整个类中，带有synchronized的方法
        //但是 成员方法的synchronized代码块不能被锁住，因为他们加锁的对象不同
        //静态方法的加锁对象是class对象，成员方法对象本身this
        Thread d1 = new Thread(()->{
            StaticMethodYcn.syn();
        });
        d1.start();
        Thread.sleep(200);
        Thread d2 = new Thread(()->{
            StaticMethodYcn.dddddd();
        });
        d2.start();
        d1.join();
        d2.join();
    }

    @Test
    public void dsggg(){
        String ddd = ddd();
        System.out.println(ddd);
    }
    public String ddd(){
        try{
            return "try";
        }catch(Exception e){
            return "catch";
        }finally {
            System.out.println("finally");
        }
    }

    AtomicReference<LinkedBlockingDeque> at = new AtomicReference<LinkedBlockingDeque>();

    @Test
    public void sdsdsd() throws InterruptedException {
        LinkedBlockingDeque d = at.get();
        d.add("a");
        d.offer("b");

        d.put("c");//对应take
        System.out.println(d);
        d.push("1");
        d.push("2");
        d.push("3");
        d.push("4");
        d.push("5");
        System.out.println(d);
        Iterator iterator = d.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if("3".equals(next)){
                iterator.remove();
                d.push(next);
            }

        }

        System.out.println(d);
        at.compareAndSet(at.get(),d);
    }

    /**
     * 测试加锁方式和阻塞方式的区别
     */
    @Test
    public void dddasdasd() throws InterruptedException {
        new Thread(()->{
            Synchronized实用 s = new Synchronized实用();
            try {
                s.staticMethodSync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程2！").start();
        Thread.sleep(500);
        new Thread(()->{
            Synchronized实用 s = new Synchronized实用();
            try {
                s.methodSync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程1！").start();
        Thread.sleep(200);
        new Thread(()->{
            Synchronized实用 s = new Synchronized实用();
            try {
                s.methodClassSync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程3！").start();

        Thread.sleep(15000);
    }
}

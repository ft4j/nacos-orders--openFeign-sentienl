package com.tuling.springcloud.stock.threadLocal.深入理解;

import com.tuling.springcloud.stock.threadLocal.Class1;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal概论：
 *  ThreadLocal存在的数据结构：
 *      1）Thread->ThreadLocal.ThreadLocalMap->Entry，只要使用了ThreadLocal的set方法，Entry[]就会一直存在且寿命和Thread一致
 *  1、什么是内存泄漏？
 *      一个对象在后续的代码中永远不可达，但还存在于内存中，并且不能被垃圾回收。
 *      可以得出结论，一个静态对象不肯能被认定为内存泄漏，虽然它一致存在于内存中，但是它随意可以被使用，永远可达！这也可以解释第二点的疑惑！
 *      如果不可达但是可以被回收，那就是垃圾，垃圾也不是内存泄漏！
 *  2、ThreadLocal怎么发生内存泄漏？
 *      而成员变量则不一样：比如作为成员变量的ThreadLocal，当它所在的方法执行完，这个ThreadLocal肯定无法再被使用了，但是它的值，依然被
 *      Thread->ThreadLocalMap->Entry引用着，如果在线程还在被执行，那这个ThreadLocal永远无法被获取，gc无法回收value对象，
 *      内存泄漏就发生了。如果发生gc时，已经出栈的方法中的ThreadLocal被回收，Entry就是Entry(null,object)，这个样子了。
 *
 *      注意：这里泄漏的不是ThreadLocal对象，而是它对应的值！
 *
 *  3、为什么当ThreadLocal和Thread寿命一样或者比线程寿命更长时，不会发生内存泄漏？
 *      根据1的内容可知，静态变量的ThreadLocal数据不会内存泄漏，因为他们时刻可达，且自身一直被强引用
 *      当线程销毁之后，线程所对应的所有引用都不存在了，Entry[]本身都成为垃圾需要被回收了，Entry无论是什么形状
 *      都是满脸垃圾的样子，发生gc就消失了。
 *
 *      *******************************
 *      注意嗷，ThreadLocal本身不存储任何数据的（值其实都在Thread中），它就算一直存在也不会内存泄漏
 *      ThreadLocal作为被引用者，它的存在不会影响Entry被回收。
 *      *******************************
 *
 *  4、如何避免ThreadLocal内存泄漏？
 *      1）用完就调用remove方法
 *      2）不要在常驻线程中使用ThreadLocal（线程短命，即使泄漏问题也不大）
 *      3）只使用静态的ThreadLocal（寿命长的很）
 *      4）看看ThreaLocal的set方法中的replaceStaleEntry，它会被key为null的entry的引用重新指向null，让那些key为null值被回收
 *  5、所以弱引用的作用是？避免ThreadLocal对象的内存泄漏！
 *      使用ThreadLocal时，程序对ThreadLocal的引用有两个，
 *      1）Thread->ThreaaLocalMap->Entry的key->ThreadLocal
 *      2）new ThreadLocal的地方
 *      已知静态的ThreadLocal不存在内存泄漏
 *      那么局部变量的ThreadLocal，有如下场景，它所在的方法已经出栈，线程对ThreadLocal的强引用不存在的了
 *      这时候发生gc，如果Entry中的key----ThreadLocal如果是强引用，那么这时候的ThreadLocal已经没用了还是不能被回收，内存泄漏就发生了
 *      如果Entry对key----ThreadLocal的引用是弱引用，用完的ThreadLocal就被垃圾回收了！
 *
 *      所以，为什么弱引用？！！！！！！
 *      1、当ThreadLocal出栈，但线程未结束，那么弱引用至少可以让Entry的key（就是ThreadLocal本身）不泄露。（value依然泄漏哦）
 *      2、当使用set/get方法时，会删除key为null的value，多一重保障！（key在垃圾回收后就为null嘛）
 */
public class ThreadLocalTest {
    @Test
    public void fsdfsdf(){
        Meth1 m1 = new Meth1();
        m1.dsdsd();
        Meth2 m2 = new Meth2();
        m2.dfsdf();
    }


    @Test
    public void 为什么内存泄漏() throws InterruptedException {
        Thread thread = Thread.currentThread();
        ThreadLocalTest test = new ThreadLocalTest();
        test.test1();
        System.gc();//test1执行完出栈，test1方法中的 threadLocal1、threadLocal2 指向的对象，gc时会被回收
        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread);//debug 观察当前线程的 threadLocals 属性值：1、2对应的entry的key变成null
    }
    //entry的key为什么用弱引用？
    //若不用弱引用，会导致方法执行完 threadLocal1、threadLocal2，无法被gc
    //因为，Entry实例的key指向这个ThreadLocal实例
    public void test1() throws InterruptedException {
        ThreadLocal threadLocal1 = new ThreadLocal();
        threadLocal1.set(147);
        ThreadLocal threadLocal2 = new ThreadLocal();
        threadLocal2.set(258);
        Thread thread = Thread.currentThread();
        System.out.println(thread);//debug 观察当前线程的 threadLocals 属性值：1、2对应的entry的key有值

        /**
         * 这时候的ThreadLocal是不会被回收的，无论怎么GC，因为当前方法的”栈帧“引用了这两个ThreadLocal，并且是强引用
         * 如果这个方法结束，那么这个方法的”栈帧“就弹出了”栈“，强引用就不存在了，此时threadLocal就只存在一个弱引用
         *
         */
        System.gc();
        Thread.sleep(5000);
        System.out.println("ddddd");
    }

    @Test
    public void dsdsdsd(){
        Class1 c = new Class1();
    }

    @Test
    public void 虚引用测试(){
        String str = new String("abc");
        ReferenceQueue queue = new ReferenceQueue();
        // 创建虚引用，要求必须与一个引用队列关联
        PhantomReference pr = new PhantomReference(str, queue);

        for (int i = 0; i < 50; i++) {
            System.gc();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Reference poll = queue.poll();
            if(poll != null){
                System.out.println("虚引用被回收了！");
            }
        }

    }


    @Test
    public void dfsdfsdf(){
        ThreadLocal t = new ThreadLocal();
        t.set("dd");

        Object o = t.get();
        System.out.println(o);
    }


    @Test
    public void dddddddd(){
        ThreadLocal<String> tl = new ThreadLocal<String>();
        tl.set("ddd");
    }

    /**
     * 模拟ThreadLocal的多线程，同一个key获得不同value案例
     */
    @Test
    public void sadssd(){

        String ss = new String("dddd");
        Thread d = new Thread(()->{
            Map m = new HashMap<String,String>();
            m.put(ss,"线程1");
            System.out.println(m.get(ss));
        });
        Thread d1 = new Thread(()->{
            Map m = new HashMap<String,String>();
            m.put(ss,"线程2");
            System.out.println(m.get(ss));
        });

        d.start();
        d1.start();

    }

    @Test
    public void ffgfg(){
        Meth1 m1 = new Meth1();
        m1.dsdsd();
        Meth2 m2 = new Meth2();
        m2.dfsdf();
    }


    @Test
    public void sdsd() throws IOException {
        FileInputStream fis = new FileInputStream("");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bf = new BufferedReader(isr);
        bf.readLine();
    }
    @Test
    public void dfsfsdfgfdgdfg(){
        ThreadLocal tl = new ThreadLocal();
        tl.set("123");
        tl.set("456");
        System.out.println(tl.get());

        ThreadLocal x = new ThreadLocal();
        x.set("zzzzzz");
        System.out.println(x.get());

        System.out.println(tl.get());
    }
}

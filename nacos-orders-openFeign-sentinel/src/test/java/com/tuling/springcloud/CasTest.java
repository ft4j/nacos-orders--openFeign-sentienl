package com.tuling.springcloud;

import com.tuling.springcloud.orders.domain.JustObject;
import com.tuling.springcloud.orders.domain.Result;
import com.tuling.springcloud.orders.domain.Users;
import com.tuling.springcloud.orders.线程池.Dddd;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasTest {
    /**
     * volatile的作用，一个线程写，多个线程读的情况下，可以保证线程安全，但是不能保证多线程写的线程安全，因为volatile不存在原子性
     * ……………………
     * volatile可见性实现原理：原本一个变量的值存储在主内存中，当一个线程需要操作它的时候，就会读取这个主内存中的主到线程的本线程的工作内存当中
     * 一般来说，在本地内存当中的值才可以被线程操作。但是被添加了volatile的变量，读取数据都是直接从主内存读取，因此当一个线程修改数据之后，其他线程
     * 也会同步读取到修改之后的值。但是由于修改值的操作，并不是原子性的，所以volatile不能保证多线程操作下的值线程安全，只有单线程修改，多线程读取的
     * 场景下，该值永远读取的是最新的主内存的值，而不是本线程的工作内存中的值。因此可以保证可见性。
     * ……………………
     * volatile有序性实现原理：
     * LoadLoad	    Load1; LoadLoad; Load2	保证load1的读取操作在load2及后续读取操作之前执行
     * StoreStore	Store1; StoreStore; Store2 在store2及其后的写操作执行前，保证store1的写操作已刷新到主内存
     * LoadStore	Load1; LoadStore; Store2  在stroe2及其后的写操作执行前，保证load1的读操作已读取结束
     * StoreLoad	Store1; StoreLoad; Load2  保证store1的写操作已刷新到主内存之后，load2及其后的读操作才能执行

     *
     * compareAndSwap，比较并替换。当预期值等于内存中的值时，将原来的值替换为新的值，否则进入循环，直到满足条件，将原来的值替换为新的值
     * 作用：AtomicInteger可以保证一个int类型的数据单词计算的线程安全性。这是一个cas原理下实现的乐观锁
     * AtomicInteger实现加减的原子性具有 1、粒度小 2、可读性好 3、当并发不高的时候性能好且消耗的资源也少
     * 缺点为：1、只能实现单个值的简单运算  2、并发高的时候非常消耗内存  3、可能存在aba问题
     * @throws InterruptedException
     */
    @Test
    public void testAtomicInteger() throws InterruptedException {
        new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                addCPunt();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                addCPunt();
            }
        }).start();
        Thread.sleep(1000);
        System.out.println(ai.get());
    }

    AtomicInteger ai = new AtomicInteger(0);

    /**
     * 如何解决cas的ABA问题
     * 直接初始化AtomicStampedReference的初始值，并添加对应的版本号
     */
    AtomicStampedReference<String> asr = new AtomicStampedReference<String>("abc",0);

    void addCPunt(){
        ai.addAndGet(1);
    }
    AtomicLong aii = new AtomicLong(0);

    @Test
    public void testUnsafe(){
        final Unsafe us = Unsafe.getUnsafe();
        Integer s = 12;
        int andAddInt = us.getAndAddInt(s, 2, 2);
        System.out.println(andAddInt);
    }


    private  sun.misc.Unsafe UNSAFE;
    @Test
    public void 实例化() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException, CloneNotSupportedException {
        //直接用class的newInstance
        Class aClass = Class.forName("com.tuling.springcloud.orders.domain.Result");
        Object o = aClass.newInstance();

        //获取到对象的constructor进行newInstance
        Constructor[] constructors = aClass.getConstructors();
        Constructor constructor = constructors[0];
        Object o1 = constructor.newInstance("","","");

        //直接new
        Result s = new Result("","");

        //unsafe类创建
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        UNSAFE = (Unsafe) field.get(null);
        Object o2 = UNSAFE.allocateInstance(aClass);
        System.out.println(o2);

        //clone创建对象
        JustObject j = new JustObject();
        j.setName("张三");
        Users u1 = new Users("1","2",j);
        //这个只是拷贝了引用
        Users u3 = u1;
        Users u2 = u1.clone();
        //u1和u2指向不同的对象，说明u2是u1的拷贝对象，但是JustObject指向同一对象，说明java提供的clone是浅clone
        /**
         * clone方法实现神拷贝需要非基本类型的字段也进行clone才能实现神拷贝
         * 还要就是通过流来实现深拷贝
         */
        System.out.println("u1:"+u1.toString());
        System.out.println("u2:"+u2.toString());
        System.out.println("u1:"+u1.getJustObject());
        System.out.println("u2:"+u2.getJustObject());

        //执行静态代码块，获取class就会执行静态代码
        Class classes = Class.forName("com.tuling.springcloud.orders.domain.Student");
        //静态代码块只能执行一次
        Class classess = Class.forName("com.tuling.springcloud.orders.domain.Student");
        //执行非静态代码块，再执行构造方法
        classes.newInstance();
        //执行非静态代码块，再执行构造方法
        classes.newInstance();
        Field user = classes.getDeclaredField("user");
        user.setAccessible(true);
        //静态对象可以这样子直接获取字段的值，但是非静态对象无法获取
        Object o3 = user.get(null);
        System.out.println(o3);
    }


    @Test
    public void dsdafsfsdf(){
        ExecutorService es = new Dddd(1,1,1, TimeUnit.SECONDS,new LinkedBlockingDeque<>(10));
        es.shutdownNow();
    }



    @Test
    public void sdsdsds(){

    }
}

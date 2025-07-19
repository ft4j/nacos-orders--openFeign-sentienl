package com.tuling.springcloud.stock.线程及线程安全.锁;

import org.junit.Test;

public class Volatile {

    /**
     * 有序性问题，以下代码如果是按照顺序执行的，那么X和Y是不可能同时=0的。
     *
     * volatile的实现：
     *  1、可见性：java被创建时会存在于工作内存，并且会在很短的时间内和主内存进行数据的同步，但是其他线程无法第一时间获取该对象的值
     *      volatile修饰的参数会直接存在于主内存，这会对所有线程可见，保证了数据的可见性
     *  2、有序性：指令重排就只能发生到volatile修饰对象的前后，volatile对象不会被重排(通过内存挡板去实现)
     */
    private int a,b,x,y=0;

    @Test
    public void testVolatile() throws InterruptedException {
        int i = 0;
        while(true){
            i++;
            //每次重设
            x=0;y=0;a=0;b=0;
            //开两个线程，第一个线程执行a=1;x=b;第二个线程执行b=1;y=a
            Thread thread1=new Thread(new Runnable() {
                @Override
                public void run() {
                    //线程1会比线程2先执行，因此用nanoTime让线程1等待线程2 0.01毫秒
//                    shortWait(10000);
                    a=1;
                    x=b;
                }
            });
            Thread thread2=new Thread(new Runnable() {
                @Override
                public void run() {
                    b=1;
                    y=a;
                }
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            //等两个线程都执行完毕后拼接结果
            String result="第"+i+"次执行x="+x+"y="+y;
            //如果x=0且y=0，则跳出循环
            if (x==0&&y==0){
                System.out.println(result);
                break;
            }else{
                System.out.println(result);
            }
        }
    }


    //等待interval纳秒
    private static void shortWait(long interval) {
        long start=System.nanoTime();
        long end;
        do {
            end=System.nanoTime();
        }while (start+interval>=end);
    }
}

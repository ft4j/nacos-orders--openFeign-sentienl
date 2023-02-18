package com.tuling.springcloud;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class CountDownLatchTest {
    /**
     * 根据一下两个test的结论，可以得出结果：countDownLatch和cyclicBarrier阻挡的对象不一样！
     * countDownLatch挡主线程
     * cyclicBarrie挡被调用线程
     *
     * countDownLatch是给调用线程准备的的api。countDownLatch的await()方法在调用线程中执行。
     * countDownLatch会让调用线程暂停，并让被调用线程使用countDown()方法让countDownLatch的挡板层数-1
     * 当countDownLatch的挡板层数为0时，调用线程才继续执行下去
     * countDownLatch的挡板数，不会在归零之后自行恢复
     * ---------------------------------------
     * cyclicBarrier是给被调用线程准备的api。cyclicBarrier的await()方法在被调用线程中执行。
     * cyclicBarrier会让被调用线程暂停，每暂停一次，cyalicBarrier的挡板数-1
     * 当cyclicBarrier的挡板数为0时，被调用线程才会继续执行countDownLatch的await()方法之后的代码。
     * cyclicBarrier的挡板数量会在归零之后，自动恢复为构造函数中的层数
     * @throws InterruptedException
     */
    @Test
    public void testCountDownLatch() throws InterruptedException {
        //这里模拟4个玩家准备好之后，游戏开始
        CountDownLatch cdl = new CountDownLatch(4);
        for (int i = 0; i < cdl.getCount(); i++) {
            int f = i;
            new Thread(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("玩家："+ f +"已经准备完毕");
                cdl.countDown();
            }).start();
        }
        System.out.println("正在等待玩家准备完成");
        cdl.await();
        System.out.println("开始游戏");
    }

    @Test
    public void testCyclicBarrier() throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            CountDownLatch cdl = new CountDownLatch(3);
            new Thread(()->{
                try {
                    System.out.println("线程1执行到cb的await了");
                    cdl.countDown();
                    cb.await();
                    System.out.println("线程1被放开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
            new Thread(()->{
                try {
                    System.out.println("线程2执行到cb的await了");
                    cdl.countDown();
                    cb.await();
                    System.out.println("线程2被放开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    System.out.println("线程3执行到cb的await了");
                    cdl.countDown();
                    cb.await();
                    System.out.println("线程3被放开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
            cdl.await();

            Thread.sleep(1000);
            System.out.println("-----------");
        }
    }



    @Test
    public void testSemaphore(){
        Semaphore s = new Semaphore(1);
        new Thread(()->{
            try {
                System.out.println("A执行获取permit");
                s.acquire();
                for (int i = 0; i < 120000000; i++) {

                }
                s.release();
                System.out.println("A执行完成，释放permit");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println("B执行获取permit");
                s.acquire();
                s.release();
                System.out.println("B执行完成，释放permit");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

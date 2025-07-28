package com.tuling.springcloud;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class CountDownLatchTest {
    /**
     * 根据一下两个test的结论，可以得出结果：countDownLatch和cyclicBarrier阻挡的对象不一样！
     * countDownLatch挡主线程（倒计时闩锁）
     * cyclicBarrie挡被调用线程（循环挡板）
     * Semaphore（信号量）
     *
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
     *
     * Semaphore在构造是传入一个int值，这个值就是总信号量
     * Semaphore的aquire方法会获取一个信号，release方法会释放一个信号。
     * 当信号量为0时，想要获取信号的线程会被阻塞
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
                s.tryAcquire();
                s.tryAcquire(2);
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

    /**
     * CountDownLatch
     */
    @Test
    public void fsdfsdf() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(3);
        System.out.println("正在启动游戏...");
        Thread d1 = new Thread(()->{
            System.out.println("模块1加载中");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cdl.countDown();
        });
        d1.start();
        Thread d2 = new Thread(()->{
            try {
                d1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("模块2加载中");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cdl.countDown();
        });
        d2.start();
        Thread d3 = new Thread(()->{
            try {
                d2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("模块3加载中");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cdl.countDown();
        });
        d3.start();
        cdl.await();
        System.out.println("加载完成");

    }

    /**
     *
     */
    @Test
    public void cycccc() throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(3);
        new Thread(()->{
            System.out.println("第一个线程挡板前");
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("第一个线程挡板后");
        }).start();
        new Thread(()->{
            System.out.println("第二个线程挡板前");
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("第二个线程挡板后");
        }).start();
        new Thread(()->{
            System.out.println("第三个线程挡板前");
            try {
                System.out.println("第三个线程睡5秒");
                Thread.sleep(5000);
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("第三个线程挡板后");
        }).start();
        Thread.sleep(10000);
    }

    @Test
    public void afsdfsdf() throws InterruptedException {
        Semaphore sp = new Semaphore(2);
        new Thread(()->{
            try {
                sp.acquire();
                System.out.println("线程1获取信号量");
                Thread.sleep(2000);
                sp.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                sp.acquire();
                System.out.println("线程2获取信号量");
                Thread.sleep(2000);
                sp.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                sp.acquire();
                System.out.println("线程3获取信号量");
                Thread.sleep(2000);
                sp.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                sp.acquire();
                System.out.println("线程4获取信号量");
                Thread.sleep(2000);
                sp.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
    }

}

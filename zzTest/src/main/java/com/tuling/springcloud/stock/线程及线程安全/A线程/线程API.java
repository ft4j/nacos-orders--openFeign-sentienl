package com.tuling.springcloud.stock.线程及线程安全.A线程;

import org.junit.Test;

public class 线程API {
    /**
     * d.join();的位置
     * @throws InterruptedException
     */
    @Test
    public void dsdsd() throws InterruptedException {
        Thread d = new Thread(()->{
            System.out.println("ddd");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(200);
        Thread d1 = new Thread(()->{
            try {
                d.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("sssss");
        });
        d.start();
        d1.start();
        Thread.sleep(5000);
    }

    /**
     * yield()方法可以让出CPU的使用权，但并不是一定会让其他线程先成执行
     */

    /**
     * 如何推出线程
     * 运行过程中添加表示让他正确退出
     * Thread.stop();过时的方法，可能导致数据完整性被破坏
     * 让线程在执行过程中出错
     * 通过给线程添加状态，并获取这个状态，判断之后让线程退出 interrupted()，这是重点
     */
    @Test
    public void fsfsdf(){
        Thread d = new Thread();
        //这个会让线程直接退出，可能破怪数据的完整性，比如包含事务的场景，这可能导致数据库的锁不被释放
        d.stop();

        Thread dd = new Thread(()->{
            //加一个标识让他正确退出
            Integer ii = readInteger();
            if(ii == 3){
                return;
            }
            if(ii == 4){
                int i = 1/0;
            }

            if(Thread.interrupted()){
                return;
            }
        });

        /**
         * 这里将展示线程打断最合理的方式interrupt
         * interrupt是一个实例方法，它可以个对应线程添加一个打断标识
         * 告诉这个线程，有人想打断它
         * 它可以直接让sleep的线程报错，
         */
        Thread.currentThread().interrupt();
        dd.interrupt();
        /**
         * 线程的静态方法，如果一个线程被调用过interrupt()方法，那它会返回true
         * 否则，返回false，调用这个方法之后，打断标识会被重置为false，所以不可以用它重复判断状态
         */
        Thread.interrupted();
        /**
         * 线程的实例方法，如果一个线程被调用过interrupted()放，那他会返回true
         * 否则，返回false.调用这个方法之后，打断表示不会被重置，所以它可以多次判断
         */
        Thread.currentThread().isInterrupted();


    }
    public static Integer integer = 3;
    public Integer readInteger(){
        return integer;
    }

    /**
     * wait/notify的使用，以及和sleep区别
     */
    @Test
    public void dsdsfsdfsdf() throws InterruptedException {
        String dd = "";
        String cc = "";
        /**
         * this.wait();
         * 在没有synchronized修饰的代码块调用wait()方法，会爆IllegalMonitorStateException异常
         * wait()方法其实是让该对象放弃synchronized锁的持有
         * notify()方法，是让wait的线程重新加入到锁竞争中来，不再停止执行，他是按照wait()的顺序唤醒的
         * notifyAll()，则是一次性唤醒所有wait()的线程
         *
         * wait()的根本作用是给synchronized代码块除了执行结束之外的另一种放弃锁的方式
         */
        Thread d1 = new Thread(()->{

            synchronized (dd){
                try {
                    System.out.println("d1sleep前："+System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("d1sleep后，开始wait："+System.currentTimeMillis());
                    dd.wait();
                    System.out.println("d1结束wait："+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        d1.start();
        Thread d3 = new Thread(()->{
            synchronized (dd){
                try {
                    System.out.println("d3sleep前："+System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("d3sleep后，开始wait："+System.currentTimeMillis());
                    dd.wait();
                    System.out.println("d3结束wait："+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        d3.start();
        Thread.sleep(4500);
        Thread d2 = new Thread(()->{
            synchronized (cc){
                System.out.println("d2进来了！："+System.currentTimeMillis());
                dd.notifyAll();
                System.out.println("notify之后！："+System.currentTimeMillis());
            }
        });
        d2.start();

        Thread.sleep(10000);
    }


}

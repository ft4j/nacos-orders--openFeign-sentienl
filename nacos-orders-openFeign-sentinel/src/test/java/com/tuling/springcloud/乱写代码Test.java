package com.tuling.springcloud;

import com.tuling.springcloud.orders.job.cl.AppleComputer;
import com.tuling.springcloud.orders.job.cl.Computer;
import com.tuling.springcloud.orders.job.cl.DellComputer;
import com.tuling.springcloud.orders.job.cl.HuaweiComputer;
import com.tuling.springcloud.orders.线程池.CallableImp;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class 乱写代码Test {
    @Test
    public void dsdsdsd(){
        List l = new ArrayList<String>();
        Object[] objects = l.toArray();
        List<String> strings = Arrays.asList("", "", "");
        String[] strs = {"1","2","3"};
        String[] strss = new String[5];
        String[] strsss = new String[]{"1","3","4"};
        List<String> strings1 = Arrays.asList(strs);
        System.out.println(strings1);
//        new ArrayList<String>(strs);
        Class<? extends List> aClass = strings1.getClass();
        System.out.println(aClass.getName());
        List ll = new ArrayList<String>(strings1);
        ll.addAll(strings1);
        Class<? extends List> aClass1 = ll.getClass();
        System.out.println(aClass1);

        Collections.addAll(ll,strs);
        System.out.println(strsss);

    }

    @Test
    public void sssdfsdf() throws ExecutionException, InterruptedException {
        CallableImp c = new CallableImp();
        FutureTask<String> ft = new FutureTask(c);
        Thread d = new Thread(ft);
        d.start();
        Object o = ft.get();
        System.out.println(o);
        Executors.newFixedThreadPool(1);
        ExecutorService es = new ThreadPoolExecutor(2,4,5
                , TimeUnit.SECONDS,new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 两个list是否相等
     */
    @Test
    public void fsdfsd(){
        List l = new ArrayList();
        List l2 = new ArrayList();
        l.add("a");
        l.add("s");
        l.add("d");
        l.add("f");
        l.add("g");


        l2.add("d");
        l2.add("s");
        l2.add("g");
        l2.add("a");
        l2.add("f");

        List ll = new ArrayList(l);
        List l22 = new ArrayList(l2);
        ll.removeAll(l22);
        System.out.println(ll.size());

        List lll = new ArrayList(l);
        List l222 = new ArrayList(l2);
        l222.removeAll(lll);
        System.out.println(l222.size());
        boolean b = l.containsAll(l2);
        boolean b1 = l2.containsAll(ll);
        System.out.println(b+" "+b1);
    }


    /**
     * 1. 请设计一个策略模式示例
     *
     *
     * 2.打印圣诞树，要用*号填充树，要求树居中，可以通过传参确认打印的高度，圣诞树如下所示：
     *      *
     *     * *
     *    * * *
     *   * * * *
     *  * * * * *
     *
     *
     * 3.题目：请使用 Java 设计一个 LRU 缓存结构
     */



    @Test
    public void sfsfds(){

    }



    @Test
    public void printSds(){
        int i = 4;
        print(i);
    }

    private void print(int i){
        List<String> l = new ArrayList();
        for (int j = 0; j < i; j++) {
            String result = "";
            String starsBefore = "* ";
            String stars = "*";
            String space = " ";
            if(j==0){
                for (int k = 0; k < i-j; k++) {
                    result += space;
                }
                result += stars;
            }else{
                for (int k = 0; k < i-j; k++) {
                    result += space;
                }
                for (int k = 0; k < j; k++) {
                    result += starsBefore;
                }
                result +=stars;
            }
            l.add(result);
        }
        for (String s : l) {
            System.out.println(s);
        }
    }

    @Test
    public void clTest(){
        String name = "苹果";
        cl(name);
        int i = RandomUtils.nextInt(100);
        System.out.println(i);
    }

    public void cl(String computerName){
        if("苹果".equals(computerName)){
            clll(new AppleComputer());
        }else if("戴尔".equals(computerName)){
            clll(new DellComputer());
        }else{
            clll(new HuaweiComputer());
        }
    }

    public void clll(Computer computer){
        computer.getComputer();
    }

}

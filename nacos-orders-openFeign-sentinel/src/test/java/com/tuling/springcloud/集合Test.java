package com.tuling.springcloud;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class 集合Test {

    @Test
    public void sdfsdfsd(){
        Map<String,String> m = new ConcurrentHashMap<String,String>();
        m.put("key2","");
        m.put("key2","x");
        m.put("key14","");
        m.put("key25","");
        m.put("key36","ccc");


        String s = m.get("key36");
        Map<String,String> ss = new HashMap<String,String>();
        ss.put("","");
    }

    @Test
    public void fsdfdf(){
        int i1 = 6546841 ^ 15;
        System.out.println(i1);
    }

    static final int spread(int h) {
        return (h ^ (h >>> 16)) & 0x7fffffff;
    }

    @Test
    public void ssdfsdf(){
        int s = 5;

        int d = Integer.numberOfLeadingZeros(s) | (1 << (13 - 1));

        System.out.println(3|5);

        System.out.println(true & true);
    }

    @Test
    public void sdfsdf(){
        for (;;){
            System.out.println("dddd");
        }
    }


    @Test
    public void dfd(){
        System.out.println(dd());
    }

    /**
     * 执行结果
     * fianlly了
     * 返回了
     * 说明先finally再走到return，并且如果finally中带return，那么就返回finally中的return
     * 否则等finally执行完，再执行try中的return
     * @return
     */
    public String dd(){
        try{
            return "返回了";
        }catch(Exception e){
            System.out.println("catch了");
            e.printStackTrace();
            return "catch返回了";
        }finally {
            System.out.println("fianlly了");
//            return "finally返回了";
        }
    }




}

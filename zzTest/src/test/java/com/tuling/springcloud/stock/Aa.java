package com.tuling.springcloud.stock;

import com.tuling.springcloud.stock.乱写包.Baaa;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import com.tuling.springcloud.stock.aa.Ceshi;


public class Aa {
    @Test
    public void dsds(){
        Random r = new Random();
        System.out.println(r.nextInt());
    }

    @Test
    public void sdsfsdfsdf() throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(5);
        queue.take();


    }

    @Test
    public void ddfdfdf(){
        while(true){
            System.out.println("dddddd");
        }
    }
    @Test
    public void sfsdfsdf(){
        Baaa baaa = new Baaa();
    }

    @Test
    public void dssdfs(){
        //就是为了语义的歧义
        Ceshi str = new Ceshi();
        Runnable run = ()->{
            System.out.println(str);
        };
        //Ceshi str = new Ceshi();就是为了语义的歧义
    }
}

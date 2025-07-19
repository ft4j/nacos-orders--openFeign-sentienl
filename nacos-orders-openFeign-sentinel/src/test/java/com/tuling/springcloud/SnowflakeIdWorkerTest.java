package com.tuling.springcloud;

import org.junit.Test;

public class SnowflakeIdWorkerTest {

    @Test
    public void sdsdfsdf(){
        long l1 = System.currentTimeMillis();
        System.out.println("开始："+l1);
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 500; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
            //System.out.println(Long.toBinaryString(id));
        }
        System.out.println("结束："+(System.currentTimeMillis()-l1));

    }
}

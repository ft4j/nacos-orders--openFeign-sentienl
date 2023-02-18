package com.tuling.springcloud.stock.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class RedissonController {
    @Autowired
    private Redisson redisson;

    /**
     * 1、redisson分布式锁的逻辑。默认加上的分布式锁的过期时间为30秒，在开启锁的时候会启动一个线程每10秒检查这个锁这个是否存在
     * 如果这个锁依然存在，那么开始锁续命，将锁的持续时间重新设置为30秒
     * 2、如果其他请求想要加锁，这时候另一个线程会一直启动一个while循环，多次尝试加锁
     * @return
     */
    @RequestMapping("/testRedisson")
    public String testRedisson(){
        String key = "deduct_stock_key";
        RLock lock = redisson.getLock(key);
        try{
            lock.lock();
            //lock.lock(30, TimeUnit.SECONDS);
            //业务代码
        }finally{
            lock.unlock();
        }
        return "执行成功";
    }

    public void dd(){
        Lock lock = new ReentrantLock(true);
        lock.lock();
        lock.unlock();
    }
    /**
     * redisson性能提升攻略：
     *  1、
     */

}

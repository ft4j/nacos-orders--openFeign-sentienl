package com.tuling.springcloud.orders.分布式锁;

import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@RestController
public class RedisLockController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/lockTest")
    public String lockTest(){
        String uuid = UUID.randomUUID().toString();
        //同时设置锁的同时超时时间
        Boolean product = stringRedisTemplate.opsForValue().setIfAbsent("product", uuid,3, TimeUnit.SECONDS);
        if(!product){
            return "没抢到锁";
        }
        try{
            stringRedisTemplate.opsForValue().decrement("num");
        }finally {
            if(uuid.equals(stringRedisTemplate.opsForValue().get("product"))){
                stringRedisTemplate.delete("product");
            }
        }
        System.out.println(System.currentTimeMillis());
        return "抢到锁了，购买成功";
    }

    @RequestMapping("/setProduct")
    public String setProduct(){
        stringRedisTemplate.opsForValue().setIfAbsent("num","300");
        return "success";
    }

    //redis分布式锁
    @Autowired
    Redisson redisson;

    @RequestMapping("redissonLock")
    public String redissonLock() throws InterruptedException {
        String lockStr = "product:101";
        //获取redis分布式锁  redisson获取的各种锁，参数都是锁的名字
        RLock lock = redisson.getLock(lockStr);
        RLock unFireLock = redisson.getFairLock(lockStr);
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("");
        readWriteLock.readLock();
        readWriteLock.writeLock();

        RSemaphore dd = redisson.getSemaphore("dd");
        dd.trySetPermits(3);
        dd.acquire();
        dd.release();

        RCountDownLatch aa = redisson.getCountDownLatch("aa");
        aa.trySetCount(5);
        aa.countDown();
        aa.await();
        RLock fair = redisson.getFairLock("fair");
        /**
         * 添加了尝试加锁时间和leaseTime锁过期时间的tryLock
         */
        lock.tryLock(3,10,TimeUnit.SECONDS);
        lock.unlock();
        /**
         * 普通加锁，这个时间值得是锁释放的时间，加了这个时间，就不会锁续命了
         */
        lock.lock(10,TimeUnit.SECONDS);
        /**
         * 业务逻辑
         */
        lock.unlock();

        lock.lock();
        lock.lock(11,TimeUnit.SECONDS);
        lock.lockInterruptibly();
        lock.lockInterruptibly(11,TimeUnit.SECONDS);
        lock.tryLock();
        lock.tryLock(11, TimeUnit.SECONDS);
        lock.tryLock(11,12, TimeUnit.SECONDS);
        /**
         * 业务逻辑
         */
        lock.unlock();

        return "redisson加锁成功";
    }


    @RequestMapping("redLock")
    public String redLock(){
        //这里需要根据实际配置的redis集群分别进行redis锁的操作。这里只是用同一个redisson来进行伪代码的编写而已
        //这里的redisson需要在application类中进行多个配置并进行多次实例化来调起一个redis分布式集群的分布式锁
        RLock rlock1 = redisson.getLock("key1");
        RLock rlock2 = redisson.getLock("key1");
        RLock rlock3 = redisson.getLock("key1");

        RedissonRedLock rrl = new RedissonRedLock(rlock1,rlock2,rlock3);
        try{
            boolean b = rrl.tryLock();
            if(b){
                System.out.println("这地方加了分布式锁");
                //如果加锁成功，业务逻辑在此进行
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //最后都要进行解锁
            rrl.unlock();
        }

        return "加锁成功";
    }

    @RequestMapping("/redissonLockTest")
    public String redissonLockTest(){
        String lock = "lock01";
        RLock rLock = redisson.getLock(lock);
        try{
            rLock.tryLock();
            System.out.println("这里给锁住了");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }
        return "强人所难";
    }
    String lock = "lock01";
    @RequestMapping("/redissonLockTimeOutTest")
    public String redissonLockTimeOutTest(){
        System.out.println("获取锁前"+System.currentTimeMillis());

        RLock rLock = redisson.getLock(lock);
        try{
            System.out.println("枷锁前"+System.currentTimeMillis());
            rLock.lock();
            System.out.println("枷锁后"+System.currentTimeMillis());
            System.out.println("这里给锁住了"+System.currentTimeMillis());
        }catch(Exception e){
            e.printStackTrace();
        }finally {

        }
        rLock.unlock();
        return "强人所难";
    }

    @RequestMapping("/redissonLockTimeOutTestx")
    public String redissonLockTimeOutTesxt(){
        String lock = "lock01";
        System.out.println("获取锁前");
        try{
            System.out.println("枷锁前");
            System.out.println(Thread.currentThread().getName());
//            Thread.sleep(2000);
            System.out.println("枷锁后");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
        }
        return "强人所难";
    }


    String lockObj = "lockStr";
    @RequestMapping("lock1")
    public String lock1() throws InterruptedException {
        RLock rLock = redisson.getLock(lockObj);
        System.out.println("lock1启动");
        rLock.lock();
        Thread.sleep(80000000);
        System.out.println("lock1睡醒");
        rLock.unlock();
        return "加锁1";
    }

    @RequestMapping("lock2")
    public String lock2() throws InterruptedException {
        RLock rLock = redisson.getLock(lockObj);
        System.out.println("lock2启动");
        rLock.lock();
        Thread.sleep(80000000);
        System.out.println("lock2睡醒");
        rLock.unlock();
        LockSupport.park();
        Thread.sleep(123);
        return "加锁2";
    }

}

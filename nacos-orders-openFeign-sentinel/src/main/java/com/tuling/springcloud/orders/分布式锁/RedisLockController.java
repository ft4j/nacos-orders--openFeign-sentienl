package com.tuling.springcloud.orders.分布式锁;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisLockController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

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
        //获取redis分布式锁
        RLock lock = redisson.getLock(lockStr);
        /**
         * 添加了尝试加锁时间和锁过期时间的tryLock
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



















}

package com.tuling.springcloud.stock.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    /**
     * 1、这是一个简单的分布式锁，但是减去库存的操作有可能报错导致永远不解锁，stringRedisTemplate.delete(key);用不执行
     * 因此需要加上try catch finally
     * 2、如果在加锁并执行代码的过程中redis挂了，那么也会操作死锁
     * 因此可以给锁加上一个时间
     * 3、有时候代码执行速度太慢导致一个锁在有效时间结束之后自动生成，第二个请求进入，第二个请求加锁，而第一个请求执行结束之后执行了删除key的操作导致锁失效
     * 一个请求在设置key的时候，value可以添加一个uuid，让这个锁只能过期失效或者只能被自己这个请求删除
     * 4、万一真的出现线程中断导致锁不能释放，或者代码确实运行了很久需要延长锁的使用时间，需要锁续命，将锁的超时时间重新设置防止过期
     * 参考RedissonController中的代码 redisson分布式锁!
     * @return
     */
    @RequestMapping("/deduct_stock")
    public String deductStock(){
        String k1 = "";
        String value = UUID.randomUUID().toString();
        String key = "deduct_stock_key";
        RLock lock = redisson.getLock(key);
        //1、try finally必然删除锁
        try{
            //2、防止永久死锁
            //给这个锁加上过期时间
            Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(key, value,10, TimeUnit.SECONDS);
            if(!aBoolean){
                return "被上锁，请等待";
            }
            k1 = stringRedisTemplate.opsForValue().get("k1");
            System.out.println(k1);
        }finally{
            //3、自己加的自己删除
            if(value.equals(stringRedisTemplate.opsForValue().get(key))){
                stringRedisTemplate.delete(key);
            }
        }

        return k1;
    }

}

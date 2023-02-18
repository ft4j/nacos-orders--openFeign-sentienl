package com.tuling.springcloud.orders.BloomFilter;


import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class TestBloomFilter {
    /**
     * 布隆过滤器
     * @param args
     */
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");

        RedissonClient redissonClient = Redisson.create(config);
        RBloomFilter<String> myFilter = redissonClient.getBloomFilter("MyFilter");

        myFilter.tryInit(100000000,0.01);

        myFilter.add("heihei");
        System.out.println(myFilter.getSize());
        myFilter.add("haha");

        System.out.println(myFilter.getSize());
        System.out.println(myFilter.contains("haha"));
        System.out.println(myFilter.contains("feijijiji"));
        myFilter.delete();
    }
}

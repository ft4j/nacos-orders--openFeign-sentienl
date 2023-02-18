package com.tuling.springcloud.orders.service;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import java.util.List;

public class PiplineImpl implements RedisCallback {
    public Object doInRedis(RedisConnection connection) throws DataAccessException {
        connection.openPipeline();
        for (int i = 0; i < 100; i++) {
            String key = "hello"+i;
            connection.zCount(key.getBytes(),0,Integer.MAX_VALUE);
        }
        List<Object> objects = connection.closePipeline();
        return null;
    }
}

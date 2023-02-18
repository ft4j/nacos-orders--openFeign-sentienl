package com.tuling.springcloud.orders.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tuling.springcloud.orders.domain.ResultMapBean;
import com.tuling.springcloud.orders.domain.User;
import com.tuling.springcloud.orders.domain.Uu;
import com.tuling.springcloud.orders.domain.UuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UuMapper uuMapper;

    public Uu query1(String name){
        return uuMapper.query1(name);
    }

    public List<Uu> query11(String name){
        return uuMapper.query11(name);
    }


    public Map queryForMap(String name){
        return uuMapper.queryForMap(name);
    }

    public ResultMapBean queryForResultMap(String name){
        return uuMapper.queryForResult(name);
    }

    public List<ResultMapBean> queryForResultMap11(String name){
        return uuMapper.queryForResult11(name);
    }

    public List<ResultMapBean> queryForObject(String name){
        User user = new User();
        user.setName(name);
        return uuMapper.queryForObject(user);
    }
    //添加了@SentinelResource注解的资源是不会使用统一异常处理的所以必须自己指定 blockHandler方法
    @SentinelResource(value="getUser",blockHandler = "getUserHandler")
    public User getUser(User user){
        return user;
    }
    public User getUserHandler(User user,BlockException e){
        return new User("张三","28");
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    public void dd(){
        stringRedisTemplate.opsForValue().set("","");
    }

    public void ddd(){
        List<Object> objects = stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForValue().set("cc", "vv");
                operations.opsForValue().increment("cc");
                operations.opsForValue().set("vv", "456");
                return null;
            }
        });
        System.out.println(objects);
    }
}

package com.tuling.springcloud.cahce;

import java.util.HashMap;
import java.util.Map;

public class UserinfoCache {
    public static Map<String,UserInfo> userinfoMysqlMap = new HashMap(50);
    static{
        userinfoMysqlMap.put("zhangsan",new UserInfo("zhangsan","123456","张三"));
        userinfoMysqlMap.put("zhangsan",new UserInfo("lisi","123456","李四"));
        userinfoMysqlMap.put("zhangsan",new UserInfo("wangwu","123456","王五"));
        userinfoMysqlMap.put("zhangsan",new UserInfo("zhaoliu","123456","赵六"));
    }

    public static Map<String,UserInfo> userinfoRedisMap = new HashMap(50);
}

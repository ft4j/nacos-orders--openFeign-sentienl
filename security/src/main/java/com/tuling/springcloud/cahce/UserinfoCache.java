package com.tuling.springcloud.cahce;


import com.tuling.springcloud.domain.MyUserDetail;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
public class UserinfoCache {
    //这是redis缓存 哈哈哈
    public static Map<String, MyUserDetail> userinfoRedisMap = new HashMap(50);
}

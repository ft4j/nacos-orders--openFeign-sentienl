package com.tuling.springcloud.digest.impl;

import com.tuling.springcloud.digest.Digest;
import org.apache.commons.codec.digest.DigestUtils;

public class Md5Digest implements Digest {
    @Override
    public String encrypt(String str) {
        System.out.println("Md5Digest正在被执行!!!!!!!");
        String s = DigestUtils.md5Hex(str);
        return s;
    }
}

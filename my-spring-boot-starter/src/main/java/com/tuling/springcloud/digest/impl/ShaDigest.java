package com.tuling.springcloud.digest.impl;

import com.tuling.springcloud.digest.Digest;
import org.apache.commons.codec.digest.DigestUtils;

public class ShaDigest implements Digest {
    @Override
    public String encrypt(String str) {
        System.out.println("ShaDigest正在被执行xxxxxxxx");
        String bytes = DigestUtils.sha256Hex(str.getBytes());
        return bytes;
    }
}

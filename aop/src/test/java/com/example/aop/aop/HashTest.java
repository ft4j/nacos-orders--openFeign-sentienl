package com.example.aop.aop;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 消息摘要算法，方式原文本被修改
 */
public class HashTest {
    @Test
    public void dd() throws Exception{
        //原文
        String myPreStr = "aa";
        //算法类型
        String algorithm = "sha-256";// md5 sha-1 sha-256 sha-512
        //创建摘要对象
        MessageDigest md = MessageDigest.getInstance(algorithm);
        //进行摘要
        byte[] digest = md.digest(myPreStr.getBytes());
        System.out.println(Base64.encode(digest));
        System.out.println(Arrays.toString(digest));//打印，验证最后一行
        String hexString = "";
        for (byte b : digest) {
            //把密文转为16进制
            String s = Integer.toHexString(b & 0xff);
            //一个byte转换为16进制有可能只有个位数的，那就在个位数前面+0
            if(StringUtils.hasText(s) && s.length() == 1){
                s = "0"+s;
            }
            hexString+=s;
        }
        System.out.println(hexString);//这就是我们常见的结果，md5的密文，一般不使用base64转换
        //转换16进制之后还原
        byte[] bytes = hexToBytes(hexString);
        System.out.println(Arrays.toString(digest));//将16进制转化为byte[]数组
    }

    public byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}

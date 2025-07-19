package com.example.aop.aop;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AesDesTest {
    /**
     * 使用aes或者des加密解密
     * aes和des是对称加密，加密和解密使用的是同一个key
     * Cipher.getInstance("AES/ECB/PKCS5Padding");这是aes/des默认的加密模式和填充模式
     * 为了安全考虑 一般采用Cipher.getInstance("AES/CBC/PKCS5Padding");
     * @throws Exception
     */
    @Test
    public void testDes() throws Exception {
        System.out.println("开始加密");
        String sourceString = "这是被加密的文本！张三李四去喝酒啊！";//被加密的文本
        /**
         * 如果使用NoPadding，那么需要把原文，手工修改成8Byte的倍数的长度。所以还是别NoPadding了
         */
        String tran = "AES/CBC/PKCS5padding";//加密算法
        String algorithm = "AES";//加密类型，这是加密算法的标准名称，按照algorithm的类型，会校验key的长度！
//        String tran = "AES";//加密算法
//        String algo = "AES";//加密类型
        byte[] bytes = getKey(algorithm);//加密解密的key  key必须是8个字节的
        String encode = Base64.encode(bytes);
        System.out.println(tran+"密钥为："+encode);
        String desEncryptString = desEncrypt(sourceString, tran, encode, algorithm,bytes);
        System.out.println("加密之后的密文，加密来的byte[]被base64转为这个："+desEncryptString);
        String desDecrypt = desDecrypt(desEncryptString, tran, encode, algorithm,bytes);
        System.out.println("密文解密之后："+desDecrypt);

    }

    /**
     * 如果是自动生成key，可以将key保存下来，然后将数据进行保存，以防止key丢失，造成无法解密
     * 如果不是自动生成，那就注意自己保存好key
     * @param type
     * @return
     * @throws Exception
     */
    public byte[] getKey(String type) throws Exception{
        KeyGenerator kgen = KeyGenerator.getInstance(type);
        if("DES".equals(type)){
            kgen.init(56);//只能是56
            SecretKey secretKey = kgen.generateKey();
            byte[] encoded = secretKey.getEncoded();
            return encoded;
        }else if("AES".equals(type)){
            kgen.init(128);//128 192 256除以8就是 16 24 32
            SecretKey secretKey = kgen.generateKey();
            byte[] encoded = secretKey.getEncoded();
            return encoded;
        }
        return null;
    }
    /**
     * des加密算法
     * @param sourceString
     * @param key
     * @param algorithm
     * @return 返回的是base64转换后的字符串，后续解密也需要base64转换成Byte[]
     * @throws Exception
     */
    public String desEncrypt(String sourceString,String transformation,
                             String key,String algorithm,byte[] b) throws Exception{
        Cipher des = Cipher.getInstance(transformation);
        SecretKeySpec sks = new SecretKeySpec(Base64.decode(key),algorithm);
        /**
         * 使用CBC时，需要添加IV向量 否则报错   这个字符串是自定义的，必须时16字节   默认使用ECB时，不需要这个IV向量
         * 注意，这个IV向量需要使用key的byte[]数组来创建
         */
        IvParameterSpec ips = new IvParameterSpec("1234567812345678".getBytes());
        des.init(Cipher.ENCRYPT_MODE,sks,ips);
        byte[] bytes = des.doFinal(sourceString.getBytes());
        String encode = Base64.encode(bytes);
        return encode;
    }

    /**
     * des解密案例
     * base64将密文转换为byte[]，doFinal执行的参数都是byte[]
     * @param decodeString
     * @param key
     * @param algorithm
     * @return 返回的是原文
     * @throws Exception
     */
    public String desDecrypt(String decodeString,String transformation,
                             String key,String algorithm,byte[] b) throws Exception{
        Cipher des = Cipher.getInstance(transformation);
        SecretKeySpec sks = new SecretKeySpec(Base64.decode(key),algorithm);
        /**
         * 使用CBC时，需要添加IV向量 否则报错      默认使用ECB时，不需要这个IV向量
         * 这个字符串是自定义的，AES必须时16字节 DES必须8字节
         */
        IvParameterSpec ips = new IvParameterSpec("1234567812345678".getBytes());
        des.init(Cipher.DECRYPT_MODE,sks,ips);
        byte[] bytes = des.doFinal(Base64.decode(decodeString));
        return new String(bytes);
    }

    @Test
    public void dsdsd(){
        System.out.println(Arrays.toString("1".getBytes()));
        System.out.println(Base64.encode("1".getBytes()));
        String encode = Base64.encode("1".getBytes());
        byte[] decode = Base64.decode(encode);
        String s = decode.toString();
        System.out.println("toString:"+s);

        System.out.println(new String(new byte[]{123,33,-12}));
    }

}

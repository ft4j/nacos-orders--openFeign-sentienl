package com.example.aop.aop;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 如果需要安全性更好的加签和验签功能，就添加CFCA（中金金融认证）的PFX文件，这是需要付费的
 * 如果需要免费的，可能需要向当地政府申请
 */
public class 加签验签API {
    @Test
    public void dfdf() throws Exception{
        String srcPdf = "E:\\signTest\\myPdf.pdf";//文件
        String algorthm = "SHA-256";
        String encryptAlgo = "RSA";
        String hashString = calculateHash(srcPdf, algorthm);
        Object[] pairKey = getPairKey(encryptAlgo);
        PrivateKey privateKey = (PrivateKey)pairKey[0];
        PublicKey publicKey = (PublicKey)pairKey[1];
        //开始加密
        Cipher cipher = Cipher.getInstance(encryptAlgo);
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        byte[] bytes = cipher.doFinal(hashString.getBytes());
        String encryptString = Base64.encode(bytes);//密文
        byte[] encoded = publicKey.getEncoded();
        String keyString = Base64.encode(encoded);//公钥
        //将文件、密文、公钥一起发送给对方，对方进行验签
        //根据公钥的字符串获取公钥对象
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spe = new X509EncodedKeySpec(Base64.decode(keyString));
        PublicKey publicKey1 = kf.generatePublic(spe);
        Cipher deCipher = Cipher.getInstance(encryptAlgo);
        deCipher.init(Cipher.DECRYPT_MODE,publicKey1);
        byte[] bytes1 = deCipher.doFinal(Base64.decode(encryptString));
        String DeEncode = new String(bytes1);
        System.out.println(hashString);
        System.out.println(DeEncode);
    }

    /**
     * 对文件进行hash计算
     * @return
     */
    public String calculateHash(String filePath, String algorithm) throws Exception{
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] digest = md.digest(Files.readAllBytes(Paths.get(filePath)));
        String encode = Base64.encode(digest);
        return encode;
    }

    /**
     * 获取公钥和私钥，返回值是一个Object[],下标0为私钥，下标1为公钥
     */
    public Object[] getPairKey(String algorithm) throws Exception{
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        return new Object[]{aPrivate,aPublic};
    }


    @Test
    public void sdsdsd(){

    }



}

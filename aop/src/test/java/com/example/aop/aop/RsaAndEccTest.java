package com.example.aop.aop;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaAndEccTest {
    @Test
    public void testRsa() throws Exception{
        //加密方式
        String algorithm = "RSA";
        //创建公钥和私钥
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        byte[] encoded = aPrivate.getEncoded();
        byte[] encoded1 = aPublic.getEncoded();
        String publicEncode = Base64.encode(encoded);
        System.out.println(publicEncode);
        String privateEncode = Base64.encode(encoded1);
        System.out.println(privateEncode);
        //被加密的对象
        String preEncryptStr = "张三李四被加密的String";
        //创建加密对象
        Cipher instance = Cipher.getInstance(algorithm);
        //对加密进行初始化 第一个参数：加密模式  第二个参数：使用公钥还是私钥进行加密
        instance.init(Cipher.ENCRYPT_MODE,aPublic);
        //使用公钥进行加密
        byte[] bytes = instance.doFinal(preEncryptStr.getBytes());
        String encode = Base64.encode(bytes);
        System.out.println("公钥加密的结果："+encode);

        //使用私钥进行解密
        instance.init(Cipher.DECRYPT_MODE,aPrivate);
        byte[] bytes1 = instance.doFinal(Base64.decode(encode));//bytes
        System.out.println("私钥解密的结果："+new String(bytes1));

    }

    /**
     * 通过字符串生成公钥和私钥对象
     * @return
     * @throws Exception
     */
    @Test
    public void getPairKey() throws Exception{
        String privateKeyString = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMAknHV8l4cnveG1tKZPfjEn0h3dFDxDFmKx/B+WUzVGpYWMZguVWLVNz5grEoQIqjM9TlZwAaITXDvgnsM4OZgMbycBDAafuNbeKrMytE2e6htFxiBs6JRa2O1bdaZeABFQGx9l1gVLI9az06RL81mODZ1G1OS8LmaYuirIrSYnAgMBAAECgYBLJsavSSD3MvsYqfpTRipETZ/oIE1G2LKLvbOwIxq+brhUyLos86fzmdCFYutmCwGO8v6N3lc1Lj9YCB3wmzAi80izMqRA6JFIPUBVJsnvaCayAXVjWD6bc3USnev9J4q8FXlAZXVLNf4BlLUQ73IPt4lPgsjiEGz36QLUqPNIiQJBAPANSD+CBQAwrLHXSwUvy5GYZDDzM+9T1yPVG0oPYjBY9Dmf49Nigf/da4xycRVHnZbOFiuMCSqtJ3V/6Y78b2sCQQDM6IMCx80aSCEUXGlLjtpMIx+nrkDGjCSrv8wqZrR6b1bZ7RPGuhhed9lf5n3m9CEZdrnj4c/hXyCTw9xWbH81AkAEr02sp53vXgPQyLHXHRKs+bti1Sw7THiTY3JP5LL23JWPllY85txAoRKbQBSC5M3nhogaO3ZmVdDCU0e8maKXAkEArZrM7L0lC4BS7nJBD7APSXR0rFdZMB6qLw1HyCDOBfFw7yAKolMmtFrsQ3D/+H2b0XwikGzP+EzlR+woMNyphQJAEh/D3SrfXF2zaDOg820D3emxMFVJfsis7gX0X25Wp5tgOFt9q1XDB9gKHnMYLu61CUPRjXPIED2Zi4Sc1nEMiA==";
        String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAJJx1fJeHJ73htbSmT34xJ9Id3RQ8QxZisfwfllM1RqWFjGYLlVi1Tc+YKxKECKozPU5WcAGiE1w74J7DODmYDG8nAQwGn7jW3iqzMrRNnuobRcYgbOiUWtjtW3WmXgARUBsfZdYFSyPWs9OkS/NZjg2dRtTkvC5mmLoqyK0mJwIDAQAB";
        //创建key工厂
        KeyFactory rsa = KeyFactory.getInstance("RSA");
        //创建公钥key的规则
        X509EncodedKeySpec  pkcs8EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        //获取公钥对象
        PublicKey publicKeyss = rsa.generatePublic(pkcs8EncodedKeySpec);
        //创建私钥的规则
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec1 = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
        //获取私钥对象
        PrivateKey privateKeyss = rsa.generatePrivate(pkcs8EncodedKeySpec1);




    }


}

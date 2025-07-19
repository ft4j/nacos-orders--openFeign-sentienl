package com.tuling.springcloud.stock.ca;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.junit.Test;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

public class PfxProvider {
    @Test
    public void pfxCreate() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // 生成密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 设置证书有效期
        Calendar calendar = Calendar.getInstance();
        Date startDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();

        // 构建证书主体信息
        X500Name issuer = new X500Name("CN=MyCompany, O=MyOrg, C=CN");
        BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis());

        // 创建证书构建器
        X509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
                issuer,
                serialNumber,
                startDate,
                endDate,
                issuer, // 自签名证书，签发者和主题相同
                keyPair.getPublic()
        );
        // 签名证书
        ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSA")
                .setProvider("BC").build(keyPair.getPrivate());
        X509Certificate certificate = new JcaX509CertificateConverter()
                .setProvider("BC").getCertificate(certBuilder.build(signer));

        // 验证证书
        certificate.verify(keyPair.getPublic());
        //长度最多为7
        String password = "xxxxxxx";
        // 保存到PKCS12密钥库
        KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
        keyStore.load(null, null);
        keyStore.setKeyEntry("mykey", keyPair.getPrivate(), password.toCharArray(),
                new Certificate[]{certificate});

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\34296\\Desktop\\file\\pfx\\mycert.pfx")) {
            keyStore.store(fos, password.toCharArray());
        }

        System.out.println("证书生成成功：mycert.pfx");
    }
}

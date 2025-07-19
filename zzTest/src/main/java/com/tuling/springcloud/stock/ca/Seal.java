package com.tuling.springcloud.stock.ca;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.StampingProperties;
import com.itextpdf.layout.element.Image;
import com.itextpdf.signatures.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;

public class Seal {
    @Test
    public void sealForPdf() throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        String password = "xxxxxxx";
        // 2. 加载证书和私钥
        try (FileInputStream fis = new FileInputStream("C:\\Users\\34296\\Desktop\\file\\pfx\\mycert.pfx")) {
            keyStore.load(fis, password.toCharArray());
        }
        String alias = keyStore.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
        Certificate[] certificateChain = keyStore.getCertificateChain(alias);

        // 3. 对PDF进行签名和盖章
        String sourcePdf = "C:\\Users\\34296\\Desktop\\file\\cccc.pdf";
        String signedPdf = "C:\\Users\\34296\\Desktop\\file\\zzzz.pdf";

    // 创建PDF阅读器和写入器
        PdfReader reader = new PdfReader(sourcePdf);
        PdfWriter writer = new PdfWriter(signedPdf);

        // 创建PDF签名器，设置为追加模式以支持多次签名
        PdfSigner signer = new PdfSigner(reader, writer, new StampingProperties().useAppendMode());

        // 设置签名外观
        Rectangle rect = new Rectangle(36, 648, 200, 100); // 签名位置和大小
        PdfSignatureAppearance appearance = signer.getSignatureAppearance();
        appearance.setReason("业务审批")
                .setLocation("财务部门")
                .setReuseAppearance(false)
                .setPageRect(rect)
                .setPageNumber(1);

        // 自定义签章外观
        ImageData imageData = ImageDataFactory.create("C:\\Users\\34296\\Desktop\\file\\aaaa.png");
//        Image image = new Image(imageData);
        appearance.setImage(imageData);

        // 设置签名算法
        IExternalSignature externalSignature = new PrivateKeySignature(privateKey, "SHA256", "BC");
        IExternalDigest externalDigest = new BouncyCastleDigest();

        // 执行签名
        signer.signDetached(externalDigest, externalSignature, certificateChain,
                null, null, null, 0, PdfSigner.CryptoStandard.CMS);
    }



}

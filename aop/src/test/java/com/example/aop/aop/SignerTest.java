package com.example.aop.aop;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.signatures.*;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * PDF盖章和签名工具类
 */
public class SignerTest {
    private static final String BC = BouncyCastleProvider.PROVIDER_NAME;

    static {
        // 注册BouncyCastle安全提供者
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 给PDF文件盖章并签名
     * @param src 源PDF文件路径
     * @param dest 目标PDF文件路径
     * @param imagePath 印章图片路径
     * @param p12Path PKCS#12证书路径
     * @param password 证书密码
     * @param reason 签名原因
     * @param location 签名位置
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static void signPdfWithStamp(String src, String dest, String imagePath, String p12Path,
                                        char[] password, String reason, String location)
            throws GeneralSecurityException, IOException {
        // 加载证书和私钥
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(p12Path), password);
        String alias = ks.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password);
        Certificate[] chain = ks.getCertificateChain(alias);

        // 创建PDF阅读器和签名器
        PdfReader reader = new PdfReader(src);
        PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), new StampingProperties());

        // 设置签名外观
        PdfSignatureAppearance appearance = signer.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);

        // 设置签名位置和大小
        Rectangle rect = new Rectangle(36, 648, 120, 120); // 签名位置和大小
        appearance.setPageRect(rect);
        appearance.setPageNumber(1);

        // 添加印章图片
        ImageData imageData = ImageDataFactory.create(imagePath);
        PdfCanvas canvas = new PdfCanvas(signer.getDocument().getFirstPage());

        // 设置透明度
        PdfExtGState gs1 = new PdfExtGState();
        gs1.setFillOpacity(0.8f);
        canvas.setExtGState(gs1);

        // 添加图片到指定位置
        canvas.addImageFittedIntoRectangle(imageData, rect, false);
        // 创建签名外观
        appearance.setLayer2Text("数字签名: " + ((X509Certificate) chain[0]).getSubjectDN());
        appearance.setLayer2FontSize(8);

        // 设置签名算法
        IExternalSignature pks = new PrivateKeySignature(privateKey, DigestAlgorithms.SHA256, BC);
        IExternalDigest digest = new BouncyCastleDigest();

        // 执行签名
        signer.signDetached(digest, pks, chain, null, null, null,
                0, PdfSigner.CryptoStandard.CMS);

        // 关闭资源
        reader.close();
        signer.getDocument().close();
    }

    /**
     * 验证PDF文件的签名
     * @param signedPdf 已签名的PDF文件路径
     * @return 签名验证结果列表
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static List<VerificationResult> verifyPdfSignatures(String signedPdf)
            throws IOException, GeneralSecurityException {
        List<VerificationResult> results = new ArrayList<>();

        PdfReader reader = new PdfReader(signedPdf);
        PdfDocument pdfDoc = new PdfDocument(reader);
        SignatureUtil signUtil = new SignatureUtil(pdfDoc);
        List<String> names = signUtil.getSignatureNames();

        for (String name : names) {
            VerificationResult result = new VerificationResult();
            result.setSignatureName(name);

//            PdfPKCS7 pkcs7 = signUtil.verifySignature(name);
//            result.setValid(pkcs7.verify());
//            result.setSignDate(pkcs7.getSignDate().getTime());
//            result.setCertificationLevel(signUtil.getCertificationLevel());
//
//            X509Certificate certificate = pkcs7.getSigningCertificate();
//            if (certificate != null) {
//                result.setSignerName(certificate.getSubjectDN().getName());
//                result.setCertificateValidUntil(certificate.getNotAfter());
//            }

            results.add(result);
        }

        pdfDoc.close();
        reader.close();

        return results;
    }

    /**
     * 签名验证结果类
     */
    public static class VerificationResult {
        private String signatureName;
        private boolean valid;
        private Date signDate;
        private String signerName;
        private Date certificateValidUntil;
        private int certificationLevel;

        // Getters and setters
        public String getSignatureName() { return signatureName; }
        public void setSignatureName(String signatureName) { this.signatureName = signatureName; }

        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }

        public Date getSignDate() { return signDate; }
        public void setSignDate(Date signDate) { this.signDate = signDate; }

        public String getSignerName() { return signerName; }
        public void setSignerName(String signerName) { this.signerName = signerName; }

        public Date getCertificateValidUntil() { return certificateValidUntil; }
        public void setCertificateValidUntil(Date certificateValidUntil) { this.certificateValidUntil = certificateValidUntil; }

        public int getCertificationLevel() { return certificationLevel; }
        public void setCertificationLevel(int certificationLevel) { this.certificationLevel = certificationLevel; }

        @Override
        public String toString() {
            return "Signature: " + signatureName +
                    ", Valid: " + valid +
                    ", Signer: " + signerName +
                    ", Date: " + signDate +
                    ", Cert. Valid Until: " + certificateValidUntil;
        }
    }

    /**
     * 主方法示例
     */
    public static void main(String[] args) {
        try {
            String src = "E:/signTest/a.pdf";
            String dest = "E:/signTest/a_signed.pdf";
            String imagePath = "E:/signTest/b.png";
            String p12Path = "E:/signTest/certificate.pfx";
            char[] password = "yourPassword".toCharArray();
            String reason = "合同签署";
            String location = "北京";

            // 执行盖章和签名
            signPdfWithStamp(src, dest, imagePath, p12Path, password, reason, location);
            System.out.println("PDF盖章和签名完成");

            // 验证签名
            List<VerificationResult> results = verifyPdfSignatures(dest);
            for (VerificationResult result : results) {
                System.out.println("验证结果: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}    
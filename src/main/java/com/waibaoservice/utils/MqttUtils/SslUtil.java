package com.waibaoservice.utils.MqttUtils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;

/***
 *  两种方式验证
 * @author YiMing
 * @date 2023/2/22
 * @since 1.0.0
 */
public class SslUtil {

    /**
     * 用证书和私钥配置sslContext
     *
     * @param caCrtFile
     *            CA证书（验证连接）
     * @param crtFile
     *            发给对方的证书
     * @param keyFile
     *            pem 私钥（请求连接的消息是用公钥加密的，需要用私钥解密）
     * @param password
     *            私钥密码
     * @return
     * @throws Exception
     */
    public static SSLSocketFactory getSocketFactoryByCert(final String caCrtFile, final String crtFile,
                                                          final String keyFile, final String password) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // 加载CA证书（用于验证的根证书）
        PEMReader reader =
                new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(caCrtFile)))));
        X509Certificate caCert = (X509Certificate)reader.readObject();
        reader.close();

        // 加载自己的证书，用于发送给客户端
        reader = new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(crtFile)))));
        X509Certificate cert = (X509Certificate)reader.readObject();
        reader.close();

        // 加载私钥
        reader = new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(keyFile)))),
                () -> password.toCharArray());
        KeyPair key = (KeyPair)reader.readObject();
        reader.close();

        // 用CA证书创建TrustManagerFactory
        KeyStore caKs = KeyStore.getInstance(KeyStore.getDefaultType());
        caKs.load(null, null);
        caKs.setCertificateEntry("ca-certificate", caCert);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(caKs);

        // 用证书和私钥创建KeyManagerFactory
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, null);
        ks.setCertificateEntry("certificate", cert);
        ks.setKeyEntry("private-key", key.getPrivate(), password.toCharArray(),
                new java.security.cert.Certificate[] {cert});
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, password.toCharArray());

        SSLContext context = SSLContext.getInstance("TLSv1");
        // kmf用于发送关键信息让服务端校验，tmf用于校验服务端的证书。双向认证
        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
        return context.getSocketFactory();
    }

    /**
     * 通过keyStore加载
     *
     * @param keyStorePath
     *            keystore路径（保存自己的秘钥和证书）
     * @param trustKeyStorePath
     *            truststore路径（保存受信的证书）
     * @param ksPass
     *            keystore密码
     * @param tsPass
     *            truststore密码
     * @return
     * @throws Exception
     */
    public static SSLSocketFactory getSocketFactoryByKeystore(String keyStorePath, String trustKeyStorePath,
                                                              String ksPass, String tsPass) throws Exception {
        // keytool生成的keystore的类型就是JKS
        KeyStore keyStore = KeyStore.getInstance("JKS");
        KeyStore trustKeyStore = KeyStore.getInstance("JKS");
        // 通过密码加载keystore
        FileInputStream fis = new FileInputStream(keyStorePath);
        keyStore.load(fis, ksPass.toCharArray());
        fis.close();
        // 加载trustKeyStore
        FileInputStream trustFis = new FileInputStream(trustKeyStorePath);
        trustKeyStore.load(trustFis, tsPass.toCharArray());
        trustFis.close();
        // 创建管理JKS密钥库的密钥管理器 (SunX509)
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        // 使用密钥内容源初始化此工厂。 提供者通常使用 KeyStore 来获取在安全套接字协商期间所使用的密钥内容
        kmf.init(keyStore, ksPass.toCharArray());
        // SunX509
        TrustManagerFactory tmFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmFactory.init(trustKeyStore);

        // 初始sslcontext
        SSLContext sslContext = SSLContext.getInstance("SSLv3");
        // SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmFactory.getTrustManagers(), new SecureRandom());
        return sslContext.getSocketFactory();
    }

}
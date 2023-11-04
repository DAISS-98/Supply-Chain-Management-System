package com.common.security;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.common.StringUtil;
import com.thoughtworks.xstream.core.util.Base64Encoder;



public abstract class RSACrypto extends KryptoBase {
    //
    public static final String KEY_ALGORITHM = "RSA";

    //
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    //
    private static final String PUBLIC_KEY = "RSAPublicKey";

    //
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 最大文件加密块
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * 最大文件解密块
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 默认的密码
     */
    private static final String DEFAULT_PASS = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBi";

    /**
     * 默认的公钥
     */
    private static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3R92LSRUWx5Fy"
            + "Z1iX3X/8jONr7bJBywXw7RQm+/nKOibvsLKuxPxL5C6Bml7" + "yBmv84lr1kMORQj7/JhvBhE7V37bzldzDKbwrNavjEfm9cj"
            + "LGagYnklT6mX5uBvi0EPHkxVvchflMf1Y8oSsDwcenAogFZ" + "DftZKpdG/ji9/cRqwIDAQAB";

    /**
     * 默认的私钥
     */
    private static final String DEFAULT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAo" + "GBALdH3YtJFRbHkXJnWJfdf/yM42vtskHLBfDt"
            + "FCb7+co6Ju+wsq7E/EvkLoGaXvIGa/ziWvWQw5" + "FCPv8mG8GETtXftvOV3MMpvCs1q+MR+b1yMsZq" + "BieSVPqZfm4G+LQQ8eTFW9yF+Ux/VjyhKwPBx6"
            + "cCiAVkN+1kql0b+OL39xGrAgMBAAECgYB86EVc" + "P5pXw/qj9TqzhQlMnNgBHiV2IV8TKv+o8eDyqV" + "8Py2R7J1SqhAAN458BYs8FsV48LUSbIoK8cMRJx"
            + "lnS0FqBeQheGadSxp9gOpnGiL/eVMjY6BMzhxRO" + "eQo6vzKqOCXTkTDLcDhFOyVNv5c8F5c6r8J4cyL+R" + "LkxDyUEgQJBAOoLJP8sHaSlF5IRKNtFh3r7UP14Ux7"
            + "soBZnpEhfOgugCwXI1YJ3CFw3RtfPUIv30e3azZMKIT" + "aAgULzWnPeygMCQQDIeZZCoUplvbqOuo5pi0HTx/LCw"
            + "2ZlrIRc/Fg5/LpMl0O+1yT2L7QPSn1bEOhrBOjZsKTc" + "y863SqLOwOc/U105AkBh3/091SYkZ4Ie7JwobsBElvr"
            + "8tSgVo6EjHX6fYWt11x0k8yMO51aQsfEnaSRnFGEvVjR" + "oTHEtSLpF1F08XawfAkAmbTfiGax7B/0EcrntfSXnBEK"
            + "CFpZAEutkL3Jx2wGSwMPZhr+s+MPY0nxowjrqmIxRfcx" + "8DgzXkVTXsJ4iw0XxAkEAzKfgzDT3tjFcWPU6ZCszkk8"
            + "FzD0p1ODU+dcxAyuFZb7+z0MctsNyjKUvBv1RvSI6FE" + "Y+SiBT4QhIkW1IysdTag==";

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data
     *            加密数据
     * @param privateKey
     *            私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data
     *            加密数据
     * @param publicKey
     *            公钥
     * @param sign
     *            数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {

        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);

        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm(), new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 解密byte数组最大长度限制: 128
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm(), new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 加密<br>
     * 用私钥加密

     */
    public static String encryptByDefaultPrivateKey(String content) {
        if (content == null) {
            return null;
        }
        if (StringUtil.isNotNullString(content)) {
            try {
                //byte[] encryptBytes = encryptByPrivateKey(content.getBytes("UTF-8"), DEFAULT_PRIVATE_KEY);
                byte[] encryptBytes = encrypt(content, DEFAULT_PASS);
                return new Base64Encoder().encode(encryptBytes).replace("\n", "").replace("\r", "").replace("+", "-").replace("/", "*");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return "";
    }


    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    public static byte[] encrypt(String content, String password) {
        try {
            byte[] byteContent = content.getBytes("utf-8");
            return byteContent; // 加密
        } catch(Exception e){

        }
        return null;
    }


    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptByDefaultPublicKey(String content) {
        if (content == null) {
            return null;
        }
        try {
            if (StringUtil.isNotNullString(content)) {

                //byte[] decryptBytes = decryptByPublicKey(new Base64Encoder().decode(content.replace("-", "+").replace("*", "/")), DEFAULT_PUBLIC_KEY);
                byte[] decryptBytes = decrypt(new Base64Encoder().decode(content.replace("-", "+").replace("*", "/")), DEFAULT_PASS);
                return new String(decryptBytes, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "";
    }

    /**
     * 解密
     *
     * @param content
     *            待解密内容
     * @param password
     *            解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
//            KeyGenerator kgen = KeyGenerator.getInstance("AES");
//            kgen.init(128, new SecureRandom(password.getBytes()));
//            SecretKey secretKey = kgen.generateKey();
//            byte[] enCodeFormat = secretKey.getEncoded();
//            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
//            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
//            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
//            byte[] result = cipher.doFinal(content);
            return content; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKeyFile(byte[] data, String publicKeyFile) throws Exception {

        // 取得公钥
        KryptoUtil kryptoUtil = new KryptoUtil();
        PublicKey publicKey = kryptoUtil.getStoredPublicKey(publicKeyFile);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm(), new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm(), new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKeyFile(byte[] data, String keyFilePath) throws Exception {

        // 取得私钥
        KryptoUtil kryptoUtil = new KryptoUtil();
        PrivateKey privateKey = kryptoUtil.getStoredPrivateKey(keyFilePath);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm(), new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm(), new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     *
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        System.err.println("私钥加密——公钥解密");

        Map<String, Object> keyMap = RSACrypto.initKey();
        String publicKey = RSACrypto.getPublicKey(keyMap);
        String privateKey = RSACrypto.getPrivateKey(keyMap);
        // System.err.println("公钥: \n\r" + publicKey);
        // System.err.println("私钥： \n\r" + privateKey);

        String inputStr = "start signadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwosignadsfljlasdjflaskjdfljwo end";
        // byte[] data = inputStr.getBytes();
        // byte[] encodedData = RSACoder.encryptByPrivateKey(data, DEFAULT_PRIVATE_KEY);
        // String encodedStr1 = new String(encodedData,"ISO-8859-1");
        // byte[] decodedData = RSACoder.decryptByPublicKey(encodedStr1.getBytes("ISO-8859-1"), DEFAULT_PUBLIC_KEY);
        // String outputStr = new String(decodedData);

        for(int i=0; i<10000; i++){
        String encryptStr = RSACrypto.encryptByDefaultPrivateKey(inputStr);
        System.err.println("miwen: " + encryptStr);
        String outputStr = RSACrypto.decryptByDefaultPublicKey(encryptStr);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        System.err.println("私钥签名——公钥验证签名");
        }
        System.err.println("end");
    }

}

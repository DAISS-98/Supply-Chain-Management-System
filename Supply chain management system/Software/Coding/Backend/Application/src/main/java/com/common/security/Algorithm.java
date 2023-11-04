package com.common.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.DigestUtils;


/**
 * 算法
 */
public class Algorithm {

/**
 * 算法一：AES算法
 *ES 是一种可逆加密算法，对用户的敏感信息加密处理
 *对原始数据进行AES加密后，在进行Base64编码转化；加密用的Key 可以用26个字母和数字组成,
 *此处使用AES-128-CBC加密模式，key需要为16位。
 */
 private String ivParameter="0123456789abcdef";
 private String password = "0123456789abcdef";
 private static Algorithm instance=null;
    //AES加密
    public String encrypt(String sSrc ,String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = password.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
    }
    //AES解密
    public String decrypt(String sSrc,String password) throws Exception {
        try {
            byte[] raw = password.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    //AES加密默认密码
    public String encrypt(String sSrc) throws Exception {
        return this.encrypt(sSrc,password);
    }

    //AES解密默认密码
    public String decrypt(String sSrc) throws Exception {
        return this.decrypt(sSrc,password);
    }
    /**
     * 算法二
     * MD5加密之方法一
     * @explain 借助apache工具类DigestUtils实现
     * @param str
     *            待加密字符串
     * @return 16进制加密字符串
     */
    public static String encryptToMD5(String str) {
        return DigestUtils.md5Hex(str);
    }


}

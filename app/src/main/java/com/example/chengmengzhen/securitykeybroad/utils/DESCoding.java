package com.example.chengmengzhen.securitykeybroad.utils;


import android.text.LoginFilter;
import android.util.Log;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密算法
 *
 * @author
 */
public class DESCoding {

    private static String mAlgorithm = "DESede"; //定义 加密算法，默认为DESede
    private byte[] keyBytes = null;

    private static String trimAlgorithm(String alg) {
        int p = alg.indexOf('/');
        if (p == -1)
            return alg;
        return alg.substring(0, p);
    }

    /**
     * 对指定的字节数组进行加密
     *
     * @param src 需要进行加密的字节数组
     * @return byte[]     加密后的字节数组，若加密失败，则返回null
     */
    public static String encode(byte[] src) {
        try {
            //  生成密钥
            byte[] keyBytes = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            Log.e("11", "11");
            SecretKey deskey = new SecretKeySpec(keyBytes, trimAlgorithm("DESede"));
            //加密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return Arrays.toString(c1.doFinal(src));
        } catch (java.security.NoSuchAlgorithmException e1) {
//            logger.error("DESCoding.encode error in java.security.NoSuchAlgorithmException:" + e1);
            return null;
        } catch (javax.crypto.NoSuchPaddingException e2) {
//            logger.error("DESCoding.encode error in javax.crypto.NoSuchPaddingException:" + e2);
            return null;
        } catch (Exception e3) {
//            logger.error("DESCoding.encode error in Exception:" + e3);
            return null;
        }
    }

    /**
     * 解密
     *
     * @param src 用3DES加密后的字节数组
     * @return byte[]     解密后的字节数组
     */
    public byte[] decode(byte[] src, byte[] keyBytes, String Algorithm) {
        try {
            //  生成密钥
            SecretKey deskey = new SecretKeySpec(keyBytes, trimAlgorithm(Algorithm));

            //  解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
//            logger.error("DESCoding.decode error in java.security.NoSuchAlgorithmException:" + e1);
            return null;
        } catch (javax.crypto.NoSuchPaddingException e2) {
//            logger.error("DESCoding.decode error in javax.crypto.NoSuchPaddingException:" + e2);
            return null;
        } catch (Exception e3) {
//            logger.error("DESCoding.decode error in Exception:" + e3);
            return null;
        }
    }


}

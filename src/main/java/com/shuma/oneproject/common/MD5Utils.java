package com.shuma.oneproject.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 *  辅助生成MD5的工具类
 */
public class MD5Utils {

    /**
     * 将字符串加密成MD5串
     * @param str 要加密的字符串
     * @return
     *  MD5加密后的字符串
     */
    public static String From(String str) {
        StringBuffer md5Code = new StringBuffer();
        try {
            //获取加密方式为md5的算法对象
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(str.getBytes());
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 0xff);
                if (hexString.length() < 2) {
                    hexString = "0"+hexString;
                }
                md5Code = md5Code.append(b);
            }
        } catch (NoSuchAlgorithmException e) {
        }

        return md5Code.toString();
    }
}

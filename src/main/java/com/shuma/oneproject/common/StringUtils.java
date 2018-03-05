package com.shuma.oneproject.common;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/31
 * @desc
 *  字符串工具类
 */
public class StringUtils {

    /**
     * 判断字符串是否为null或空
     * @param str 要判断的字符串
     * @return 判断结果
     */
    public static boolean IsNullOrEmpty(String str) {
        if(str == null) return true;
        if(str.equals("")) return true;

        return false;
    }

    /**
     * 判断字符串是否为null或可能存在空格的空字符串
     * @param str 要判断的字符串
     * @return 判断结果
     */
    public static boolean IsNullOrWhiteSpace(String str) {
        if(str == null) return true;
        if(str.trim().equals("")) return true;

        return false;
    }

    /**
     * 生成首字母大写的字符串
     * @param str 源字符串
     * @return 首字母大写的字符串
     */
    public static String CaptureName(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}

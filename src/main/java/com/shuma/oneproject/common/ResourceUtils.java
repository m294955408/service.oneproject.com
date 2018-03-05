package com.shuma.oneproject.common;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 *  字符串资源工具类，用于字符串资源的翻译及显示
 */
public class ResourceUtils {

    /**
     * 翻译字符串资源
     * @param path 资源路径
     * @param key 资源KEY
     * @return 翻译结果
     */
    public static String Trans(String path, String key) {
        // 资源包名称
        String resourcePath = "languages/" + path;

        // 设置语言环境
        Locale cn = Locale.CHINA;

        // 加载资源
        ResourceBundle resourceBundle = ResourceBundle.getBundle(resourcePath, cn);

        return resourceBundle.getString(key);
    }

    /**
     * 翻译字符串资源
     * @param resourceKey
     * @return 翻译结果
     */
    public static String Trans(String resourceKey) {
        String path = resourceKey.split("\\.")[0];
        String key = resourceKey.split("\\.")[1];

        return Trans(path, key);
    }
}

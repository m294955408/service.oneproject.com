package com.shuma.oneproject.common.result;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON解析生成Util
 *
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/23
 * @desc
 */
public class JsonUtil {

    private static ObjectMapper objectMapper;

    /**
     * 解析json
     *
     * @param content
     * @param valueType
     * @return
     */
    public static <T> T fromJson(String content, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

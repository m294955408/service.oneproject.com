package com.shuma.oneproject.common.dbutils;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/15
 * @desc
 */
public class DataSourceContextHolder {

    public static final String UserDataSource = "usersDataSource";
    public static final String BlogDataSource = "blogDataSource";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }
    public static String getDbType() {
        return ((String) contextHolder.get());
    }
    public static void clearDbType() {
        contextHolder.remove();
    }
}

package com.shuma.oneproject.common.dbutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/30
 * @desc
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    public String value() default  DataSourceContextHolder.UserDataSource;

}

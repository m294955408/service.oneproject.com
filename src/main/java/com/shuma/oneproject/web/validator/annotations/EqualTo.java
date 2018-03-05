package com.shuma.oneproject.web.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/8
 * @desc
 *  和其他字段相等
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualTo {

    /**
     * 与之相等的字段名称
     * @return
     */
    public String fieldName() default "";
}

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
 *  限定字符串长度
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {

    /**
     * 最短长度 < 0 无限制
     * @return
     */
    public int min() default -1;

    /**
     * 最长长度 < 0 无限制
     * @return
     */
    public int max() default -1;

}

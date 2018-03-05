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
 *  限定数字范围
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

    /**
     * 最小值
     * @return
     */
    public double min() default Double.MIN_VALUE;

    /**
     * 最大值
     * @return
     */
    public double max() default Double.MAX_VALUE;

}

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
 *  注释元素必需为boolean且为真
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AssertTrue {
}

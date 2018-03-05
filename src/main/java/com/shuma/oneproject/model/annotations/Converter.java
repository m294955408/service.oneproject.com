package com.shuma.oneproject.model.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/31
 * @desc
 *  模型转换注释类
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Converter {

    /**
     * 所对应的Entity中实体的名称
     * @return
     */
    public String from() default "";

    /**
     * 所对应的ViewModel中实体的名称
     * @return
     */
    public String to() default "";

    /**
     * 是否会被赋值
     * @return
     */
    public boolean isAssign() default true;

}

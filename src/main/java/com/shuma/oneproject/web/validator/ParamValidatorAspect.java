package com.shuma.oneproject.web.validator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/7
 * @desc
 */
@Component
@Aspect
public class ParamValidatorAspect {

    @Pointcut("@annotation(com.shuma.oneproject.web.validator.ParamValidator)")
    public void controllerAspect() { }

    @Before("controllerAspect()")
    public void before(JoinPoint point) throws ParamValidatorErrorException {
        // 获取切入的方法
        Method method = ((MethodSignature)point.getSignature()).getMethod();
        // 获取参数
        Object [] params = point.getArgs();
        // 遍历参数，对注释需要校验的参数进行校验
        ValidResult validResult = new ValidResult();
        // 获取参数注释
        Annotation [][] annotations = method.getParameterAnnotations();
        for (int i = 0; i <params.length; i++) {
            // 获取校验注释，判断该参数是否需要进行校验
            boolean isNeedValid = false;
            for(int j = 0; j < annotations[i].length; j++) {
                if(annotations[i][j] instanceof  Valid) {
                    isNeedValid = true;
                }
            }
            if(!isNeedValid) continue;

            // 进行校验
            validResult.combine(Validator.Valid(params[i]));
        }

        if(validResult.hasErrors()) {
            // 存在错误，则返回错误信息
           throw new ParamValidatorErrorException(validResult.getErrors());
        }
    }
}

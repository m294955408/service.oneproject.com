package com.shuma.oneproject.common.dbutils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/30
 * @desc
 */
@Aspect
@Component
public class DataSourceAspect {

    @Before("execution(* com.shuma.oneproject.dao.*.*(..))")
    public void setDataSourceKey(JoinPoint point) {
        // 数据源标识
        DataSource annotation = null;

        // 获取数据访问类接口，遍历取得数据源标识注解
        Class<?> interfaces[] = point.getTarget().getClass().getInterfaces();
        for (Class<?> _interface :
             interfaces) {
            annotation = _interface.getAnnotation(DataSource.class);
            if(annotation != null) break;
        }

        // 数据源，默认使用用户数据源
        String dataSource = DataSourceContextHolder.UserDataSource;

        // 根据注解切换数据源，若注解为null，则使用默认数据源
        if(annotation != null) {
            dataSource = annotation.value();
        }

        // 切换数据源
        DataSourceContextHolder.setDbType(dataSource);
    }

}

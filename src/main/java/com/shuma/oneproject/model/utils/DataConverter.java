package com.shuma.oneproject.model.utils;

import com.shuma.oneproject.common.StringUtils;
import com.shuma.oneproject.model.annotations.Converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/31
 * @desc
 *  数据转换操作
 *  用反射的方法转换Entity、Model、ViewModel
 */
public class DataConverter {

    /**
     * 将Entity对象转换为Model对象
     * @param source Entity对象
     * @param targetClazz 要转换的Model对象的class
     * @param <S> Entity class
     * @param <T> Model class
     * @return 转换结果（Model对象）
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T, S> T Cast(S source, Class<T> targetClazz) throws InstantiationException, IllegalAccessException {
        // 创建目标对象
        T target = targetClazz.newInstance();

        // 获取Model所有属性
        Field[] fields = target.getClass().getDeclaredFields();
        
        // 遍历Model中所有属性，调用get set方法赋值
        for (Field field:
             fields) {
            // 获取Converter注解，若没有该注解，该属性不会被转换
            Converter annotation = field.getAnnotation(Converter.class);
            if(annotation == null) continue;

            // 存在注解，则先获取属性名称
            String fieldName = field.getName();
            // 再获取该属性在Entity中对应的属性名称
            String sourceFieldName = StringUtils.IsNullOrWhiteSpace(annotation.from()) ? fieldName : annotation.from();

            // 接下来获取Entity中Field对应的get方法，获取Model中Field对应的set方法
            String getSourceMethodName = "get" + StringUtils.CaptureName(sourceFieldName);
            String setTargetMethodName = "set" + StringUtils.CaptureName(fieldName);
            Method getSourceMethod = null;
            Method setTargetMethod = null;
            try {
                getSourceMethod = source.getClass().getMethod(getSourceMethodName);
                setTargetMethod = target.getClass().getMethod(setTargetMethodName, field.getType());
            } catch (NoSuchMethodException e) {
                //e.printStackTrace();
            }
            // 若Entity或Model中有任意一个方法不存在，则跳过不赋值
            if(getSourceMethod == null || setTargetMethod == null) continue;

            // 赋值
            try {
                Object value = getSourceMethod.invoke(source);
                setTargetMethod.invoke(target, value);
            } catch (InvocationTargetException e) { }
        }

        return target;
    }

    /**
     * 将Entity对象列表转换为Model对象列表
     * @param sourceList Entity对象列表
     * @param targetClazz 要转换的Model对象的class
     * @param <S> Entity class
     * @param <T> Model class
     * @return 转换结果（Model对象数组）
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T, S> List<T> Cast(List<S> sourceList, Class<T> targetClazz) throws InstantiationException, IllegalAccessException {
        List<T> targetList = new ArrayList<T>();
        Iterator<S> iterator = sourceList.iterator();

        while(iterator.hasNext()) {
            targetList.add(Cast(iterator.next(), targetClazz));
        }

        return targetList;
    }

    /**
     * 将Model对象转换为Entity对象
     * @param source Model对象
     * @param targetClazz 要转换的Entity对象的class
     * @param <T> Entity class
     * @param <S> Model class
     * @return 转换结果（Entity对象）
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T, S> T Restore(S source, Class<T> targetClazz) throws InstantiationException, IllegalAccessException {
        // 创建目标对象
        T target = targetClazz.newInstance();

        // 获取源对象（Model）所有属性
        Field[] fields = source.getClass().getDeclaredFields();

        // 遍历Model中所有属性，调用get set方法赋值
        for (Field field:
                fields) {
            // 获取Converter注解，若没有该注解，该属性不会被转换
            Converter annotation = field.getAnnotation(Converter.class);
            if(annotation == null) continue;

            // 存在注解，则先获取属性名称
            String fieldName = field.getName();
            // 再获取该属性在Entity中对应的属性名称
            String targetFieldName = StringUtils.IsNullOrWhiteSpace(annotation.from()) ? fieldName : annotation.from();

            // 获取Entity的field
            Field targetField = null;
            try {
                targetField = target.getClass().getDeclaredField(targetFieldName);
            } catch (NoSuchFieldException e) { }
            if(targetField == null) continue;

            // 接下来获取Entity中Field对应的get方法，获取Model中Field对应的set方法
            String getSourceMethodName = "get" + StringUtils.CaptureName(fieldName);
            String setTargetMethodName = "set" + StringUtils.CaptureName(targetFieldName);
            Method getSourceMethod = null;
            Method setTargetMethod = null;
            try {
                getSourceMethod = source.getClass().getMethod(getSourceMethodName);
                setTargetMethod = target.getClass().getMethod(setTargetMethodName, targetField.getType());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            // 若Entity或Model中有任意一个方法不存在，则跳过不赋值
            if(getSourceMethod == null || setTargetMethod == null) continue;

            // 赋值
            try {
                Object value = getSourceMethod.invoke(source);
                setTargetMethod.invoke(target, value);
            } catch (InvocationTargetException e) { }
        }

        return target;
    }

    /**
     * 将Model对象数组转换成Entity对象数组
     * @param sourceList Model对象数组
     * @param targetClazz 要转换的Entity对象的class
     * @param <T>Entity class
     * @param <S>Model class
     * @return 转换结果（Entity对象数组）
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T, S> List<T> Restore(List<S> sourceList, Class<T> targetClazz) throws InstantiationException, IllegalAccessException {
        List<T> targetList = new ArrayList<T>();
        Iterator<S> iterator = sourceList.iterator();

        while(iterator.hasNext()) {
            targetList.add(Restore(iterator.next(), targetClazz));
        }

        return targetList;
    }

    /**
     *
     * @param source
     * @param target
     * @param <S>
     */
    public static <S> void Assign(S source, Object target) {
        // 获取源对象（Model）所有属性
        Field[] fields = source.getClass().getDeclaredFields();

        // 遍历Model中所有属性，调用get set方法赋值
        for (Field field:
                fields) {
            // 获取Converter注解，若没有该注解，该属性不会被转换
            Converter annotation = field.getAnnotation(Converter.class);
            if(annotation == null) continue;
            // 若属性不允许赋值，则不转换
            if(!annotation.isAssign()) continue;

            // 存在注解，则先获取属性名称
            String fieldName = field.getName();
            // 再获取该属性在Entity中对应的属性名称
            String targetFieldName = StringUtils.IsNullOrWhiteSpace(annotation.from()) ? fieldName : annotation.from();

            // 获取Entity的field
            Field targetField = null;
            try {
                targetField = target.getClass().getDeclaredField(targetFieldName);
            } catch (NoSuchFieldException e) { }
            if(targetField == null) continue;

            // 接下来获取Entity中Field对应的get方法，获取Model中Field对应的set方法
            String getSourceMethodName = "get" + StringUtils.CaptureName(fieldName);
            String setTargetMethodName = "set" + StringUtils.CaptureName(targetFieldName);
            Method getSourceMethod = null;
            Method setTargetMethod = null;
            try {
                getSourceMethod = source.getClass().getMethod(getSourceMethodName);
                setTargetMethod = target.getClass().getMethod(setTargetMethodName, targetField.getType());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            // 若Entity或Model中有任意一个方法不存在，则跳过不赋值
            if(getSourceMethod == null || setTargetMethod == null) continue;

            // 赋值
            try {
                Object value = getSourceMethod.invoke(source);
                setTargetMethod.invoke(target, value);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}

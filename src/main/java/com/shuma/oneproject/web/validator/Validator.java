package com.shuma.oneproject.web.validator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.regex.Matcher;

import com.shuma.oneproject.common.ResourceUtils;
import com.shuma.oneproject.common.StringUtils;
import com.shuma.oneproject.web.validator.annotations.*;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/1
 * @desc
 *  校验器
 */
public class Validator {

    public static ValidResult Valid(Object object) {
        // 定义校验结果
        ValidResult validResult = new ValidResult();

        // 开始校验
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field:
                fields) {
            // 获取field的值
            String fieldName = field.getName();
            String getMethodName = "get" + StringUtils.CaptureName(fieldName);
            Method getMethod = null;
            try {
                getMethod = object.getClass().getMethod(getMethodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if(getMethod == null) continue;
            try {
                Object value = getMethod.invoke(object);

                // 开始各类校验
                validResult.combine(nullValid(value, fieldName, field.getAnnotation(Null.class)));
                validResult.combine(notNullValid(value, fieldName, field.getAnnotation(NotNull.class)));
                validResult.combine(assertTrueValid(value, fieldName, field.getAnnotation(AssertTrue.class)));
                validResult.combine(assertFalseValid(value, fieldName, field.getAnnotation(AssertFalse.class)));
                validResult.combine(lengthValid(value, fieldName, field.getAnnotation(Length.class)));
                validResult.combine(rangeValid(value, fieldName, field.getAnnotation(Range.class)));
                validResult.combine(patternValid(value, fieldName, field.getAnnotation(Pattern.class)));
                validResult.combine(equalToValid(value, fieldName, object, field.getAnnotation(EqualTo.class)));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return validResult;
    }

    /**
     * 空校验
     * @param value
     * @param fieldName
     * @param annotation
     * @return
     */
    private static ValidResult nullValid(Object value, String fieldName, Null annotation) {
        ValidResult validResult = new ValidResult();

        if(annotation != null) {
            if(value != null) {
                Error error = new Error();
                error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.Null"), fieldName));
                validResult.addError(error);
            }
        }

        return validResult;
    }

    /**
     * 非空校验
     * @param value
     * @param fieldName
     * @param annotation
     * @return
     */
    private static ValidResult notNullValid(Object value, String fieldName, NotNull annotation) {
        ValidResult validResult = new ValidResult();

        if(annotation != null) {
            if(value == null) {
                Error error = new Error();
                error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.Null"), fieldName));
                validResult.addError(error);
            }
        }

        return validResult;
    }

    /**
     * 元素为真校验
     * @param value
     * @param fieldName
     * @param annotation
     * @return
     */
    private static ValidResult assertTrueValid(Object value, String fieldName, AssertTrue annotation) {
        ValidResult validResult = new ValidResult();

        if(annotation != null) {
            Error error = new Error();
            if(value instanceof Boolean) {
                if(!((Boolean) value).booleanValue()) {
                    error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.AssertTrue"), fieldName));
                    validResult.addError(error);
                }
            }
            else {
                error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.FiledIsNotBoolean"), fieldName));
                validResult.addError(error);
            }
        }

        return validResult;
    }

    /**
     * 元素为假校验
     * @param value
     * @param fieldName
     * @param annotation
     * @return
     */
    private static ValidResult assertFalseValid(Object value, String fieldName, AssertFalse annotation) {
        ValidResult validResult = new ValidResult();

        if(annotation != null) {
            Error error = new Error();
            if(value instanceof Boolean) {
                if(((Boolean) value).booleanValue()) {
                    error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.AssertFalse"), fieldName));
                    validResult.addError(error);
                }
            }
            else {
                error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.FiledIsNotBoolean"), fieldName));
                validResult.addError(error);
            }
        }

        return validResult;
    }

    /**
     * 字符串长度校验
     * @param value
     * @param fieldName
     * @param annotation
     * @return
     */
    private static ValidResult lengthValid(Object value, String fieldName, Length annotation) {
        ValidResult validResult = new ValidResult();

        if(annotation != null) {
            Error error = new Error();
            if(value instanceof String) {
                int min = annotation.min();
                int max = annotation.max();

                if(min >= 0) {
                    if(((String) value).length() < min) {
                        error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.LengthMin"), fieldName, min));
                        validResult.addError(error);
                    }
                }

                if(max >= 0) {
                    if(((String) value).length() > max) {
                        error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.LengthMax"), fieldName, max));
                        validResult.addError(error);
                    }
                }
            }
            else {
                error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.FiledIsNotString"), fieldName));
                validResult.addError(error);
            }
        }

        return validResult;
    }

    /**
     * 数字大小校验
     * @param value
     * @param fieldName
     * @param annotation
     * @return
     */
    private static ValidResult rangeValid(Object value, String fieldName, Range annotation) {
        ValidResult validResult = new ValidResult();

        if(annotation != null) {
            Error error = new Error();
            if(isDigit(value)) {
                double min = annotation.min();
                double max = annotation.max();

                if(Double.valueOf((String)value) < min) {
                    error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.RangeMin"), fieldName, min));
                    validResult.addError(error);
                }

                if(Double.valueOf((String)value) > max) {
                    error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.RangeMax"), fieldName, max));
                    validResult.addError(error);
                }
            }
            else {
                error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.FiledIsNotDigit"), fieldName));
                validResult.addError(error);
            }
        }

        return validResult;
    }

    /**
     * 正则式校验
     * @param value
     * @param fieldName
     * @param annotation
     * @return
     */
    private static ValidResult patternValid(Object value, String fieldName, Pattern annotation) {
        ValidResult validResult = new ValidResult();

        if(annotation != null) {
            if(!StringUtils.IsNullOrWhiteSpace(annotation.value())) {
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(annotation.value());
                Matcher matcher = pattern.matcher((String)value);
                if(!matcher.matches()) {
                    Error error = new Error();
                    error.setMessage(StringUtils.IsNullOrWhiteSpace(annotation.message()) ?
                            MessageFormat.format(ResourceUtils.Trans("Validator.Pattern"), fieldName) :
                            annotation.message());
                    validResult.addError(error);
                }
            }
        }

        return validResult;
    }

    /**
     * 字段相等校验
     * @param value
     * @param fieldName
     * @param source
     * @param annotation
     * @return
     */
    private static ValidResult equalToValid(Object value, String fieldName, Object source, EqualTo annotation) {
        ValidResult validResult = new ValidResult();

        if(annotation != null) {
            if(!StringUtils.IsNullOrWhiteSpace(annotation.fieldName())) {
                String sourceFieldName = annotation.fieldName();
                String getMethodName = "get" + StringUtils.CaptureName(sourceFieldName);
                Method getMethod = null;
                try {
                    getMethod = source.getClass().getMethod(getMethodName);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                if(getMethod != null) {
                    try {
                        Object target = getMethod.invoke(source);
                        if(!value.equals(target)) {
                            Error error = new Error();
                            error.setMessage(MessageFormat.format(ResourceUtils.Trans("Validator.EqualTo"), fieldName, sourceFieldName));
                            validResult.addError(error);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }

        return validResult;
    }

    /**
     * 判断value是否为数字
     * @param value
     * @return
     */
    private static boolean isDigit(Object value) {
        try {
            Double.valueOf((String)value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}

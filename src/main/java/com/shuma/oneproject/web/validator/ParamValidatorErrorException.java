package com.shuma.oneproject.web.validator;

import java.util.List;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/7
 * @desc
 *  参数校验失败异常
 */
public class ParamValidatorErrorException extends RuntimeException {

    /**
     * 错误描述列表
     */
    List<Error> errors;

    public ParamValidatorErrorException(List<Error> errors) {
        this.errors = errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return this.errors;
    }
}

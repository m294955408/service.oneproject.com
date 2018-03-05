package com.shuma.oneproject.web.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/1
 * @desc
 *  单个参数的校验结果
 */
public class ValidResult {

    /**
     * 校验错误列表
     */
    private List<Error> errors;

    public List<Error> getErrors() { return this.errors; }

    public boolean hasErrors() {
        return errors != null;
    }

    public void addError(Error error) {
        if(errors == null)
            errors = new ArrayList<Error>();

        errors.add(error);
    }

    public void combine(ValidResult validResult) {
        if(validResult.hasErrors()) {
            for (Error error:
                 validResult.getErrors()) {
                addError(error);
            }
        }
    }
}

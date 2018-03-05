package com.shuma.oneproject.model.viewmodel;

import com.shuma.oneproject.model.utils.DataConverter;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 */
public class FormModel {

    public <T> T toModel(Class<T> targetClazz) throws InstantiationException, IllegalAccessException {
        return DataConverter.Cast(this, targetClazz);
    }

}

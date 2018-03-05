package com.shuma.oneproject.model.viewmodel.blog;

import com.shuma.oneproject.model.viewmodel.FormModel;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/9/13
 * @desc
 */
public class CategoryFormModel extends FormModel {

    /**
     * 类别名称
     */
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
}

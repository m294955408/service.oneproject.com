package com.shuma.oneproject.model;

import com.shuma.oneproject.model.annotations.Converter;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/26
 * @desc
 */
public class CategoryModel {

    /**
     * 自增ID
     */
    @Converter( isAssign = false )
    private int id;

    /**
     * 类别名称
     */
    @Converter
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

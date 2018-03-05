package com.shuma.oneproject.entity;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/26
 * @desc
 */
public class Category {

    /**
     * 自增ID
     */
    private int id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 所属用户用户名
     */
    private String userName;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

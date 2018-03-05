package com.shuma.oneproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shuma.oneproject.model.annotations.Converter;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/23
 * @desc
 *  账号模型
 */
public class AccountModel {

    /**
     * 自增ID
     */
    @Converter( isAssign = false )
    private int id;

    /**
     * 用户名
     */
    @Converter
    private String userName;

    /**
     * 手机号
     */
    @Converter
    private String phone;

    /**
     * 邮箱
     */
    @Converter
    private String email;

    /**
     * 昵称
     */
    @Converter
    private String nickName;

    /**
     * 密码
     */
    @Converter
    private String password;

    public int getId() { return this.id; }
    public String getUserName() { return this.userName; }
    public String getPhone() { return this.phone; }
    public String getEmail() { return this.email; }
    public String getNickName() { return this.nickName; }
    @JsonIgnore
    public String getPassword() { return this.password; }

    public void setId(int id) { this.id = id; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public void setPassword(String password) { this.password = password;}

}

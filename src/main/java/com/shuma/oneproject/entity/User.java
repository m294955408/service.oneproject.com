package com.shuma.oneproject.entity;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/23
 * @desc
 *  用户实体
 */
public class User {

    /**
     * 自增ID
     */
    private int id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    public int getId() { return this.id; }
    public String getUserName() { return this.userName; }
    public String getPhone() { return this.phone; }
    public String getEmail() { return this.email; }
    public String getNickName() { return this.nickName; }

    public void setId(int id) { this.id = id; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setNickName(String nickName) { this.nickName = nickName; }

}

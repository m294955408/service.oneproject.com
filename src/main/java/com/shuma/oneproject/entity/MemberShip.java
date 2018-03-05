package com.shuma.oneproject.entity;

import java.util.Date;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 */
public class MemberShip {

    /**
     * 用户ID，外键，对应User的自增ID
     */
    private int userId;

    /**
     * 账户创建日期
     */
    private Date createDate;

    /**
     * 身份验证激活Token，可发送至用户邮箱让用户来激活账号
     */
    private String confirmationToken;

    /**
     * 账号是否已激活
     */
    private boolean isConfirmed;

    /**
     * 最后一次登录密码输入错误的时间
     */
    private Date lastPasswordFailureDate;

    /**
     * 最后一次登录成功到现在用户密码输入错误的次数
     */
    private int passwordFailuresSinceLastSuccess;

    /**
     * 密码，单项加密存储
     */
    private String password;

    /**
     * 密码更改日期
     */
    private Date passwordChangedDate;

    /**
     * 密码“盐”值
     */
    private String passwordSalt;

    /**
     * 更改密码时用于确认的Token值
     */
    private String passwordVerificationToken;

    /**
     * 更改密码时用于确认的Token的失效时间
     */
    private Date passwordVerificationTokenExpirationDate;

    public int getUserId() { return this.userId; }
    public Date getCreateDate() { return this.createDate; }
    public String getConfirmationToken() { return this.confirmationToken; }
    public boolean getIsConfirmed() { return this.isConfirmed; }
    public Date getLastPasswordFailureDate() { return this.lastPasswordFailureDate; }
    public int getPasswordFailuresSinceLastSuccess() { return this.passwordFailuresSinceLastSuccess; }
    public String getPassword() { return this.password; }
    public Date getPasswordChangedDate() { return this.passwordChangedDate; }
    public String getPasswordSalt() { return this.passwordSalt; }
    public String getPasswordVerificationToken() { return this.passwordVerificationToken; }
    public Date getPasswordVerificationTokenExpirationDate() { return this.passwordVerificationTokenExpirationDate; }

    public void setUserId(int userId) { this.userId = userId; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public void setConfirmationToken(String confirmationToken) { this.confirmationToken = confirmationToken; }
    public void setIsConfirmed(boolean isConfirmed) { this.isConfirmed = isConfirmed; }
    public void setLastPasswordFailureDate(Date lastPasswordFailureDate) { this.lastPasswordFailureDate = lastPasswordFailureDate; }
    public void setPasswordFailuresSinceLastSuccess(int passwordFailuresSinceLastSuccess) { this.passwordFailuresSinceLastSuccess = passwordFailuresSinceLastSuccess; }
    public void setPassword(String password) { this.password = password; }
    public void setPasswordChangedDate(Date passwordChangedDate) { this.passwordChangedDate = passwordChangedDate; }
    public void setPasswordSalt(String passwordSalt) { this.passwordSalt = passwordSalt; }
    public void setPasswordVerificationToken(String passwordVerificationToken) { this.passwordVerificationToken = passwordVerificationToken; }
    public void setPasswordVerificationTokenExpirationDate(Date passwordVerificationTokenExpirationDate) { this.passwordVerificationTokenExpirationDate = passwordVerificationTokenExpirationDate;}


}

package com.shuma.oneproject.model.viewmodel.user;

import com.shuma.oneproject.web.validator.annotations.EqualTo;
import com.shuma.oneproject.web.validator.annotations.Length;
import com.shuma.oneproject.web.validator.annotations.NotNull;
import com.shuma.oneproject.web.validator.annotations.Pattern;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 */
public class CreateUserFormModel extends UserFormModel {

    /**
     * 用户名
     */
    @NotNull
    @Length(min = 4, max = 32)
    private String userName;

    /**
     * 密码
     */
    @NotNull
    @Pattern(value = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$")
    private String password;

    /**
     * 确认密码
     */
    @NotNull
    @EqualTo(fieldName = "password")
    private String confirmPassword;

    public String getUserName() { return this.userName; }
    public String getPassword() { return this.password; }
    public String getConfirmPassword() { return this.confirmPassword; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setPassword(String password) { this.password = password; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

}

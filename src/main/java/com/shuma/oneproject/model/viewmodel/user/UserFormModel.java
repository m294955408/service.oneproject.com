package com.shuma.oneproject.model.viewmodel.user;

import com.shuma.oneproject.model.viewmodel.FormModel;
import com.shuma.oneproject.web.validator.annotations.Length;
import com.shuma.oneproject.web.validator.annotations.NotNull;
import com.shuma.oneproject.web.validator.annotations.Pattern;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/9/13
 * @desc
 */
public class UserFormModel  extends FormModel {
    /**
     * 昵称
     */
    @NotNull
    @Length(min = 2, max = 32)
    private String nickName;

    /**
     * 手机
     */
    @Pattern(value = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$")
    private String phone;

    /**
     * 邮箱
     */
    @Pattern(value = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")
    private String email;

    public String getNickName() { return this.nickName; }
    public String getPhone() { return this.phone; }
    public String getEmail() { return this.email; }

    public void setNickName(String nickName) { this.nickName = nickName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
}

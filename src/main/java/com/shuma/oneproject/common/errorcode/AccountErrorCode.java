package com.shuma.oneproject.common.errorcode;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 *  账户相关错误码定义
 */
public class AccountErrorCode extends ErrorCode {

    /**
     * 两次密码输入不相等
     */
    public static final int CONFIRM_PASSWORD_IS_NOT_EQUAL = 100;

    /**
     * 用户已经存在
     */
    public static final int USERNAME_HAS_EXIST = 101;

    /**
     * 用户不存在
     */
    public static final int USER_NOT_EXIST = 102;

    /**
     * 密码错误
     */
    public static final int PASSWORD_WRONG = 103;

    /**
     * 用户未登录
     */
    public static final int USER_NOT_LOGIN = 104;

}

package com.shuma.oneproject.common.errorcode;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/30
 * @desc
 */
public class BlogErrorCode extends ErrorCode {

    /**
     * 当前用户已定义过该类别
     */
    public static final int CURRENT_USER_EXIST_CATEGORY = 200;

    /**
     * 当前用户不拥有该类别
     */
    public static final int CURRENT_USER_DONOT_HAVE_CATEGORY = 201;

}

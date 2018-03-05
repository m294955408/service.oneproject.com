package com.shuma.oneproject.common.result;

import com.shuma.oneproject.common.errorcode.ErrorCode;
import com.sun.net.httpserver.Authenticator;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 */
public class SingleOperateResult extends OperateResult {

    /**
     * 单个模型数据
     */
    private Object object;

    public Object getObject() { return this.object; }

    public void setObject(Object object) { this.object = object; }

    /**
     * 成功
     * @return
     *  返回空的数据模型
     */
    public static  SingleOperateResult Success() {
        return SingleOperateResult.Success(null);
    }

    /**
     * 成功
     * @param object 返回的数据模型
     * @return
     */
    public static SingleOperateResult Success(Object object) {
        SingleOperateResult result = new SingleOperateResult();
        result.setCode(ErrorCode.SUCCESS);
        result.setObject(object);

        return result;
    }

    /**
     * 失败
     * @param code 错误码
     * @return
     *  返回空的数据模型
     */
    public static SingleOperateResult Fail(int code) {
        return SingleOperateResult.Fail(code, null);
    }

    /**
     * 失败
     * @param code 错误码
     * @param object 返回的数据模型
     * @return
     */
    public static SingleOperateResult Fail(int code, Object object) {
        SingleOperateResult result = new SingleOperateResult();
        result.setCode(code);
        result.setObject(object);

        return result;
    }
}

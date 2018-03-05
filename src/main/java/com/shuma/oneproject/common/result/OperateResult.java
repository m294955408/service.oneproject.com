package com.shuma.oneproject.common.result;

import java.util.Date;

import com.shuma.oneproject.common.errorcode.ErrorCode;
import com.shuma.oneproject.common.ResourceUtils;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 *  操作返回
 */
public class OperateResult {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回描述
     */
    private String message;

    /**
     * 返回时间
     */
    private Long time = new Date().getTime();

    /**
     * 成功
     * @return
     */
    public static OperateResult Success() {
        OperateResult result = new OperateResult();
        result.setCode(ErrorCode.SUCCESS);

        return result;
    }

    /**
     * 失败
     * @param code 错误码
     * @return
     */
    public static OperateResult Fail(int code) {
        OperateResult result = new OperateResult();
        result.setCode(code);

        return result;
    }

    public int getCode() { return this.code; }
    public String getMessage()
    {
        this.message = ResourceUtils.Trans("ErrorCode.ERROR_CODE_" + this.code);
        return this.message;
    }
    public Long getTime() { return  this.time; }

    public void setCode(int code) { this.code = code; }

    public boolean getIsSuccess() { return this.code == ErrorCode.SUCCESS; }

}

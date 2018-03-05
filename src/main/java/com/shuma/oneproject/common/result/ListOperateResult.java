package com.shuma.oneproject.common.result;

import com.shuma.oneproject.common.PageList;
import com.shuma.oneproject.common.errorcode.ErrorCode;

import java.util.List;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 */
public class ListOperateResult extends OperateResult {

    /**
     * 分页的列表对象
     */
    private List<?> items;

    public List<?> getItems() { return this.items; }

    public void setItems(List<?> items) { this.items = items; }

    /**
     * 成功
     * @return
     *  返回空的数据模型
     */
    public static ListOperateResult Success() {
        return ListOperateResult.Success(null);
    }

    /**
     * 成功
     * @param items 分页列表模型
     * @return
     */
    public static ListOperateResult Success(List<?> items) {
        ListOperateResult result = new ListOperateResult();
        result.setCode(ErrorCode.SUCCESS);
        result.setItems(items);

        return result;
    }

    /**
     * 失败
     * @param code 错误码
     * @return
     *  返回一个空的列表
     */
    public static ListOperateResult Fail(int code) {
        return ListOperateResult.Fail(code, null);
    }

    /**
     * 失败
     * @param code 错误码
     * @param items 分页列表模型
     * @return
     */
    public static  ListOperateResult Fail(int code, List<?> items) {
        ListOperateResult result = new ListOperateResult();
        result.setCode(code);
        result.setItems(items);

        return result;
    }

}

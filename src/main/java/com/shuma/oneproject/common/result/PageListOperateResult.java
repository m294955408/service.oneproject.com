package com.shuma.oneproject.common.result;

import com.shuma.oneproject.common.PageList;
import com.shuma.oneproject.common.errorcode.ErrorCode;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/8
 * @desc
 */
public class PageListOperateResult extends OperateResult {
    /**
     * 分页的列表对象
     */
    private PageList<?> items;

    public PageList<?> getItems() { return this.items; }

    public void setItems(PageList<?> items) { this.items = items; }

    /**
     * 成功
     * @return
     *  返回空的数据模型
     */
    public static PageListOperateResult Success() {
        return PageListOperateResult.Success(null);
    }

    /**
     * 成功
     * @param items 分页列表模型
     * @return
     */
    public static PageListOperateResult Success(PageList<?> items) {
        PageListOperateResult result = new PageListOperateResult();
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
    public static PageListOperateResult Fail(int code) {
        return PageListOperateResult.Fail(code, null);
    }

    /**
     * 失败
     * @param code 错误码
     * @param items 分页列表模型
     * @return
     */
    public static  PageListOperateResult Fail(int code, PageList<?> items) {
        PageListOperateResult result = new PageListOperateResult();
        result.setCode(code);
        result.setItems(items);

        return result;
    }
}

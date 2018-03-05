package com.shuma.oneproject.service;

import com.shuma.oneproject.common.result.ListOperateResult;
import com.shuma.oneproject.common.result.SingleOperateResult;
import com.shuma.oneproject.model.CategoryModel;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/26
 * @desc
 */
public interface BlogService {

    //region 类别管理

    /**
     * 获取分类信息
     * @param id 类别ID
     * @return
     */
    SingleOperateResult getCategory(int id, String username);

    /**
     * 获取用户设置的所有类别
     * @param username 用户名
     * @return 类别返回
     */
    ListOperateResult getCategorys(String username);

    /**
     * 创建分类
     * @param model
     * @return
     */
    SingleOperateResult createCategory(CategoryModel model);

    /**
     * 编辑分类
     * @param id
     * @param model
     * @return
     */
    SingleOperateResult editCategory(int id, CategoryModel model);

    //endregion

}

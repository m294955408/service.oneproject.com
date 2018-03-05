package com.shuma.oneproject.service;

import java.util.List;

import com.shuma.oneproject.common.PageList;
import com.shuma.oneproject.common.result.SingleOperateResult;
import com.shuma.oneproject.model.AccountModel;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/23
 * @desc
 */
public interface AccountService {

    /**
     * 登录
     * @param model 账号数据模型
     * @return
     *  登录结果，会将用户信息返回
     */
    SingleOperateResult login(AccountModel model);

    /**
     * 创建账号
     * @param model 账号数据模型
     * @return
     *  创建结果，会将创建的用户信息返回
     */
    SingleOperateResult createAccount(AccountModel model);

    /**
     * 编辑账号
     * @param id 被编辑的用户ID
     * @param model 账号数据模型
     * @return
     *  编辑结果，会返回编辑后的用户信息
     */
    SingleOperateResult editAccount(int id, AccountModel model);

    /**
     * 获取单个用户信息
     * @param id 要获取的用户ID
     * @return
     *  用户模型
     */
    AccountModel getUser(int id);

    /**
     * 获取用户列表
     * @param page 页码
     * @param pagesize 每页数据个数
     * @return
     *  用户模型分页列表
     */
    PageList<AccountModel> getUsers(int page, int pagesize);

}

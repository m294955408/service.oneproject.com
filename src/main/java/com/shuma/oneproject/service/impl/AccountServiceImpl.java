package com.shuma.oneproject.service.impl;

import java.util.*;

import com.shuma.oneproject.model.utils.DataConverter;
import com.shuma.oneproject.web.auth.AccountExistException;
import com.shuma.oneproject.web.auth.WebSecurity;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuma.oneproject.model.AccountModel;
import com.shuma.oneproject.service.AccountService;
import com.shuma.oneproject.dao.UserDao;
import com.shuma.oneproject.entity.User;
import com.shuma.oneproject.common.PageList;
import com.shuma.oneproject.common.errorcode.AccountErrorCode;
import com.shuma.oneproject.common.result.SingleOperateResult;
import com.shuma.oneproject.dao.MemberShipDao;


/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/23
 * @desc
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MemberShipDao memberShipDao;

    @Autowired
    private WebSecurity webSecurity;

    /**
     * 登录接口实现
     * @param model 账号数据模型
     * @return
     */
    public SingleOperateResult login(AccountModel model) {
        boolean loginResult = false;

        try {
            loginResult = webSecurity.login(model.getUserName(), model.getPassword());
        } catch (UnknownAccountException uae) {
            return SingleOperateResult.Fail(AccountErrorCode.USER_NOT_EXIST);
        } finally {
            if(loginResult) return SingleOperateResult.Success();
            else return SingleOperateResult.Fail(AccountErrorCode.PASSWORD_WRONG);
        }
    }

    /**
     * 创建账号接口实现
     * @param model 账号数据模型
     * @return
     */
    public SingleOperateResult createAccount(AccountModel model) {
        try {
            // 创建新的User
            User user = null;
            try {
                user = DataConverter.Restore(model, User.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 创建账户
            webSecurity.createAccountAndUser(model.getUserName(), model.getPassword(), user);
        } catch (AccountExistException e) {
            return SingleOperateResult.Fail(AccountErrorCode.USERNAME_HAS_EXIST);
        }

        return SingleOperateResult.Success();
    }

    /**
     * 编辑账户接口实现
     * @param id 被编辑的用户ID
     * @param model 账号数据模型
     * @return
     */
    public SingleOperateResult editAccount(int id, AccountModel model) {
        // 根据用户ID获取当前用户
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("id", id);
        User user = userDao.selectOne(condition);
        if(user == null) {
            // 如果用户不存在，则返回错误信息
            return SingleOperateResult.Fail(AccountErrorCode.USER_NOT_EXIST);
        }

        // 更新数据
        user.setEmail(model.getEmail());
        user.setPhone(model.getPhone());
        user.setNickName(model.getNickName());
        userDao.update(user);

        return SingleOperateResult.Success();
    }

    /**
     * 获取用户信息接口实现
     * @param id 要获取的用户ID
     * @return
     */
    public AccountModel getUser(int id) {
        // 根据用户ID获取用户实体
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("id", id);
        User user = userDao.selectOne(condition);
        if(user == null) {
            return null;
        }

        // 将实体转换成Model
        AccountModel model = null;
        try {
            model = DataConverter.Cast(user, AccountModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    /**
     * 获取用户信息列表接口实现
     * @param page 页码
     * @param pagesize 每页数据个数
     * @return
     */
    public PageList<AccountModel> getUsers(int page, int pagesize) {
        // 获取用户列表
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("offset", ( page - 1 ) * pagesize);
        condition.put("limit", pagesize);
        List<User> users = userDao.select(condition);

        // 将用户实体列表转换为账户模型列表
        List<AccountModel> items = new ArrayList<AccountModel>();
        try {
            items = DataConverter.Cast(users, AccountModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取用户总数
        condition.clear();
        int count = userDao.count(condition);

        // 生成分页列表
        return new PageList<AccountModel>(page, pagesize, count, items);
    }
}

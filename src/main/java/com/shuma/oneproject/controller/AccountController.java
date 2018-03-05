package com.shuma.oneproject.controller;

import com.shuma.oneproject.common.StringUtils;
import com.shuma.oneproject.common.result.PageListOperateResult;
import com.shuma.oneproject.model.viewmodel.user.CreateUserFormModel;
import com.shuma.oneproject.model.viewmodel.user.EditUserFormModel;
import com.shuma.oneproject.web.validator.ParamValidator;
import com.shuma.oneproject.web.validator.ParamValidatorErrorException;
import com.shuma.oneproject.web.validator.Valid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.shuma.oneproject.service.AccountService;
import com.shuma.oneproject.common.result.JsonUtil;
import com.shuma.oneproject.model.AccountModel;
import com.shuma.oneproject.model.viewmodel.*;
import com.shuma.oneproject.common.errorcode.AccountErrorCode;
import com.shuma.oneproject.common.result.SingleOperateResult;
import com.shuma.oneproject.web.auth.WebSecurity;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/23
 * @desc
 *  账户控制器
 */
@Controller
@RequestMapping("/api/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private WebSecurity webSecurity;

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping ( value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ParamValidator
    public String login(@RequestBody @Valid LoginFormModel user) {

        AccountModel model = new AccountModel();
        model.setUserName(user.getUserName());
        model.setPassword(user.getPassword());

        return JsonUtil.toJson(accountService.login(model));
    }

    @RequestMapping( value = "/logout", method = RequestMethod.POST )
    @ResponseBody
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        return JsonUtil.toJson(SingleOperateResult.Success());
    }

    @RequestMapping( value = "/isLogin")
    @ResponseBody
    public String isLogin() {
        String username = webSecurity.getCurrentUserName();

        if(StringUtils.IsNullOrWhiteSpace(username)) {
            return JsonUtil.toJson(SingleOperateResult.Fail(AccountErrorCode.USER_NOT_LOGIN));
        }
        else {
            return JsonUtil.toJson(SingleOperateResult.Success(username));
        }
    }

    @RequestMapping ( value = "/create", method = RequestMethod.POST )
    @ResponseBody
    public String create(@RequestBody CreateUserFormModel user) {

        if(user.getPassword().equals(user.getConfirmPassword())) {
            AccountModel model = new AccountModel();
            model.setUserName(user.getUserName());
            model.setNickName(user.getNickName());
            model.setEmail(user.getEmail());
            model.setPhone(user.getPhone());
            model.setPassword(user.getPassword());

            return JsonUtil.toJson(accountService.createAccount(model));
        }
        else {
            return JsonUtil.toJson(SingleOperateResult.Fail(AccountErrorCode.CONFIRM_PASSWORD_IS_NOT_EQUAL));
        }
    }

    @RequestMapping ( value = "/edit", method = RequestMethod.POST )
    @ResponseBody
    public String edit(int id, @RequestBody EditUserFormModel user) {
        AccountModel model = new AccountModel();
        model.setNickName(user.getNickName());
        model.setPhone(user.getPhone());
        model.setEmail(user.getEmail());

        return JsonUtil.toJson(accountService.editAccount(id, model));
    }

    @RequestMapping ( value = "/find", method = RequestMethod.GET )
    @ResponseBody
    public String find(int id) {
        return JsonUtil.toJson(SingleOperateResult.Success(accountService.getUser(id)));
    }

    @RequestMapping ( value = "/list", method = RequestMethod.GET )
    @ResponseBody
    public String list(int page, int pageSize) {
        return JsonUtil.toJson(PageListOperateResult.Success(accountService.getUsers(page, pageSize)));
    }

    @RequestMapping( value = "/test", method = RequestMethod.GET)
    @ResponseBody
    @ParamValidator
    public String test() throws ParamValidatorErrorException {
        AccountModel user = new AccountModel();
        user.setUserName("admin");
        user.setPassword("a12345");
        user.setPhone("18800000000");
        user.setEmail("18800000000@163.com");
        user.setNickName("管理员");

        accountService.createAccount(user);

        logger.debug("Test Debug");
        logger.info("Test Info");
        logger.error("Test Error");
        logger.warn("Test Warn");
        return JsonUtil.toJson(accountService.getUsers(1, 20));
    }

    @RequestMapping( value = "/initadmin", method = RequestMethod.GET)
    public String init()
    {
        AccountModel user = new AccountModel()
        {{
            setUserName("admin");
            setPassword("123456");
        }};

        accountService.createAccount(user);

        return "";
    }
}

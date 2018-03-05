package com.shuma.oneproject.web.auth;

import com.shuma.oneproject.common.MD5Utils;
import com.shuma.oneproject.dao.MemberShipDao;
import com.shuma.oneproject.dao.UserDao;
import com.shuma.oneproject.entity.MemberShip;
import com.shuma.oneproject.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/29
 * @desc
 */
@Component
public class WebSecurity {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MemberShipDao memberShipDao;

    /**
     * 登录（默认下次不记住登录状态）
     * @param username 用户名
     * @param password 密码
     * @return 登录是否成功
     * @throws UnknownAccountException 账户不存在异常
     */
    public boolean login(String username, String password) throws  UnknownAccountException {
        return login(username, password, false);
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param rememberMe 是否记住我
     * @return 登录是否成功
     * @throws UnknownAccountException 账户不存在异常
     */
    public boolean login(String username, String password, boolean rememberMe) throws UnknownAccountException {

        boolean loginResult = false;

        // 使用shiro进行登录
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Utils.From(password));
        token.setRememberMe(rememberMe);
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
        } catch (IncorrectCredentialsException ice) {
            loginResult = false;
        } finally {
            loginResult = currentUser.isAuthenticated();
        }

        return loginResult;
    }

    /**
     * 退出登录
     */
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    /**
     * 创建账号
     * @param username 用户名
     * @param password 密码
     * @return 账号的用户名
     * @throws AccountExistException 用户已存在异常
     */
    public String createAccount(String username, String password)
            throws AccountExistException {
        return createAccount(username, password, false);
    }

    /**
     * 创建账号
     * @param username 用户名
     * @param password 密码
     * @param requireConfirmationToken 是否需要验证
     * @return 账号的用户名
     * @throws AccountExistException 用户已存在异常
     */
    public String createAccount(String username, String password, boolean requireConfirmationToken)
            throws AccountExistException {
        User user = new User();
        user.setUserName(username);

        return createAccountAndUser(username, password, user, requireConfirmationToken);
    }

    /**
     * 创建账号和用户信息
     * @param username 用户名
     * @param password 密码
     * @param user 用户信息
     * @return 账号的用户名
     * @throws AccountExistException 用户已存在异常
     */
    public String createAccountAndUser(String username, String password, User user)
            throws AccountExistException {
        return createAccountAndUser(username, password, user, false);
    }

    /**
     * 创建账号和用户信息
     * @param username 用户名
     * @param password 密码
     * @param user 用户信息
     * @param requireConfirmationToken 是否需要验证
     * @return 账号的用户名
     * @throws AccountExistException 用户已存在异常
     */
    public String createAccountAndUser(String username, String password, User user, boolean requireConfirmationToken)
            throws AccountExistException{
        Map<String, Object> map = new HashMap<String, Object>();
        // 得到类对象
        Class userClazz = (Class) user.getClass();
        // 得到类中所有属性
        Field[] fields = userClazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = new Object();
            try {
                value = field.get(user);
                map.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        // 判断要创建账号的UserName是否已经存在
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userName", username);
        if(userDao.selectOne(condition) != null) {
            throw new AccountExistException();
        }

        // 往User表中插入数据
        userDao.insert(user);

        // 再次获取User
        user = userDao.selectOne(condition);

        // 创建MemberShip信息
        MemberShip memberShip = new MemberShip();
        memberShip.setUserId(user.getId());
        memberShip.setCreateDate(new Date());

        memberShip.setPasswordFailuresSinceLastSuccess(0);
        memberShip.setPassword(MD5Utils.From(password));
        memberShip.setPasswordChangedDate(new Date());
        memberShip.setPasswordSalt("");
        // 如果需要验证，则生成验证token
        if(requireConfirmationToken) {
            memberShip.setIsConfirmed(false);
            memberShip.setConfirmationToken(UUID.randomUUID().toString());
        } else {
            memberShip.setIsConfirmed(true);
        }
        memberShipDao.insert(memberShip);

        return username;
    }

    /**
     * 修改密码
     * @param username 用户名
     * @param currentPassword 当前密码
     * @param newPassword 新密码
     * @return 修改密码是否成功
     * @throws UnknownAccountException 用户名不存在
     */
    public boolean ChangePassword(String username, String currentPassword, String newPassword)
            throws UnknownAccountException{
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userName", username);
        User user = userDao.selectOne(condition);
        if(user == null) {
            throw new UnknownAccountException();
        }

        condition.clear();
        condition.put("userId", user.getId());
        MemberShip memberShip = memberShipDao.selectOne(condition);
        if(memberShip == null) {
            throw new UnknownAccountException();
        }
        if(memberShip.getPassword().equals(MD5Utils.From(currentPassword))) {
            memberShip.setPassword(MD5Utils.From(newPassword));
            memberShip.setPasswordChangedDate(new Date());
            memberShipDao.update(memberShip);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 生成更改密码的token
     * @param username 用户名
     * @return token
     * @throws UnknownAccountException 未知账户
     */
    public String generatePasswordResetToekn(String username)
            throws UnknownAccountException {
        return generatePasswordResetToekn(username, 1440);
    }

    /**
     * 生成更改密码的token
     * @param username 用户名
     * @param tokenExpirationInMinutesFromNow 从现在起到截止日期的分钟数
     * @return token
     * @throws UnknownAccountException 未知账户
     */
    public String generatePasswordResetToekn(String username, int tokenExpirationInMinutesFromNow)
            throws UnknownAccountException {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userName", username);
        User user = userDao.selectOne(condition);
        if(user == null) {
            throw new UnknownAccountException();
        }

        condition.clear();
        condition.put("userId", user.getId());
        MemberShip memberShip = memberShipDao.selectOne(condition);
        if(memberShip == null) {
            throw new UnknownAccountException();
        }
        String token = UUID.randomUUID().toString();
        Date expirationDate = new Date();
        long time = expirationDate.getTime();
        time = time + tokenExpirationInMinutesFromNow * 60 * 1000;
        expirationDate.setTime(time);

        memberShip.setPasswordVerificationToken(token);
        memberShip.setPasswordVerificationTokenExpirationDate(expirationDate);
        memberShipDao.update(memberShip);

        return token;
    }

    /**
     * 根据token重置密码
     * @param passwordResetToken
     * @param newPassword
     * @return
     */
    public boolean resetPassword(String passwordResetToken, String newPassword) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("passwordVerificationToken", passwordResetToken);
        MemberShip memberShip = memberShipDao.selectOne(condition);

        if(memberShip == null) {
            // 若memberShip不存在，返回重置密码失败
            return false;
        }

        if(memberShip.getPasswordVerificationTokenExpirationDate().before(new Date())) {
            // 若token已经过期，则返回重置密码失败
            return false;
        }

        // 重置密码
        memberShip.setPassword(MD5Utils.From(newPassword));
        memberShip.setPasswordChangedDate(new Date());
        memberShipDao.update(memberShip);

        return true;
    }

    /**
     * 获取当前用户的用户名
     * @return
     *  当前用户的用户名，若为null，则用户未登录
     */
    public String getCurrentUserName() {
        Subject currentUser = SecurityUtils.getSubject();
        Session userSession = currentUser.getSession();
        return userSession.getAttribute("currentUser") == null ? null : userSession.getAttribute("currentUser").toString();
    }
}

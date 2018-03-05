package com.shuma.oneproject.web.auth;

import com.shuma.oneproject.dao.MemberShipDao;
import com.shuma.oneproject.dao.UserDao;
import com.shuma.oneproject.entity.MemberShip;
import com.shuma.oneproject.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/27
 * @desc
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MemberShipDao memberShipDao;

    public AuthRealm() {
        super();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roles = new HashSet<String>();
        roles.add("admin");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }

    /**
     * 验证当前登录的Subject
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userName", (token.getUsername()));
        User user = userDao.selectOne(condition);

        if(user != null) {
            condition.clear();
            condition.put("userId", user.getId());
            MemberShip memberShip = memberShipDao.selectOne(condition);

            if(memberShip != null) {
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), memberShip.getPassword(), user.getNickName());
                Subject currentUser = SecurityUtils.getSubject();
                if(currentUser.isAuthenticated()) {
                    // 登录成功，设置session，更新memberShip值
                    this.setSession("currentUser", user.getUserName());
                    memberShip.setPasswordFailuresSinceLastSuccess(0);
                    memberShipDao.update(memberShip);
                } else {
                    // 登录失败，更新memberShip
                    memberShip.setPasswordFailuresSinceLastSuccess(memberShip.getPasswordFailuresSinceLastSuccess() + 1);
                    memberShip.setLastPasswordFailureDate(new Date());
                    memberShipDao.update(memberShip);
                }

                return authenticationInfo;
            }
        }

        return null;
    }

    /**
     * 设置Session
     * @param key
     * @param value
     */
    private void setSession(Object key, Object value){

        Subject currentUser = SecurityUtils.getSubject();

        if(null != currentUser){
            Session session = currentUser.getSession();

            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");

            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }

}

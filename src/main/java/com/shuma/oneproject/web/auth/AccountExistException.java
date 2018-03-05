package com.shuma.oneproject.web.auth;

import org.apache.shiro.authc.AccountException;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/7/29
 * @desc
 */
public class AccountExistException extends AccountException {
    public AccountExistException() {
    }

    public AccountExistException(String message) {
        super(message);
    }

    public AccountExistException(Throwable cause) {
        super(cause);
    }

    public AccountExistException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.atguigu.crowd.exception;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.exception
 * @description: TODO
 * @author: xia liang
 * @create: 2020-06-28 15:30
 */
public class LoginFailedExceprion extends RuntimeException{
    static final long serialVersionUID = 1L;
    public LoginFailedExceprion() {
        super();
    }
    
    public LoginFailedExceprion(String message) {
        super(message);
    }
    
    public LoginFailedExceprion(String message, Throwable cause) {
        super(message, cause);
    }
    
    public LoginFailedExceprion(Throwable cause) {
        super(cause);
    }
    
    protected LoginFailedExceprion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

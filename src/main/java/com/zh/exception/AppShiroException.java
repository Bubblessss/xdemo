package com.zh.exception;

import org.apache.shiro.authc.AccountException;

public class AppShiroException extends AccountException {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMsg;

    public AppShiroException() {
    }


    public AppShiroException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

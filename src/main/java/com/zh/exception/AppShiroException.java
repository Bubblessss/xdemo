package com.zh.exception;

import lombok.Data;
import org.apache.shiro.authc.AccountException;

@Data
public class AppShiroException extends AccountException {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMsg;

    public AppShiroException() {
    }

    public AppShiroException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

}

package com.zh.exception;

import lombok.Data;

/**
 * 全局业务异常
 * @author zhanghang
 * @date 2017/12/22
 */
@Data
public class AppException extends RuntimeException   {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMsg;

    public AppException() {
    }

    public AppException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

}

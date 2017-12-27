package com.zh.exception;

/**
 * 全局自定义异常
 * @author zhanghang
 * @date 2017/12/22
 */
public class AppException extends RuntimeException   {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMsg;

    public AppException() {
    }

    public AppException(String errorCode, String errorMsg) {
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

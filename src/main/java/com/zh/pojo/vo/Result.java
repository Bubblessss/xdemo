package com.zh.pojo.vo;

public class Result {
    private Object data;
    private Boolean success = true;
    private Boolean session = true;
    private long total;
    private Object ext1;
    private Object ext2;
    private Object ext3;
    private String errorMsg;

    public Result() {
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(Boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public Result(Object data, long total) {
        this.data = data;
        this.total = total;
    }

    public Result(Object data, long total, Object ext1) {
        this.data = data;
        this.total = total;
        this.ext1 = ext1;
    }

    public Result(Object data, long total, Object ext1, Object ext2) {
        this.data = data;
        this.total = total;
        this.ext1 = ext1;
        this.ext2 = ext2;
    }

    public Result(Object data, long total, Object ext1, Object ext2, Object ext3) {
        this.data = data;
        this.total = total;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getSession() {
        return session;
    }

    public void setSession(Boolean session) {
        this.session = session;
    }

    public Object getExt1() {
        return ext1;
    }

    public void setExt1(Object ext1) {
        this.ext1 = ext1;
    }

    public Object getExt2() {
        return ext2;
    }

    public void setExt2(Object ext2) {
        this.ext2 = ext2;
    }

    public Object getExt3() {
        return ext3;
    }

    public void setExt3(Object ext3) {
        this.ext3 = ext3;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

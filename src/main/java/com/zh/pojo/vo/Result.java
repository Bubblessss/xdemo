package com.zh.pojo.vo;

import lombok.Data;

@Data
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

}

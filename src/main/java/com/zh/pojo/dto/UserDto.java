package com.zh.pojo.dto;

import com.zh.pojo.po.User;

public class UserDto extends User {
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}

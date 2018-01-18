package com.zh.pojo.dto;

import com.zh.pojo.po.User;
import lombok.Data;

@Data
public class UserDto extends User {
    private String deptName;

}

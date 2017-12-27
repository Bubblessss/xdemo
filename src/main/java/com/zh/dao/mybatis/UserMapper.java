package com.zh.dao.mybatis;

import com.zh.pojo.po.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


    List<User> selectAllUsers();
}
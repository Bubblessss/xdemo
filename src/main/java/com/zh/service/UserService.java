package com.zh.service;

import com.zh.pojo.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户服务类接口
 * @author zhanghang
 * @date 2017/12/19
 */
public interface UserService {
    User getUserById(Integer id);

    Page<User> listUsersByDeptId(Integer deptId, Pageable pageable);

    List<User> listUsers();

    void saveUser(User user);

    void removeUserById(Integer id);

    String getUserSexByName(String name);

    void removeFromRedisByKey(String key);
}

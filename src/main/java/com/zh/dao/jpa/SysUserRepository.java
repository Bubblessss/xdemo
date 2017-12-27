package com.zh.dao.jpa;


import com.zh.pojo.po.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    SysUser findByName(String name);
    SysUser findByAccount(String account);
}

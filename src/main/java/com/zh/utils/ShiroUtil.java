package com.zh.utils;

import com.zh.exception.AppException;
import com.zh.pojo.po.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * shiro工具类
 * @author zhanghang
 * @date 2018/1/18
 */
@Component
public class ShiroUtil {

    public SysUser getCurrentSysUser(){
        Object subject = SecurityUtils.getSubject().getPrincipal();
        if (subject == null){
            throw new AppException("ERR_0001");
        }else{
            return (SysUser) subject;
        }
    }
}

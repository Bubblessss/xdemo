package com.zh.config.shiro;

import com.zh.dao.jpa.SysUserRepository;
import com.zh.exception.AppShiroException;
import com.zh.pojo.po.SysUser;
import com.zh.utils.Md5Util;
import com.zh.utils.MyApp;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义认证类
 * PropertySource 指定配置文件
 * @author zhanghang
 * @date 2017/12/22
 */
@Component
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public String getName() {
        return "myShiroRealm";
    }

    /**
     * 登录认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
        log.info("=====================shiro开始认证身份====================");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String account = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SysUser sysUser = this.sysUserRepository.findByAccount(account);
        if (sysUser != null){
            if (!Md5Util.md5(password + sysUser.getName()).equals(sysUser.getPassword())){
                throw new AuthenticationException();
            }
            if (MyApp.SYS_USER_STATUS_Y.equals(sysUser.getStatus())) {
                return new SimpleAuthenticationInfo(sysUser,password,this.getName());
            }else if (MyApp.SYS_USER_STATUS_N.equals(sysUser.getStatus())){
                throw new AppShiroException("ERR_1001");
            }else{
                throw new AppShiroException("ERR_0000");
            }
        }else{
            throw new AppShiroException("ERR_1000");
        }
    }

    /**
     * 权限信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}

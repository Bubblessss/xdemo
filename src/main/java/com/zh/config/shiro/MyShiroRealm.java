package com.zh.config.shiro;

import com.zh.controller.UserController;
import com.zh.dao.jpa.SysUserRepository;
import com.zh.exception.AppException;
import com.zh.exception.AppShiroException;
import com.zh.pojo.po.SysUser;
import com.zh.pojo.vo.Result;
import com.zh.utils.MD5Util;
import com.zh.utils.MyApp;
import com.zh.utils.SpringContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 自定义认证类
 * PropertySource 指定配置文件
 * @author zhanghang
 * @date 2017/12/22
 */
@Component
@PropertySource(value = "classpath:/exception.properties",encoding = "utf-8")
public class MyShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    private static String SYS_USER_ERR_100 = "SYS_USER_ERR_100";
    @Value("${SYS_USER_ERR_100}")
    private String SYS_USER_ERR_100_MSG;

    private static String SYS_USER_ERR_101 = "SYS_USER_ERR_101";
    @Value("${SYS_USER_ERR_101}")
    private String SYS_USER_ERR_101_MSG;

    private static String BASE_NULL_USER_ERR = "BASE_NULL_USER_ERR";
    @Value("${BASE_NULL_USER_ERR}")
    private String BASE_NULL_USER_ERR_MSG;

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
        logger.info("=====================shiro开始认证身份====================");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String account = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SysUser sysUser = this.sysUserRepository.findByAccount(account);
        if (sysUser != null){
            if (!MD5Util.md5(password + sysUser.getName()).equals(sysUser.getPassword())){
                throw new AuthenticationException();
            }
            if (MyApp.SYS_USER_STATUS_Y.equals(sysUser.getStatus())) {

                return new SimpleAuthenticationInfo(sysUser,password,this.getName());
            }else if (MyApp.SYS_USER_STATUS_N.equals(sysUser.getStatus())){
                throw new AppShiroException(SYS_USER_ERR_100,this.SYS_USER_ERR_100_MSG);
            }else{
                throw new AppShiroException(SYS_USER_ERR_101,this.SYS_USER_ERR_101_MSG);
            }
        }else{
            throw new AppShiroException(BASE_NULL_USER_ERR,this.SYS_USER_ERR_100_MSG);
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

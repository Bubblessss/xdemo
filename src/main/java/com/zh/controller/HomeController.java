package com.zh.controller;

import com.zh.exception.AppShiroException;
import com.zh.pojo.po.SysUser;
import com.zh.utils.LoadProperty;
import com.zh.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 登录controller
 * @author zhanghang
 * @date 2017/12/21
 */
@Controller
@RequestMapping("/home")
@Slf4j
public class HomeController {
    @Autowired
    private ShiroUtil shiroUtil;
    @Autowired
    private LoadProperty loadProperty;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(SysUser sysUser, String kaptcha,Model model, HttpSession session) {
        if (StringUtils.isEmpty(sysUser.getAccount()) || StringUtils.isEmpty(sysUser.getPassword())){
            model.addAttribute("loginFail","请输入用户名或密码!");
            return "login";
        }
        if (StringUtils.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(session.getAttribute("kaptcha").toString())){
            model.addAttribute("loginFail","请输入正确的验证码!");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getAccount(), sysUser.getPassword());
        try {
            subject.login(token);
        }  catch (AppShiroException e){
            log.error(e.getMessage());
            token.clear();
            model.addAttribute("loginFail",this.loadProperty.getValue(e.getErrorCode()));
            return "login";
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            token.clear();
            model.addAttribute("loginFail","用户名或密码错误!");
            return "login";
        }
        model.addAttribute("currentSysUserName", this.shiroUtil.getCurrentSysUser().getName());
        return "index";
    }

   @GetMapping("/main")
    public String toIndex(Model model){
        model.addAttribute("currentSysUserName", this.shiroUtil.getCurrentSysUser().getName());
        return "index";
    }

    @GetMapping("/403")
    public String noAuth() {
        return "403";
    }

}

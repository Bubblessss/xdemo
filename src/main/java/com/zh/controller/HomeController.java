package com.zh.controller;

import com.zh.dao.jpa.SysUserRepository;
import com.zh.exception.AppShiroException;
import com.zh.pojo.po.SysUser;
import com.zh.pojo.vo.Result;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
    private SysUserRepository sysUserRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Result doLogin(SysUser sysUser, HttpSession session) {
        if (StringUtils.isEmpty(sysUser.getAccount()) || StringUtils.isEmpty(sysUser.getPassword())){
            return new Result(false, "请输入用户名或密码!");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getAccount(), sysUser.getPassword());
        try {
            subject.login(token);
        } catch (AppShiroException e) {
            log.error(e.getMessage());
            token.clear();
            return new Result(false, e.getMessage());
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            token.clear();
            return new Result(false, "用户名或密码错误");
        }
        sysUser = this.sysUserRepository.findByAccount(sysUser.getAccount());
        session.setAttribute("name", sysUser.getName());
        return new Result();
    }

   @GetMapping("/main")
    public String toIndex(Model model,HttpSession session){
        model.addAttribute("name", session.getAttribute("name"));
        return "index";
    }

    @GetMapping("/403")
    public String noAuth() {
        return "403";
    }

}

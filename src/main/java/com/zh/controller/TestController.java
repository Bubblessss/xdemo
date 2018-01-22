package com.zh.controller;

import com.zh.exception.AppException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试
 * @author zhanghang
 * @date 2018/1/22
 */
@Controller
@RequestMapping("/test")
public class TestController {

    /**
     * 测试全局异常捕获器，
     * 以后service抛出的异常在controller均可继续向上抛，不必在此层捕获处理
     */
    @GetMapping("/testGlobalException")
    public void testException(){
        throw new AppException("ERR_0000");
    }
}

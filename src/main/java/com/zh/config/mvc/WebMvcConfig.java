package com.zh.config.mvc;

import com.zh.config.interceptor.ErrorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * mvc配置
 * @author zhanghang
 * @date 2018/1/22
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 实例化拦截器
     * 多个拦截器组成一个拦截器链
     * addPathPatterns 用于添加拦截规则
     *  excludePathPatterns 用户排除拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ErrorInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}

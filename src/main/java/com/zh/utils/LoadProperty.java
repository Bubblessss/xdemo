package com.zh.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 读取异常信息配置文件
 * @author zhanghang
 * @date 2018/1/18
 */
@Component
@PropertySource(value = "classpath:/exception.properties",encoding = "utf-8")
public class LoadProperty {

    @Autowired
    private Environment env;

    /**
     * 根据code获取属性的值
     * @param code
     * @return
     */
    public String getValue(String code){
        return env.getProperty(code);
    }

}
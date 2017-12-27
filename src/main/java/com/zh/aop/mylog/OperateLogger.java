package com.zh.aop.mylog;

import java.lang.annotation.*;

/**
 * 操作日志注解.
 * @author zhanghang
 * @date 2017/12/19
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLogger {

    /**
     * 日志描述
     */
    String description()  default "";

    /**
     * 操作类型：增，删，改，查(默认)
     */
    String operateType() default "SELECT";

}

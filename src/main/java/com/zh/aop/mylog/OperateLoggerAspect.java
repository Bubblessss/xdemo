package com.zh.aop.mylog;

import com.alibaba.fastjson.JSONObject;
import com.zh.config.activemq.MqProduct;
import com.zh.utils.MyApp;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 操作日志注解切面类
 * @author zhanghang
 * @date 2017/12/19
 */
@Component
@Aspect
public class OperateLoggerAspect {
    private Logger logger = LoggerFactory.getLogger(OperateLoggerAspect.class);

    @Autowired(required = false)
    private HttpServletRequest httpServletRequest;
    @Autowired
    private MqProduct mqProduct;

    @Pointcut(value = "@annotation(operateLogger)", argNames = "operateLogger")
    public void aroundPointcut(OperateLogger operateLogger){}

    @Around("aroundPointcut(operateLogger)")
    public void doLog(ProceedingJoinPoint pjp, OperateLogger operateLogger) throws Throwable {
        logger.info("===========================拦截日志开启===============================");
        //得到业务方法的参数
        Object[] args = pjp.getArgs();
        String method = pjp.getSignature().getName();
        //得到业务方法所在类(全路径)
        String classPath = pjp.getSignature().getDeclaringType().getName();
        Map<String, String[]> map = httpServletRequest.getParameterMap();
        String description = operateLogger.description();
        String operateType = operateLogger.operateType();
        if (MyApp.INSERT_UPDATE.equals(operateType)) {
            if (map.get(MyApp.ID) == null) {
                operateType = MyApp.INSERT;
            } else {
                operateType = MyApp.UPDATE;
            }
        }
        JSONObject jsonLog = new JSONObject();
        jsonLog.put("createTime",new Date());
        jsonLog.put("class",classPath);
        jsonLog.put("method",method);
        jsonLog.put("operateType",operateType);
        jsonLog.put("description",description);
        jsonLog.put("args",JSONObject.toJSONString(map));
        //aop代理链执行之前拦截组织参数，之后记录日志，如果执行过程出现异常抛出不记录日志
        pjp.proceed();
        this.mqProduct.writeOperateLog(jsonLog.toJSONString());
    }

}

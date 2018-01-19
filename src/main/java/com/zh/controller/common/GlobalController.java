package com.zh.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.zh.exception.AppException;
import com.zh.utils.LoadProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常总拦截,返回error.html
 * @author zhanghang
 * @date 2018/1/18
 */
@Slf4j
@ControllerAdvice
public class GlobalController {

    @Autowired
    private LoadProperty loadProperty;

    public static final String DEFAULT_ERROR_VIEW = "500";

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //此处一般用作全局日期格式化，已通过更优雅的方式处理，详见: DateConverterConfig类
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("common", "每个页面都能看到这句话!");
    }

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest req, Exception e, HandlerMethod method, HttpServletResponse response) throws Exception {
        ResponseBody responseBody = method.getMethod().getAnnotation(ResponseBody.class);
        RestController restController = method.getBeanType().getAnnotation(RestController.class);
        if(responseBody != null || restController != null){
            response.setCharacterEncoding("utf-8");
            response.setStatus(908);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            AppException ex;
            if(e instanceof AppException){
                ex =  (AppException) e;
                String msg = loadProperty.getValue(ex.getErrorCode());
                ex.setErrorMsg(msg == null ? "未设定错误信息" : msg);
                log.error(ex.getErrorMsg());
            }else{
                ex =  (AppException) e;
                ex.setErrorCode("ERR_500");
                ex.setErrorMsg("未知异常错误");
                log.error(ex.getErrorMsg());
            }
            JSONObject errorMsg = new JSONObject();
            errorMsg.put("code",ex.getErrorCode());
            errorMsg.put("msg",ex.getErrorMsg());
            response.getWriter().write(JSONObject.toJSONString(errorMsg));
            return null;
        }else{
            ModelAndView mav = new ModelAndView();
            log.error(e.getMessage());
            if(e instanceof AppException){
                AppException ex =  (AppException) e;
                String msg = loadProperty.getValue(ex.getErrorCode());
                ex.setErrorMsg(msg == null ? "未设定错误信息" : msg);
                log.error(ex.getErrorMsg());
                mav.addObject("exception", ex);
            }else{
                AppException ex =  (AppException) e;
                ex.setErrorCode("ERR_500");
                ex.setErrorMsg("未知异常错误");
                log.error(ex.getErrorMsg());
                mav.addObject("exception",ex);
            }
            mav.addObject("url", req.getRequestURL());
            mav.setViewName(DEFAULT_ERROR_VIEW);
            return mav;
        }
    }
}
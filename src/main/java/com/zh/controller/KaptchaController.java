package com.zh.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * 验证码
 * @author zhanghang
 * @date 2018/1/22
 */
@Controller
@RequestMapping("/kaptcha")
@Slf4j
public class KaptchaController {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpSession session, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            session.setAttribute("kaptcha", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

//    @RequestMapping("/imgvrifyControllerDefaultKaptcha")
//    @ResponseBody
//    public Integer imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam("type") String type, @RequestParam("code") String code){
//        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode"+type);
//        System.out.println("Session  vrifyCode "+captchaId+" form vrifyCode "+code);
//        if (!captchaId.equals(code)) {
//          return -1; //验证错误
//        } else {
//            return 0;//验证成功
//        }
//    }
}

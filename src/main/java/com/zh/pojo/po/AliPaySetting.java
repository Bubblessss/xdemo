package com.zh.pojo.po;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 支付宝实体
 * @author zhanghang
 * @date 2018/1/25
 */
@Configuration
@PropertySource("classpath:/alipay.properties")
@Data
public class AliPaySetting {

    @Value("${URL}")
    private String url;

    @Value("${APPID}")
    private String appId;

    @Value("${APP_PRIVATE_KEY}")
    private String appPrivateKey;

    @Value("${FORMAT}")
    private String format;

    @Value("${SIGN_TYPE}")
    private String signType;

    @Value("${CHARSET}")
    private String charset;

    @Value("${ALIPAY_PUBLIC_KEY}")
    private String alipayPublicKey;

    @Value("${notify_url}")
    private String notify_url;

    @Value("${return_url}")
    private String return_url;
}

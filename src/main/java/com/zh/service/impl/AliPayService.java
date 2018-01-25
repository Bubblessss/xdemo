package com.zh.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.tour.modules.tour.model.TUser;
import com.zh.pojo.po.AliPaySetting;
import com.zh.service.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 支付宝
 * @author zhanghang
 * @date 2018/1/25
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AliPayServiceImpl implements AliPayService {
    @Autowired
    private AliPaySetting aliPaySetting;

    /**
     * 支付方法
     * @return
     */
    @Override
    public String doPay(Integer currentUserId, BigDecimal money, String paySerialNo) {
        //初始化AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(paySettings.getGatewayUrl(), paySettings.getAppid(),paySettings.getPrivate_key(),"json",
                paySettings.getCharset(),paySettings.getPublic_key(), paySettings.getSign_type());
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //创建API对应的request
        alipayRequest.setReturnUrl(paySettings.getReturn_url());
        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl(paySettings.getNotify_url());
        //填充业务参数
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + paySerialNo + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + money + "," +
                "    \"subject\":\"旅行商品\"," +
                "    \"body\":\"旅行商品\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"uid\":\"" + currentUserId + "\"" +
                "    }"+
                "  }");
        String form;
        try {
             form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
             return form;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "error";
        }
    }
}

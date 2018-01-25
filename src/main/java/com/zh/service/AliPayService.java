package com.zh.service;

import java.math.BigDecimal;

/**
 * 支付宝service
 * @author zhanghang
 * @date 2018/1/23
 */
public interface AliPayService {
    /**
     * 支付
     * @param currentUserId 当前登陆用户
     * @param money 金额
     * @param paySerialNo 支付流水号
     * @return
     */
    String doPay(Integer currentUserId,BigDecimal money,String paySerialNo);
}

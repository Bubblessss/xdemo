package com.zh.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 * @author zhanghang
 * @date 2017/12/22
 */
@Slf4j
public class Md5Util {

    private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e','f' };

    /**
     * 对字符串进行MD5加密
     * @param res
     * @return
     */
    public static String md5(String res) {
        try {
            byte[] strTemp = res.getBytes("utf-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[(byte0 >>> 4) & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

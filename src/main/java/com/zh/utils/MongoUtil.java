package com.zh.utils;

import java.util.Calendar;

/**
 * Mongo工具类
 * @author zhanghang
 * @date 2017/12/19
 */
public class MongoUtil {

    public static String getLogCollectionName(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year + "_operateLog_" + month;
    }

}

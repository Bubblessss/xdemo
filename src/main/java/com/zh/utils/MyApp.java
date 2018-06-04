package com.zh.utils;

/**
 * MyApp工具类
 * @author zhanghang
 * @date 2017/12/19
 */
public class MyApp {
    /**
     * 主键id
     */
    public static final String ID = "id";
    /**
     * 排序：id
     */
    public static final String SORT_ID = "id";
    /**
     * 全局操作日志集合长度
     */
    public static final long OPERATE_LOG_LIST_LIMIT_SIZE = 2;
    /**
     * 全局操作日志集合
     */
    public static final String OPERATE_LOG_LIST = "operateLogList";

    public static final String INSERT = "INSERT";

    public static final String DELETE = "DELETE";

    public static final String UPDATE = "UPDATE";

//    public static final String SEARCH = "SEARCH";//默认

    public static final String INSERT_UPDATE = "INSERT_UPDATE";

    public static final String MQ_TOPIC_MESSAGE = "message";

    public static final String MQ_TOPIC_OPERATE_LOG = "operateLog";

    public static final String MQ_QUEUE_ZH = "zh-queue";

    public static final String MQ_QUEUE_DELAY = "delay";

    /**
     * 系统用户在职
     */
    public static final String SYS_USER_STATUS_Y = "Y";
    /**
     * 系统用户离职
     */
    public static final String SYS_USER_STATUS_N = "N";
}

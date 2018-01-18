package com.zh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zh.utils.MongoUtil;
import com.zh.utils.MyApp;
import com.zh.utils.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 日志服务类
 * @author zhanghang
 * @date 2017/12/19
 */
@Service
@Slf4j
public class LogService{

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private JedisPool jedisPool;

    public synchronized void writeOperateLog(String content){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long length = jedis.llen(MyApp.OPERATE_LOG_LIST);
            //缓存中集合长度大于等于限制长度(因为方法枷锁，单台服务器并发下只会等于但集群并发下可能会大于,需要用到分布式锁，此处未处理)
            if (length >= MyApp.OPERATE_LOG_LIST_LIMIT_SIZE){
                String collectionName = MongoUtil.getLogCollectionName();
                List<String> operateLogs = jedis.lrange(MyApp.OPERATE_LOG_LIST,0,-1);
                List<JSONObject> list = operateLogs.stream().map(JSONObject :: parseObject).collect(Collectors.toList());
                //异步把缓存中的日志数据批量写库
                SpringContext.getBean(LogService.class).writeOperateLog2MongoDB(list,collectionName);
                //清空缓存中的日志list
                jedis.ltrim(MyApp.OPERATE_LOG_LIST,-1,0);
                //把新的日志加入缓存中
                jedis.lpush(MyApp.OPERATE_LOG_LIST,content);
            }else {//缓存中list存的日志不满时就继续往尾部追加
                jedis.rpush(MyApp.OPERATE_LOG_LIST,content);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Async
    public void writeOperateLog2MongoDB(List<JSONObject> data,String collectionName){
        this.mongoTemplate.insert(data,collectionName);
    }
}

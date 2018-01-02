package com.zh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * springboot 启动类
 * MapperScan 扫描mybatis的dao层
 * EnableTransactionManagement 开启事物支持
 * EnableAsync 开启异步支持
 * EnableCaching 启用spring cache缓存
 * EnableRedisHttpSession 开启spring session支持,实现session共享
 * @author zhanghang
 * @date 2017/12/19
 */
@EnableAsync
@EnableCaching
@EnableTransactionManagement
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=3600)
@MapperScan("com.zh.dao.mybatis")
@SpringBootApplication
public class XdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(XdemoApplication.class, args);
	}
}

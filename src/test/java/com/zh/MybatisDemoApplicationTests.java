package com.zh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zh.config.rabbitmq.RabbitProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDemoApplicationTests {

	@Autowired
	private RabbitProduct rabbitProduct;

	@Test
	public void contextLoads() {
		rabbitProduct.testTopic("我有一只小毛驴我从来也不骑");
	}

}

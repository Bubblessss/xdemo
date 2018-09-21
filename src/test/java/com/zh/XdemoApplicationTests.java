package com.zh;

import com.alibaba.fastjson.JSONObject;
import com.zh.config.rabbitmq.RabbitProduct;
import com.zh.pojo.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class XdemoApplicationTests {

	@Autowired
	private RabbitProduct rabbitProduct;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void contextLoads() throws InterruptedException {
//		rabbitProduct.testDelay("我有一只小毛驴我从来也不骑");
//		rabbitProduct.testTopic("我有一只小毛驴我从来也不骑");
//		rabbitProduct.testFanout("我有一只小毛驴我从来也不骑");
		//字符串 redisTemplate.opsForValue()
		redisTemplate.opsForValue().set("testString","我有一只小毛驴我从来也不骑");
		String value1 = redisTemplate.opsForValue().get("testString").toString();
		log.info("testString的值为: {}",value1);
		//hash对象 redisTemplate.opsForHash()
		User user1 = new User();
		user1.setId(1);
		user1.setName("爱新觉罗·弘历");
		user1.setSex("M");
		user1.setDeptId(10);
		//putAll放一个map
		redisTemplate.opsForHash().putAll("user-1",JSONObject.parseObject(JSONObject.toJSONString(user1)));
		Map<Object,Object> value2 = redisTemplate.opsForHash().entries("user-1");
		log.info("user-1的值为: {}",value2);
		String value3 = redisTemplate.opsForHash().get("user-1","name").toString();
		log.info("user-1的name值为: {}",value3);
		//put放一个对象里的key-value
		redisTemplate.opsForHash().put("user-2","id",2);
		redisTemplate.opsForHash().put("user-2","name","爱新觉罗·溥仪");
		Integer value4 = (Integer) redisTemplate.opsForHash().get("user-2","id");
		log.info("user-2的id值为: {}",value4);
		String value5 = redisTemplate.opsForHash().get("user-2","name").toString();
		log.info("user-2的name值为: {}",value5);
		//list列表 redisTemplate.opsForList()
		//放单个元素用leftPush，放一个集合用leftPushAll
//		redisTemplate.opsForList().leftPush("users",user1);
		User user2 = new User();
		user2.setId(2);
		user2.setName("爱新觉罗·溥仪");
		user2.setSex("M");
		user2.setDeptId(10);
//		redisTemplate.opsForList().rightPush("users",user2);
		List<Object> users = redisTemplate.opsForList().range("users",0,-1);
		users.forEach(e -> {
			User user = (User) e;
			log.info("users的id为{}的元素的name值为: {}",user.getId(),user.getName());
		});
		//set集合 redisTemplate.opsForSet()
		redisTemplate.opsForSet().add("setString","a","b","c","a");
		Set<Object> setStrings =  redisTemplate.opsForSet().members("setString");
		log.info("setString值为: {}",setStrings);
		//zset有续集和 redisTemplate.opsForZSet()
		redisTemplate.opsForZSet().add("zsetString","a",1);
		redisTemplate.opsForZSet().add("zsetString","b",2);
		redisTemplate.opsForZSet().add("zsetString","c",3);
		Set<Object> zsetStrings =  redisTemplate.opsForZSet().range("zsetString",0,-1);
		log.info("zsetStrings: {}",zsetStrings);
	}

}

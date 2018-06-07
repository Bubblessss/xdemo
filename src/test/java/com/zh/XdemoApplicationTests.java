package com.zh;

import com.zh.config.rabbitmq.RabbitProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class XdemoApplicationTests {

	@Autowired
	private RabbitProduct rabbitProduct;

	@Test
	public void contextLoads() throws InterruptedException {
		rabbitProduct.testDelay("我有一只小毛驴我从来也不骑");
//		rabbitProduct.testTopic("我有一只小毛驴我从来也不骑");
//		rabbitProduct.testFanout("我有一只小毛驴我从来也不骑");
	}

}

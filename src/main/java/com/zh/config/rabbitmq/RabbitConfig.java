package com.zh.config.rabbitmq;

import com.zh.utils.MyApp;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类
 * Queue:构造函数第二个参数默认为true,意为持久化
 * @author zhanghang
 * @date 2018/5/31
 */
@Configuration
public class RabbitConfig {

    @Bean("messageQueue")
    public Queue messageQueue() {
        return new Queue(MyApp.MQ_TOPIC_MESSAGE);
    }

    @Bean("operateLogQueue")
    public Queue operateLogQueue() {
        return new Queue(MyApp.MQ_TOPIC_OPERATE_LOG);
    }
}

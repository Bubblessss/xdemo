package com.zh.config.rabbitmq;


import com.zh.utils.MyApp;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * rabbitMq消费者类
 * @author zhanghang
 * @date 2017/12/19
 */
@Component
public class RabbitProduct{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        this.rabbitTemplate.convertAndSend(MyApp.MQ_DIRECT_MESSAGE,message);
    }

    public void writeOperateLog(String message) {
        this.rabbitTemplate.convertAndSend(MyApp.MQ_DIRECT_OPERATE_LOG,message);
    }

    public void testTopic(String message){
        this.rabbitTemplate.convertAndSend(MyApp.MQ_EXCHANGE_TOPIC_TEST,"test",message);
    }

}

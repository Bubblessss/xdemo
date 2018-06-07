package com.zh.config.rabbitmq;


import com.zh.utils.MyApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


/**
 * rabbitMq消费者类
 * @author zhanghang
 * @date 2017/12/19
 */
@Component
@Slf4j
public class RabbitProduct{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String text) {
        this.rabbitTemplate.convertAndSend(MyApp.MQ_DIRECT_MESSAGE,text);
    }

    public void writeOperateLog(String text) {
        this.rabbitTemplate.convertAndSend(MyApp.MQ_DIRECT_OPERATE_LOG,text);
    }

    public void testTopic(String text){
        this.rabbitTemplate.convertAndSend(MyApp.MQ_EXCHANGE_TOPIC_TEST,"test",text);
    }

    public void testFanout(String text){
        this.rabbitTemplate.convertAndSend(MyApp.MQ_EXCHANGE_FANOUT_TEST,"",text);
    }

    public void testDelay(String text) throws InterruptedException {
        this.rabbitTemplate.convertAndSend(MyApp.MQ_EXCHANGE_DIRECT_DELAY,"delay",text,message -> {
            message.getMessageProperties().setExpiration(10 * 1000 + "");
            return message;
        });
        log.info("发送时间 : {}", LocalDateTime.now());
        //由于使用单元测试所以此处睡15s防止服务器关闭接收不到rabbitmq推过来的消息，如果部署启动服务器测试则不需要睡
        TimeUnit.SECONDS.sleep(15);
    }

}

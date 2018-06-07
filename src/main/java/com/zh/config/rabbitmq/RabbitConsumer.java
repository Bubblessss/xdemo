package com.zh.config.rabbitmq;

//import org.springframework.jms.annotation.JmsListener;


import com.rabbitmq.client.Channel;
import com.zh.service.impl.LogService;
import com.zh.utils.EmailUtil;
import com.zh.utils.MyApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * activeMq消费者类
 * @author zhanghang
 * @date 2017/12/19
 */
@Component
@Slf4j
public class RabbitConsumer {

    @Autowired
    private LogService logService;


    /**
     * 默认情况下,如果没有配置手动ACK, 那么Spring Data AMQP 会在消息消费完毕后自动帮我们去ACK
     * 存在问题：如果报错了,消息不会丢失,但是会无限循环消费,一直报错,如果开启了错误日志很容易就吧磁盘空间耗完
     * 解决方案：手动ACK,或者try-catch 然后在 catch 里面讲错误的消息转移到其它的系列中去
     * spring.rabbitmq.listener.simple.acknowledge-mode = manual
     * @param text 监听的内容
     */
    @RabbitListener(queues=MyApp.MQ_DIRECT_MESSAGE)
    public void receiveSendMessageDirect(String text, Message message, Channel channel){
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("rabbitmq接受的消息为空!!!");
        }
        log.info("[listenerAutoAck 监听的消息] - [{}]", text);
        // TODO 如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            EmailUtil.sendEmail("286910682@qq.com","测试邮件",text);
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // TODO 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                log.error(e1.getMessage(),e1);
            }
        }
    }

    @RabbitListener(queues=MyApp.MQ_DIRECT_OPERATE_LOG)
    public void receiveWriteOperateLogDirect(String text, Message message, Channel channel){
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("activemq接受的消息为空!!!");
        }
        log.info("[listenerAutoAck 监听的消息] - [{}]", text);
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            this.logService.writeOperateLog(text);
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            try {
                // TODO 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                log.error(e1.getMessage(),e1);
            }
        }
    }

    @RabbitListener(queues=MyApp.MQ_TOPIC_TEST)
    public void receiveTestTopic(String text, Message message, Channel channel){
        log.info("Test Topic Receiver  : {}",text);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error("消费失败!");
        }
    }

    @RabbitListener(queues=MyApp.MQ_TOPIC_TEST2)
    public void receiveTestTopic2(String text, Message message, Channel channel){
        log.info("Test2 Topic Receiver  : {}",text);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error("消费失败!");
        }
    }

    @RabbitListener(queues=MyApp.MQ_QUEUE_RECEIVE_DELAY)
    public void receiveTestDealy(String text, Message message, Channel channel){
        log.info("接收时间:{},接受内容:{}", LocalDateTime.now(),text);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error("消费失败!");
        }
    }

}

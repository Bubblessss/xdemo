package com.zh.config.activemq;

import com.zh.utils.MyApp;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * activeMq监听队列配置类
 * @author zhanghang
 * @date 2017/12/19
 */
@Configuration
@EnableJms
public class MqListenersCfg {

    /**
     * 点对点
     * @return
     */
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(MyApp.MQ_QUEUE_ZH);
    }

    /**
     * 发布/订阅[发消息]
     * @return
     */
    @Bean("messageTopic")
    public Topic messageTopic(){
        return new ActiveMQTopic(MyApp.MQ_TOPIC_MESSAGE);
    }

    /**
     * 发布/订阅[记操作日志]
     * @return
     */
    @Bean("operateLogTopic")
    public Topic operateLogTopic(){
        return new ActiveMQTopic(MyApp.MQ_TOPIC_OPERATE_LOG);
    }
}

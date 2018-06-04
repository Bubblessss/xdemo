package com.zh.config.activemq;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * activeMq消费者类
 * @author zhanghang
 * @date 2017/12/19
 */
//@Component
//public class MqProduct{
//    @Resource
//    private Topic messageTopic;
//    @Resource
//    private Topic operateLogTopic;
//    @Resource
//    private Queue delaySendMessageQueue;
//
//    @Autowired(required = false)
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    public void sendMessage(String message) {
//        jmsMessagingTemplate.convertAndSend(this.messageTopic,message);
//    }
//
//    public void writeOperateLog(String message) {
//        jmsMessagingTemplate.convertAndSend(this.operateLogTopic,message);
//    }

//}

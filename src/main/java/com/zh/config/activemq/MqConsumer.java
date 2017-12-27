package com.zh.config.activemq;

import com.zh.service.impl.LogService;
import com.zh.utils.EmailUtil;
import com.zh.utils.MyApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * activeMq消费者类
 * @author zhanghang
 * @date 2017/12/19
 */
@Component
public class MqConsumer {
    private Logger logger = LoggerFactory.getLogger(MqConsumer.class);

    @Autowired
    private LogService logService;

    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     */
    @JmsListener(destination = MyApp.MQ_TOPIC_MESSAGE)
    public void receiveSendMessageTopic(String text){
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("activemq接受的消息为空!!!");
        }
        try {
            EmailUtil.sendEmail("286910682@qq.com","测试邮件",text);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @JmsListener(destination = MyApp.MQ_TOPIC_OPERATE_LOG)
    public void receiveWriteOperateLogTopic(String text){
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("activemq接受的消息为空!!!");
        }
        try {
            this.logService.writeOperateLog(text);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
}

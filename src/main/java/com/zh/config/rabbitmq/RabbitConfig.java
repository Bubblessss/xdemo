package com.zh.config.rabbitmq;

import com.zh.utils.MyApp;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq配置类
 * rabbitmq包括:生产者,交换机,消息队列,消费者四个模块
 * 交换机:生产者将消息发送给交换机,而交换机则根据调度策略把相应的消息转发给对应的消息队列
 * 交换机有四种类型:Direct(默认), topic, headers, Fanout.
 * Direct:默认的交换机模式,也是最简单的模式.即创建消息队列的时候,指定一个BindingKey.当发送者发送消息的时候,指定对应的Key.当Key和消息队列的BindingKey一致的时候,消息将会被发送到该消息队列中.
 * Topic:转发信息主要是依据通配符,队列和交换机的绑定主要是依据一种模式(通配符+字符串),而当发送消息的时候,只有指定的Key和该模式相匹配的时候,消息才会被发送到该消息队列中.
 * Headers:也是根据一个规则进行匹配,在消息队列和交换机绑定的时候会指定一组键值对规则, 而发送消息的时候也会指定一组键值对规则, 当两组键值对规则相匹配的时候, 消息会被发送到匹配的消息队列中.
 * Fanout:是路由广播的形式,将会把消息发给绑定它的全部队列,即便设置了key,也会被忽略(相当于activemq的Topic模式).
 * Queue:构造函数第二个参数默认为true,意为持久化
 * @author zhanghang
 * @date 2018/5/31
 */
@Configuration
public class RabbitConfig {

    //======================Direct==========================

    @Bean
    public Queue messageDirectQueue() {
        return new Queue(MyApp.MQ_DIRECT_MESSAGE);
    }

    @Bean
    public Queue operateLogDirectQueue() {
        return new Queue(MyApp.MQ_DIRECT_OPERATE_LOG);
    }

    //======================Topic=======================

    @Bean
    public Queue testTopicQueue(){
        return new Queue(MyApp.MQ_TOPIC_TEST);
    }

    @Bean
    public Queue testTopicQueue2(){
        return new Queue(MyApp.MQ_TOPIC_TEST2);
    }

    @Bean
    public TopicExchange testTopicExchange(){
        return new TopicExchange(MyApp.MQ_EXCHANGE_TOPIC_TEST);
    }

    @Bean
    public Binding testTopicBinding(Queue testTopicQueue,TopicExchange testTopicExchange){
        return BindingBuilder.bind(testTopicQueue).to(testTopicExchange).with(MyApp.MQ_TOPIC_TEST_ROUTING_KEY);
    }

    @Bean
    public Binding testTopicBinding2(Queue testTopicQueue2,TopicExchange testTopicExchange){
        return BindingBuilder.bind(testTopicQueue2).to(testTopicExchange).with(MyApp.MQ_TOPIC_TEST2_ROUTING_KEY);
    }

    //======================Fanout=======================

    @Bean
    public FanoutExchange testFanoutExchange(){
        return new FanoutExchange(MyApp.MQ_EXCHANGE_FANOUT_TEST);
    }

    @Bean
    public Binding testFanoutBinding(Queue messageDirectQueue,FanoutExchange testFanoutExchange){
        return BindingBuilder.bind(messageDirectQueue).to(testFanoutExchange);
    }

    @Bean
    public Binding testFanoutBinding2(Queue operateLogDirectQueue,FanoutExchange testFanoutExchange){
        return BindingBuilder.bind(operateLogDirectQueue).to(testFanoutExchange);
    }

    @Bean
    public Binding testFanoutBinding3(Queue testTopicQueue,FanoutExchange testFanoutExchange){
        return BindingBuilder.bind(testTopicQueue).to(testFanoutExchange);
    }

    @Bean
    public Binding testFanoutBinding4(Queue testTopicQueue2,FanoutExchange testFanoutExchange){
        return BindingBuilder.bind(testTopicQueue2).to(testFanoutExchange);
    }

    //=======================TTL==========================

    /**
     * 延时队列交换机
     * @return
     */
    @Bean
    public DirectExchange delayDirectExchange(){
        return new DirectExchange(MyApp.MQ_EXCHANGE_DIRECT_DELAY);
    }

    /**
     * 绑定了死信交换机的延时队列
     * @return
     */
    @Bean
    public Queue delayQueue(){
        Map<String,Object> map = new HashMap<>(16);
        map.put("x-dead-letter-exchange",MyApp.MQ_EXCHANGE_DIRECT_RECEIVE_DELAY);
        map.put("x-dead-letter-routing-key", "receive_delay");
        return new Queue(MyApp.MQ_QUEUE_DELAY,true,false,false,map);
    }

    /**
     * 给延时队列绑定交换机
     * @return
     */
    @Bean
    public Binding delayBinding(){
        return BindingBuilder.bind(delayQueue()).to(delayDirectExchange()).with(MyApp.MQ_QUEUE_DELAY_ROUTING_KEY);
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public DirectExchange receiveDelayDirectExchange(){
        return new DirectExchange(MyApp.MQ_EXCHANGE_DIRECT_RECEIVE_DELAY);
    }

    /**
     * 真正接收延时队列死信的消费队列
     * @return
     */
    @Bean
    public Queue receiveDelayQueue(){
        return new Queue(MyApp.MQ_QUEUE_RECEIVE_DELAY);
    }

    /**
     * 死信交换机绑定消费队列
     * @return
     */
    @Bean
    public Binding receiveDelayBinding(){
        return BindingBuilder.bind(receiveDelayQueue()).to(receiveDelayDirectExchange()).with(MyApp.MQ_QUEUE_RECEIVE_DELAY_ROUTING_KEY);
    }




}

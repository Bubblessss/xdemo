# xdemo
# 以springboot为基础的demo
# 功能简介：
* 1.shiro实现简单的登陆界面，输入账户、密码登录跳转欢迎当前用户界面可以登出
* 2.使用redis缓存session，实现分布式session共享
* 3.使用swagger2测试客户controller所有方法
* 4.使用activemq、spring mail消息中间件发送邮件，异步记录操作日志
* 5.使用mybatis，jpa实现客户的增删改查，使用aop自定义注解记录操作日志入mongodb

# 目前整合的技术栈有:
* 缓存:spring data redis、jedis
* 数据源:spring data jpa、mybatis、spring data mongo
* api文档:swagger2
* 消息中间件:activemq
* 日志:spring aop自定义日志注解
* 邮件:spring mail
* 分页插件:spring data jpa提供的Pageable、PageHelper
* 权限框架:shiro

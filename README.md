# xdemo
# 以springboot为基础的demo
# 个人博客：http://blog.csdn.net/eumenides_ (博客地址最后带有下划线_，直接点击访问不了，请粘贴到地址栏)
# 访问地址：http://localhost:8080
# 运行注意点：
* 1.请先运行项目下的sql包下的test.sql数据库文件建库，sys_user两个用户密码都是123456(md5(password+name))，登录请使用account登录
* 2.请在appication.properties里面自行修改邮件发送邮箱地址和专用密码(非邮箱登陆密码，需要自己进入邮箱设置),mq消费者类里修改邮件接收邮箱
* 3.测试全部功能请自行安装redis，rabbitmq，mongodb，nginx
# 功能简介：
* 1.shiro实现简单的登陆界面，输入账户、密码登录跳转欢迎当前用户界面(可以登出和测试swagger-api)
* 2.使用redis缓存session，实现分布式session共享
* 3.使用swagger2测试客户controller所有方法
* 4.使用rabbitmq、spring mail消息中间件发送邮件，异步记录操作日志
* 5.使用mybatis，jpa实现客户的增删改查，使用aop自定义注解记录操作日志入mongodb
* 6.使用@ControllerAdvice处理全局业务异常，自定义拦截器处理404，400错误，返回统一界面

# 目前整合的技术栈有:
* 缓存:spring data redis、jedis
* 数据源:spring data jpa、mybatis、spring data mongo
* api文档:swagger2
* 消息中间件:rabbitmq
* 日志:spring aop自定义日志注解
* 邮件:spring mail
* 分页插件:spring data jpa提供的Pageable、PageHelper
* 权限框架:shiro

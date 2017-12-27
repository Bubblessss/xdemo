/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.10-log : Database - test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `test`;

/*Table structure for table `date_test` */

DROP TABLE IF EXISTS `date_test`;

CREATE TABLE `date_test` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date_str` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `date_test` */

insert  into `date_test`(`id`,`date_str`) values (1,'20171030');

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dept` */

insert  into `dept`(`id`,`name`) values (1,'技术部'),(2,'人事部'),(3,'业务部'),(4,'客服部'),(5,'经理部');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `account` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'username',
  `password` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`name`,`account`,`password`,`status`) values (1,'张三','10000','ea000b6d22e95b5ae874453918efa533','Y'),(2,'李四','10001','2f4ce48a2f919d6df49edef749142e87','Y');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dept_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dept_id` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`sex`,`dept_id`) values (1,'张三','M',1),(2,'李四','F',2),(3,'王五','F',2),(4,'赵六','M',3),(5,'田七','M',5),(6,'李萌','M',4),(7,'王栋','F',3),(8,'张大奎','F',1),(9,'刘毅','F',1),(10,'孟天','F',1),(11,'胡晓','M',2),(12,'黄艾梦','M',2),(26,'李冬','F',2),(28,'芳华','M',2),(31,'李世民','F',1),(32,'李唐','F',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.48 : Database - dp-lte-boot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dp-lte-boot` /*!40100 DEFAULT CHARACTER SET utf8 */;


/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`,`gmt_create`,`gmt_modified`) values (1,0,'系统管理',NULL,'',0,'fa fa-coffee',0,'2017-08-09 22:49:47','2017-10-26 10:03:40'),(2,1,'系统菜单','base/menu/list.html',NULL,1,'fa fa-th-list',1,'2017-08-09 22:55:15','2017-08-17 10:00:12'),(3,0,'组织机构',NULL,NULL,0,'fa fa-desktop',1,'2017-08-09 23:06:55','2017-08-17 09:54:28'),(4,1,'通用字典','base/macro/list.html',NULL,1,'fa fa-book',2,'2017-08-09 23:06:58','2017-08-17 10:00:24'),(6,3,'用户管理','base/user/list.html',NULL,1,'fa fa-user',2,'2017-08-10 14:12:11','2017-08-17 09:57:40'),(7,3,'角色管理','base/role/list.html',NULL,1,'fa fa-paw',1,'2017-08-10 14:13:19','2017-08-17 09:57:32'),(11,6,'刷新',NULL,'sys:user:list',2,NULL,0,'2017-08-14 10:51:05',NULL),(12,6,'新增',NULL,'sys:user:save',2,NULL,0,'2017-08-14 10:51:35',NULL),(13,6,'编辑',NULL,'sys:user:edit',2,NULL,0,'2017-08-14 10:52:06',NULL),(14,6,'删除',NULL,'sys:user:remove',2,NULL,0,'2017-08-14 10:52:24',NULL),(15,7,'刷新',NULL,'sys:role:list',2,NULL,0,'2017-08-14 10:56:37',NULL),(16,7,'新增',NULL,'sys:role:save',2,NULL,0,'2017-08-14 10:57:02',NULL),(17,7,'编辑',NULL,'sys:role:edit',2,NULL,0,'2017-08-14 10:57:31','2017-08-17 14:28:27'),(18,7,'删除',NULL,'sys:role:remove',2,NULL,0,'2017-08-14 10:57:50',NULL),(19,7,'操作权限',NULL,'sys:role:authorizeOpt',2,NULL,0,'2017-08-14 10:58:55','2017-08-17 13:48:54'),(20,2,'刷新',NULL,'sys:menu:list',2,NULL,0,'2017-08-14 10:59:32',NULL),(21,2,'新增',NULL,'sys:menu:save',2,NULL,0,'2017-08-14 10:59:56',NULL),(22,2,'编辑',NULL,'sys:menu:edit',2,NULL,0,'2017-08-14 11:00:26',NULL),(23,2,'删除',NULL,'sys:menu:remove',2,NULL,0,'2017-08-14 11:00:58',NULL),(24,6,'启用',NULL,'sys:user:enable',2,NULL,0,'2017-08-14 17:27:18',NULL),(25,6,'停用',NULL,'sys:user:disable',2,NULL,0,'2017-08-14 17:27:43',NULL),(26,6,'重置密码',NULL,'sys:user:resetPassword',2,NULL,0,'2017-08-14 17:28:34',NULL),(27,1,'系统日志','base/log/list.html',NULL,1,'fa fa-warning',3,'2017-08-14 22:11:53','2017-08-17 09:55:19'),(28,27,'刷新',NULL,'sys:log:list',2,NULL,0,'2017-08-14 22:30:22',NULL),(29,27,'删除',NULL,'sys:log:remove',2,NULL,0,'2017-08-14 22:30:43',NULL),(30,27,'清空',NULL,'sys:log:clear',2,NULL,0,'2017-08-14 22:31:02',NULL),(32,4,'刷新',NULL,'sys:macro:list',2,NULL,0,'2017-08-15 16:55:33',NULL),(33,4,'新增',NULL,'sys:macro:save',2,NULL,0,'2017-08-15 16:55:52',NULL),(34,4,'编辑',NULL,'sys:macro:edit',2,NULL,0,'2017-08-15 16:56:09',NULL),(35,4,'删除',NULL,'sys:macro:remove',2,NULL,0,'2017-08-15 16:56:29',NULL),(36,3,'机构管理','base/org/list.html',NULL,1,'fa fa-sitemap',0,'2017-08-17 09:57:14',NULL),(37,1,'行政区域','base/area/list.html',NULL,1,'fa fa-leaf',0,'2017-08-17 09:59:57',NULL),(38,37,'刷新',NULL,'sys:area:list',2,NULL,0,'2017-08-17 10:01:33','2017-08-17 10:01:52'),(39,37,'新增',NULL,'sys:area:save',2,NULL,0,'2017-08-17 10:02:16',NULL),(40,37,'编辑',NULL,'sys:area:edit',2,NULL,0,'2017-08-17 10:02:33',NULL),(41,37,'删除',NULL,'sys:area:remove',2,NULL,0,'2017-08-17 10:02:50',NULL),(42,36,'刷新',NULL,'sys:org:list',2,NULL,0,'2017-08-17 10:03:36',NULL),(43,36,'新增',NULL,'sys:org:save',2,NULL,0,'2017-08-17 10:03:54',NULL),(44,36,'编辑',NULL,'sys:org:edit',2,NULL,0,'2017-08-17 10:04:11',NULL),(45,36,'删除',NULL,'sys:org:remove',2,NULL,0,'2017-08-17 10:04:30',NULL),(46,7,'数据权限',NULL,'sys:role:authorizeData',2,NULL,0,'2017-08-17 13:48:11',NULL),(47,1,'定时任务','base/quartz/list.html',NULL,1,'fa fa-bell',4,'2017-08-19 23:00:08',NULL),(48,47,'刷新',NULL,'quartz:job:list',2,NULL,0,'2017-08-19 23:00:54',NULL),(49,47,'新增',NULL,'quartz:job:save',2,NULL,0,'2017-08-19 23:01:29',NULL),(50,47,'编辑',NULL,'quartz:job:edit',2,NULL,0,'2017-08-19 23:01:58',NULL),(51,47,'删除',NULL,'quartz:job:remove',2,NULL,0,'2017-08-19 23:02:30',NULL),(52,68,'启用',NULL,'quartz:job:enable',2,NULL,0,'2017-08-19 23:08:59','2017-09-14 16:53:00'),(53,68,'停用',NULL,'quartz:job:disable',2,NULL,0,'2017-08-19 23:09:31','2017-09-14 16:53:14'),(54,68,'立即执行',NULL,'quartz:job:run',2,NULL,0,'2017-08-19 23:10:09','2017-09-14 16:53:30'),(55,47,'日志列表',NULL,'quartz:job:log',1,NULL,0,'2017-08-19 23:10:40','2017-09-14 16:53:43'),(56,55,'刷新',NULL,'quartz:log:list',2,NULL,0,'2017-08-21 13:25:33','2017-09-14 16:53:57'),(57,55,'删除',NULL,'quartz:log:remove',2,NULL,0,'2017-08-21 13:25:52','2017-09-14 16:54:12'),(58,55,'清空',NULL,'quartz:log:clear',2,NULL,0,'2017-08-21 13:26:11','2017-09-14 16:54:24'),(59,1,'敏捷开发','base/generator/list.html',NULL,1,'fa fa-archive',5,'2017-09-05 09:44:44',NULL),(60,59,'刷新',NULL,'sys:gen:list',2,NULL,0,'2017-09-05 09:45:10',NULL),(61,59,'生成代码',NULL,'sys:gen:code',2,NULL,0,'2017-09-05 09:45:29',NULL),(67,1,'系统监控','druid/index.html','',1,'fa fa-bug',6,'2017-09-05 14:47:41','2017-09-05 14:49:45'),(68,47,'更多',NULL,'quartz:job:more',1,NULL,0,'2017-09-14 16:52:45',NULL);

/*Table structure for table `sys_org` */

DROP TABLE IF EXISTS `sys_org`;

CREATE TABLE `sys_org` (
  `org_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级机构ID，一级机构为0',
  `code` varchar(100) DEFAULT NULL COMMENT '机构编码',
  `name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `status` tinyint(4) DEFAULT '1' COMMENT '可用标识  1：可用  0：不可用',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='机构管理';

/*Data for the table `sys_org` */

insert  into `sys_org`(`org_id`,`parent_id`,`code`,`name`,`order_num`,`status`,`gmt_create`,`gmt_modified`) values (1,0,'js','江苏省',0,1,'2017-08-17 12:03:15','2017-08-17 17:06:08'),(2,1,'nj','南京市',0,1,'2017-08-17 12:03:43',NULL),(3,2,'gc','高淳区',0,1,'2017-08-17 12:04:47',NULL),(4,3,'syxx','高淳区实验小学',0,1,'2017-08-17 12:05:31',NULL),(5,4,'syxx01','一年级',0,1,'2017-08-17 12:06:35',NULL),(6,4,'syxx02','二年级',1,1,'2017-08-17 12:06:37',NULL),(7,4,'syxx03','三年级',2,1,'2017-08-17 12:06:41',NULL),(9,5,'syxx0102','二班',1,1,'2017-08-17 12:07:39',NULL),(10,5,'syxx0103','三班',2,1,'2017-08-17 12:07:41',NULL),(11,6,'syxx0201','一班',0,1,'2017-08-17 12:08:56',NULL),(12,6,'syxx0202','二班',1,1,'2017-08-17 12:08:59',NULL),(13,6,'syxx0203','三班',2,1,'2017-08-17 12:09:03',NULL),(14,7,'syxx0301','一班',0,1,'2017-08-17 12:09:05',NULL),(15,7,'syxx0302','二班',1,1,'2017-08-17 12:09:08',NULL),(16,7,'syxx0303','三班',2,1,'2017-08-17 12:09:10',NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `org_id` bigint(255) DEFAULT NULL COMMENT '所属机构',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='系统角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`org_id`,`role_name`,`role_sign`,`remark`,`user_id_create`,`gmt_create`,`gmt_modified`) values (1,1,'超级管理员','admin','【系统内置】',2,'2017-08-12 00:43:52','2017-08-12 19:14:59'),(39,3,'测试用户','test',NULL,1,'2017-08-17 19:41:15','2017-08-17 19:42:39');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1066 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values (1008,1,1),(1009,1,37),(1010,1,38),(1011,1,39),(1012,1,40),(1013,1,41),(1014,1,2),(1015,1,20),(1016,1,21),(1017,1,22),(1018,1,23),(1019,1,4),(1020,1,32),(1021,1,33),(1022,1,34),(1023,1,35),(1024,1,27),(1025,1,28),(1026,1,29),(1027,1,30),(1028,1,47),(1029,1,48),(1030,1,49),(1031,1,50),(1032,1,51),(1033,1,55),(1034,1,56),(1035,1,57),(1036,1,58),(1037,1,68),(1038,1,52),(1039,1,53),(1040,1,54),(1041,1,59),(1042,1,60),(1043,1,61),(1044,1,67),(1045,1,3),(1046,1,36),(1047,1,42),(1048,1,43),(1049,1,44),(1050,1,45),(1051,1,7),(1052,1,15),(1053,1,16),(1054,1,17),(1055,1,18),(1056,1,19),(1057,1,46),(1058,1,6),(1059,1,11),(1060,1,12),(1061,1,13),(1062,1,14),(1063,1,24),(1064,1,25),(1065,1,26);

/*Table structure for table `sys_role_org` */

DROP TABLE IF EXISTS `sys_role_org`;

CREATE TABLE `sys_role_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='角色与机构对应关系';

/*Data for the table `sys_role_org` */

insert  into `sys_role_org`(`id`,`role_id`,`org_id`) values (14,1,1),(15,1,2),(22,39,3);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `org_id` bigint(255) DEFAULT NULL COMMENT '所属机构',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`org_id`,`username`,`password`,`email`,`mobile`,`status`,`remark`,`user_id_create`,`gmt_create`,`gmt_modified`) values (1,1,'admin','33808479d49ca8a3cdc93d4f976d1e3d','admin@example.com','123456',1,NULL,1,'2017-08-15 21:40:39','2017-08-15 21:41:00');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (46,1,1);

/*Table structure for table `sys_user_token` */

DROP TABLE IF EXISTS `sys_user_token`;

CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `token` varchar(100) NOT NULL COMMENT 'token',
  `gmt_expire` datetime DEFAULT NULL COMMENT '过期时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

/*Data for the table `sys_user_token` */

insert  into `sys_user_token`(`user_id`,`token`,`gmt_expire`,`gmt_modified`) values (1,'a3c646202882f1213b63fe74dc118e9d','2017-10-26 22:10:52','2017-10-26 10:10:52'),(2,'5004cd9815f7b6e8ac8d831af7eb9324','2017-09-05 04:04:34','2017-09-04 16:04:34');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

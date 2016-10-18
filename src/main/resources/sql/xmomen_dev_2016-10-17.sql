# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.99.100 (MySQL 5.1.73)
# Database: xmomen_dev
# Generation Time: 2016-10-16 17:19:40 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table xmo_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_group`;

CREATE TABLE `xmo_group` (
  `ID` char(32) NOT NULL DEFAULT '' COMMENT '主键',
  `GROUP_CODE` varchar(30) NOT NULL DEFAULT '' COMMENT '用户组代码',
  `GROUP_NAME` varchar(100) NOT NULL DEFAULT '' COMMENT '用户组名称',
  `DESCRIPTION` varchar(200) DEFAULT '' COMMENT '用户组描述',
  `ACTIVE` char(1) NOT NULL DEFAULT '' COMMENT '激活',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_GROUP_CODE` (`GROUP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

LOCK TABLES `xmo_group` WRITE;
/*!40000 ALTER TABLE `xmo_group` DISABLE KEYS */;

INSERT INTO `xmo_group` (`ID`, `GROUP_CODE`, `GROUP_NAME`, `DESCRIPTION`, `ACTIVE`)
VALUES
	('a78763b2931211e68e96080027b274d7','XMO_USER','普通用户','可登录系统，访问基本信息','1');

/*!40000 ALTER TABLE `xmo_group` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_group_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_group_permission`;

CREATE TABLE `xmo_group_permission` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `GROUP_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '组表ID',
  `PERMISSION_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '权限表ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

LOCK TABLES `xmo_group_permission` WRITE;
/*!40000 ALTER TABLE `xmo_group_permission` DISABLE KEYS */;

INSERT INTO `xmo_group_permission` (`ID`, `GROUP_ID`, `PERMISSION_ID`)
VALUES
	('134','3','1'),
	('135','3','1'),
	('136','3','1'),
	('137','3','1'),
	('138','3','1'),
	('139','3','1'),
	('140','3','2'),
	('141','3','5'),
	('142','3','5'),
	('143','3','4'),
	('144','3','3'),
	('157','3','6'),
	('158','3','7'),
	('159','3','16'),
	('160','3','17'),
	('161','3','26'),
	('162','3','34');

/*!40000 ALTER TABLE `xmo_group_permission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_schedule_job
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_schedule_job`;

CREATE TABLE `xmo_schedule_job` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `JOB_NAME` varchar(50) NOT NULL COMMENT '任务名称',
  `JOB_GROUP` varchar(50) NOT NULL COMMENT '任务属组',
  `JOB_STATUS` varchar(10) NOT NULL DEFAULT '' COMMENT '任务状态',
  `JOB_DESCRIPTION` varchar(200) NOT NULL COMMENT '任务描述',
  `CRON_EXPRESSION` varchar(50) NOT NULL COMMENT 'CRON表达式',
  `TRIGGER_ID` varchar(100) NOT NULL COMMENT '触发器ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_dictionary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_dictionary`;

CREATE TABLE `xmo_dictionary` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `DICTIONARY_TYPE` varchar(50) DEFAULT NULL COMMENT '字典类型',
  `SHOW_VALUE` varchar(50) DEFAULT NULL COMMENT '显示值',
  `CODE_VALUE` varchar(50) DEFAULT NULL COMMENT '实际值',
  `SORT_VALUE` int(3) DEFAULT NULL COMMENT '排序',
  `ACTIVE` bit(1) DEFAULT NULL COMMENT '激活',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父节点',
  `SHOW` bit(1) DEFAULT NULL COMMENT '显示',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典参数表';



# Dump of table xmo_dictionary_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_dictionary_group`;

CREATE TABLE `xmo_dictionary_group` (
  `ID` varchar(32) NOT NULL DEFAULT '',
  `DICTIONARY_TYPE` varchar(50) DEFAULT NULL COMMENT '字典编号',
  `DICTIONARY_DESC` varchar(255) DEFAULT NULL COMMENT '字典描述',
  `ACTIVE` bit(1) DEFAULT NULL COMMENT '激活',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

LOCK TABLES `xmo_dictionary_group` WRITE;
/*!40000 ALTER TABLE `xmo_dictionary_group` DISABLE KEYS */;

INSERT INTO `xmo_dictionary_group` (`ID`, `DICTIONARY_TYPE`, `DICTIONARY_DESC`, `ACTIVE`)
VALUES
	('8bbdb84a92a211e68e96080027b274d7','SEX','性别',10000000);

/*!40000 ALTER TABLE `xmo_dictionary_group` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_log_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_log_info`;

CREATE TABLE `xmo_log_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `ACTION_NAME` varchar(50) NOT NULL COMMENT '操作名称',
  `ACTION_DATE` datetime NOT NULL COMMENT '操作时间',
  `ACTION_PARAMS` varchar(500) DEFAULT NULL COMMENT '参数',
  `CLIENT_IP` varchar(100) NOT NULL COMMENT '客户端IP',
  `ACTION_RESULT` varchar(500) DEFAULT NULL COMMENT '操作结果',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `xmo_log_info` WRITE;
/*!40000 ALTER TABLE `xmo_log_info` DISABLE KEYS */;

INSERT INTO `xmo_log_info` (`ID`, `USER_ID`, `ACTION_NAME`, `ACTION_DATE`, `ACTION_PARAMS`, `CLIENT_IP`, `ACTION_RESULT`)
VALUES
	(1,1,'getPermissionList','2016-06-15 16:12:00','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=44, pages=2}'),
	(2,1,'getPermissionList','2016-06-15 16:14:09','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=43, pages=2}'),
	(3,1,'deletePermission','2016-06-15 16:14:20','[15]','127.0.0.1',NULL),
	(4,1,'getPermissionList','2016-06-15 16:14:20','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=42, pages=2}'),
	(5,1,'deletePermission','2016-06-15 16:14:27','[18]','127.0.0.1',NULL),
	(6,1,'getPermissionList','2016-06-15 16:14:27','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=41, pages=2}'),
	(7,1,'deletePermission','2016-06-15 16:14:33','[19]','127.0.0.1',NULL),
	(8,1,'getPermissionList','2016-06-15 16:14:33','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=40, pages=2}'),
	(9,1,'getPermissionList','2016-06-15 16:20:15','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(10,1,'getPermissionList','2016-06-15 16:21:02','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(11,1,'getUserList','2016-06-15 16:21:04','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=3, pages=1}'),
	(12,1,'deleteUser','2016-06-15 16:21:28','[1]','127.0.0.1',NULL),
	(13,1,'getUserList','2016-06-15 16:21:28','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(14,1,'getUserList','2016-06-15 16:24:08','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(15,1,'getGroupList','2016-06-15 16:35:15','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(16,1,'getGroupList','2016-06-15 16:38:31','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(17,1,'findUsersByGroup','2016-06-15 16:38:36','[3, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(18,1,'findUsersByGroup','2016-06-15 16:38:36','[3, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=1, pages=1}'),
	(19,1,'findUsersByGroup','2016-06-15 16:43:46','[3, true, 1]','127.0.0.1',NULL),
	(20,1,'查询用户组列表','2016-06-15 16:46:53','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(21,1,'查询用户组下属用户','2016-06-15 16:46:55','[3, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=5, pages=1}'),
	(22,1,'查询用户组下属用户','2016-06-15 16:46:55','[3, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(23,1,'查询用户组下属用户','2016-06-15 16:46:58','[3, false, 1]','127.0.0.1',NULL),
	(24,1,'查询用户组下属用户','2016-06-15 16:46:58','[3, false, 1]','127.0.0.1',NULL),
	(25,1,'查询用户组下属用户','2016-06-15 16:46:58','[3, false, 1]','127.0.0.1',NULL),
	(26,1,'查询用户组下属用户','2016-06-15 16:46:58','[3, false, 1]','127.0.0.1',NULL),
	(27,1,'查询用户组下属用户','2016-06-15 16:46:58','[3, false, 1]','127.0.0.1',NULL),
	(28,1,'查询用户组下属用户','2016-06-15 16:47:03','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(29,1,'查询用户组下属用户','2016-06-15 16:47:03','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=1, pages=1}'),
	(30,1,'查询用户组下属用户','2016-06-15 16:47:04','[2, true, 1]','127.0.0.1',NULL),
	(31,1,'查询用户组下属用户','2016-06-15 16:47:07','[3, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(32,1,'查询用户组下属用户','2016-06-15 16:47:07','[3, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=1, pages=1}'),
	(33,1,'查询用户组下属用户','2016-06-15 16:47:08','[3, true, 1]','127.0.0.1',NULL),
	(34,1,'查询用户组下属用户','2016-06-15 16:55:15','[3, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=1, pages=1}'),
	(35,1,'查询用户组下属用户','2016-06-15 16:55:15','[3, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(36,1,'查看权限列表','2016-06-15 16:55:40','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(37,1,'查询用户组列表','2016-06-15 16:55:42','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(38,1,'查看权限列表','2016-06-15 16:56:59','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(39,1,'查询用户组列表','2016-06-15 16:57:15','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(40,1,'查看权限列表','2016-06-15 16:58:35','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(41,1,'查询用户','2016-06-15 17:05:21','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(42,1,'查询用户组列表','2016-06-15 17:05:31','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(43,1,'查询用户','2016-06-15 17:06:16','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(44,1,'查询用户','2016-06-15 17:06:17','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(45,1,'查询用户','2016-06-15 17:06:17','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(46,1,'查询用户','2016-06-15 17:06:17','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(47,1,'查询用户','2016-06-15 17:06:17','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(48,1,'查询用户','2016-06-15 17:06:17','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(49,1,'查询用户','2016-06-15 17:06:18','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(50,1,'查询用户','2016-06-15 17:06:19','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(51,1,'查询用户','2016-06-15 17:06:19','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(52,1,'查询用户','2016-06-15 17:06:19','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(53,1,'查询用户','2016-06-15 17:06:19','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(54,1,'查询用户','2016-06-15 17:06:56','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(55,1,'查询用户组列表','2016-06-15 17:10:47','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(56,1,'修改用户信息','2016-06-15 17:11:05','[1, true]','127.0.0.1',NULL),
	(57,1,'修改用户信息','2016-06-15 17:11:06','[1, false]','127.0.0.1',NULL),
	(58,1,'查询用户组列表','2016-06-15 17:15:06','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(59,1,'查询用户组列表','2016-06-15 17:15:21','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(60,1,'getUserList','2016-06-15 17:27:15','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(61,1,'getUserList','2016-06-15 17:28:39','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(62,1,'getGroupList','2016-06-15 17:28:44','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(63,1,'getUserList','2016-06-15 17:29:12','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(64,1,'getGroupList','2016-06-15 17:29:13','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(65,1,'getUserList','2016-06-15 17:30:32','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(66,1,'getGroupList','2016-06-15 17:30:35','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(67,1,'getUserList','2016-06-15 17:31:20','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(68,1,'getGroupList','2016-06-15 17:31:21','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(69,1,'getUserList','2016-06-15 17:32:42','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(70,1,'getGroupList','2016-06-15 17:32:45','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(71,1,'getUserList','2016-06-15 17:40:46','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(72,1,'getGroupList','2016-06-15 17:40:56','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(73,1,'getGroupList','2016-06-15 17:41:05','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(74,1,'getUserList','2016-06-15 17:41:57','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(75,1,'getGroupList','2016-06-15 17:42:00','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(76,1,'getUserList','2016-06-15 17:46:02','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(77,1,'getGroupList','2016-06-15 17:46:08','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(78,1,'getUserList','2016-06-15 17:47:29','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(79,1,'getGroupList','2016-06-15 17:47:30','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(80,1,'getGroupList','2016-06-15 17:48:37','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(81,1,'getGroupList','2016-06-15 17:52:59','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(82,1,'getGroupList','2016-06-15 17:53:09','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(83,1,'getUserList','2016-06-15 17:53:26','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(84,1,'getGroupList','2016-06-15 17:53:28','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(85,1,'getUserList','2016-06-15 17:53:52','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(86,1,'getUserList','2016-06-15 17:56:35','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(87,1,'getGroupList','2016-06-15 17:56:36','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(88,1,'getUserList','2016-06-15 17:59:05','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(89,1,'getGroupList','2016-06-15 17:59:07','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(90,1,'getUserList','2016-06-15 18:00:42','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(91,1,'getGroupList','2016-06-15 18:00:45','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(92,1,'getGroupList','2016-06-16 09:43:38','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(93,1,'getUserList','2016-06-16 09:43:44','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(94,1,'getPermissionList','2016-06-16 09:43:58','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(95,1,'getGroupList','2016-06-16 09:44:02','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(96,1,'findUsersByGroup','2016-06-16 09:44:03','[3, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=1, pages=1}'),
	(97,1,'findUsersByGroup','2016-06-16 09:44:03','[3, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(98,1,'findUsersByGroup','2016-06-16 09:44:08','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=1, pages=1}'),
	(99,1,'findUsersByGroup','2016-06-16 09:44:08','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(100,1,'findPermissionByGroup','2016-06-16 09:44:11','[3, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(101,1,'findPermissionByGroup','2016-06-16 09:44:11','[3, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=11, pages=1}'),
	(102,1,'getPermissionList','2016-06-16 09:49:39','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(103,1,'getGroupList','2016-06-16 09:49:41','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(104,1,'getPermissionList','2016-06-16 09:49:43','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(105,1,'getGroupList','2016-06-16 09:49:43','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(106,1,'getPermissionList','2016-06-16 09:49:47','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(107,1,'getGroupList','2016-06-16 09:49:51','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(108,1,'findPermissionByGroup','2016-06-16 09:49:54','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(109,1,'findPermissionByGroup','2016-06-16 09:49:54','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=11, pages=1}'),
	(110,1,'修改用户组所属权限','2016-06-16 09:51:39','[2, true, 4]','127.0.0.1',NULL),
	(111,1,'修改用户组所属权限','2016-06-16 09:51:47','[2, true, 5]','127.0.0.1',NULL),
	(112,1,'修改用户组所属权限','2016-06-16 09:52:02','[2, true, 7]','127.0.0.1',NULL),
	(113,1,'修改用户组所属权限','2016-06-16 09:52:09','[2, true, 1]','127.0.0.1',NULL),
	(114,1,'修改用户组所属权限','2016-06-16 09:52:22','[2, true, 2]','127.0.0.1',NULL),
	(115,1,'修改用户组所属权限','2016-06-16 09:52:22','[2, true, 3]','127.0.0.1',NULL),
	(116,1,'修改用户组所属权限','2016-06-16 09:52:22','[2, true, 6]','127.0.0.1',NULL),
	(117,1,'修改用户组所属权限','2016-06-16 09:52:22','[2, true, 16]','127.0.0.1',NULL),
	(118,1,'修改用户组所属权限','2016-06-16 09:52:22','[2, true, 17]','127.0.0.1',NULL),
	(119,1,'修改用户组所属权限','2016-06-16 09:52:22','[2, true, 26]','127.0.0.1',NULL),
	(120,1,'修改用户组所属权限','2016-06-16 09:52:22','[2, true, 34]','127.0.0.1',NULL),
	(121,1,'查看权限列表','2016-06-16 09:53:30','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(122,1,'查询用户组列表','2016-06-16 09:54:02','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(123,1,'查询用户组所属权限','2016-06-16 09:54:05','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(124,1,'查询用户组所属权限','2016-06-16 09:54:05','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=12, pages=1}'),
	(125,1,'查看权限列表','2016-06-16 09:54:11','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(126,1,'查看权限列表','2016-06-16 09:54:14','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(127,1,'查看权限列表','2016-06-16 09:54:19','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(128,1,'查询用户列表','2016-06-16 09:59:05','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(129,1,'查询用户组列表','2016-06-16 09:59:08','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(130,1,'查询用户组列表','2016-06-16 10:02:07','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(131,1,'查看权限列表','2016-06-16 10:03:06','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(132,1,'查询用户组列表','2016-06-16 10:03:09','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(133,1,'查询用户组下属用户','2016-06-16 10:03:11','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=1, pages=1}'),
	(134,1,'查询用户组下属用户','2016-06-16 10:03:11','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(135,1,'查询用户组所属权限','2016-06-16 10:03:14','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=12, pages=1}'),
	(136,1,'查询用户组所属权限','2016-06-16 10:03:14','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(137,1,'查询用户组所属权限','2016-06-16 10:03:21','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=12, pages=1}'),
	(138,1,'查询用户组所属权限','2016-06-16 10:03:21','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(139,1,'修改用户组所属权限','2016-06-16 10:03:23','[2, false, 7]','127.0.0.1',NULL),
	(140,1,'修改用户组所属权限','2016-06-16 10:03:23','[2, false, 7]','127.0.0.1',NULL),
	(141,1,'修改用户组所属权限','2016-06-16 10:03:23','[2, false, 5]','127.0.0.1',NULL),
	(142,1,'修改用户组所属权限','2016-06-16 10:03:26','[2, false, 7]','127.0.0.1',NULL),
	(143,1,'修改用户组所属权限','2016-06-16 10:03:26','[2, false, 7]','127.0.0.1',NULL),
	(144,1,'修改用户组所属权限','2016-06-16 10:03:26','[2, false, 7]','127.0.0.1',NULL),
	(145,1,'修改用户组所属权限','2016-06-16 10:03:26','[2, false, 7]','127.0.0.1',NULL),
	(146,1,'修改用户组所属权限','2016-06-16 10:03:26','[2, false, 7]','127.0.0.1',NULL),
	(147,1,'修改用户组所属权限','2016-06-16 10:03:26','[2, false, 7]','127.0.0.1',NULL),
	(148,1,'修改用户组所属权限','2016-06-16 10:03:26','[2, false, 7]','127.0.0.1',NULL),
	(149,1,'修改用户组所属权限','2016-06-16 10:03:36','[2, false, 7]','127.0.0.1',NULL),
	(150,1,'修改用户组所属权限','2016-06-16 10:03:36','[2, false, 7]','127.0.0.1',NULL),
	(151,1,'修改用户组所属权限','2016-06-16 10:03:36','[2, false, 7]','127.0.0.1',NULL),
	(152,1,'修改用户组所属权限','2016-06-16 10:03:40','[2, false, 17]','127.0.0.1',NULL),
	(153,1,'修改用户组所属权限','2016-06-16 10:03:40','[2, false, 17]','127.0.0.1',NULL),
	(154,1,'修改用户组所属权限','2016-06-16 10:03:40','[2, false, 26]','127.0.0.1',NULL),
	(155,1,'查询用户组所属权限','2016-06-16 10:03:40','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=8, pages=1}'),
	(156,1,'查询用户组所属权限','2016-06-16 10:03:40','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=4, pages=1}'),
	(157,1,'查询用户组所属权限','2016-06-16 10:03:40','[3, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=11, pages=1}'),
	(158,1,'查询用户组所属权限','2016-06-16 10:03:40','[3, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=6, pages=1}'),
	(159,1,'查询用户组所属权限','2016-06-16 10:03:50','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=7, pages=1}'),
	(160,1,'查询用户组所属权限','2016-06-16 10:03:50','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=4, pages=1}'),
	(161,1,'查询用户组所属权限','2016-06-16 10:03:54','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=7, pages=1}'),
	(162,1,'查询用户组所属权限','2016-06-16 10:03:54','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=4, pages=1}'),
	(163,1,'修改用户组所属权限','2016-06-16 10:03:55','[2, false, 4]','127.0.0.1',NULL),
	(164,1,'修改用户组所属权限','2016-06-16 10:03:56','[2, false, 1]','127.0.0.1',NULL),
	(165,1,'修改用户组所属权限','2016-06-16 10:03:57','[2, false, 2]','127.0.0.1',NULL),
	(166,1,'修改用户组所属权限','2016-06-16 10:03:58','[2, false, 3]','127.0.0.1',NULL),
	(167,1,'修改用户组所属权限','2016-06-16 10:03:58','[2, false, 6]','127.0.0.1',NULL),
	(168,1,'修改用户组所属权限','2016-06-16 10:03:59','[2, false, 16]','127.0.0.1',NULL),
	(169,1,'修改用户组所属权限','2016-06-16 10:04:00','[2, false, 34]','127.0.0.1',NULL),
	(170,1,'查询用户列表','2016-06-16 10:04:07','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(171,1,'查询用户组列表','2016-06-16 10:04:12','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(172,1,'查询用户列表','2016-06-16 10:04:14','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(173,1,'查询用户组列表','2016-06-16 10:04:17','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(174,1,'查询用户组所属权限','2016-06-16 10:04:19','[3, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=11, pages=1}'),
	(175,1,'查询用户组所属权限','2016-06-16 10:04:19','[3, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=6, pages=1}'),
	(176,1,'修改用户组所属权限','2016-06-16 10:04:21','[3, true, 6]','127.0.0.1',NULL),
	(177,1,'修改用户组所属权限','2016-06-16 10:04:22','[3, true, 7]','127.0.0.1',NULL),
	(178,1,'修改用户组所属权限','2016-06-16 10:04:22','[3, true, 16]','127.0.0.1',NULL),
	(179,1,'修改用户组所属权限','2016-06-16 10:04:23','[3, true, 17]','127.0.0.1',NULL),
	(180,1,'修改用户组所属权限','2016-06-16 10:04:23','[3, true, 26]','127.0.0.1',NULL),
	(181,1,'修改用户组所属权限','2016-06-16 10:04:24','[3, true, 34]','127.0.0.1',NULL),
	(182,1,'查询用户列表','2016-06-16 10:04:26','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(183,1,'查询用户组列表','2016-06-16 10:04:28','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(184,1,'查询用户列表','2016-06-16 10:11:03','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(185,1,'查询用户组列表','2016-06-16 10:11:04','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(186,1,'查询用户列表','2016-06-16 10:15:16','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(187,1,'查询用户组列表','2016-06-16 10:15:26','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(188,1,'查询用户列表','2016-06-16 10:16:17','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(189,1,'查询用户组列表','2016-06-16 10:16:18','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(190,1,'查询用户组列表','2016-06-16 10:16:40','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(191,1,'查询用户列表','2016-06-16 10:16:43','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(192,1,'查询用户组列表','2016-06-16 10:16:44','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(193,1,'查询用户列表','2016-06-16 10:17:04','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(194,1,'查询用户列表','2016-06-16 10:17:25','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(195,1,'查询用户列表','2016-06-16 10:17:37','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(196,1,'查询用户组列表','2016-06-16 10:17:59','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(197,1,'查询用户列表','2016-06-16 10:18:33','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=1, pages=1}'),
	(198,1,'查询用户组列表','2016-06-16 10:18:34','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(199,1,'新增用户','2016-06-16 10:19:53','[com.xmomen.module.user.model.CreateUser@4c746815, org.springframework.validation.BeanPropertyBindingResult: 0 errors]','127.0.0.1','com.xmomen.module.user.entity.SysUsers@7810d5d9'),
	(200,1,'查询用户列表','2016-06-16 10:19:53','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(201,1,'查询用户组列表','2016-06-16 10:21:05','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(202,1,'查询用户组列表','2016-06-16 10:21:07','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(203,1,'查询用户组列表','2016-06-16 10:21:26','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(204,1,'查询用户组列表','2016-06-16 10:21:40','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(205,1,'查询用户组列表','2016-06-16 10:21:43','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(206,1,'查询用户组列表','2016-06-16 10:26:55','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(207,1,'getUserList','2016-06-16 13:46:24','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(208,1,'getGroupList','2016-06-16 13:46:28','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(209,1,'getUserList','2016-06-16 13:47:47','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(210,1,'getUserList','2016-06-16 13:47:48','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(211,1,'getGroupList','2016-06-16 13:47:51','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(212,1,'查询用户','2016-06-16 13:50:49','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(213,1,'查询用户组列表','2016-06-16 13:50:53','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(214,1,'更新用户','2016-06-16 13:50:57','[2, com.xmomen.module.user.model.UpdateUser@13a0db28, org.springframework.validation.BeanPropertyBindingResult: 0 errors]','127.0.0.1',NULL),
	(215,1,'查询用户','2016-06-16 13:50:57','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(216,1,'查询用户','2016-06-16 13:51:01','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(217,1,'查询用户','2016-06-16 13:54:29','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(218,1,'查询用户组列表','2016-06-16 13:54:32','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(219,1,'查询用户组列表','2016-06-16 13:54:36','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(220,1,'更新用户','2016-06-16 13:55:16','[2, com.xmomen.module.user.model.UpdateUser@4966e9ca, org.springframework.validation.BeanPropertyBindingResult: 0 errors]','127.0.0.1',NULL),
	(221,1,'查询用户','2016-06-16 13:55:16','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(222,1,'查询用户列表','2016-06-16 14:01:51','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(223,1,'查询用户组列表','2016-06-16 14:02:03','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(224,1,'查询用户列表','2016-06-16 14:02:07','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(225,1,'查询用户列表','2016-06-16 14:02:20','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(226,1,'查询用户组列表','2016-06-16 14:02:22','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(227,1,'查询用户列表','2016-06-16 14:02:26','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(228,1,'查询用户组列表','2016-06-16 14:02:48','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(229,1,'查询用户列表','2016-06-16 14:03:24','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(230,1,'查询用户列表','2016-06-16 14:05:37','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(231,1,'查询用户组列表','2016-06-16 14:05:40','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(232,1,'查询用户列表','2016-06-16 14:06:02','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(233,1,'查询用户组列表','2016-06-16 14:07:06','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(234,1,'查询用户列表','2016-06-16 14:07:37','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(235,1,'查询用户组列表','2016-06-16 14:08:24','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(236,1,'查询用户列表','2016-06-16 14:08:38','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(237,1,'查询用户组列表','2016-06-16 14:08:55','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(238,1,'查询用户','2016-06-16 14:14:02','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(239,1,'查询用户组列表','2016-06-16 14:14:06','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(240,1,'查询用户','2016-06-16 14:15:48','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(241,1,'查询用户','2016-06-16 14:17:00','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(242,1,'查询用户','2016-06-16 14:17:21','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(243,1,'查询用户组列表','2016-06-16 14:17:23','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(244,1,'更新用户','2016-06-16 14:17:27','[2, UpdateUser(id=2, username=admin, email=admin@xmomen.com, realname=管理员, phoneNumber=13300009999, age=null, qq=null, officeTel=null, locked=false, sex=0, userGroupIds=[2]), org.springframework.validation.BeanPropertyBindingResult: 0 errors]','127.0.0.1',NULL),
	(245,1,'查询用户','2016-06-16 14:17:27','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(246,1,'查询用户组列表','2016-06-16 14:17:31','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(247,1,'更新用户','2016-06-16 14:17:35','[2, UpdateUser(id=2, username=admin, email=admin@xmomen.com, realname=管理员, phoneNumber=13300009999, age=null, qq=null, officeTel=null, locked=false, sex=0, userGroupIds=[2, 3]), org.springframework.validation.BeanPropertyBindingResult: 0 errors]','127.0.0.1',NULL),
	(248,1,'查询用户','2016-06-16 14:17:35','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(249,1,'查询用户组列表','2016-06-16 14:17:39','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(250,1,'更新用户','2016-06-16 14:17:43','[1, UpdateUser(id=1, username=tanxinzheng, email=null, realname=新政, phoneNumber=121212, age=null, qq=null, officeTel=null, locked=false, sex=null, userGroupIds=[2]), org.springframework.validation.BeanPropertyBindingResult: 0 errors]','127.0.0.1',NULL),
	(251,1,'查询用户','2016-06-16 14:17:43','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(252,2,'查询用户','2016-06-16 14:17:58','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(253,1,'查询用户','2016-06-16 14:18:51','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(254,1,'查询用户组列表','2016-06-16 14:18:52','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(255,1,'查看权限列表','2016-06-16 14:18:53','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(256,1,'查询用户','2016-06-16 14:18:54','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(257,1,'查询用户','2016-06-16 14:18:56','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(258,1,'查询用户组列表','2016-06-16 14:18:56','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(259,1,'查看权限列表','2016-06-16 14:18:57','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(260,1,'查询用户组列表','2016-06-16 14:18:59','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(261,1,'查询用户','2016-06-16 14:19:01','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(262,1,'查询用户组列表','2016-06-16 14:24:44','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(263,1,'查询用户组列表','2016-06-16 14:24:48','[1000, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=1000, startRow=0, endRow=1000, total=2, pages=1}'),
	(264,1,'查询用户组列表','2016-06-16 14:24:55','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(265,1,'查询用户','2016-06-16 14:24:56','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(266,1,'查询用户组列表','2016-06-16 14:25:02','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(267,1,'查询用户组下属用户','2016-06-16 14:25:04','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=2, pages=1}'),
	(268,1,'查询用户组下属用户','2016-06-16 14:25:04','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(269,1,'查询用户组所属权限','2016-06-16 14:25:07','[2, true, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=0, pages=0}'),
	(270,1,'查询用户组所属权限','2016-06-16 14:25:07','[2, false, 50, 1]','127.0.0.1','Page{pageNum=1, pageSize=50, startRow=0, endRow=50, total=11, pages=1}'),
	(271,1,'查询用户','2016-06-16 14:25:13','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(272,1,'查询用户组列表','2016-06-16 14:25:14','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(273,1,'查询用户','2016-06-16 14:25:16','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(274,1,'查看权限列表','2016-06-16 14:25:17','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(275,1,'查询用户','2016-06-16 14:25:21','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(276,1,'查询用户','2016-06-16 14:28:16','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(277,1,'查询用户','2016-06-16 14:31:07','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(278,1,'查询用户','2016-06-16 14:32:55','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(279,1,'查询用户','2016-06-16 14:33:45','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(280,1,'查询用户列表','2016-06-16 14:35:54','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(281,1,'查询用户','2016-06-16 14:36:42','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(282,1,'查询用户','2016-06-16 14:39:24','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(283,1,'查看权限列表','2016-06-16 14:39:27','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(284,1,'查询用户组列表','2016-06-16 14:39:28','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(285,1,'查看权限列表','2016-06-16 14:39:30','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(286,2,'查询用户','2016-06-16 15:57:30','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(287,2,'查看权限列表','2016-06-16 16:04:24','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(288,2,'查询用户组列表','2016-06-16 16:08:03','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(289,2,'查询用户','2016-06-16 16:08:05','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(290,2,'查询数据字典组','2016-06-16 16:08:12','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=0, pages=0}'),
	(291,2,'查询用户','2016-06-16 16:08:15','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(292,2,'查询用户组列表','2016-06-16 16:08:17','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(293,2,'查询用户','2016-06-16 16:08:42','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(294,2,'查询用户组列表','2016-06-16 16:08:43','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(295,2,'查看权限列表','2016-06-16 16:08:44','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(296,2,'查询用户','2016-06-16 16:08:45','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(297,2,'查询数据字典组','2016-06-16 16:08:45','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=0, pages=0}'),
	(298,2,'查询用户组列表','2016-06-16 16:08:53','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(299,2,'查询用户','2016-06-16 16:08:54','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(300,2,'查询数据字典组','2016-06-16 16:08:55','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=0, pages=0}'),
	(301,2,'查询用户','2016-06-16 16:09:15','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(302,2,'查询用户组列表','2016-06-16 16:09:15','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(303,2,'查询数据字典组','2016-06-16 16:09:16','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=0, pages=0}'),
	(304,2,'查询用户','2016-06-16 16:09:17','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(305,2,'查询用户组列表','2016-06-16 16:09:18','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(306,2,'查看权限列表','2016-06-16 16:09:19','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(307,2,'查看权限列表','2016-06-16 16:09:25','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(308,2,'查看权限列表','2016-06-16 16:09:27','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(309,2,'查询用户','2016-06-16 16:09:35','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(310,2,'查询用户组列表','2016-06-16 16:09:37','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(311,2,'查询用户','2016-06-16 16:09:39','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(312,2,'查询数据字典组','2016-06-16 16:09:42','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=0, pages=0}'),
	(313,2,'查询用户','2016-06-16 16:09:43','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(314,2,'查询用户组列表','2016-06-16 16:09:44','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(315,2,'查看权限列表','2016-06-16 16:09:45','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(316,2,'查询用户','2016-06-16 17:42:17','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(317,2,'查询用户','2016-06-16 17:43:21','[10, 1, null, null, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(318,2,'查询用户组列表','2016-06-16 17:43:22','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=2, pages=1}'),
	(319,2,'查看权限列表','2016-06-16 17:43:22','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(320,2,'查看权限列表','2016-06-16 17:47:27','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(321,2,'查看权限列表','2016-06-16 17:50:02','[30, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=30, startRow=0, endRow=30, total=11, pages=1}'),
	(322,NULL,'查看权限列表','2016-08-20 23:39:44','[10, 1, null]','127.0.0.1','Page{pageNum=1, pageSize=10, startRow=0, endRow=10, total=11, pages=2}');

/*!40000 ALTER TABLE `xmo_log_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_organization
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_organization`;

CREATE TABLE `xmo_organization` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `NAME` varchar(30) NOT NULL DEFAULT '' COMMENT '组织名称',
  `CODE` varchar(20) NOT NULL DEFAULT '' COMMENT '组织代码',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '组织描述',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '上级组织',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';



# Dump of table xmo_organization_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_organization_user`;

CREATE TABLE `xmo_organization_user` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `ORGANIZATION_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '部门表ID',
  `USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '用户表ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门用户关联表';



# Dump of table xmo_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_permission`;

CREATE TABLE `xmo_permission` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `PERMISSION_CODE` varchar(50) NOT NULL DEFAULT '' COMMENT '权限代码',
  `PERMISSION_NAME` varchar(100) NOT NULL DEFAULT '' COMMENT '权限名称',
  `DESCRIPTION` varchar(200) NOT NULL DEFAULT '' COMMENT '权限描述',
  `ACTIVE` char(1) NOT NULL DEFAULT '' COMMENT '激活',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

LOCK TABLES `xmo_permission` WRITE;
/*!40000 ALTER TABLE `xmo_permission` DISABLE KEYS */;

INSERT INTO `xmo_permission` (`ID`, `PERMISSION_CODE`, `PERMISSION_NAME`, `DESCRIPTION`, `ACTIVE`)
VALUES
	('1','USER_VIEW','','用户管理','0'),
	('16','BASE_MENU','','基础数据菜单','0'),
	('17','DICTIONARY_VIEW','','数据字典查询','0'),
	('2','USER_GROUP_VIEW','','用户组管理','0'),
	('26','SYSTEM_MENU','','系统管理菜单','1'),
	('3','PERMISSION_VIEW','','权限管理','0'),
	('34','SCHEDULE_VIEW','','任务调度查询','1'),
	('4','ORGANIZATION_VIEW','','组织管理','0'),
	('5','USER_GROUP_ADD','','添加用户组资源','0'),
	('6','USER_ADD','','添加用户','0'),
	('7','PERMISSION_ADD','','新增权限','0');

/*!40000 ALTER TABLE `xmo_permission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_user_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_user_group`;

CREATE TABLE `xmo_user_group` (
  `ID` varchar(32) NOT NULL DEFAULT '',
  `USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '用户表ID',
  `GROUP_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '组表ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

LOCK TABLES `xmo_user_group` WRITE;
/*!40000 ALTER TABLE `xmo_user_group` DISABLE KEYS */;

INSERT INTO `xmo_user_group` (`ID`, `USER_ID`, `GROUP_ID`)
VALUES
	('13','2','2'),
	('14','2','3'),
	('16','1','2');

/*!40000 ALTER TABLE `xmo_user_group` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_user_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_user_permission`;

CREATE TABLE `xmo_user_permission` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '用户表ID',
  `PERMISSION_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '权限表ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_user`;

CREATE TABLE `xmo_user` (
  `ID` varchar(32) NOT NULL DEFAULT '',
  `USERNAME` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `NICKNAME` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `SALT` varchar(50) NOT NULL DEFAULT '' COMMENT '密码盐值',
  `PASSWORD` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `EMAIL` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `PHONE_NUMBER` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `IS_LOCK` char(1) DEFAULT NULL COMMENT '禁用',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  UNIQUE KEY `PHONE_NUMBER` (`PHONE_NUMBER`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

LOCK TABLES `xmo_user` WRITE;
/*!40000 ALTER TABLE `xmo_user` DISABLE KEYS */;

INSERT INTO `xmo_user` (`ID`, `USERNAME`, `NICKNAME`, `SALT`, `PASSWORD`, `EMAIL`, `PHONE_NUMBER`, `IS_LOCK`)
VALUES
	('3','admin','谭新政','abdc2a9939ef4a00','226b3c56bac086d987991cd0eda93171','277303310@qq.com','15000084483','0');

/*!40000 ALTER TABLE `xmo_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

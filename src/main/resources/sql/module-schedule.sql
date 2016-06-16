# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.99.100 (MySQL 5.1.73)
# Database: dms_dev
# Generation Time: 2016-06-14 07:50:06 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table schedule_job
# ------------------------------------------------------------

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `JOB_NAME` varchar(50) NOT NULL COMMENT '任务名称',
  `JOB_GROUP` varchar(50) NOT NULL COMMENT '任务属组',
  `JOB_STATUS` tinyint(1) NOT NULL COMMENT '任务状态：0-正常运行中，1-暂停，2-已完成，3-异常，4-阻塞，-1-无',
  `JOB_DESCRIPTION` varchar(200) NOT NULL COMMENT '任务描述',
  `CRON_EXPRESSION` varchar(50) NOT NULL COMMENT 'CRON表达式',
  `TRIGGER_ID` varchar(100) NOT NULL COMMENT '触发器ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

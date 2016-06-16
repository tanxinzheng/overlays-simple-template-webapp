# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.99.100 (MySQL 5.1.73)
# Database: dms_dev
# Generation Time: 2016-06-14 07:44:33 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_dictionary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dictionary`;

CREATE TABLE `sys_dictionary` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DICTIONARY_CODE` varchar(30) DEFAULT NULL COMMENT '字典编号',
  `DICTIONARY_DESC` varchar(255) DEFAULT NULL COMMENT '字典描述',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';



# Dump of table sys_dictionary_parameter
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dictionary_parameter`;

CREATE TABLE `sys_dictionary_parameter` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SYS_DICTIONARY_ID` int(11) DEFAULT NULL COMMENT '字典',
  `SHOW_VALUE` varchar(50) DEFAULT NULL COMMENT '显示值',
  `REAL_VALUE` varchar(50) DEFAULT NULL COMMENT '实际值',
  `SORT_VALUE` int(3) DEFAULT NULL COMMENT '排位',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '0-禁用，1-启用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典参数表';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

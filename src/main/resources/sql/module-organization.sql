# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.99.100 (MySQL 5.1.73)
# Database: dms_dev
# Generation Time: 2016-06-16 02:08:01 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_organization
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '部门描述',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '上级组织',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';



# Dump of table sys_user_organization
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_organization`;

CREATE TABLE `sys_user_organization` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORGANIZATION_ID` int(11) DEFAULT NULL COMMENT '部门',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门用户关联表';




--
-- Dumping routines (FUNCTION) for database 'dms_dev'
--
DELIMITER ;;

# Dump of FUNCTION query_children_organization
# ------------------------------------------------------------

/*!50003 DROP FUNCTION IF EXISTS `query_children_organization` */;;
/*!50003 SET SESSION SQL_MODE=""*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`%`*/ /*!50003 FUNCTION `query_children_organization`(organization_id INt(11)) RETURNS varchar(4000) CHARSET utf8
BEGIN
	DECLARE s_temp VARCHAR(4000);
	DECLARE s_temp_chd VARCHAR(4000);
	SET s_temp = '$';
	SET s_temp_chd = cast(organization_id as char);
	WHILE s_temp_chd is not NULL DO
	SET s_temp = CONCAT(s_temp,',',s_temp_chd);
	SELECT group_concat(id) INTO s_temp_chd FROM sys_organization where FIND_IN_SET(parent_id,s_temp_chd)>0;
	END WHILE;
	return s_temp;
    END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

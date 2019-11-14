-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 some_code 的数据库结构
CREATE DATABASE IF NOT EXISTS `some_code` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `some_code`;

-- 导出  表 some_code.dict 结构
CREATE TABLE IF NOT EXISTS `dict` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `catalog` varchar(50) DEFAULT NULL COMMENT '目录',
  `code` varchar(50) DEFAULT NULL COMMENT '代码',
  `text` varchar(50) DEFAULT NULL COMMENT '描述',
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序',
  `create_time` date DEFAULT NULL COMMENT '创建日期',
  `modify_time` date DEFAULT NULL COMMENT '修改日期',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建者',
  `modify_user` varchar(50) DEFAULT NULL COMMENT '修改者',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识：0正常，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- 正在导出表  some_code.dict 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `dict` DISABLE KEYS */;
INSERT INTO `dict` (`id`, `catalog`, `code`, `text`, `sort`, `create_time`, `modify_time`, `create_user`, `modify_user`, `del_flag`) VALUES
	('123adsa', 'sex', 'other', '保密', 0, NULL, NULL, NULL, NULL, 0),
	('adsada', 'sex', 'famale', '女', 2, NULL, NULL, NULL, NULL, 0),
	('asdadsa', 'sex', 'male', '男', 1, NULL, NULL, NULL, NULL, 0);
/*!40000 ALTER TABLE `dict` ENABLE KEYS */;

-- 导出  表 some_code.test_dict 结构
CREATE TABLE IF NOT EXISTS `test_dict` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `ino` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  some_code.test_dict 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `test_dict` DISABLE KEYS */;
INSERT INTO `test_dict` (`id`, `name`, `sex`, `ino`) VALUES
	('3dads', 'asd', 'male', 'asa'),
	('adafa', 'adsas', 'famale', NULL);
/*!40000 ALTER TABLE `test_dict` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

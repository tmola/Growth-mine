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


-- 导出 db_fish 的数据库结构
CREATE DATABASE IF NOT EXISTS `db_fish` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_fish`;

-- 导出  表 db_fish.fish_demo 结构
CREATE TABLE IF NOT EXISTS `fish_demo` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `income_type` varchar(50) DEFAULT NULL COMMENT '收入类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  db_fish.fish_demo 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `fish_demo` DISABLE KEYS */;
INSERT INTO `fish_demo` (`id`, `sex`, `income_type`) VALUES
	('as', 'xx1', 'xx3');
/*!40000 ALTER TABLE `fish_demo` ENABLE KEYS */;

-- 导出  表 db_fish.fish_dict 结构
CREATE TABLE IF NOT EXISTS `fish_dict` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `type` varchar(50) DEFAULT NULL COMMENT '分类',
  `code` varchar(50) DEFAULT NULL COMMENT '代码',
  `text` varchar(50) DEFAULT NULL COMMENT '描述',
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  db_fish.fish_dict 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `fish_dict` DISABLE KEYS */;
INSERT INTO `fish_dict` (`id`, `type`, `code`, `text`, `sort`) VALUES
	('1', NULL, 'xx1', '男', 1),
	('2', NULL, 'xx2', '女', 2),
	('3', NULL, 'xx3', '工作', 1),
	('4', NULL, 'xx4', '兼职', 2);
/*!40000 ALTER TABLE `fish_dict` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

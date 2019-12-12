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


-- 导出 tb_practice 的数据库结构
CREATE DATABASE IF NOT EXISTS `tb_practice` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tb_practice`;

-- 导出  表 tb_practice.tb_income 结构
CREATE TABLE IF NOT EXISTS `tb_income` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建者',
  `modify_user` varchar(50) DEFAULT NULL COMMENT '修改者',
  `del_flag` tinyint(1) unsigned DEFAULT NULL COMMENT '删除标识：0正常，1已删除',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `date` date DEFAULT NULL COMMENT '日期',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '收入金额',
  `type` varchar(50) DEFAULT NULL COMMENT '收入分类',
  `explain` varchar(255) DEFAULT NULL COMMENT '收入说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收入表';

-- 正在导出表  tb_practice.tb_income 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_income` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_income` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

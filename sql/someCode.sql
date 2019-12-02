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

-- 导出  表 some_code.blog_easay 结构
CREATE TABLE IF NOT EXISTS `blog_easay` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `essay_title` varchar(50) DEFAULT NULL COMMENT '文章标题',
  `publish_time` datetime DEFAULT NULL COMMENT '发表时间',
  `essay_status` varchar(50) DEFAULT NULL COMMENT '文章状态(已发布，未发布，已舍弃)',
  `essay_type` varchar(50) DEFAULT NULL COMMENT '文章类型',
  `essay_contant` varchar(50) DEFAULT NULL COMMENT '文章内容',
  `easay_save_path` varchar(50) DEFAULT NULL COMMENT '文章保存路径',
  `create_time` date DEFAULT NULL COMMENT '创建日期',
  `modify_time` date DEFAULT NULL COMMENT '修改日期',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建者',
  `modify_user` varchar(50) DEFAULT NULL COMMENT '修改者',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识：0正常，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章表';

-- 正在导出表  some_code.blog_easay 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `blog_easay` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_easay` ENABLE KEYS */;

-- 导出  表 some_code.blog_essay_tag 结构
CREATE TABLE IF NOT EXISTS `blog_essay_tag` (
  `id` varchar(50) NOT NULL,
  `essay_id` varchar(50) DEFAULT NULL,
  `tag_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `essay_tag_index` (`essay_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  some_code.blog_essay_tag 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `blog_essay_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_essay_tag` ENABLE KEYS */;

-- 导出  表 some_code.blog_tag 结构
CREATE TABLE IF NOT EXISTS `blog_tag` (
  `id` varchar(50) NOT NULL,
  `tag` varchar(50) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  some_code.blog_tag 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `blog_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_tag` ENABLE KEYS */;

-- 导出  表 some_code.relev_role_auth 结构
CREATE TABLE IF NOT EXISTS `relev_role_auth` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `authority_id` varchar(50) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `authority_id` (`authority_id`),
  KEY `role_authority` (`role_id`,`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限关联表';

-- 正在导出表  some_code.relev_role_auth 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `relev_role_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `relev_role_auth` ENABLE KEYS */;

-- 导出  表 some_code.relev_user_role 结构
CREATE TABLE IF NOT EXISTS `relev_user_role` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `role_id` varchar(50) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `user_role` (`user_id`,`role_id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表';

-- 正在导出表  some_code.relev_user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `relev_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `relev_user_role` ENABLE KEYS */;

-- 导出  表 some_code.sys_authority 结构
CREATE TABLE IF NOT EXISTS `sys_authority` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `authority_code` varchar(50) DEFAULT NULL COMMENT '权限代码',
  `authority_desc` varchar(50) DEFAULT NULL COMMENT '权限描述',
  `create_time` date DEFAULT NULL COMMENT '创建日期',
  `modify_time` date DEFAULT NULL COMMENT '修改日期',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建者',
  `modify_user` varchar(50) DEFAULT NULL COMMENT '修改者',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识：0正常，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `authority_code` (`authority_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- 正在导出表  some_code.sys_authority 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_authority` ENABLE KEYS */;

-- 导出  表 some_code.sys_dict 结构
CREATE TABLE IF NOT EXISTS `sys_dict` (
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
  UNIQUE KEY `code` (`code`),
  KEY `catalog` (`catalog`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- 正在导出表  some_code.sys_dict 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` (`id`, `catalog`, `code`, `text`, `sort`, `create_time`, `modify_time`, `create_user`, `modify_user`, `del_flag`) VALUES
	('123adsa', 'sex', 'other', '保密', 0, NULL, NULL, NULL, NULL, 0),
	('adsada', 'sex', 'famale', '女', 2, NULL, NULL, NULL, NULL, 0),
	('asdadsa', 'sex', 'male', '男', 1, NULL, NULL, NULL, NULL, 0);
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;

-- 导出  表 some_code.sys_excel 结构
CREATE TABLE IF NOT EXISTS `sys_excel` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `entity_name` varchar(255) DEFAULT NULL COMMENT '实体类名称',
  `entity_clazz` varchar(255) DEFAULT NULL COMMENT '实体类地址（包名+''.''+类名）',
  `excel_name` varchar(255) DEFAULT NULL COMMENT 'excel类名称',
  `excel_clazz` varchar(255) DEFAULT NULL COMMENT 'excel类地址（包名+''.''+类名）',
  `deal_service_name` varchar(255) DEFAULT NULL COMMENT '服务处理类名称',
  `service_clazz` varchar(255) DEFAULT NULL COMMENT '服务处理类地址（包名+''.''+类名）',
  `begin_sheet` int(11) DEFAULT NULL COMMENT '开始sheet',
  `begin_row` int(11) DEFAULT NULL COMMENT '开始行',
  `max_row` int(11) DEFAULT NULL COMMENT '最大行',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` date NOT NULL COMMENT '创建日期',
  `modify_time` date DEFAULT NULL COMMENT '修改日期',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建者',
  `modify_user` varchar(50) DEFAULT NULL COMMENT '修改者',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识：0正常，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`entity_name`,`excel_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='excel配置表';

-- 正在导出表  some_code.sys_excel 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_excel` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_excel` ENABLE KEYS */;

-- 导出  表 some_code.sys_file 结构
CREATE TABLE IF NOT EXISTS `sys_file` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `file_name` varchar(50) DEFAULT NULL COMMENT '文件名',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小(单位:B)',
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `path_url` varchar(50) DEFAULT NULL COMMENT '所在路径',
  `user_id` varchar(50) DEFAULT NULL COMMENT '所有者ID',
  `file_abs` varchar(50) DEFAULT NULL COMMENT '访问属性',
  `upload_date` datetime DEFAULT NULL COMMENT '上传日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';

-- 正在导出表  some_code.sys_file 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_file` ENABLE KEYS */;

-- 导出  表 some_code.sys_log 结构
CREATE TABLE IF NOT EXISTS `sys_log` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `log_level` varchar(50) DEFAULT NULL COMMENT '日志等级',
  `log_type` varchar(50) DEFAULT NULL COMMENT '日志类型',
  `log_content` varchar(50) DEFAULT NULL COMMENT '日志内容',
  `req_method` varchar(50) DEFAULT NULL COMMENT '请求方法名',
  `req_url` varchar(50) DEFAULT NULL COMMENT '请求路径',
  `req_params` varchar(50) DEFAULT NULL COMMENT '请求参数',
  `req_result` varchar(50) DEFAULT NULL COMMENT '请求返回结果',
  `req_ip` varchar(50) DEFAULT NULL COMMENT '请求IP',
  `opt_type` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `opt_user` varchar(50) DEFAULT NULL COMMENT '操作用户',
  `opt_date` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_cost_time` bigint(20) DEFAULT NULL COMMENT '操作耗时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- 正在导出表  some_code.sys_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;

-- 导出  表 some_code.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `role_code` varchar(50) NOT NULL COMMENT '角色代码',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(50) DEFAULT NULL COMMENT '描述',
  `create_time` date NOT NULL COMMENT '创建日期',
  `modify_time` date DEFAULT NULL COMMENT '修改日期',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建者',
  `modify_user` varchar(50) DEFAULT NULL COMMENT '修改者',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识：0正常，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 正在导出表  some_code.sys_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 some_code.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '账号名称',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(50) DEFAULT NULL COMMENT '账号密匙',
  `slat` varchar(50) DEFAULT NULL COMMENT '盐',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `borndate` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '联系邮箱',
  `address` varchar(50) DEFAULT NULL COMMENT '联系地址',
  `create_time` date DEFAULT NULL COMMENT '创建日期',
  `modify_time` date DEFAULT NULL COMMENT '修改日期',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建者',
  `modify_user` varchar(50) DEFAULT NULL COMMENT '修改者',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识：0正常，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `列 3` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  some_code.sys_user 的数据：~10 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `name`, `real_name`, `password`, `slat`, `sex`, `borndate`, `phone`, `email`, `address`, `create_time`, `modify_time`, `create_user`, `modify_user`, `del_flag`) VALUES
	('2019-11-26-5cfc49fa82564ee572e86b0d', '1412.0', '123.0', '123456', NULL, 'male', '2019-09-08', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
	('2019-11-26-7acc4f0b9a9aaf325dc57777', '213.0', '123.0', '123456', NULL, 'famale', '2019-09-08', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
	('2019-11-26-eb08442abd685ef8242cfe67', '1213.0', '123.0', '123456', NULL, 'other', '2019-09-08', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
	('2019-11-26-ef354069a49f868c4285199e', '123.0', '123.0', '123456', NULL, 'other', '2019-09-08', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
	('2019-12-02-01f142c892c833568a495387', '广东', '揭阳', '123456', NULL, 'famale', NULL, NULL, NULL, NULL, '2019-12-02', NULL, NULL, NULL, 0),
	('2019-12-02-48914667a97ae28ebdd39466', '四川', '成都', '123456', NULL, 'other', NULL, NULL, NULL, NULL, '2019-12-02', NULL, NULL, NULL, 0),
	('2019-12-02-7cd64bedb995473ba7a93631', 'asa', 'asas', '123456', NULL, 'famale', NULL, NULL, NULL, NULL, '2019-12-02', NULL, NULL, NULL, 0),
	('2019-12-02-e2814088891fb2e9b62ee77e', 'sasa', 'asas', '123456', NULL, 'other', NULL, NULL, NULL, NULL, '2019-12-02', NULL, NULL, NULL, 0),
	('a2c8471640364351b5864c0187ccb3eb', 'jty', 'string', 'string', 'string', 'other', NULL, 'string', 'string', 'string', NULL, NULL, NULL, NULL, 0),
	('string', 'string', 'string', 'string', 'string', 'string', '2019-11-26', 'string', 'string', 'string', '2019-11-18', '2019-11-18', 'string', 'string', 0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

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

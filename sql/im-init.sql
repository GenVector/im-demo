-- --------------------------------------------------------
-- 主机:                           123.206.126.235
-- 服务器版本:                        5.7.28 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  11.0.0.5934
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 im 的数据库结构
DROP DATABASE IF EXISTS `im`;
CREATE DATABASE IF NOT EXISTS `im` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `im`;

-- 导出  表 im.im_category 结构
DROP TABLE IF EXISTS `im_category`;
CREATE TABLE IF NOT EXISTS `im_category` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='好友分组';

-- 正在导出表  im.im_category 的数据：~0 rows (大约)
DELETE FROM `im_category`;
/*!40000 ALTER TABLE `im_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `im_category` ENABLE KEYS */;

-- 导出  表 im.im_friend 结构
DROP TABLE IF EXISTS `im_friend`;
CREATE TABLE IF NOT EXISTS `im_friend` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `category_id` varchar(50) DEFAULT NULL COMMENT '分类id',
  `friend_id` varchar(50) DEFAULT NULL COMMENT '朋友id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='朋友表';

-- 正在导出表  im.im_friend 的数据：~0 rows (大约)
DELETE FROM `im_friend`;
/*!40000 ALTER TABLE `im_friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `im_friend` ENABLE KEYS */;

-- 导出  表 im.im_group 结构
DROP TABLE IF EXISTS `im_group`;
CREATE TABLE IF NOT EXISTS `im_group` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `avatar` varchar(50) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL COMMENT '类型',
  `master_id` varchar(50) DEFAULT NULL COMMENT '群主',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  im.im_group 的数据：~0 rows (大约)
DELETE FROM `im_group`;
/*!40000 ALTER TABLE `im_group` DISABLE KEYS */;
INSERT INTO `im_group` (`id`, `name`, `avatar`, `type`, `master_id`, `create_time`) VALUES
	('1', '公共聊天群', 'group.png', 'public', 'system', '2020-03-25 11:31:34');
/*!40000 ALTER TABLE `im_group` ENABLE KEYS */;

-- 导出  表 im.im_group_user 结构
DROP TABLE IF EXISTS `im_group_user`;
CREATE TABLE IF NOT EXISTS `im_group_user` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `group_id` varchar(50) DEFAULT NULL COMMENT '群组',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  im.im_group_user 的数据：~1 rows (大约)
DELETE FROM `im_group_user`;
/*!40000 ALTER TABLE `im_group_user` DISABLE KEYS */;
INSERT INTO `im_group_user` (`id`, `group_id`, `user_id`, `create_time`) VALUES
	('1', '1', '0', '2020-04-24 13:01:23');
/*!40000 ALTER TABLE `im_group_user` ENABLE KEYS */;

-- 导出  表 im.im_message 结构
DROP TABLE IF EXISTS `im_message`;
CREATE TABLE IF NOT EXISTS `im_message` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `from_id` varchar(50) DEFAULT NULL COMMENT '发送者',
  `to_id` varchar(50) DEFAULT NULL COMMENT '接收者',
  `type` varchar(50) DEFAULT NULL COMMENT '名称',
  `msg_type` varchar(50) DEFAULT NULL COMMENT '消息类型',
  `content` text COMMENT '内容',
  `send_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  im.im_message 的数据：~0 rows (大约)
DELETE FROM `im_message`;
/*!40000 ALTER TABLE `im_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `im_message` ENABLE KEYS */;

-- 导出  表 im.im_offline_message 结构
DROP TABLE IF EXISTS `im_offline_message`;
CREATE TABLE IF NOT EXISTS `im_offline_message` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `message_id` varchar(50) DEFAULT NULL COMMENT '消息id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='离线信息';

-- 正在导出表  im.im_offline_message 的数据：~0 rows (大约)
DELETE FROM `im_offline_message`;
/*!40000 ALTER TABLE `im_offline_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `im_offline_message` ENABLE KEYS */;

-- 导出  表 im.im_session 结构
DROP TABLE IF EXISTS `im_session`;
CREATE TABLE IF NOT EXISTS `im_session` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `session_id` varchar(50) DEFAULT NULL COMMENT '会话id',
  `session_name` varchar(50) DEFAULT NULL COMMENT '会话名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  im.im_session 的数据：~0 rows (大约)
DELETE FROM `im_session`;
/*!40000 ALTER TABLE `im_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `im_session` ENABLE KEYS */;

-- 导出  表 im.im_user 结构
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE IF NOT EXISTS `im_user` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `avatar` varchar(100) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  im.im_user 的数据：~1 rows (大约)
DELETE FROM `im_user`;
/*!40000 ALTER TABLE `im_user` DISABLE KEYS */;
INSERT INTO `im_user` (`id`, `name`, `avatar`, `gender`, `status`, `create_time`) VALUES
	('0', '管理员', 'avatar.jpg', 1, 0, '2020-04-22 15:39:04');
/*!40000 ALTER TABLE `im_user` ENABLE KEYS */;

-- 导出  表 im.sys_file 结构
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE IF NOT EXISTS `sys_file` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `ext` varchar(10) DEFAULT NULL COMMENT '拓展名',
  `size` int(11) DEFAULT NULL COMMENT '大小',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件表';

-- 正在导出表  im.sys_file 的数据：~0 rows (大约)
DELETE FROM `sys_file`;
/*!40000 ALTER TABLE `sys_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_file` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

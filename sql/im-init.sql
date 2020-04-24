-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.19 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.0.0.5934
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 im 的数据库结构
CREATE DATABASE IF NOT EXISTS `im` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `im`;

-- 导出  表 im.im_category 结构
CREATE TABLE IF NOT EXISTS `im_category` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='好友分组';

-- 数据导出被取消选择。

-- 导出  表 im.im_friend 结构
CREATE TABLE IF NOT EXISTS `im_friend` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `category_id` varchar(50) DEFAULT NULL COMMENT '分类id',
  `friend_id` varchar(50) DEFAULT NULL COMMENT '朋友id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='朋友表';

-- 数据导出被取消选择。

-- 导出  表 im.im_group 结构
CREATE TABLE IF NOT EXISTS `im_group` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `avatar` varchar(50) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL COMMENT '类型',
  `master_id` varchar(50) DEFAULT NULL COMMENT '群主',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 数据导出被取消选择。

-- 导出  表 im.im_group_user 结构
CREATE TABLE IF NOT EXISTS `im_group_user` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `group_id` varchar(50) DEFAULT NULL COMMENT '群组',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 数据导出被取消选择。

-- 导出  表 im.im_message 结构
CREATE TABLE IF NOT EXISTS `im_message` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `from_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发送者',
  `to_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '接收者',
  `type` varchar(50) DEFAULT NULL COMMENT '名称',
  `msg_type` varchar(50) DEFAULT NULL COMMENT '消息类型',
  `content` text COMMENT '内容',
  `send_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 数据导出被取消选择。

-- 导出  表 im.im_session 结构
CREATE TABLE IF NOT EXISTS `im_session` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `session_id` varchar(50) DEFAULT NULL COMMENT '会话id',
  `session_name` varchar(50) DEFAULT NULL COMMENT '会话名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 数据导出被取消选择。

-- 导出  表 im.im_user 结构
CREATE TABLE IF NOT EXISTS `im_user` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `avatar` varchar(100) DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 数据导出被取消选择。

-- 导出  表 im.sys_file 结构
CREATE TABLE IF NOT EXISTS `sys_file` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `ext` varchar(10) DEFAULT NULL COMMENT '拓展名',
  `size` int DEFAULT NULL COMMENT '大小',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件表';

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

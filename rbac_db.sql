/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : rbac_db

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-09-07 18:06:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `auth_access_token`;
CREATE TABLE `auth_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `access_token` varchar(255) NOT NULL COMMENT 'Access Token',
  `user_id` int(11) NOT NULL COMMENT '关联的用户ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `client_id` int(11) NOT NULL COMMENT '接入的客户端ID',
  `expires_in` bigint(11) NOT NULL COMMENT '过期时间戳',
  `grant_type` varchar(50) DEFAULT NULL COMMENT '授权类型，比如：authorization_code',
  `scope` varchar(100) DEFAULT NULL COMMENT '可被访问的用户的权限范围，比如：basic、super',
  `create_user` int(11) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) DEFAULT NULL COMMENT '最后更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Access Token信息表';

-- ----------------------------
-- Records of auth_access_token
-- ----------------------------
INSERT INTO `auth_access_token` VALUES ('1', '1.6659c9d38f5943f97db334874e5229284cdd1523.2592000.1537600367', '1', 'admin', '1', '1537600367', 'authorization_code', 'basic', '1', '2018-08-20 14:27:59', '1', '2018-08-23 15:12:47');
INSERT INTO `auth_access_token` VALUES ('2', '1.854373728dbcc35f6f56c7671823bf5e49042f34.2592000.1537600978', '2', 'zifangsky', '2', '1537600978', 'authorization_code', 'super', '2', '2018-08-23 14:08:07', '2', '2018-08-23 15:22:59');

-- ----------------------------
-- Table structure for auth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `auth_client_details`;
CREATE TABLE `auth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `client_id` varchar(100) NOT NULL COMMENT '接入的客户端ID',
  `client_name` varchar(100) DEFAULT NULL,
  `client_secret` varchar(255) NOT NULL COMMENT '接入的客户端的密钥',
  `redirect_uri` varchar(1000) NOT NULL COMMENT '回调地址',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述信息',
  `create_user` int(11) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) DEFAULT NULL COMMENT '最后更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `status` int(2) DEFAULT '0' COMMENT '0表示未开通；1表示正常使用；2表示已被禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='接入的客户端信息表';

-- ----------------------------
-- Records of auth_client_details
-- ----------------------------
INSERT INTO `auth_client_details` VALUES ('1', 'x3qwrgrO1wYdz72joZ8YyIuD', '测试客户端', 'DPTyJsBv1xjzEgv0MEjBnIVigaVUm3cr', 'http://127.0.0.1:7000/user/userIndex', '这是一个测试客户端服务', '1', '2018-08-06 15:17:18', '1', '2018-08-06 15:17:18', '1');
INSERT INTO `auth_client_details` VALUES ('2', '7Ugj6XWmTDpyYp8M8njG3hqx', 'zifangsky的个人博客', 'tur2rlFfywR9OOP3fB5ZbsLTnNuNabI3', 'http://localhost:7080/login', '用于测试在客户端应用中连接授权服务器', '1', '2018-08-23 11:30:09', '1', '2018-08-23 11:30:09', '1');
INSERT INTO `auth_client_details` VALUES ('3', 'pbAjLKuogxziSF2hGlRdl066', '测试客户端2', 'xUkqZSQQgY5SqfD9qqYXCquWRpiHTbTp', 'http://127.0.0.1:7000/user/userIndex', '这是一个测试客户端服务', '1', '2018-08-31 16:39:52', '1', '2018-08-31 16:39:52', '1');

-- ----------------------------
-- Table structure for auth_client_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_client_user`;
CREATE TABLE `auth_client_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_client_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `auth_scope_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户对某个接入客户端的授权信息';

-- ----------------------------
-- Records of auth_client_user
-- ----------------------------
INSERT INTO `auth_client_user` VALUES ('1', '1', '1', '2');
INSERT INTO `auth_client_user` VALUES ('2', '2', '2', '1');

-- ----------------------------
-- Table structure for auth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `auth_refresh_token`;
CREATE TABLE `auth_refresh_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `token_id` int(11) NOT NULL COMMENT '表auth_access_token对应的Access Token记录',
  `refresh_token` varchar(255) NOT NULL COMMENT 'Refresh Token',
  `expires_in` bigint(11) NOT NULL COMMENT '过期时间戳',
  `create_user` int(11) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) DEFAULT NULL COMMENT '最后更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Refresh Token信息表';

-- ----------------------------
-- Records of auth_refresh_token
-- ----------------------------
INSERT INTO `auth_refresh_token` VALUES ('1', '1', '2.b19923a01cf35ccab48ddbd687750408bd1cb763.31536000.1566544316', '1566544316', '1', '2018-08-20 14:50:27', '1', '2018-08-23 15:11:57');
INSERT INTO `auth_refresh_token` VALUES ('2', '2', '2.cb8a3e903eecd6b74fc33c111b4a286099ace3ce.31536000.1566544978', '1566544978', '2', '2018-08-23 14:08:07', '2', '2018-08-23 15:22:59');

-- ----------------------------
-- Table structure for auth_scope
-- ----------------------------
DROP TABLE IF EXISTS `auth_scope`;
CREATE TABLE `auth_scope` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scope_name` varchar(100) NOT NULL COMMENT '可被访问的用户的权限范围，比如：basic、super',
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='可被访问的用户权限表';

-- ----------------------------
-- Records of auth_scope
-- ----------------------------
INSERT INTO `auth_scope` VALUES ('1', 'super', '用户所有信息');
INSERT INTO `auth_scope` VALUES ('2', 'basic', '用户基本信息');

-- ----------------------------
-- Table structure for func
-- ----------------------------
DROP TABLE IF EXISTS `func`;
CREATE TABLE `func` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `status` int(2) DEFAULT '1' COMMENT '1表示正常使用；2表示已被禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of func
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'manager', '管理员');
INSERT INTO `role` VALUES ('2', 'user', '普通用户');

-- ----------------------------
-- Table structure for role_func
-- ----------------------------
DROP TABLE IF EXISTS `role_func`;
CREATE TABLE `role_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `func_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`role_id`),
  CONSTRAINT `roleId` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_func
-- ----------------------------

-- ----------------------------
-- Table structure for sso_access_token
-- ----------------------------
DROP TABLE IF EXISTS `sso_access_token`;
CREATE TABLE `sso_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `access_token` varchar(255) NOT NULL COMMENT 'Access Token',
  `user_id` int(11) NOT NULL COMMENT '关联的用户ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(50) DEFAULT NULL COMMENT '用户来源IP',
  `client_id` int(11) DEFAULT NULL,
  `channel` varchar(50) DEFAULT '' COMMENT '表示这个Token用于什么渠道，比如：官网、APP1、APP2等等',
  `expires_in` bigint(11) NOT NULL COMMENT '过期时间戳',
  `create_user` int(11) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) DEFAULT NULL COMMENT '最后更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='单点登录的Access Token信息表';

-- ----------------------------
-- Records of sso_access_token
-- ----------------------------
INSERT INTO `sso_access_token` VALUES ('1', '11.0e7baee3e290429b54a5692a4eee8af5f99a9862.2592000.1538210962', '1', 'admin', '127.0.0.1', '2', 'APP1', '1538210962', '1', '2018-08-30 16:22:00', '1', '2018-08-30 16:49:23');
INSERT INTO `sso_access_token` VALUES ('2', '11.eb9866fa4f6dd1d4d32acb72e3db33502a7541ed.2592000.1538536378', '1', 'admin', '127.0.0.1', '1', 'TEST_CLIENT1', '1538536378', '1', '2018-08-30 17:33:26', '1', '2018-09-03 11:12:58');
INSERT INTO `sso_access_token` VALUES ('3', '11.6ce7388a0d37c81c2a0c5661dbb94e1670a81e34.2592000.1538213738', '1', 'admin', '127.0.0.1', '3', 'APP2', '1538213738', '1', '2018-08-30 17:35:38', '1', '2018-08-30 17:35:38');
INSERT INTO `sso_access_token` VALUES ('4', '11.c40f4990d25fd30670d0171bc99625d3aef134d0.2592000.1538288980', '2', 'zifangsky', '127.0.0.1', '1', 'TEST_CLIENT1', '1538288980', '2', '2018-08-31 14:11:19', '2', '2018-08-31 14:29:40');

-- ----------------------------
-- Table structure for sso_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sso_client_details`;
CREATE TABLE `sso_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(100) DEFAULT NULL COMMENT '接入单点登录的系统名称',
  `description` varchar(1000) DEFAULT NULL,
  `redirect_url` varchar(255) NOT NULL COMMENT '请求Token的回调URL',
  `logout_url` varchar(255) NOT NULL COMMENT '注销URL',
  `status` int(2) DEFAULT NULL COMMENT '0表示未开通；1表示正常使用；2表示已被禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='单点登录的回调域名的白名单';

-- ----------------------------
-- Records of sso_client_details
-- ----------------------------
INSERT INTO `sso_client_details` VALUES ('1', 'TEST_CLIENT1', null, 'http://127.0.0.1:7000/user/userIndex', 'http://127.0.0.1:7000/logout', '1');
INSERT INTO `sso_client_details` VALUES ('2', 'APP1', null, 'http://127.0.0.1:6080/login', 'http://127.0.0.1:6080/logout', '1');
INSERT INTO `sso_client_details` VALUES ('3', 'APP2', null, 'http://192.168.197.130:6080/login', 'http://192.168.197.130:6080/logout', '1');

-- ----------------------------
-- Table structure for sso_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `sso_refresh_token`;
CREATE TABLE `sso_refresh_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `token_id` int(11) NOT NULL COMMENT '表sso_access_token对应的Access Token记录',
  `refresh_token` varchar(255) NOT NULL COMMENT 'Refresh Token',
  `expires_in` bigint(11) NOT NULL COMMENT '过期时间戳',
  `create_user` int(11) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) DEFAULT NULL COMMENT '最后更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='单点登录的Refresh Token信息表';

-- ----------------------------
-- Records of sso_refresh_token
-- ----------------------------
INSERT INTO `sso_refresh_token` VALUES ('3', '1', '12.143a279cb81b0e5063af9912f346ae16e49c17e2.31536000.1567154963', '1567154963', '1', '2018-08-30 16:22:35', '1', '2018-08-30 16:49:23');
INSERT INTO `sso_refresh_token` VALUES ('4', '2', '12.76ea056a721c7142c3a5c48d3f1e73f627c94c2e.31536000.1567231591', '1567231591', '1', '2018-08-30 17:33:27', '1', '2018-08-31 14:06:32');
INSERT INTO `sso_refresh_token` VALUES ('5', '3', '12.08d739a43784b1b155cf26f44bbf082f91810727.31536000.1567157738', '1567157738', '1', '2018-08-30 17:35:39', '1', '2018-08-30 17:35:39');
INSERT INTO `sso_refresh_token` VALUES ('6', '4', '12.62f4e8fd4a41e2bf075979d3639fd9a637999548.31536000.1567232980', '1567232980', '2', '2018-08-31 14:11:19', '2', '2018-08-31 14:29:40');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '0表示未开通；1表示正常使用；2表示已被禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '$5$B1pRvzEl$cIB/RBKJ8JYq5PEnuXggnA7nJQMx2/EF10lcbLonaP3', '110', 'admin@zifangsky.cn', '2018-01-01 00:00:00', '2018-01-01 00:00:00', '1');
INSERT INTO `user` VALUES ('2', 'zifangsky', '$5$toOBSeX2$hSnSDyhJmVVRpbmKuIY4SxDgyeGRGacQaBYGrtEBnZA', '110', 'admin@zifangsky.cn', '2018-01-01 00:00:00', '2018-01-01 00:00:00', '1');
INSERT INTO `user` VALUES ('3', 'Tom', '$5$Ws3utS0/$3LSjNvLl/hU1iTMsSLkQtK7iRZnk.ensK.EmIZapk72', '12306', 'admin@zifangsky.cn', '2018-08-03 13:46:27', '2018-08-03 13:46:27', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`),
  CONSTRAINT `userId` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');

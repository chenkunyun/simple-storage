/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : localhost
 Source Database       : simple_storage

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : utf-8

 Date: 03/18/2017 19:12:42 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL COMMENT 'user name',
  `password` varbinary(256) NOT NULL COMMENT 'encrypted password',
  `salt` varchar(32) NOT NULL COMMENT 'salt',
  `nick_name` varchar(64) DEFAULT NULL COMMENT 'nickname',
  `role` varchar(32) NOT NULL DEFAULT '0' COMMENT '0-normal user, 1-admin',
  `enabled` int(11) NOT NULL DEFAULT '1',
  `phone` varchar(18) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `account`
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES ('6', 'admin', 0xff665b7194cceb9fd34264a0a3f3d0d5f6230ac6308fd365e261ee6afb1a2a9f, '19qSCnBi7XpIjlDjHJrxwZrMUkgNQqrx', 'nickname', 'admin', '1', '12345678900', '1111111@gmail.com');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

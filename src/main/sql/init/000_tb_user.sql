/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50632
Source Host           : localhost:3306
Source Database       : thome.user

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2017-07-23 14:18:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户自增ID',
  `UserName` varchar(50) DEFAULT NULL COMMENT '用户名，唯一，登录用',
  `Phone` varchar(20) DEFAULT NULL COMMENT '手机号，唯一',
  `Email` varchar(320) DEFAULT NULL COMMENT '邮箱，唯一',
  `NickName` varchar(50) DEFAULT NULL COMMENT '昵称，显示用',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

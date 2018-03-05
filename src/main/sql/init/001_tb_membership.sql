/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50632
Source Host           : localhost:3306
Source Database       : thome.user

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2017-07-27 13:45:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_membership
-- ----------------------------
DROP TABLE IF EXISTS `tb_membership`;
CREATE TABLE `tb_membership` (
  `UserId` int(11) NOT NULL COMMENT '用户自增ID，主键+外键，标识了是哪一个用户',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建日期',
  `ConfirmationToken` varchar(128) DEFAULT NULL COMMENT 'Token，确认，修改密码用',
  `IsConfirmed` bit(1) DEFAULT b'0' COMMENT 'LastPasswordFailureDate	datetime	0	0	-1	0	0	0	0		0					0	0',
  `LastPasswordFailureDate` datetime DEFAULT NULL COMMENT '最后一次密码错误时间',
  `PasswordFailuresSinceLastSuccess` int(11) NOT NULL DEFAULT '0' COMMENT '最后一次成功登陆后密码错误登录时间',
  `Password` varchar(128) NOT NULL COMMENT '密码，被加密',
  `PasswordChangedDate` datetime DEFAULT NULL COMMENT '密码更改日期',
  `PasswordSalt` varchar(128) NOT NULL COMMENT '密码salt值',
  `PasswordVerificationToken` varchar(128) DEFAULT NULL COMMENT '密码验证Token，更改密码用',
  `PasswordVerificationTokenExpirationDate` datetime DEFAULT NULL COMMENT '密码验证Token生成日期',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

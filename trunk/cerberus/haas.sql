/*
MySQL Data Transfer
Source Host: localhost
Source Database: haas
Target Host: localhost
Target Database: haas
Date: 2007/4/19 ¤W¤È 10:14:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for cerberus_role
-- ----------------------------
CREATE TABLE `cerberus_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `desc` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cerberus_role_task
-- ----------------------------
CREATE TABLE `cerberus_role_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `roleId` bigint(20) NOT NULL,
  `taskId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cerberus_task
-- ----------------------------
CREATE TABLE `cerberus_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `desc` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cerberus_user
-- ----------------------------
CREATE TABLE `cerberus_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `account` varchar(255) NOT NULL,
  `passwd` varchar(255) NOT NULL,
  `desc` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cerberus_user_role
-- ----------------------------
CREATE TABLE `cerberus_user_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cerberus_user_task
-- ----------------------------
CREATE TABLE `cerberus_user_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `userId` bigint(20) NOT NULL,
  `taskId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `cerberus_role` VALUES ('1', 'r1', 'r1');
INSERT INTO `cerberus_role` VALUES ('2', 'r2', 'r2');
INSERT INTO `cerberus_role_task` VALUES ('1', '1', '1');
INSERT INTO `cerberus_role_task` VALUES ('2', '1', '2');
INSERT INTO `cerberus_role_task` VALUES ('3', '2', '2');
INSERT INTO `cerberus_role_task` VALUES ('4', '2', '3');
INSERT INTO `cerberus_task` VALUES ('1', 't1', 't1');
INSERT INTO `cerberus_task` VALUES ('2', 't2', 't2');
INSERT INTO `cerberus_task` VALUES ('3', 't3', 't3');
INSERT INTO `cerberus_user` VALUES ('1', 'kk1', 'kk1', 'kk1', 'kk1');
INSERT INTO `cerberus_user` VALUES ('2', 'kk2', 'kk2', 'kk2', 'kk2');
INSERT INTO `cerberus_user_role` VALUES ('1', '1', '1');
INSERT INTO `cerberus_user_role` VALUES ('2', '2', '2');
INSERT INTO `cerberus_user_task` VALUES ('1', '2', '3');

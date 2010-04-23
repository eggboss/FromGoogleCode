# SQL Manager 2005 for MySQL 3.7.0.1
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : ymservice


SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `ymservice`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `ymservice`;

#
# Structure for the `cerberus_role` table : 
#

DROP TABLE IF EXISTS `cerberus_role`;

CREATE TABLE `cerberus_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `cerberus_role_task` table : 
#

DROP TABLE IF EXISTS `cerberus_role_task`;

CREATE TABLE `cerberus_role_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `roleId` bigint(20) NOT NULL,
  `taskId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `cerberus_task` table : 
#

DROP TABLE IF EXISTS `cerberus_task`;

CREATE TABLE `cerberus_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `cerberus_user` table : 
#

DROP TABLE IF EXISTS `cerberus_user`;

CREATE TABLE `cerberus_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `account` varchar(255) NOT NULL,
  `passwd` varchar(255) character set utf8 collate utf8_bin NOT NULL,
  `note` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `cerberus_user_role` table : 
#

DROP TABLE IF EXISTS `cerberus_user_role`;

CREATE TABLE `cerberus_user_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `cerberus_user_task` table : 
#

DROP TABLE IF EXISTS `cerberus_user_task`;

CREATE TABLE `cerberus_user_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `userId` bigint(20) NOT NULL,
  `taskId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


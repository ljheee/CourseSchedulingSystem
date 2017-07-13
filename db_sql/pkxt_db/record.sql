/*
Navicat MySQL Data Transfer

Source Server         : abc
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : courseschedule

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-06-30 10:25:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(20) DEFAULT NULL,
  `major_name` varchar(120) DEFAULT NULL,
  `week` varchar(30) DEFAULT NULL,
  `oktime` varchar(30) DEFAULT NULL,
  `lib_room` varchar(12) DEFAULT NULL,
  `insert_time` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('1', '李建华', '14级软件工程#30人--1、2班', '5-13', '星期三第5、6节', '615', '2017-06-30 10:25:59');




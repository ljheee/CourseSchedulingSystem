/*
Navicat MySQL Data Transfer

Source Server         : abc
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : courseschedule

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-04-22 10:10:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for week6
-- ----------------------------
DROP TABLE IF EXISTS `week6`;
CREATE TABLE `week6` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `day1` varchar(255) DEFAULT NULL,
  `day2` varchar(255) DEFAULT NULL,
  `day3` varchar(255) DEFAULT NULL,
  `day4` varchar(255) DEFAULT NULL,
  `day5` varchar(255) DEFAULT NULL,
  `day6` varchar(255) DEFAULT NULL,
  `day7` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of week6
-- ----------------------------
INSERT INTO `week6` VALUES ('1', null, null, null, null, null, null, null);
INSERT INTO `week6` VALUES ('2', null, null, null, null, null, null, null);
INSERT INTO `week6` VALUES ('3', null, null, null, null, null, null, null);
INSERT INTO `week6` VALUES ('4', null, null, null, null, null, null, null);
INSERT INTO `week6` VALUES ('5', null, null, null, null, null, null, null);

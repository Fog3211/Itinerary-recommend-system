/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : roadsys

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-06-14 00:45:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for change
-- ----------------------------
DROP TABLE IF EXISTS `change`;
CREATE TABLE `change` (
  `staname` varchar(20) NOT NULL,
  `sroadnum` varchar(8) NOT NULL,
  `eroadnum` varchar(8) NOT NULL,
  `changetime` int(11) NOT NULL,
  PRIMARY KEY (`staname`,`sroadnum`,`eroadnum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of change
-- ----------------------------
INSERT INTO `change` VALUES ('复兴门', 'dt000001', 'dt000002', '2');
INSERT INTO `change` VALUES ('朝阳门', 'dt000002', 'dt000006', '1');
INSERT INTO `change` VALUES ('西直门', 'dt000002', 'dt000004', '1');
INSERT INTO `change` VALUES ('平安里', 'dt000006', 'dt000004', '3');

-- ----------------------------
-- Table structure for change_copy
-- ----------------------------
DROP TABLE IF EXISTS `change_copy`;
CREATE TABLE `change_copy` (
  `staname` varchar(20) NOT NULL,
  `sroadnum` varchar(8) NOT NULL,
  `eroadnum` varchar(8) NOT NULL,
  `changetime` int(11) NOT NULL,
  PRIMARY KEY (`staname`,`sroadnum`,`eroadnum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of change_copy
-- ----------------------------
INSERT INTO `change_copy` VALUES ('复兴门', 'dt000001', 'dt000002', '2');
INSERT INTO `change_copy` VALUES ('朝阳门', 'dt000002', 'dt000006', '1');
INSERT INTO `change_copy` VALUES ('西直门', 'dt000003', 'dt000002', '2');
INSERT INTO `change_copy` VALUES ('平安里', 'dt000006', 'dt000004', '3');
INSERT INTO `change_copy` VALUES ('角门西', 'dt000004', 'dt000010', '2');
INSERT INTO `change_copy` VALUES ('首都国际机场', 'dt000010', 'CA1521', '120');
INSERT INTO `change_copy` VALUES ('北京南站', 'dt000004', 'G507', '30');
INSERT INTO `change_copy` VALUES ('正定国际机场', 'G507', '9C8904', '60');
INSERT INTO `change_copy` VALUES ('西直门', 'dt000004', 'dt000002', '2');
INSERT INTO `change_copy` VALUES ('西直门', 'dt000002', 'dt000004', '2');
INSERT INTO `change_copy` VALUES ('平安里', 'dt000004', 'dt000006', '2');
INSERT INTO `change_copy` VALUES ('朝阳门', 'dt000006', 'dt000002', '2');
INSERT INTO `change_copy` VALUES ('首都国际机场', 'CA1521', 'dt000010', '110');
INSERT INTO `change_copy` VALUES ('角门西', 'dt000010', 'dt000004', '2');
INSERT INTO `change_copy` VALUES ('正定国际机场', '9C8904', 'G507', '50');
INSERT INTO `change_copy` VALUES ('北京南站', 'G507', 'dt000004', '30');
INSERT INTO `change_copy` VALUES ('虹桥国际机场T2', 'CA1521', '9C8904', '30');

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight` (
  `flynum` varchar(8) NOT NULL,
  `flyfirm` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `stime` time DEFAULT NULL,
  `etime` time DEFAULT NULL,
  `sadress` varchar(20) DEFAULT NULL,
  `eadress` varchar(20) DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `price` float(8,2) DEFAULT NULL,
  PRIMARY KEY (`flynum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES ('KN5737', '中国联航', '波音737-800(中型)', '07:50:00', '09:50:00', '南苑机场', '虹桥国际机场T2', null, '490.00');
INSERT INTO `flight` VALUES ('MU5138', '东方航空', '空中客车A330(大型)', '07:00:00', '09:20:00', '首都国际机场T2', '虹桥国际机场T2', '0.97', '600.00');
INSERT INTO `flight` VALUES ('KN5977', '中国联航', '波音737(中型)', '11:55:00', '14:05:00', '南苑机场', '浦东国际机场T1', '0.83', '608.00');
INSERT INTO `flight` VALUES ('MU5152', '东方航空', '空中客车A321(中型)', '11:30:00', '14:00:00', '首都国际机场T2', '虹桥国际机场T2', '0.83', '640.00');
INSERT INTO `flight` VALUES ('MU5154', '东方航空', '空中客车A330(大型)', '13:30:00', '15:40:00', '首都国际机场T2', '虹桥国际机场T2', '0.9', '640.00');
INSERT INTO `flight` VALUES ('MU5104', '东方航空', '空中客车A330(大型)', '09:00:00', '11:15:00', '首都国际机场T2', '虹桥国际机场T2', '0.93', '660.00');
INSERT INTO `flight` VALUES ('MU5106', '东方航空', '空中客车A330(大型)', '10:00:00', '12:15:00', '首都国际机场T2', '虹桥国际机场T2', '0.93', '660.00');
INSERT INTO `flight` VALUES ('KN5987', '中国联航', '波音737(中型)', '19:20:00', '21:30:00', '南苑机场', '浦东国际机场T1', '0.97', '667.00');
INSERT INTO `flight` VALUES ('MU5160', '东方航空', '空中客车A330(大型)', '21:50:00', '23:55:00', '首都国际机场T2', '虹桥国际机场T2', '0.9', '670.00');
INSERT INTO `flight` VALUES ('KN5955', '中国联航', '波音737(中型)', '15:50:00', '18:00:00', '南苑机场', '虹桥国际机场T2', '0.77', '678.00');
INSERT INTO `flight` VALUES ('HU7611', '海南航空', '波音787-9(中型)', '07:40:00', '09:55:00', '首都国际机场T1', '浦东国际机场T2', '0.93', '705.00');
INSERT INTO `flight` VALUES ('HU7605', '海南航空', '波音787-9(大型)', '07:45:00', '10:00:00', '首都国际机场T1', '虹桥国际机场T2', '0.97', '705.00');
INSERT INTO `flight` VALUES ('HU7603', '海南航空', '波音737-800(中型)', '20:35:00', '22:55:00', '首都国际机场T1', '虹桥国际机场T2', '0.93', '705.00');
INSERT INTO `flight` VALUES ('FM9102', '上海航空', '波音737-900ER(中型)', '20:20:00', '22:50:00', '首都国际机场T2', '虹桥国际机场T2', null, '760.00');
INSERT INTO `flight` VALUES ('HU7607', '海南航空', '空中客车A350(大型)', '08:45:00', '11:00:00', '首都国际机场T1', '虹桥国际机场T2', '0.9', '785.00');
INSERT INTO `flight` VALUES ('CZ3907', '南方航空', '空中客车A330(大型)', '08:15:00', '10:30:00', '首都国际机场T2', '虹桥国际机场T2', '0.93', '790.00');
INSERT INTO `flight` VALUES ('MU5156', '东方航空', '空中客车A330(大型)', '08:30:00', '10:40:00', '首都国际机场T2', '虹桥国际机场T2', '0.97', '790.00');
INSERT INTO `flight` VALUES ('MU5108', '东方航空', '空中客车A330(大型)', '11:00:00', '13:20:00', '首都国际机场T2', '虹桥国际机场T2', '0.97', '790.00');
INSERT INTO `flight` VALUES ('MU5110', '东方航空', '空中客车A350-900(大型)', '12:00:00', '14:20:00', '首都国际机场T2', '虹桥国际机场T2', '1', '790.00');
INSERT INTO `flight` VALUES ('HO1252', '吉祥航空', '空中客车A321(中型)', '06:50:00', '09:05:00', '首都国际机场T3', '虹桥国际机场T2', null, '800.00');
INSERT INTO `flight` VALUES ('MU5183', '东方航空', '空中客车A321(中型)', '07:35:00', '09:50:00', '首都国际机场T2', '浦东国际机场T1', '1', '830.00');
INSERT INTO `flight` VALUES ('MU5102', '东方航空', '空中客车A330-300(大型)', '08:00:00', '10:15:00', '首都国际机场T2', '虹桥国际机场T2', '0.9', '830.00');
INSERT INTO `flight` VALUES ('MU5390', '东方航空', '空中客车A320(中型)', '13:10:00', '18:10:00', '首都国际机场T2', '虹桥国际机场T2', null, '830.00');
INSERT INTO `flight` VALUES ('MU5128', '东方航空', '波音777-300ER(大型)', '21:05:00', '23:20:00', '首都国际机场T2', '虹桥国际机场T2', '0.83', '830.00');
INSERT INTO `flight` VALUES ('HU7601', '海南航空', '波音787-9(大型)', '17:30:00', '19:40:00', '首都国际机场T1', '虹桥国际机场T2', '0.8', '875.00');
INSERT INTO `flight` VALUES ('MU5112', '东方航空', '波音777-300ER(大型)', '13:00:00', '15:15:00', '首都国际机场T2', '虹桥国际机场T2', '0.9', '930.00');
INSERT INTO `flight` VALUES ('MU563', '东方航空', '空中客车A330(大型)', '17:15:00', '19:30:00', '首都国际机场T2', '浦东国际机场T1', '0.87', '980.00');
INSERT INTO `flight` VALUES ('MU9102', '东方航空', '波音737-900ER(中型)', '20:20:00', '22:50:00', '首都国际机场T2', '虹桥国际机场T2', null, '980.00');
INSERT INTO `flight` VALUES ('HU7609', '海南航空', '空中客车A330-100(大型)', '15:05:00', '17:10:00', '首都国际机场T1', '虹桥国际机场T2', '0.97', '995.00');
INSERT INTO `flight` VALUES ('MF8178', '厦门航空', '波音737-800(中型)', '13:05:00', '15:30:00', '首都国际机场T2', '虹桥国际机场T2', null, '1032.00');
INSERT INTO `flight` VALUES ('CA1835', '中国国航', '空中客车A330-300(大型)', '08:05:00', '10:15:00', '首都国际机场T3', '浦东国际机场T2', '1', '1100.00');
INSERT INTO `flight` VALUES ('CA1883', '中国国航', '全新空客A350-900', '20:20:00', '22:35:00', '首都国际机场T3', '浦东国际机场T2', '0.9', '1100.00');
INSERT INTO `flight` VALUES ('CA1589', '中国国航', '波音747(大型)', '20:30:00', '22:40:00', '首都国际机场T3', '虹桥国际机场T2', '0.97', '1100.00');
INSERT INTO `flight` VALUES ('FM9108', '上海航空', '波音737-800(中型)', '18:30:00', '20:50:00', '首都国际机场T2', '虹桥国际机场T2', '0.87', '1230.00');
INSERT INTO `flight` VALUES ('MU271', '东方航空', '空中客车A321(中型)', '13:05:00', '15:20:00', '首都国际机场T2', '浦东国际机场T1', '1', '1480.00');
INSERT INTO `flight` VALUES ('MU5114', '东方航空', '空中客车A330(大型)', '14:00:00', '16:20:00', '首都国际机场T2', '虹桥国际机场T2', '0.93', '1480.00');
INSERT INTO `flight` VALUES ('MU5116', '东方航空', '空中客车A330(大型)', '15:00:00', '17:20:00', '首都国际机场T2', '虹桥国际机场T2', '0.87', '1480.00');
INSERT INTO `flight` VALUES ('MU5120', '东方航空', '空中客车A330-300(大型)', '17:00:00', '19:15:00', '首都国际机场T2', '虹桥国际机场T2', '0.73', '1480.00');
INSERT INTO `flight` VALUES ('MU5122', '东方航空', '空中客车A330(大型)', '18:00:00', '20:15:00', '首都国际机场T2', '虹桥国际机场T2', '0.8', '1480.00');
INSERT INTO `flight` VALUES ('MU9108', '东方航空', '波音737-800(中型)', '18:30:00', '20:50:00', '首都国际机场T2', '虹桥国际机场T2', '0.87', '1480.00');
INSERT INTO `flight` VALUES ('MU5124', '东方航空', '空中客车A330(大型)', '19:00:00', '21:15:00', '首都国际机场T2', '虹桥国际机场T2', '0.67', '1480.00');
INSERT INTO `flight` VALUES ('MU5130', '东方航空', '空中客车A320-300(中型)', '19:35:00', '22:00:00', '首都国际机场T2', '浦东国际机场T1', '0.8', '1480.00');
INSERT INTO `flight` VALUES ('MU5126', '东方航空', '空中客车A350-900(大型)', '20:00:00', '22:15:00', '首都国际机场T2', '虹桥国际机场T2', '1', '1480.00');
INSERT INTO `flight` VALUES ('CA1831', '中国国航', '空中客车A330(大型)', '07:30:00', '09:40:00', '首都国际机场T3', '虹桥国际机场T2', '0.97', '1490.00');
INSERT INTO `flight` VALUES ('CA1501', '中国国航', '全新空客A350-900', '08:30:00', '10:40:00', '首都国际机场T3', '虹桥国际机场T2', '0.93', '1490.00');
INSERT INTO `flight` VALUES ('CA1519', '中国国航', '波音777-300ER(大型)', '09:30:00', '11:40:00', '首都国际机场T3', '虹桥国际机场T2', '0.97', '1490.00');
INSERT INTO `flight` VALUES ('CA1557', '中国国航', '空中客车A330(大型)', '11:30:00', '13:40:00', '首都国际机场T3', '虹桥国际机场T2', '0.83', '1490.00');
INSERT INTO `flight` VALUES ('CA1517', '中国国航', '波音777-300ER(大型)', '13:30:00', '15:40:00', '首都国际机场T3', '虹桥国际机场T2', '0.9', '1490.00');
INSERT INTO `flight` VALUES ('CA1521', '中国国航', '波音777-300ER(大型)', '14:30:00', '16:40:00', '首都国际机场', '虹桥国际机场T2', '0.8', '1490.00');
INSERT INTO `flight` VALUES ('CA1515', '中国国航', '波音777-300ER(大型)', '15:30:00', '17:40:00', '首都国际机场', '虹桥国际机场T2', '0.73', '1490.00');
INSERT INTO `flight` VALUES ('CA1583', '中国国航', '波音777-300ER(大型)', '16:05:00', '18:30:00', '首都国际机场', '虹桥国际机场T2', '1', '1490.00');
INSERT INTO `flight` VALUES ('CA1549', '中国国航', '波音777-300ER(大型)', '16:30:00', '18:40:00', '首都国际机场', '虹桥国际机场T2', '0.6', '1490.00');
INSERT INTO `flight` VALUES ('CA1855', '中国国航', '波音747-400(大型)', '17:30:00', '19:40:00', '首都国际机场', '虹桥国际机场T2', '0.83', '1490.00');
INSERT INTO `flight` VALUES ('CA1885', '中国国航', '波音777-300ER(大型)', '18:30:00', '20:40:00', '首都国际机场', '虹桥国际机场T2', '0.87', '1490.00');
INSERT INTO `flight` VALUES ('CA1857', '中国国航', '波音747(大型)', '23:30:00', '01:40:00', '首都国际机场', '虹桥国际机场T2', '0.93', '1490.00');
INSERT INTO `flight` VALUES ('KN2381', '中国联航', '波音737(中型)', '07:35:00', '10:50:00', '正定国际机场T2', '长水国际机场', '0.87', '497.00');
INSERT INTO `flight` VALUES ('KY8248', '昆明航空', '波音737-800(中型)', '20:50:00', '00:10:00', '正定国际机场T2', '长水国际机场', null, '519.00');
INSERT INTO `flight` VALUES ('8L9656', '祥鹏航空', '波音737-600(中型)', '17:50:00', '20:55:00', '正定国际机场T2', '长水国际机场', '0.8', '530.00');
INSERT INTO `flight` VALUES ('NS3311', '河北航空', '波音737-800(中型)', '05:50:00', '08:50:00', '正定国际机场T2', '长水国际机场', '1', '600.00');
INSERT INTO `flight` VALUES ('NS3269', '河北航空', '波音737-800(中型)', '16:00:00', '19:20:00', '正定国际机场T2', '长水国际机场', '0.97', '600.00');
INSERT INTO `flight` VALUES ('9C8937', '春秋航空', 'A320(中型)', '10:15:00', '13:35:00', '正定国际机场T2', '长水国际机场', '0.93', '619.00');
INSERT INTO `flight` VALUES ('3U8670', '四川航空', '空中客车A320(中型)', '13:30:00', '19:00:00', '正定国际机场T2', '长水国际机场', '0.93', '2330.00');
INSERT INTO `flight` VALUES ('9C6256', '春秋航空', '空中客车A320(中型)', '07:00:00', '08:55:00', '正定国际机场T2', '浦东国际机场T2', null, '499.00');
INSERT INTO `flight` VALUES ('9C8766', '春秋航空', '空中客车A320(中型)', '21:30:00', '23:30:00', '正定国际机场T2', '虹桥国际机场T1', '1', '499.00');
INSERT INTO `flight` VALUES ('KN2315', '中国联航', '波音737(中型)', '19:05:00', '21:05:00', '正定国际机场T2', '浦东国际机场T1', '0.93', '538.00');
INSERT INTO `flight` VALUES ('KN2355', '中国联航', '波音737(中型)', '09:30:00', '11:30:00', '正定国际机场', '虹桥国际机场T2', '0.97', '598.00');
INSERT INTO `flight` VALUES ('9C8904', '春秋航空', '空中客车A320(中型)', '16:00:00', '18:05:00', '正定国际机场', '虹桥国际机场T2', '0.9', '679.00');
INSERT INTO `flight` VALUES ('NS3217', '河北航空', '波音737-800(中型)', '17:30:00', '19:30:00', '正定国际机场', '浦东国际机场T2', '0.83', '700.00');
INSERT INTO `flight` VALUES ('KN2317', '中国联航', '波音737(中型)', '16:30:00', '18:30:00', '正定国际机场', '虹桥国际机场T2', '0.83', '716.00');
INSERT INTO `flight` VALUES ('NS3219', '河北航空', '波音737-800(中型)', '08:00:00', '10:00:00', '正定国际机场', '虹桥国际机场T2', '1', '820.00');

-- ----------------------------
-- Table structure for railway
-- ----------------------------
DROP TABLE IF EXISTS `railway`;
CREATE TABLE `railway` (
  `tnum` varchar(5) NOT NULL,
  `stime` time DEFAULT NULL,
  `etime` time DEFAULT NULL,
  `last` int(11) DEFAULT NULL,
  `ssta` varchar(20) DEFAULT NULL,
  `esta` varchar(20) DEFAULT NULL,
  `fseat` float(8,2) DEFAULT NULL,
  `sseat` float(8,2) DEFAULT NULL,
  `bseat` float(8,2) DEFAULT NULL,
  PRIMARY KEY (`tnum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of railway
-- ----------------------------
INSERT INTO `railway` VALUES ('G101', '06:43:00', '12:40:00', '557', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G5', '07:00:00', '11:40:00', '440', '北京南', '上海', '939.00', '558.00', '1762.50');
INSERT INTO `railway` VALUES ('G105', '07:20:00', '13:08:00', '548', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G143', '07:50:00', '13:12:00', '522', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G107', '08:05:00', '13:46:00', '541', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G111', '08:35:00', '14:22:00', '547', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G113', '08:50:00', '14:33:00', '543', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G1', '09:00:00', '13:28:00', '428', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G41', '09:15:00', '14:49:00', '534', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G115', '09:20:00', '14:59:00', '539', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G117', '09:25:00', '15:37:00', '612', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G7', '10:00:00', '14:28:00', '428', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G119', '10:05:00', '15:47:00', '542', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G121', '10:20:00', '16:25:00', '65', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G125', '11:10:00', '16:59:00', '549', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G411', '11:20:00', '17:25:00', '65', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G9', '12:00:00', '16:36:00', '436', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G129', '12:10:00', '18:07:00', '557', '北京南', '上海', '939.00', '558.00', '1762.50');
INSERT INTO `railway` VALUES ('G131', '12:20:00', '18:16:00', '556', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G133', '12:50:00', '18:40:00', '550', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G135', '13:05:00', '18:59:00', '554', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G137', '13:35:00', '19:17:00', '542', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G139', '13:45:00', '20:05:00', '620', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G3', '14:00:00', '18:28:00', '428', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G43', '14:05:00', '19:43:00', '538', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G141', '14:10:00', '20:09:00', '559', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G145', '14:35:00', '20:33:00', '558', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G11', '15:00:00', '19:28:00', '428', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G155', '15:45:00', '21:41:00', '556', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G147', '15:50:00', '22:00:00', '610', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G169', '16:40:00', '22:35:00', '555', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G151', '16:45:00', '23:02:00', '617', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G13', '17:00:00', '21:36:00', '436', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G153', '17:16:00', '23:11:00', '555', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G157', '17:36:00', '23:40:00', '64', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G15', '18:00:00', '22:43:00', '443', '北京南', '上海', '939.00', '558.00', '1762.50');
INSERT INTO `railway` VALUES ('G17', '19:00:00', '23:18:00', '418', '北京南', '上海虹桥', '933.00', '553.00', '1748.00');
INSERT INTO `railway` VALUES ('G21', '19:08:00', '23:36:00', '428', '北京南', '上海', '939.00', '558.00', '1762.50');
INSERT INTO `railway` VALUES ('G507', '06:42:00', '07:46:00', '14', '北京南站', '正定国际机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G529', '07:08:00', '08:27:00', '119', '北京南站', '正定国际机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G627', '08:05:00', '09:15:00', '110', '北京南站', '正定国际机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G571', '09:22:00', '10:25:00', '13', '北京南站', '正定国际机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G67', '12:13:00', '13:23:00', '110', '北京南站', '正定国际机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G6703', '12:44:00', '14:05:00', '121', '北京南站', '正定国际机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G661', '13:50:00', '15:07:00', '117', '北京南站', '正定国际机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G615', '15:03:00', '16:06:00', '13', '北京南站', '正定国际机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G1563', '17:36:00', '18:46:00', '110', '北京南站', '正定国际机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G527', '18:14:00', '19:17:00', '13', '北京西', '正定机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G6707', '19:22:00', '20:39:00', '117', '北京西', '正定机场', '179.50', '111.50', '353.50');
INSERT INTO `railway` VALUES ('G8908', '16:20:09', '16:20:11', '113', 'be', 'dd', '12.30', '111.20', '222.30');

-- ----------------------------
-- Table structure for road
-- ----------------------------
DROP TABLE IF EXISTS `road`;
CREATE TABLE `road` (
  `rdnum` varchar(15) NOT NULL,
  `sstanum` varchar(8) NOT NULL,
  `estanum` varchar(8) NOT NULL,
  `stas` varchar(1024) DEFAULT NULL,
  `transportnum` varchar(8) NOT NULL,
  `doftime` time DEFAULT NULL,
  `doltime` time DEFAULT NULL,
  `lasttime` int(11) NOT NULL,
  `price` double NOT NULL,
  `stacount` int(11) NOT NULL,
  PRIMARY KEY (`rdnum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of road
-- ----------------------------
INSERT INTO `road` VALUES ('000000000000000', '复兴门', '万寿路', '复兴门 南礼士路 木樨地 军事博物馆 公主坟 万寿路', 'dt000001', '07:00:00', '23:30:00', '1', '2', '6');
INSERT INTO `road` VALUES ('000000000000001', '万寿路', '复兴门', '万寿路 公主坟 军事博物馆 木樨地 南礼士路 复兴门', 'dt000001', '07:30:00', '23:00:00', '1', '2', '6');
INSERT INTO `road` VALUES ('000000000000002', '复兴门', '朝阳门', '复兴门 长椿街 宣武门 和平门 前门 崇文门 北京站 朝阳门', 'dt000002', '10:26:37', '10:26:43', '1', '2', '8');
INSERT INTO `road` VALUES ('000000000000003', '朝阳门', '复兴门', '朝阳门 北京站 崇文门 前门 和平门 宣武门 长椿街 复兴门', 'dt000002', '10:29:10', '10:29:17', '1', '2', '8');
INSERT INTO `road` VALUES ('000000000000004', '朝阳门', '西直门', '朝阳门 东四十条 东直门 雍和宫 安定门 鼓楼大街 积水潭 西直门', 'dt000002', '10:35:36', '10:35:39', '1', '2', '8');
INSERT INTO `road` VALUES ('000000000000005', '西直门', '朝阳门', '西直门 积水潭 鼓楼大街 安定门 雍和宫 东直门 东四十条 朝阳门', 'dt000002', '10:37:24', '10:37:26', '1', '2', '8');
INSERT INTO `road` VALUES ('000000000000006', '朝阳门', '平安里', '朝阳门 东四 南锣鼓巷 北海北 平安里', 'dt000006', '10:41:59', '10:42:02', '1', '2', '5');
INSERT INTO `road` VALUES ('000000000000007', '平安里', '朝阳门', '平安里 北海北 南锣鼓巷 东四 朝阳门', 'dt000006', '10:43:10', '10:43:13', '1', '2', '5');
INSERT INTO `road` VALUES ('000000000000008', '建国门', '复兴门', '建国门 东单 王府井 天安门东 天安门西 西单 复兴门', 'dt000001', '10:46:03', '10:46:05', '1', '2', '7');
INSERT INTO `road` VALUES ('000000000000009', '复兴门', '建国门', '复兴门 西单 天安门西 天安门东 王府井 东单 建国门', 'dt000001', '10:47:12', '10:47:14', '1', '2', '7');
INSERT INTO `road` VALUES ('000000000000010', '西直门', '平安里', '西直门 新街口 平安里', 'dt000004', '09:32:47', '09:32:49', '1', '2', '3');
INSERT INTO `road` VALUES ('000000000000011', '平安里', '西直门', '平安里 新街口 西直门', 'dt000004', '09:34:03', '09:34:07', '1', '2', '3');
INSERT INTO `road` VALUES ('000000000000012', '平安里', '菜市口', '平安里 西四 灵境胡同 西单 宣武门 菜市口', 'dt000004', '09:36:44', '09:36:46', '1', '2', '6');
INSERT INTO `road` VALUES ('000000000000013', '菜市口', '平安里', '菜市口 宣武门 西单 灵境胡同 西四 平安里', 'dt000004', '09:38:07', '09:38:10', '1', '2', '6');
INSERT INTO `road` VALUES ('000000000000014', '菜市口', '北京南站', '菜市口 陶然亭 北京南站', 'dt000004', '12:56:18', '12:56:22', '1', '2', '3');
INSERT INTO `road` VALUES ('000000000000015', '北京南站', '菜市口', '北京南站 陶然亭 菜市口', 'dt000004', '19:48:00', '19:48:02', '1', '2', '3');
INSERT INTO `road` VALUES ('000000000000016', '北京南站', '角门西', '北京南站 马家堡 角门西', 'dt000004', '19:50:46', '19:50:50', '1', '2', '3');
INSERT INTO `road` VALUES ('000000000000017', '角门西', '北京南站', '角门西 马家堡 北京南站', 'dt000004', '19:54:54', '19:54:57', '1', '2', '3');
INSERT INTO `road` VALUES ('000000000000018', '角门西', '首都国际机场', '角门西 草桥 纪家庙 首经贸 丰台站 首都国际机场', 'dt000010', '20:02:44', '20:02:48', '1', '2', '3');
INSERT INTO `road` VALUES ('000000000000019', '首都国际机场', '角门西', '首都国际机场 丰台站 首经贸 纪家庙 草桥 角门西', 'dt000010', '20:07:20', '20:07:25', '1', '2', '3');
INSERT INTO `road` VALUES ('000000000000020', '首都国际机场', '虹桥国际机场T2', '首都国际机场 虹桥国际机场T2', 'CA1521', '20:10:17', '20:10:20', '120', '2', '2');
INSERT INTO `road` VALUES ('000000000000021', '虹桥国际机场T2', '首都国际机场', '虹桥国际机场T2 首都国际机场', 'CA1521', '20:12:21', '20:12:26', '120', '2', '2');
INSERT INTO `road` VALUES ('000000000000022', '北京南站', '正定国际机场', '北京南站 正定国际机场', 'G507', '20:12:49', '20:12:55', '180', '2', '2');
INSERT INTO `road` VALUES ('000000000000023', '正定国际机场', '北京南站', '正定国际机场 北京南站', 'G507', '20:17:43', '20:17:46', '180', '2', '2');
INSERT INTO `road` VALUES ('000000000000024', '正定国际机场', '长水国际机场', '正定国际机场 长水国际机场', 'KN2381', '20:18:10', '20:18:12', '1500', '2', '2');
INSERT INTO `road` VALUES ('000000000000025', '长水国际机场', '正定国际机场', '长水国际机场 正定国际机场', 'KN2381', '20:24:05', '20:24:08', '1500', '2', '2');
INSERT INTO `road` VALUES ('000000000000026', '正定国际机场', '虹桥国际机场T2', '正定国际机场 虹桥国际机场T2', '9C8904', '20:25:04', '20:25:07', '240', '2', '2');
INSERT INTO `road` VALUES ('000000000000027', '北京南站', '上海虹桥', '北京南站 上海虹桥', 'G101', '20:38:44', '20:38:47', '360', '2', '2');
INSERT INTO `road` VALUES ('000000000000028', '上海虹桥', '北京南站', '上海虹桥 北京南站', 'G101', '20:41:52', '20:41:54', '360', '2', '2');
INSERT INTO `road` VALUES ('000000000000029', '上海虹桥', '上海市政府', '上海虹桥 东方明珠 浦东 上海市政府', 'dt000201', '20:44:08', '20:44:11', '1', '1', '4');
INSERT INTO `road` VALUES ('000000000000030', '上海市政府', '上海虹桥', '上海市政府 浦东 东方明珠 上海虹桥', 'dt000201', '20:47:15', '20:47:18', '1', '1', '4');
INSERT INTO `road` VALUES ('000000000000031', '海定黄庄', '国家图书馆', '海定黄庄 人民大学 魏公村 国家图书馆', 'dt000004', '20:50:40', '20:50:47', '1', '1', '4');
INSERT INTO `road` VALUES ('000000000000032', '国家图书馆', '海定黄庄', '国家图书馆 魏公村 人民大学 海定黄庄', 'dt000004', '20:52:20', '20:52:22', '1', '1', '4');
INSERT INTO `road` VALUES ('000000000000033', '国家图书馆', '西直门', '国家图书馆 动物园 西直门', 'dt000004', '20:54:27', '20:54:29', '1', '1', '3');
INSERT INTO `road` VALUES ('000000000000034', '西直门', '国际图书馆', '西直门 动物园 国家图书馆', 'dt000004', '20:55:43', '20:55:47', '1', '1', '3');
INSERT INTO `road` VALUES ('000000000000035', '上海虹桥', '上海世贸中心', '上海虹桥 浦西 上海世贸中心', 'dt000202', '20:58:20', '20:58:22', '1', '1', '3');
INSERT INTO `road` VALUES ('000000000000036', '上海世贸中心', '上海虹桥', '上海世贸中心 浦西 上海虹桥', 'dt000202', '20:59:37', '20:59:41', '1', '1', '3');
INSERT INTO `road` VALUES ('000000000000037', '虹桥国际机场T2', '正定国际机场', '虹桥国际机场 正定国际机场', '9C8904', '21:24:12', '21:24:15', '240', '1', '2');

-- ----------------------------
-- Table structure for road_station
-- ----------------------------
DROP TABLE IF EXISTS `road_station`;
CREATE TABLE `road_station` (
  `staname` varchar(30) NOT NULL,
  `rdnum` varchar(15) NOT NULL,
  `stime` time NOT NULL,
  `etime` time NOT NULL,
  PRIMARY KEY (`staname`,`rdnum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of road_station
-- ----------------------------
INSERT INTO `road_station` VALUES ('万寿路', '000000000000001', '06:00:00', '23:00:00');
INSERT INTO `road_station` VALUES ('复兴门', '000000000000002', '06:00:00', '23:33:44');
INSERT INTO `road_station` VALUES ('朝阳门', '000000000000004', '06:35:00', '23:35:38');
INSERT INTO `road_station` VALUES ('朝阳门', '000000000000006', '06:35:00', '23:36:06');
INSERT INTO `road_station` VALUES ('西直门', '000000000000010', '06:38:44', '23:38:46');
INSERT INTO `road_station` VALUES ('平安里', '000000000000012', '06:39:21', '23:39:23');
INSERT INTO `road_station` VALUES ('长椿街', '000000000000002', '06:44:40', '23:44:41');
INSERT INTO `road_station` VALUES ('北海北', '000000000000006', '06:00:00', '23:46:39');
INSERT INTO `road_station` VALUES ('菜市口', '000000000000012', '06:48:13', '23:48:14');
INSERT INTO `road_station` VALUES ('平安里', '000000000000007', '06:22:46', '23:22:48');
INSERT INTO `road_station` VALUES ('北京南站', '000000000000022', '06:18:20', '22:18:23');
INSERT INTO `road_station` VALUES ('角门西', '000000000000018', '06:19:14', '22:19:16');
INSERT INTO `road_station` VALUES ('首都国际机场', '000000000000020', '06:20:26', '23:20:29');
INSERT INTO `road_station` VALUES ('正定国际机场', '000000000000026', '06:21:45', '21:21:47');
INSERT INTO `road_station` VALUES ('海定黄庄', '000000000000031', '06:28:49', '21:28:51');
INSERT INTO `road_station` VALUES ('崇文门', '000000000000002', '06:14:07', '22:14:10');
INSERT INTO `road_station` VALUES ('东四十条', '000000000000005', '06:06:21', '22:06:24');
INSERT INTO `road_station` VALUES ('北京南站', '000000000000015', '06:16:43', '23:16:45');
INSERT INTO `road_station` VALUES ('朝阳门', '000000000000007', '06:12:14', '22:12:19');
INSERT INTO `road_station` VALUES ('朝阳门', '000000000000003', '06:15:49', '23:15:52');
INSERT INTO `road_station` VALUES ('复兴门', '000000000000003', '06:16:16', '22:16:18');

-- ----------------------------
-- Table structure for ry_fg
-- ----------------------------
DROP TABLE IF EXISTS `ry_fg`;
CREATE TABLE `ry_fg` (
  `tnum` varchar(5) NOT NULL,
  `flynum` varchar(8) NOT NULL,
  `wtime` int(11) DEFAULT NULL,
  PRIMARY KEY (`tnum`,`flynum`) USING BTREE,
  KEY `FK_Association_3` (`flynum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ry_fg
-- ----------------------------

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `staname` varchar(30) NOT NULL,
  `doftime` time DEFAULT NULL,
  `doltime` time DEFAULT NULL,
  `city` varchar(40) NOT NULL,
  `uoftime` time DEFAULT NULL,
  `province` varchar(40) NOT NULL,
  `uoltime` time DEFAULT NULL,
  `stop` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES ('万寿路', '06:00:00', '22:00:00', '北京', '11:06:25', '北京市', '11:06:28', '1');
INSERT INTO `station` VALUES ('复兴门', '06:00:00', '22:00:00', '北京', '11:06:30', '北京市', '11:06:32', '1');
INSERT INTO `station` VALUES ('朝阳门', '06:00:00', '22:00:00', '北京', '11:06:35', '北京市', '11:06:37', '1');
INSERT INTO `station` VALUES ('西直门', '21:46:54', '21:46:57', '北京', '11:06:39', '北京市', '11:06:42', '1');
INSERT INTO `station` VALUES ('平安里', '22:09:29', '22:09:34', '北京', '11:06:44', '北京市', '11:06:46', '1');
INSERT INTO `station` VALUES ('建国门', '10:50:05', '10:50:07', '北京', '11:06:49', '北京市', '11:06:51', '1');
INSERT INTO `station` VALUES ('崇文门', '17:04:19', '17:04:22', '北京', '17:04:29', '北京市', '17:04:37', '0');
INSERT INTO `station` VALUES ('长椿街', '17:05:09', '17:05:10', '北京', '17:05:21', '北京市', '17:05:31', '0');
INSERT INTO `station` VALUES ('北海北', '23:01:52', '23:01:55', '北京', '23:02:09', '北京市', '23:02:17', '0');
INSERT INTO `station` VALUES ('北京站', '23:11:14', '23:11:16', '北京', '23:11:25', '北京市', '23:11:38', '0');
INSERT INTO `station` VALUES ('菜市口', '09:41:12', '09:41:14', '北京', '09:41:20', '北京市', '09:41:28', '1');
INSERT INTO `station` VALUES ('海定黄庄', '12:04:53', '12:04:56', '北京', '00:00:00', '北京市', null, '1');
INSERT INTO `station` VALUES ('北京南站', null, null, '北京', null, '北京市', null, '1');
INSERT INTO `station` VALUES ('正定国际机场', null, null, '石家庄', null, '河北省', null, '1');
INSERT INTO `station` VALUES ('长水国际机场', null, null, '昆明', null, '云南省', null, '1');
INSERT INTO `station` VALUES ('首都国际机场', null, null, '北京', null, '北京市', null, '1');
INSERT INTO `station` VALUES ('角门西', null, null, '北京', null, '北京市', null, '1');
INSERT INTO `station` VALUES ('虹桥国际机场T2', null, null, '上海', null, '上海市', null, '1');
INSERT INTO `station` VALUES ('上海虹桥', null, null, '上海', null, '上海市', null, '1');
INSERT INTO `station` VALUES ('上海市政府', null, null, '上海', null, '上海市', null, '1');
INSERT INTO `station` VALUES ('上海世贸中心', null, null, '上海', null, '上海市', null, '1');
INSERT INTO `station` VALUES ('国家图书馆', null, null, '北京', null, '北京市', null, '1');
INSERT INTO `station` VALUES ('东四', null, null, '北京', null, '北京市', null, '0');
INSERT INTO `station` VALUES ('东四十条', null, null, '北京', null, '北京市', null, '0');

-- ----------------------------
-- Table structure for subway
-- ----------------------------
DROP TABLE IF EXISTS `subway`;
CREATE TABLE `subway` (
  `subnum` varchar(8) NOT NULL,
  `stanum` int(11) DEFAULT NULL,
  `stas` varchar(1024) DEFAULT NULL,
  `price` double NOT NULL,
  `lasttime` int(11) NOT NULL,
  PRIMARY KEY (`subnum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of subway
-- ----------------------------
INSERT INTO `subway` VALUES ('dt000001', '17', '万寿路*公主坟*军事博物馆*木樨地*南礼士路*复兴门*西单*天安门西*天安门东*王府井*东单*建国门*永安里*国贸*大望路*四惠*四惠东', '2', '1');
INSERT INTO `subway` VALUES ('dt000002', '18', '西直门*积水潭*鼓楼大街*安定门*雍和宫*东直门*东四十条*朝阳门*建国门*北京站*崇文门*前门*和平门*宣武门*长椿街*复兴门*阜成门*车公庄', '2', '1');
INSERT INTO `subway` VALUES ('dt000004', '35', '天宫院*生物医院基地*义和庄*黄村火车站*黄村西大街*清源路*枣园*高米店南*高米店北*西红门*新宫*公益西桥*角门西*马家堡*北京南站*陶然亭*菜市口*宣武门*西单*灵境胡同*西四*平安里*新街口*西直门*动物园*国家图书馆*魏公村*人民大学*海淀黄庄*中关村*北京大学东门*圆明园*西苑*北宫门*安河桥北', '2', '1');
INSERT INTO `subway` VALUES ('dt000005', '23', '宋家庄*刘家窑*蒲黄榆*天坛东门*磁器口*崇文门*东单*灯市口*东四*张自忠路*北新桥*雍和宫*和平里北街*和平西桥*惠新西街南口*惠新西街北口*大屯路东*北苑路北*立水桥南*立水桥*天通苑南*天通苑*天通苑北', '2', '1');
INSERT INTO `subway` VALUES ('dt000006', '24', '金安桥*苹果园*杨庄*西黄村*廖公庄*田村*海淀五路居*慈寿寺*花园桥*白石桥南*车公庄西*车公庄*平安里*北海北*南锣鼓巷*东四*朝阳门*东大桥*呼家楼*金台路*十里堡*青年路*褡裢坡*黄渠*常营*草房*物资学院路*通州北关*通运门*北运河西*北运河东*郝家府*东夏园*潞城', '2', '1');
INSERT INTO `subway` VALUES ('dt000007', '21', '北京西站*湾子*达官营*广安门内*菜市口*虎坊桥*珠市口*桥湾*磁器口*广渠门内*广渠门外*双井*九龙山*大郊亭*百子湾*化工*南楼梓庄*欢乐谷景区*垡头*双合*焦化厂', '2', '1');
INSERT INTO `subway` VALUES ('dt000008', '32', '朱辛庄*育知路*平西府*回龙观东大街*霍营*育新*西小口*永泰庄*林萃桥*森林公园南门*奥林匹克公园*奥体中心*北土城*安华桥*安德里北街*鼓楼大街*什刹海*南锣鼓巷*中国美术馆*瀛海*德茂*五福堂*火箭万源*东高地*和义*大红门南*大红门*海户屯*木樨园*永定门外*天桥*珠市口', '2', '1');
INSERT INTO `subway` VALUES ('dt000009', '13', '郭公庄*丰台科技园*科怡路*丰台南路*丰台东大街*七里庄*六里桥*六里桥东*北京西站*军事博物馆*白堆子*白石桥南*国家图书馆', '2', '1');
INSERT INTO `subway` VALUES ('dt000010', '46', '巴沟*苏州街*海淀黄庄*知春里*知春路*西土城*牡丹园*健德门*北土城*安贞门*惠新西街*南口*芍药居*太阳宫*三元桥*亮马桥*农业展览馆*团结湖*呼家楼*金台夕照*国贸*双井*潘家园*十里河*分钟寺*成寿寺*宋家庄*石榴庄*大红门*角门东*角门西*草桥*纪家庙*首经贸*丰台站*泥洼*西局*六里桥*莲花桥*公主坟*西钓鱼台*慈寿寺*车道沟*长春桥*火器营', '2', '1');
INSERT INTO `subway` VALUES ('dt000011', '1', '复兴门', '2', '1');
INSERT INTO `subway` VALUES ('dt000012', '2', 'jjjj', '2', '1');

-- ----------------------------
-- Table structure for ugsta_ug
-- ----------------------------
DROP TABLE IF EXISTS `ugsta_ug`;
CREATE TABLE `ugsta_ug` (
  `ugname` varchar(30) NOT NULL,
  `dir` varchar(1) NOT NULL,
  `xsubnum` varchar(2) NOT NULL,
  `ssubnum` varchar(2) NOT NULL,
  `wtime` int(11) DEFAULT NULL,
  PRIMARY KEY (`ugname`,`dir`,`xsubnum`,`ssubnum`) USING BTREE,
  KEY `FK_Association_4` (`xsubnum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ugsta_ug
-- ----------------------------
INSERT INTO `ugsta_ug` VALUES ('复兴门', 'u', '1', '2', '110');
INSERT INTO `ugsta_ug` VALUES ('车公庄', 'u', '2', '6', '70');
INSERT INTO `ugsta_ug` VALUES ('平安里', 'u', '6', '4', '110');
INSERT INTO `ugsta_ug` VALUES ('复兴门', 'd', '2', '1', '110');
INSERT INTO `ugsta_ug` VALUES ('车公庄', 'd', '6', '2', '70');
INSERT INTO `ugsta_ug` VALUES ('平安里', 'd', '4', '6', '110');

-- ----------------------------
-- Table structure for ug_fg
-- ----------------------------
DROP TABLE IF EXISTS `ug_fg`;
CREATE TABLE `ug_fg` (
  `flynum` varchar(8) NOT NULL,
  `subnum` varchar(2) NOT NULL,
  `wtime` int(11) DEFAULT NULL,
  PRIMARY KEY (`flynum`,`subnum`) USING BTREE,
  KEY `FK_Association_2` (`subnum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ug_fg
-- ----------------------------

-- ----------------------------
-- Table structure for ug_ry
-- ----------------------------
DROP TABLE IF EXISTS `ug_ry`;
CREATE TABLE `ug_ry` (
  `subnum` varchar(2) NOT NULL,
  `tnum` varchar(5) NOT NULL,
  `wtime` int(11) DEFAULT NULL,
  PRIMARY KEY (`subnum`,`tnum`) USING BTREE,
  KEY `FK_Association_1` (`tnum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ug_ry
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `unum` varchar(4) NOT NULL,
  `uname` varchar(10) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`unum`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------

/*
Navicat MySQL Data Transfer

Source Server         : 百步梯测试数据库
Source Server Version : 50528
Source Host           : 120.76.246.196:3306
Source Database       : his-xuwen

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-08-08 16:26:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for his_body_part
-- ----------------------------
DROP TABLE IF EXISTS `his_body_part`;
CREATE TABLE `his_body_part` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `body_part` varchar(32) NOT NULL COMMENT '身体部位',
  `sort` int(1) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='身体组织分类';

-- ----------------------------
-- Records of his_body_part
-- ----------------------------
INSERT INTO `his_body_part` VALUES ('1', '背部', '1');
INSERT INTO `his_body_part` VALUES ('2', '生殖器', '2');
INSERT INTO `his_body_part` VALUES ('3', '耳眼口鼻', '3');
INSERT INTO `his_body_part` VALUES ('4', '皮肤', '4');
INSERT INTO `his_body_part` VALUES ('5', '上肢', '5');
INSERT INTO `his_body_part` VALUES ('6', '腹部', '6');
INSERT INTO `his_body_part` VALUES ('7', '下肢', '7');
INSERT INTO `his_body_part` VALUES ('8', '胸部', '8');
INSERT INTO `his_body_part` VALUES ('9', '颈部', '9');
INSERT INTO `his_body_part` VALUES ('10', '其他', '10');
INSERT INTO `his_body_part` VALUES ('11', '头部', '11');

-- ----------------------------
-- Table structure for his_deposit_payment
-- ----------------------------
DROP TABLE IF EXISTS `his_deposit_payment`;
CREATE TABLE `his_deposit_payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `in_hos_no` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '住院号',
  `bed_no` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '床号',
  `pay_type` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '支付类型',
  `pay_record` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `pay_money` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `successful_payment` varchar(50) CHARACTER SET utf8 DEFAULT '0' COMMENT '0支付未成功，1支付成功,  2表示已退款',
  `out_trade_no` varchar(30) DEFAULT NULL COMMENT '商户订单号',
  `transaction_id` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信订单号',
  `creat_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `appid` varchar(40) CHARACTER SET utf8 DEFAULT NULL COMMENT '商户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COMMENT='押金补缴';

-- ----------------------------
-- Records of his_deposit_payment
-- ----------------------------
INSERT INTO `his_deposit_payment` VALUES ('1', '213990', '17', '5', null, '3113', '0', 'DO201902261649384853773727', null, '2019-02-26 16:49:38', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('2', '213990', '17', '5', null, '3113', '0', 'DO201902261649419158608944', null, '2019-02-26 16:49:41', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('3', '213990', '17', '5', null, '3113', '0', 'DO201902261649426744498454', null, '2019-02-26 16:49:42', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('4', '213990', '17', '5', null, '3113', '0', 'DO201902261649428759681100', null, '2019-02-26 16:49:42', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('5', '213990', '17', '5', null, '3113', '0', 'DO201902261649430668575673', null, '2019-02-26 16:49:43', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('6', '213990', '17', '5', null, '3113', '0', 'DO201902261649432679442607', null, '2019-02-26 16:49:43', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('7', '213990', '17', '5', null, '3113', '0', 'DO201902261649436421757161', null, '2019-02-26 16:49:43', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('8', '213990', '17', '5', null, '3113', '0', 'DO201902261649438430929754', null, '2019-02-26 16:49:43', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('9', '213990', '17', '5', null, '3113', '0', 'DO201902261649442260231993', null, '2019-02-26 16:49:44', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('10', '213990', '17', '5', null, '3113', '0', 'DO201902261649481237220942', null, '2019-02-26 16:49:48', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('11', '213990', '17', '5', null, '3113', '0', 'DO201902261649486901535426', null, '2019-02-26 16:49:48', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('12', '213990', '17', '5', null, '3113', '0', 'DO201902261649488892511216', null, '2019-02-26 16:49:48', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('13', '213990', '17', '5', null, '3113', '0', 'DO201902261649490988813919', null, '2019-02-26 16:49:49', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('14', '213990', '17', '5', null, '3113', '0', 'DO201902261651220144080249', null, '2019-02-26 16:51:22', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('15', '213990', '17', '5', null, '3113', '0', 'DO201902261651228433707183', null, '2019-02-26 16:51:22', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('16', '213990', '17', '5', null, '3113', '0', 'DO201902261651232756485671', null, '2019-02-26 16:51:23', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('17', '213990', '17', '5', null, '3113', '0', 'DO201902261651239391496673', null, '2019-02-26 16:51:23', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('18', '213990', '17', '5', null, '3113', '0', 'DO201902261651241638765682', null, '2019-02-26 16:51:24', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('19', '213990', '17', '5', null, '3113', '0', 'DO201902261651243712402241', null, '2019-02-26 16:51:24', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('20', '213990', '17', '5', null, '3113', '0', 'DO201902261651245461933868', null, '2019-02-26 16:51:24', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('21', '213990', '17', '5', null, '3113', '0', 'DO201902261651247389460550', null, '2019-02-26 16:51:24', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('22', '213990', '17', '5', null, '3113', '0', 'DO201902261651333301716038', null, '2019-02-26 16:51:33', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('23', '213990', '17', '5', null, '3113', '0', 'DO201902261651335397084961', null, '2019-02-26 16:51:33', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('24', '213990', '17', '5', null, '3113', '0', 'DO201902261651337235303898', null, '2019-02-26 16:51:33', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('25', '213990', '17', '5', null, '3113', '0', 'DO201902261651339468989399', null, '2019-02-26 16:51:33', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('26', '213990', '17', '5', null, '3113', '0', 'DO201902261651423472457268', null, '2019-02-26 16:51:42', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('27', '213990', '17', '5', null, '3113', '0', 'DO201902261653474210821651', null, '2019-02-26 16:53:47', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('28', '213990', '17', '5', null, '3113', '0', 'DO201902261653482900868371', null, '2019-02-26 16:53:48', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('29', '213990', '17', '5', null, '3113', '0', 'DO201902261653498580248456', null, '2019-02-26 16:53:49', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('30', '213990', '17', '5', null, '3113', '0', 'DO201902261653500814046461', null, '2019-02-26 16:53:50', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('31', '213990', '17', '5', null, '3113', '0', 'DO201902261653503384958234', null, '2019-02-26 16:53:50', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('32', '213990', '17', '5', null, '3113', '0', 'DO201902261653505621140393', null, '2019-02-26 16:53:50', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('33', '213990', '17', '5', null, '3113', '0', 'DO201902261653517061823899', null, '2019-02-26 16:53:51', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('34', '213990', '17', '5', null, '3113', '0', 'DO201902261653519217945314', null, '2019-02-26 16:53:51', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('35', '213990', '17', '5', null, '3113', '0', 'DO201902261653521381745813', null, '2019-02-26 16:53:52', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('36', '213990', '17', '5', null, '3113', '0', 'DO201902261653530021404621', null, '2019-02-26 16:53:53', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('37', '213990', '17', '5', null, '3113', '0', 'DO201902261653531946319748', null, '2019-02-26 16:53:53', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('38', '213990', '17', '5', null, '3113', '0', 'DO201902261653533856112200', null, '2019-02-26 16:53:53', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('39', '213990', '17', '5', null, '3113', '0', 'DO201902261654068912521794', null, '2019-02-26 16:54:06', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('40', '214080', '61', '5', null, '350', '0', 'DP201903011614095509715545', null, '2019-03-01 16:14:09', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('41', '214080', '61', '5', null, '350', '0', 'DP201903011614202312751899', null, '2019-03-01 16:14:20', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('42', '214080', '61', '5', null, '350', '0', 'DP201903011614203789185923', null, '2019-03-01 16:14:20', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('43', '214080', '61', '5', null, '350', '0', 'DP201903011614210725614802', null, '2019-03-01 16:14:21', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('44', '214080', '61', '5', null, '350', '0', 'DP201903011614212116089561', null, '2019-03-01 16:14:21', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('45', '214080', '61', '5', null, '350', '0', 'DP201903011614214179719279', null, '2019-03-01 16:14:21', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('46', '214080', '61', '5', null, '350', '0', 'DP201903011614215434280827', null, '2019-03-01 16:14:21', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('47', '214080', '61', '5', null, '350', '0', 'DP201903011614217344811037', null, '2019-03-01 16:14:21', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('48', '214080', '61', '5', null, '350', '0', 'DP201903011614218884714653', null, '2019-03-01 16:14:21', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('49', '214080', '61', '5', null, '350', '0', 'DP201903011614220307616507', null, '2019-03-01 16:14:22', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('50', '214080', '61', '5', null, '350', '0', 'DP201903011614221576354837', null, '2019-03-01 16:14:22', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('51', '214080', '61', '5', null, '350', '0', 'DP201903011614226157483788', null, '2019-03-01 16:14:22', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('52', '214080', '61', '5', null, '350', '0', 'DP201903011614227607681234', null, '2019-03-01 16:14:22', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('53', '214080', '61', '5', null, '350', '0', 'DP201903011614250045495714', null, '2019-03-01 16:14:25', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('54', '214080', '61', '5', null, '350', '0', 'DP201903011614251522736873', null, '2019-03-01 16:14:25', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('55', '214080', '61', '5', null, '350', '0', 'DP201903011614252877858833', null, '2019-03-01 16:14:25', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('56', '214080', '61', '5', null, '350', '0', 'DP201903011614255705536235', null, '2019-03-01 16:14:25', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('57', '214080', '61', '5', null, '350', '0', 'DP201903011614257831559952', null, '2019-03-01 16:14:25', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('58', '214080', '61', '5', null, '350', '0', 'DP201903011614260037671487', null, '2019-03-01 16:14:26', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('59', '214080', '61', '5', null, '350', '0', 'DP201903011614262207619473', null, '2019-03-01 16:14:26', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('60', '214080', '61', '5', null, '350', '0', 'DP201903011614276086727822', null, '2019-03-01 16:14:27', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('61', '214080', '61', '5', null, '350', '0', 'DP201903011614277392248567', null, '2019-03-01 16:14:27', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('62', '214080', '61', '5', null, '350', '0', 'DP201903011614581888490475', null, '2019-03-01 16:14:58', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('63', '214080', '61', '5', null, '350', '0', 'DP201903011614589959404536', null, '2019-03-01 16:14:58', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('64', '214080', '61', '5', null, '350', '0', 'DP201903011614591674840642', null, '2019-03-01 16:14:59', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('65', '214082', '5', '5', null, '200', '0', 'DP201903011615565603391179', null, '2019-03-01 16:15:56', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('66', '214080', '61', '5', null, '350', '0', 'DP201903011618565908558841', null, '2019-03-01 16:18:56', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('67', '214080', '61', '5', null, '350', '0', 'DP201903011618567321427250', null, '2019-03-01 16:18:56', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('68', '214080', '61', '5', null, '350', '0', 'DP201903011618568511400388', null, '2019-03-01 16:18:56', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('69', '213990', '17', '5', null, '3113', '0', 'DP201903011622071379441232', null, '2019-03-01 16:22:07', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('70', '214080', '61', '5', null, '350', '2', 'DP201903011744476192896423', '4200000282201903013417634194', '2019-03-01 17:44:47', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('71', '213990', '17', '5', null, '3113', '0', 'DP201903040900318593342742', null, '2019-03-04 09:00:31', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('72', '213990', '17', '5', null, '3113', '0', 'DP201903051745497337119533', null, '2019-03-05 17:45:49', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('73', '213990', '17', '5', null, '3113', '0', 'DP201903051745510627764300', null, '2019-03-05 17:45:51', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('74', '213990', '17', '5', null, '3113', '1', 'DP201903051746267661388569', '4200000281201903050550567259', '2019-03-05 17:46:26', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('75', '213990', '17', '5', null, '3113', '0', 'DP201903051746269006999610', null, '2019-03-05 17:46:26', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('76', '213990', '17', '5', null, '6226', '0', 'DP201903051750339739231914', null, '2019-03-05 17:50:33', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('77', '213990', '17', '5', null, '6226', '1', 'DP201903051751411322637920', '4200000286201903056533251086', '2019-03-05 17:51:41', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('78', '213990', '17', '5', null, '12452', '0', 'DP201903051755408118960083', null, '2019-03-05 17:55:40', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('79', '213990', '17', '5', null, '12452', '1', 'DP201903051755418940442146', '4200000260201903059611454373', '2019-03-05 17:55:41', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('80', '213990', '17', '5', null, '24904', '1', 'DP201903051819585023840558', '4200000268201903055844312712', '2019-03-05 18:19:58', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('81', '213990', '17', '5', null, '49808', '0', 'DP201903081529092134988883', null, '2019-03-08 15:29:09', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('82', '213990', '17', '5', null, '49808', '0', 'DP201903101821235696648744', null, '2019-03-10 18:21:23', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('83', '213990', '17', '5', null, '49808', '0', 'DP201903101821248826292978', null, '2019-03-10 18:21:24', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('84', '213990', '17', '5', null, '49808', '0', 'DP201903101821439769566675', null, '2019-03-10 18:21:43', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('85', '213990', '17', '5', null, '49808', '0', 'DP201903101823230872398260', null, '2019-03-10 18:23:23', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('86', '234027', '28', '5', null, '1000', '0', 'DP201903211651364005235135', null, '2019-03-21 16:51:36', 'wx14095476fefe834e');
INSERT INTO `his_deposit_payment` VALUES ('87', '0025978', '1438', '5', null, '1000', '0', 'DP201906191141288865301372', null, '2019-06-19 11:41:28', 'wx50617d578b93801d');
INSERT INTO `his_deposit_payment` VALUES ('88', '0025978', '1438', '5', null, '1000', '0', 'DP201906191141298916385781', null, '2019-06-19 11:41:29', 'wx50617d578b93801d');
INSERT INTO `his_deposit_payment` VALUES ('89', '0025978', '1438', '5', null, '1000', '0', 'DP201906191141329152333994', null, '2019-06-19 11:41:32', 'wx50617d578b93801d');
INSERT INTO `his_deposit_payment` VALUES ('90', '0025978', '1438', '5', null, '1000', '0', 'DP201906191141414677299281', null, '2019-06-19 11:41:41', 'wx50617d578b93801d');
INSERT INTO `his_deposit_payment` VALUES ('91', '0025978', '1438', '5', null, '1000', '0', 'DP201906191141415307385357', null, '2019-06-19 11:41:41', 'wx50617d578b93801d');

-- ----------------------------
-- Table structure for his_dopay_info
-- ----------------------------
DROP TABLE IF EXISTS `his_dopay_info`;
CREATE TABLE `his_dopay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `syn_user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `syn_key` varchar(11) DEFAULT NULL COMMENT '效验码',
  `hi_fee_nos` varchar(100) DEFAULT NULL COMMENT '缴费编号',
  `pay_record` varchar(50) DEFAULT NULL COMMENT '支付交易流水',
  `pay_money` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '实际支付金额',
  `socialsecurityNO` varchar(50) DEFAULT '0' COMMENT '社保卡号',
  `over_record` varchar(50) DEFAULT NULL COMMENT '统筹结算流水号',
  `over_money` varchar(50) DEFAULT NULL COMMENT '统筹金额',
  `medicare_return` varchar(50) DEFAULT NULL COMMENT '医保返回信息',
  `bank_return` varchar(50) DEFAULT NULL COMMENT '银行返回信息',
  `terminal_code` varchar(50) DEFAULT NULL COMMENT '终端号',
  `user_no` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `medicare_type` int(50) DEFAULT '0' COMMENT '0,非医保 1,市医保  2,其他医保',
  `app_id` varchar(30) DEFAULT NULL COMMENT '商户id',
  `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
  `pay_type` varchar(30) DEFAULT NULL COMMENT '支付类型(住院还是门诊还是挂号)',
  `successful_payment` varchar(10) DEFAULT '0' COMMENT '0支付未成功,1支付成功,2已退款',
  `out_trade_no` varchar(50) DEFAULT NULL COMMENT '商户订单号',
  `transaction_id` varchar(50) DEFAULT NULL COMMENT '微信订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='门诊支付';

-- ----------------------------
-- Records of his_dopay_info
-- ----------------------------
INSERT INTO `his_dopay_info` VALUES ('23', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:17', '5', null, 'DO201902131737171190682778', null);
INSERT INTO `his_dopay_info` VALUES ('24', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:18', '5', null, 'DO201902131737189402055097', null);
INSERT INTO `his_dopay_info` VALUES ('25', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:19', '5', null, 'DO201902131737195622439883', null);
INSERT INTO `his_dopay_info` VALUES ('26', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:20', '5', null, 'DO201902131737201553897786', null);
INSERT INTO `his_dopay_info` VALUES ('27', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:20', '5', null, 'DO201902131737203550995288', null);
INSERT INTO `his_dopay_info` VALUES ('28', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:20', '5', null, 'DO201902131737205394905559', null);
INSERT INTO `his_dopay_info` VALUES ('29', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:20', '5', null, 'DO201902131737207469638998', null);
INSERT INTO `his_dopay_info` VALUES ('30', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:20', '5', null, 'DO201902131737209143201241', null);
INSERT INTO `his_dopay_info` VALUES ('31', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:21', '5', null, 'DO201902131737210987075210', null);
INSERT INTO `his_dopay_info` VALUES ('32', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:25', '5', null, 'DO201902131737250692670755', null);
INSERT INTO `his_dopay_info` VALUES ('33', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:25', '5', null, 'DO201902131737255627637769', null);
INSERT INTO `his_dopay_info` VALUES ('34', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:25', '5', null, 'DO201902131737257549447457', null);
INSERT INTO `his_dopay_info` VALUES ('35', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:25', '5', null, 'DO201902131737259476028483', null);
INSERT INTO `his_dopay_info` VALUES ('36', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:26', '5', null, 'DO201902131737261474657240', null);
INSERT INTO `his_dopay_info` VALUES ('37', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:26', '5', null, 'DO201902131737263397339975', null);
INSERT INTO `his_dopay_info` VALUES ('38', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:26', '5', null, 'DO201902131737265159801366', null);
INSERT INTO `his_dopay_info` VALUES ('39', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:26', '5', null, 'DO201902131737267236900740', null);
INSERT INTO `his_dopay_info` VALUES ('40', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:53', '5', null, 'DO201902131737537708833836', null);
INSERT INTO `his_dopay_info` VALUES ('41', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:54', '5', null, 'DO201902131737545949405779', null);
INSERT INTO `his_dopay_info` VALUES ('42', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:54', '5', null, 'DO201902131737549399443270', null);
INSERT INTO `his_dopay_info` VALUES ('43', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:55', '5', null, 'DO201902131737551387930797', null);
INSERT INTO `his_dopay_info` VALUES ('44', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:37:55', '5', null, 'DO201902131737553373098683', null);
INSERT INTO `his_dopay_info` VALUES ('45', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:38:29', '5', null, 'DO201902131738293477846940', null);
INSERT INTO `his_dopay_info` VALUES ('46', '', '', 'T0000053,T0000052@999999992', null, '0.17', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-13 17:39:13', '5', null, 'DO201902131739130751445782', null);
INSERT INTO `his_dopay_info` VALUES ('47', '', '', 'T0000060,T0000063,T0000061@999999992', null, '223.46', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-14 10:52:14', '5', null, 'DO201902141052144562120588', null);
INSERT INTO `his_dopay_info` VALUES ('48', '', '', 'T0000060,T0000063,T0000061@999999992', null, '223.46', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-14 15:28:24', '5', null, 'DO201902141528241799380939', null);
INSERT INTO `his_dopay_info` VALUES ('49', '', '', 'T0000056,T0000057,T0000059,T0000055,T0000054,T0000058@999999991', null, '129.66', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-14 16:40:07', '5', null, 'DO201902141640076822745617', null);
INSERT INTO `his_dopay_info` VALUES ('50', '', '', 'T0000060,T0000063,T0000061@999999992', null, '223.46', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-14 16:42:38', '5', null, 'DO201902141642381034086346', null);
INSERT INTO `his_dopay_info` VALUES ('51', '', '', 'T0000056,T0000057,T0000059,T0000055,T0000054,T0000058@999999991', null, '129.66', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-14 16:47:37', '5', '2', 'DO201902141647371092913846', '4200000269201902142486217966');
INSERT INTO `his_dopay_info` VALUES ('52', '', '', 'T0000060,T0000063,T0000061@999999992', null, '223.46', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-14 17:22:16', '5', '0', 'DO201902141722167531931672', null);
INSERT INTO `his_dopay_info` VALUES ('53', '', '', 'T0000060,T0000063,T0000061@999999992', null, '223.46', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-02-14 18:08:24', '5', '0', 'DO201902141808240620714347', null);
INSERT INTO `his_dopay_info` VALUES ('54', '', '', 'T0000072,T0000071,T0000070@999999992', null, '12.65', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-01 16:12:11', '5', '1', 'DO201903011612114570627643', '4200000286201903014071923555');
INSERT INTO `his_dopay_info` VALUES ('55', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:52:21', '5', '0', 'DO201903081552215650417793', null);
INSERT INTO `his_dopay_info` VALUES ('56', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:28', '5', '0', 'DO201903081557286587820104', null);
INSERT INTO `his_dopay_info` VALUES ('57', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:30', '5', '0', 'DO201903081557304146898754', null);
INSERT INTO `his_dopay_info` VALUES ('58', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:33', '5', '0', 'DO201903081557335523332193', null);
INSERT INTO `his_dopay_info` VALUES ('59', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:33', '5', '0', 'DO201903081557338855284063', null);
INSERT INTO `his_dopay_info` VALUES ('60', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:34', '5', '0', 'DO201903081557341664984375', null);
INSERT INTO `his_dopay_info` VALUES ('61', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:34', '5', '0', 'DO201903081557346873270297', null);
INSERT INTO `his_dopay_info` VALUES ('62', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:34', '5', '0', 'DO201903081557349094517831', null);
INSERT INTO `his_dopay_info` VALUES ('63', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:35', '5', '0', 'DO201903081557353101932918', null);
INSERT INTO `his_dopay_info` VALUES ('64', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:35', '5', '0', 'DO201903081557355339682509', null);
INSERT INTO `his_dopay_info` VALUES ('65', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:37', '5', '0', 'DO201903081557370132992397', null);
INSERT INTO `his_dopay_info` VALUES ('66', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:37', '5', '0', 'DO201903081557371981115581', null);
INSERT INTO `his_dopay_info` VALUES ('67', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:37', '5', '0', 'DO201903081557373926653358', null);
INSERT INTO `his_dopay_info` VALUES ('68', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 15:57:37', '5', '0', 'DO201903081557376470562223', null);
INSERT INTO `his_dopay_info` VALUES ('69', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 16:02:09', '5', '0', 'DO201903081602094533173663', null);
INSERT INTO `his_dopay_info` VALUES ('70', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 16:03:21', '5', '0', 'DO201903081603217479438890', null);
INSERT INTO `his_dopay_info` VALUES ('71', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 16:03:54', '5', '0', 'DO201903081603544042780262', null);
INSERT INTO `his_dopay_info` VALUES ('72', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 16:28:07', '5', '0', 'DO201903081628075450242264', null);
INSERT INTO `his_dopay_info` VALUES ('73', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 16:28:42', '5', '0', 'DO201903081628420938520362', null);
INSERT INTO `his_dopay_info` VALUES ('74', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 16:42:31', '5', '0', 'DO201903081642319097440555', null);
INSERT INTO `his_dopay_info` VALUES ('75', '', '', 'T0000085,T0000084,T0000083@999999992', null, '44.90', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-08 16:42:39', '5', '0', 'DO201903081642398627265472', null);
INSERT INTO `his_dopay_info` VALUES ('76', '', '', 'T0000091,T0000090@999999992', null, '0.16', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-11 17:23:23', '5', '1', 'DO201903111723239510286778', '4200000286201903119851219195');
INSERT INTO `his_dopay_info` VALUES ('77', '', '', 'T0000092,T0000093@999999992', null, '0.16', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-11 17:35:29', '5', '1', 'DO201903111735297753102239', '4200000268201903113260534719');
INSERT INTO `his_dopay_info` VALUES ('78', '', '', 'T0000095,T0000094@999999992', null, '0.16', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-11 18:00:34', '5', '1', 'DO201903111800343427917597', '4200000260201903118148204418');
INSERT INTO `his_dopay_info` VALUES ('79', '', '', 'T0000097,T0000096@999999992', null, '0.16', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-11 18:21:44', '5', '0', 'DO201903111821449653880801', null);
INSERT INTO `his_dopay_info` VALUES ('80', '', '', 'T0000097,T0000096@999999992', null, '0.16', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-11 18:22:14', '5', '1', 'DO201903111822144667433021', '4200000264201903118248232055');
INSERT INTO `his_dopay_info` VALUES ('81', '', '', 'T0000098,T0000099@999999992', null, '0.16', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-11 18:27:34', '5', '1', 'DO201903111827345652079985', '4200000284201903113740822372');
INSERT INTO `his_dopay_info` VALUES ('82', '', '', 'T0486149,T0486150@0512460', null, '3.70', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-21 15:39:12', '5', '0', 'DO201903211539124359309501', null);
INSERT INTO `his_dopay_info` VALUES ('83', '', '', 'T0486149,T0486150@0512460', null, '3.70', '', '', '', null, null, null, null, '0', 'wx14095476fefe834e', '2019-03-21 15:39:29', '5', '1', 'DO201903211539295487247415', '4200000274201903219703860808');
INSERT INTO `his_dopay_info` VALUES ('84', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '0', 'wx50617d578b93801d', '2019-06-17 17:44:58', '5', '0', 'DO201906171744584135667277', null);
INSERT INTO `his_dopay_info` VALUES ('85', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '0', 'wx50617d578b93801d', '2019-06-17 17:44:59', '5', '0', 'DO201906171744595997659778', null);
INSERT INTO `his_dopay_info` VALUES ('86', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '0', 'wx50617d578b93801d', '2019-06-17 17:47:19', '5', '0', 'DO201906171747197805473421', null);
INSERT INTO `his_dopay_info` VALUES ('87', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '0', 'wx50617d578b93801d', '2019-06-17 17:50:39', '5', '0', 'DO201906171750398832734243', null);
INSERT INTO `his_dopay_info` VALUES ('88', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:41', '5', '0', 'DO201906191009412486187174', null);
INSERT INTO `his_dopay_info` VALUES ('89', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:43', '5', '0', 'DO201906191009435142360356', null);
INSERT INTO `his_dopay_info` VALUES ('90', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:44', '5', '0', 'DO201906191009440146549249', null);
INSERT INTO `his_dopay_info` VALUES ('91', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:44', '5', '0', 'DO201906191009442321068623', null);
INSERT INTO `his_dopay_info` VALUES ('92', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:44', '5', '0', 'DO201906191009444677375387', null);
INSERT INTO `his_dopay_info` VALUES ('93', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:44', '5', '0', 'DO201906191009446852646288', null);
INSERT INTO `his_dopay_info` VALUES ('94', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:44', '5', '0', 'DO201906191009449042349744', null);
INSERT INTO `his_dopay_info` VALUES ('95', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:45', '5', '0', 'DO201906191009451236825405', null);
INSERT INTO `his_dopay_info` VALUES ('96', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:45', '5', '0', 'DO201906191009453263616600', null);
INSERT INTO `his_dopay_info` VALUES ('97', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:45', '5', '0', 'DO201906191009455148126472', null);
INSERT INTO `his_dopay_info` VALUES ('98', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:46', '5', '0', 'DO201906191009465928954978', null);
INSERT INTO `his_dopay_info` VALUES ('99', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:47', '5', '0', 'DO201906191009470455273610', null);
INSERT INTO `his_dopay_info` VALUES ('100', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:47', '5', '0', 'DO201906191009472321955403', null);
INSERT INTO `his_dopay_info` VALUES ('101', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:47', '5', '0', 'DO201906191009474511730549', null);
INSERT INTO `his_dopay_info` VALUES ('102', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:47', '5', '0', 'DO201906191009476703557293', null);
INSERT INTO `his_dopay_info` VALUES ('103', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:47', '5', '0', 'DO201906191009478895691254', null);
INSERT INTO `his_dopay_info` VALUES ('104', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:48', '5', '0', 'DO201906191009481071138281', null);
INSERT INTO `his_dopay_info` VALUES ('105', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:48', '5', '0', 'DO201906191009483103835317', null);
INSERT INTO `his_dopay_info` VALUES ('106', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:48', '5', '0', 'DO201906191009484981611425', null);
INSERT INTO `his_dopay_info` VALUES ('107', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:48', '5', '0', 'DO201906191009487321238608', null);
INSERT INTO `his_dopay_info` VALUES ('108', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:49', '5', '0', 'DO201906191009493739431405', null);
INSERT INTO `his_dopay_info` VALUES ('109', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:49', '5', '0', 'DO201906191009495605048500', null);
INSERT INTO `his_dopay_info` VALUES ('110', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:49', '5', '0', 'DO201906191009499516278589', null);
INSERT INTO `his_dopay_info` VALUES ('111', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:50', '5', '0', 'DO201906191009501546947827', null);
INSERT INTO `his_dopay_info` VALUES ('112', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:50', '5', '0', 'DO201906191009503423715745', null);
INSERT INTO `his_dopay_info` VALUES ('113', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:51', '5', '0', 'DO201906191009510145369976', null);
INSERT INTO `his_dopay_info` VALUES ('114', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:51', '5', '0', 'DO201906191009514200680015', null);
INSERT INTO `his_dopay_info` VALUES ('115', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:51', '5', '0', 'DO201906191009516548928353', null);
INSERT INTO `his_dopay_info` VALUES ('116', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:52', '5', '0', 'DO201906191009520923883984', null);
INSERT INTO `his_dopay_info` VALUES ('117', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:09:52', '5', '0', 'DO201906191009525292455977', null);
INSERT INTO `his_dopay_info` VALUES ('118', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:10:54', '5', '0', 'DO201906191010544638846642', null);
INSERT INTO `his_dopay_info` VALUES ('119', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:10:55', '5', '0', 'DO201906191010550564782394', null);
INSERT INTO `his_dopay_info` VALUES ('120', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:10:55', '5', '0', 'DO201906191010552911364112', null);
INSERT INTO `his_dopay_info` VALUES ('121', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:43:40', '5', '0', 'DO201906191043408837145018', null);
INSERT INTO `his_dopay_info` VALUES ('122', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:43:42', '5', '0', 'DO201906191043429349897620', null);
INSERT INTO `his_dopay_info` VALUES ('123', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:43:44', '5', '0', 'DO201906191043444203495571', null);
INSERT INTO `his_dopay_info` VALUES ('124', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-19 10:43:44', '5', '0', 'DO201906191043446197072347', null);
INSERT INTO `his_dopay_info` VALUES ('125', '', '', '2019061700011@99201904020005', null, '825.00', '', '', '', null, null, null, null, '1', 'wx50617d578b93801d', '2019-06-21 13:47:31', '5', '0', 'DO201906211347318273444152', null);

-- ----------------------------
-- Table structure for his_doreg_info
-- ----------------------------
DROP TABLE IF EXISTS `his_doreg_info`;
CREATE TABLE `his_doreg_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organdoctor_id` varchar(11) DEFAULT '0' COMMENT '医生编号',
  `card_no` varchar(30) DEFAULT '0' COMMENT '患者身份证号',
  `patient_no` varchar(50) DEFAULT '0' COMMENT '患者编号',
  `socialsecurityNO` varchar(30) DEFAULT '0' COMMENT '社保号',
  `source_date` datetime DEFAULT NULL COMMENT '取号日期（号源日期）',
  `timestype_no` varchar(10) DEFAULT NULL COMMENT '时间段标识',
  `source_time_type` int(10) DEFAULT '0' COMMENT '1,上午，2，下午 3，晚上',
  `pay_no` varchar(10) DEFAULT '0' COMMENT '支付流水号',
  `pay_amount` double(10,2) DEFAULT '0.00' COMMENT '已支金额',
  `app_id` varchar(50) DEFAULT '0' COMMENT '商户ID',
  `creat_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `successful_payment` varchar(10) DEFAULT NULL COMMENT '0支付未成功，1支付成功,  2表示已退款',
  `out_trade_no` varchar(50) DEFAULT NULL COMMENT '商户订单号',
  `transaction_id` varchar(50) DEFAULT NULL COMMENT '微信订单号',
  `departmentorgan_id` varchar(30) DEFAULT NULL COMMENT '科室id',
  `pay_type` varchar(50) DEFAULT '5' COMMENT '支付方式 ',
  `result_msg` varchar(255) DEFAULT NULL COMMENT '返回信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=549 DEFAULT CHARSET=utf8 COMMENT='预约挂号';

-- ----------------------------
-- Records of his_doreg_info
-- ----------------------------
INSERT INTO `his_doreg_info` VALUES ('117', '155', '440822197912151449', '0004139', '', '2019-02-19 00:00:00', '0', '1', null, '6.00', 'wx14095476fefe834e', '2019-02-12 14:14:20', '1', 'RE201902121414208674500105', '4200000282201902124534361139', '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('118', '306', '420527198812104916', '999999992', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 10:22:28', '0', 'RE201902131022284604520240', '4200000273201902131453688814', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('119', '155', '420527198812104916', '999999992', '', '2019-02-19 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 10:56:49', '1', 'RE201902131056499785737729', '4200000267201902134313210340', '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('120', '306', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 11:00:59', '0', 'RE201902131100590496804995', '4200000265201902131280439017', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('121', '306', '420527198812104916', '999999992', '', '2019-02-13 00:00:00', '0', '1', null, '6.00', 'wx14095476fefe834e', '2019-02-13 11:08:23', '0', 'RE201902131108236026427166', '4200000269201902135434479162', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('122', '155', '420527198812104916', '999999992', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 11:14:31', '0', 'RE201902131114317414337755', null, '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('123', '155', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 11:49:24', '0', 'RE201902131149246630859654', '4200000276201902137431568728', '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('124', '155', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 11:52:05', '0', 'RE201902131152057204919113', '4200000285201902138499975087', '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('125', '155', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 14:30:45', '0', 'RE201902131430459008818899', null, '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('126', '155', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 14:30:48', '0', 'RE201902131430480162020471', '4200000261201902139841893651', '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('127', '306', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 15:56:24', '1', 'RE201902131556241404638232', '4200000288201902131747076184', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('128', '306', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 15:57:55', '0', 'RE201902131557554040854669', null, '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('129', '306', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 15:57:57', '0', 'RE201902131557571479474439', '4200000265201902131229077147', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('130', '306', '440822197912151449', '0004139', '', '2019-02-14 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 16:49:40', '1', 'RE201902131649402407503984', '4200000286201902139469429212', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('131', '306', '440822197912151449', '0004139', '', '2019-02-14 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 16:53:30', '1', 'RE201902131653302037348180', '4200000283201902130743196453', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('132', '306', '440822197912151449', '0004139', '', '2019-02-14 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 16:58:34', '1', 'RE201902131658349204813980', '4200000280201902131283985718', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('133', '306', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 17:19:01', '1', 'RE201902131719011280266036', '4200000283201902134636632107', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('134', '306', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 17:19:50', '0', 'RE201902131719504339159381', '4200000281201902132392419834', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('135', '306', '440822197912151449', '0004139', '', '2019-02-14 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 18:03:33', '1', 'RE201902131803335393478987', '4200000268201902139918810278', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('136', '306', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 18:05:24', '0', 'RE201902131805249838339937', '4200000285201902132828479593', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('137', '306', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 18:34:39', '0', 'RE201902131834396237860593', '4200000284201902131506420836', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('138', '306', '440822197912151449', '0004139', '', '2019-02-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-13 19:09:35', '0', 'RE201902131909356958652287', '4200000271201902138697761373', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('139', '306', '440822197912151449', '0004139', '', '2019-02-14 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-14 09:33:15', '1', 'RE201902140933151108977041', '4200000281201902147912931968', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('140', '306', '440822197912151449', '0004139', '', '2019-02-14 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-14 09:33:55', '0', 'RE201902140933554324797541', '4200000279201902142839155071', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('141', '306', '440822197912151449', '0004139', '', '2019-02-14 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-14 09:36:49', '0', 'RE201902140936491116015439', '4200000274201902145114416048', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('142', '306', '440822197912151449', '0004139', '', '2019-02-16 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-14 09:51:06', '1', 'RE201902140951061652879267', '4200000273201902140257268277', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('143', '306', '440822197912151449', '0004139', '', '2019-02-15 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-14 09:51:32', '1', 'RE201902140951323889868394', '4200000271201902147766695157', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('144', '306', '440822197912151449', '0004139', '', '2019-02-16 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-14 09:52:37', '0', 'RE201902140952372784722094', '4200000259201902142808844554', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('145', '306', '440822197912151449', '0004139', '', '2019-02-16 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-14 09:56:11', '0', 'RE201902140956115053291364', '4200000281201902143446427753', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('146', '306', '440822197912151449', '0004139', '', '2019-02-16 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-14 10:03:47', '0', 'RE201902141003471965279834', '4200000272201902148372828449', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('147', '306', '440822197912151449', '0004139', '', '2019-02-15 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-14 10:16:47', '2', 'RE201902141016478885787277', '4200000286201902142546399559', '1342', '5', null);
INSERT INTO `his_doreg_info` VALUES ('148', '4643', '420527198812104916', '999999992', '', '2019-02-14 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-14 10:37:59', '2', 'RE201902141037593362781151', '4200000279201902149011128681', '1002', '5', null);
INSERT INTO `his_doreg_info` VALUES ('149', '1941', '420527198812104916', '999999992', '', '2019-02-14 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-14 10:50:33', '2', 'RE201902141050335506200391', '4200000261201902143134234690', '124', '5', null);
INSERT INTO `his_doreg_info` VALUES ('150', '4643', '440822197912151449', '0004139', '', '2019-02-15 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-14 11:21:10', '1', 'RE201902141121107785735707', '4200000260201902141114958747', '822', '5', null);
INSERT INTO `his_doreg_info` VALUES ('151', '4643', '440822197912151449', '0004139', '', '2019-02-14 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 11:21:48', '1', 'RE201902141121484330859341', '4200000277201902149856778170', '822', '5', null);
INSERT INTO `his_doreg_info` VALUES ('152', '263', '44088119911115514X', '0437731', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 18:17:47', '0', 'RE201902141817478640145012', null, '106', '5', null);
INSERT INTO `his_doreg_info` VALUES ('153', '263', '44088119911115514X', '0437731', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 18:18:08', '0', 'RE201902141818085537834365', null, '106', '5', null);
INSERT INTO `his_doreg_info` VALUES ('154', '263', '44088119911115514X', '0437731', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 18:18:46', '1', 'RE201902141818461035732575', '4200000266201902147595955021', '106', '5', null);
INSERT INTO `his_doreg_info` VALUES ('155', '263', '44088119911115514X', '0437731', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 18:19:09', '0', 'RE201902141819098313043413', null, '106', '5', null);
INSERT INTO `his_doreg_info` VALUES ('156', '155', '', '0383471', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 19:23:12', '0', 'RE201902141923128730782348', null, '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('157', '155', '', '0383471', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 19:23:26', '2', 'RE201902141923269953048196', '4200000287201902142609932105', '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('158', '155', '', '0383471', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 19:24:20', '0', 'RE201902141924206650770375', null, '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('159', '155', '', '0383471', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 19:26:51', '0', 'RE201902141926512789702609', null, '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('160', '155', '', '0383471', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 19:33:39', '0', 'RE201902141933399400411925', null, '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('161', '155', '', '0383471', '', '2019-02-14 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 21:21:51', '0', 'RE201902142121513504808726', null, '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('162', '260', '', '0383471', '', '2019-02-15 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-14 21:22:36', '2', 'RE201902142122362329100742', '4200000286201902147149857966', '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('163', '4643', '420527198812104916', '999999992', '', '2019-02-16 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-16 09:50:10', '2', 'RE201902160950107474021180', '4200000284201902168654658645', '1002', '5', null);
INSERT INTO `his_doreg_info` VALUES ('164', '1941', '420527198812104916', '999999992', '', '2019-02-17 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-16 09:51:19', '2', 'RE201902160951190800170215', '4200000280201902165030559057', '124', '5', null);
INSERT INTO `his_doreg_info` VALUES ('165', '1941', '420527198812104916', '999999992', '', '2019-02-18 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-16 09:52:26', '2', 'RE201902160952268545152194', '4200000285201902169539311650', '124', '5', null);
INSERT INTO `his_doreg_info` VALUES ('166', '155', '440822197912151449', '0004139', '', '2019-02-17 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-16 10:51:20', '1', 'RE201902161051205419659480', '4200000279201902168787738024', '105', '5', null);
INSERT INTO `his_doreg_info` VALUES ('167', '4643', '440822197912151449', '0004139', '', '2019-02-16 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-16 10:58:25', '1', 'RE201902161058259861928331', '4200000263201902161348743511', '822', '5', null);
INSERT INTO `his_doreg_info` VALUES ('168', '1941', '420527198812104916', '999999992', '', '2019-02-20 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-20 16:35:07', '0', 'RE201902201635073976486197', null, '124', '9', null);
INSERT INTO `his_doreg_info` VALUES ('169', '1941', '420527198812104916', '999999992', '', '2019-02-20 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-20 16:59:52', '2', 'RE201902201659522336142769', '4200000264201902205798275059', '124', '9', '挂号费0,与支付金额6不一致');
INSERT INTO `his_doreg_info` VALUES ('170', '306', '440822197912151449', '0004139', '', '2019-02-21 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-20 17:51:13', '1', 'RE201902201751131293189785', '4200000264201902207441863969', '1342', '9', null);
INSERT INTO `his_doreg_info` VALUES ('171', '306', '440822197912151449', '0004139', '', '2019-02-20 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-20 17:54:47', '1', 'RE201902201754473677115358', '4200000259201902200715712002', '1342', '9', null);
INSERT INTO `his_doreg_info` VALUES ('172', '1941', '420527198812104916', '999999992', '', '2019-02-20 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-20 17:59:09', '2', 'RE201902201759094963951730', '4200000270201902201205545253', '124', '9', '挂号费0,与支付金额6不一致');
INSERT INTO `his_doreg_info` VALUES ('173', '1941', '450121198805095133', '999999991', '', '2019-02-22 00:00:00', '0', '1', null, '0.01', 'wx14095476fefe834e', '2019-02-22 09:39:28', '2', 'RE201902220939282396124909', '4200000267201902223888925519', '124', '9', '挂号费0,与支付金额6不一致');
INSERT INTO `his_doreg_info` VALUES ('174', '1941', '420527198812104916', '999999992', '', '2019-02-23 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-22 09:40:07', '2', 'RE201902220940070688718350', '4200000265201902225740891009', '124', '9', '挂号费0,与支付金额6不一致');
INSERT INTO `his_doreg_info` VALUES ('175', '1941', '420527198812104916', '999999992', '', '2019-02-23 00:00:00', '0', '2', null, '0.01', 'wx14095476fefe834e', '2019-02-22 09:40:16', '0', 'RE201902220940166152775492', null, '124', '9', null);
INSERT INTO `his_doreg_info` VALUES ('176', '306', '450121198805095133', '999999991', '', '2019-02-23 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-23 20:01:32', '2', 'RE201902232001328471667092', '4200000266201902234248285303', '1002', '9', '未查询到挂号信息！');
INSERT INTO `his_doreg_info` VALUES ('177', '155', '450121198805095133', '999999991', '', '2019-02-23 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-23 20:02:35', '1', 'RE201902232002357850850818', '4200000266201902233631609819', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('178', '4643', '450121198805095133', '999999991', '', '2019-02-24 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-23 20:04:22', '1', 'RE201902232004225531402994', '4200000286201902237414255097', '822', '9', null);
INSERT INTO `his_doreg_info` VALUES ('179', '1941', '450121198805095133', '999999991', '', '2019-02-25 00:00:00', '0', '2', null, '0.00', 'wx14095476fefe834e', '2019-02-25 09:09:59', '1', 'RE201902250909597117242093', '4200000285201902259115393601', '124', '9', null);
INSERT INTO `his_doreg_info` VALUES ('180', '306', '450121198805095133', '999999991', '', '2019-02-25 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-25 09:30:41', '2', 'RE201902250930413097111506', '4200000261201902259632045329', '1002', '9', '未查询到挂号信息！');
INSERT INTO `his_doreg_info` VALUES ('181', '306', '420527198812104916', '999999992', '', '2019-02-25 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-25 10:12:54', '1', 'RE201902251012547247105254', '4200000284201902259560201076', '1342', '9', null);
INSERT INTO `his_doreg_info` VALUES ('182', '306', '440822197912151449', '0004139', '', '2019-02-26 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-25 10:13:19', '1', 'RE201902251013195052055552', '4200000275201902250250604461', '1342', '9', null);
INSERT INTO `his_doreg_info` VALUES ('183', '155', '450121198805095133', '999999991', '', '2019-02-28 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-02-28 22:04:56', '0', 'RE201902282204565560618314', null, '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('184', '155', '450121198805095133', '999999991', '', '2019-03-01 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-01 15:29:05', '1', 'RE201903011529057949822238', '4200000268201903017151483124', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('185', '155', '440822197912151449', '0004139', '', '2019-03-01 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-01 15:35:08', '0', 'RE201903011535087474529238', null, '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('186', '306', '420527198812104916', '999999992', '', '2019-03-01 00:00:00', '0', '1', null, '6.00', 'wx14095476fefe834e', '2019-03-01 15:38:12', '1', 'RE201903011538122096206527', '4200000287201903013557836838', '1342', '9', null);
INSERT INTO `his_doreg_info` VALUES ('187', '306', '450121198805095133', '999999991', '', '2019-03-08 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-01 15:51:11', '1', 'RE201903011551113170619581', '4200000261201903016150725716', '1342', '9', null);
INSERT INTO `his_doreg_info` VALUES ('188', '155', '450121198805095133', '999999991', '', '2019-03-02 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-02 21:24:59', '1', 'RE201903022124597838145444', '4200000260201903027441059404', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('189', '306', '450121198805095133', '999999991', '', '2019-03-04 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-04 22:30:02', '1', 'RE201903042230020272436709', '4200000273201903045576800044', '1342', '9', null);
INSERT INTO `his_doreg_info` VALUES ('190', '155', '450121198805095133', '999999991', '', '2019-03-06 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-04 22:30:47', '1', 'RE201903042230473224682485', '4200000259201903042877047300', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('191', '155', '440822197912151449', '0004139', '', '2019-03-08 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-08 15:28:06', '1', 'RE201903081528063062471649', '4200000276201903080480574803', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('192', '155', '440822197912151449', '0004139', '', '2019-03-15 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-08 15:29:54', '1', 'RE201903081529544170497283', '4200000269201903089357308464', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('193', '155', '450121198805095133', '999999991', '', '2019-03-12 00:00:00', '0', '1', null, '6.00', 'wx14095476fefe834e', '2019-03-08 16:43:12', '1', 'RE201903081643125046077252', '4200000272201903088593917958', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('194', '155', '420527198812104916', '999999992', '', '2019-03-08 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-08 16:46:52', '0', 'RE201903081646525381857926', null, '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('195', '155', '420527198812104916', '999999992', '', '2019-03-08 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-08 16:47:11', '0', 'RE201903081647119456593603', null, '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('196', '155', '440822197912151449', '0004139', '', '2019-03-12 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-10 18:02:38', '0', 'RE201903101802380822823725', null, '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('197', '155', '440822197912151449', '0004139', '', '2019-03-12 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-10 18:02:40', '0', 'RE201903101802400500457262', null, '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('198', '155', '440822197912151449', '0004139', '', '2019-03-12 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-10 18:03:07', '0', 'RE201903101803072858800562', null, '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('199', '155', '420527198812104916', '999999992', '', '2019-03-11 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-11 15:01:25', '1', 'RE201903111501256505720212', '4200000262201903113958143343', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('200', '155', '420527198812104916', '999999992', '', '2019-03-15 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-11 15:04:52', '1', 'RE201903111504523411313164', '4200000269201903111622295881', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('201', '155', '420527198812104916', '999999992', '', '2019-03-11 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-11 15:19:31', '0', 'RE201903111519317026463521', null, '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('202', '155', '420527198812104916', '999999992', '', '2019-03-11 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-11 15:20:10', '1', 'RE201903111520103124174314', '4200000285201903113752696402', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('203', '306', '420527198812104916', '999999992', '', '2019-03-11 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-11 16:23:05', '1', 'RE201903111623050038631706', '4200000264201903117057003220', '1342', '9', null);
INSERT INTO `his_doreg_info` VALUES ('204', '4643', '420527198812104916', '999999992', '', '2019-03-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-11 16:53:17', '0', 'RE201903111653176358756204', null, '1002', '9', null);
INSERT INTO `his_doreg_info` VALUES ('205', '4643', '420527198812104916', '999999992', '', '2019-03-13 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-11 16:53:42', '1', 'RE201903111653428395552475', '4200000261201903111423399552', '822', '9', null);
INSERT INTO `his_doreg_info` VALUES ('206', '290', '', '0535254', '', '2019-03-16 00:00:00', '0', '1', null, '15.00', 'wx14095476fefe834e', '2019-03-16 09:34:49', '1', 'RE201903160934495568742609', '4200000287201903166571947359', '1002', '9', null);
INSERT INTO `his_doreg_info` VALUES ('207', '294', '', '0383471', '', '2019-03-18 00:00:00', '0', '2', null, '15.00', 'wx14095476fefe834e', '2019-03-17 19:37:34', '1', 'RE201903171937340841867891', '4200000266201903174687064729', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('208', '290', '未办', '0533203', '', '2019-03-18 00:00:00', '0', '1', null, '15.00', 'wx14095476fefe834e', '2019-03-18 12:56:10', '0', 'RE201903181256102409867411', null, '1002', '9', null);
INSERT INTO `his_doreg_info` VALUES ('209', '290', '', '0535022', '', '2019-03-21 00:00:00', '0', '1', null, '15.00', 'wx14095476fefe834e', '2019-03-20 11:28:38', '2', 'RE201903201128384889148302', '4200000270201903204823774153', '822', '9', '未查询到挂号信息！');
INSERT INTO `his_doreg_info` VALUES ('210', '290', '', '0535022', '', '2019-03-21 00:00:00', '0', '1', null, '15.00', 'wx14095476fefe834e', '2019-03-20 13:09:33', '2', 'RE201903201309339981797339', '4200000260201903206842872815', '822', '9', '未查询到挂号信息！');
INSERT INTO `his_doreg_info` VALUES ('211', '176', '', '0535254', '', '2019-03-20 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-20 14:54:34', '1', 'RE201903201454344807480185', '4200000271201903202213868068', '1002', '9', null);
INSERT INTO `his_doreg_info` VALUES ('212', '287', '440181199505284544', '0512460', '', '2019-03-21 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-21 11:40:19', '1', 'RE201903211140199671426852', '4200000275201903212343693636', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('213', '176', '440181199505284544', '0512460', '', '2019-03-25 00:00:00', '0', '1', null, '6.00', 'wx14095476fefe834e', '2019-03-21 13:55:56', '1', 'RE201903211355561525602455', '4200000288201903212186500331', '1002', '9', null);
INSERT INTO `his_doreg_info` VALUES ('214', '290', '440181199505284544', '0512460', '', '2019-03-21 00:00:00', '0', '2', null, '15.00', 'wx14095476fefe834e', '2019-03-21 14:20:17', '0', 'RE201903211420179810391269', null, '1002', '9', null);
INSERT INTO `his_doreg_info` VALUES ('215', '745', '440181199505284544', '0512460', '', '2019-03-21 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-21 14:40:24', '0', 'RE201903211440240547659344', null, '106', '9', null);
INSERT INTO `his_doreg_info` VALUES ('216', '745', '440181199505284544', '0512460', '', '2019-03-21 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-21 14:40:48', '1', 'RE201903211440488651396924', '4200000281201903214177190499', '106', '9', null);
INSERT INTO `his_doreg_info` VALUES ('217', '286', '440181199505284544', '0512460', '', '2019-03-21 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-21 15:21:03', '1', 'RE201903211521037034136853', '4200000287201903211579474531', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('218', '322', '440181199505284544', '0512460', '', '2019-03-21 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-21 16:07:26', '1', 'RE201903211607260721928914', '4200000287201903217687559614', '106', '9', null);
INSERT INTO `his_doreg_info` VALUES ('219', '378', '440881199810101626', '0477324', '', '2019-03-23 00:00:00', '0', '2', null, '15.00', 'wx14095476fefe834e', '2019-03-22 10:46:48', '1', 'RE201903221046486023263432', '4200000268201903222885081804', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('220', '176', '', '0538275', '', '2019-03-23 00:00:00', '0', '1', null, '6.00', 'wx14095476fefe834e', '2019-03-23 08:59:56', '1', 'RE201903230859568238683833', '4200000283201903236756399573', '1002', '9', null);
INSERT INTO `his_doreg_info` VALUES ('221', '290', '', '0537279', '', '2019-03-25 00:00:00', '0', '1', null, '15.00', 'wx14095476fefe834e', '2019-03-25 12:14:03', '2', 'RE201903251214039055026349', '4200000269201903250810834718', '1002', '9', '当前号源已无效请更换时间');
INSERT INTO `his_doreg_info` VALUES ('222', '290', '未办', '0533203', '', '2019-03-26 00:00:00', '0', '1', null, '15.00', 'wx14095476fefe834e', '2019-03-26 03:20:59', '0', 'RE201903260320592876328679', null, '1002', '9', null);
INSERT INTO `his_doreg_info` VALUES ('223', '281', '450121198805095111', '0514551', '', '2019-03-27 00:00:00', '0', '2', null, '6.00', 'wx14095476fefe834e', '2019-03-27 10:00:18', '1', 'RE201903271000180055596153', '4200000284201903274798194074', '105', '9', null);
INSERT INTO `his_doreg_info` VALUES ('224', '4734', '430223199509062314', '99201904020006', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 14:21:59', '0', 'RE201904121421599690260068', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('225', '4734', '430223199509062314', '99201904020006', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 14:22:02', '0', 'RE201904121422020344667446', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('226', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:13', '0', 'RE201904121625133932632399', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('227', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:15', '0', 'RE201904121625154030837047', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('228', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:17', '0', 'RE201904121625172888689342', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('229', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:17', '0', 'RE201904121625178257570246', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('230', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:18', '0', 'RE201904121625180201360187', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('231', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:18', '0', 'RE201904121625181951907633', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('232', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:18', '0', 'RE201904121625183886974813', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('233', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:18', '0', 'RE201904121625185855033164', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('234', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:18', '0', 'RE201904121625187664266249', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('235', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:18', '0', 'RE201904121625189316592657', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('236', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:19', '0', 'RE201904121625191156697887', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('237', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:19', '0', 'RE201904121625192791454247', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('238', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:19', '0', 'RE201904121625194771829906', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('239', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:19', '0', 'RE201904121625196520790739', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('240', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:20', '0', 'RE201904121625200868263705', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('241', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:20', '0', 'RE201904121625202800015621', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('242', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:20', '0', 'RE201904121625204477421107', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('243', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:20', '0', 'RE201904121625206409222002', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('244', '4734', '430223199609164578', '99201904020004', '', null, '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-12 16:25:20', '0', 'RE201904121625207546551341', null, '6', '9', null);
INSERT INTO `his_doreg_info` VALUES ('245', '4835', '430223199809064571', '99201904120001', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 09:46:55', '0', 'RE201904150946559095099960', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('246', '4835', '430223199809064571', '99201904120001', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 09:47:19', '0', 'RE201904150947194776320104', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('247', '4835', '430223199809064571', '99201904120001', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 09:47:22', '0', 'RE201904150947228938472944', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('248', '4835', '430223199809064571', '99201904120001', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 09:47:12', '0', 'RE201904150947121660617947', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('249', '4835', '430223199809064571', '99201904120001', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 09:47:15', '0', 'RE201904150947157346018691', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('250', '4835', '430223199809064571', '99201904120001', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 09:47:15', '0', 'RE201904150947157344856187', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('251', '4835', '430223199809064571', '99201904120001', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 09:47:15', '0', 'RE201904150947158178110629', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('252', '4835', '430223199809064571', '99201904120001', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 09:47:16', '0', 'RE201904150947165349418887', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('253', '4835', '430223199609064824', '99201903280005', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 10:02:00', '0', 'RE201904151002001331621861', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('254', '4835', '430223199609064824', '99201903280005', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-15 11:41:10', '0', 'RE201904151141102313817839', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('255', '4835', '430223199609064824', '99201903280005', '', '2019-04-17 00:00:00', '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-16 16:09:29', '0', 'RE201904161609291048089840', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('256', '4835', '430223199509062314', '99201904020006', '', '2019-04-17 00:00:00', '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-16 16:10:29', '0', 'RE201904161610290422271348', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('257', '4835', '430223199609064824', '99201903280005', '', '2019-04-17 00:00:00', '0', '3', null, '0.01', 'wx50617d578b93801d', '2019-04-16 16:48:09', '0', 'RE201904161648099133747397', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('258', '4835', '430223199609064824', '99201903280005', '', null, '0', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-17 09:01:50', '0', 'RE201904170901500761364899', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('259', '4835', '430223199609261245', '99201904020003', '', '2019-04-20 00:00:00', null, '1', null, '0.01', 'wx50617d578b93801d', '2019-04-17 17:04:20', '0', 'RE201904171704203937718240', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('260', '4835', '430223199609261245', '99201904020003', '', '2019-04-23 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-04-23 14:47:17', '0', 'RE201904231447176388734628', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('261', '4835', '430223199609261245', '99201904020003', '', '2019-04-23 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-04-23 14:47:21', '0', 'RE201904231447214857561940', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('262', '4867', '430223199609261245', '99201904020003', '', '2019-05-01 00:00:00', 'Z977377', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-24 14:08:56', '0', 'RE201904241408561251526187', null, '21', '9', null);
INSERT INTO `his_doreg_info` VALUES ('263', '4835', '430223199609064824', '99201903280005', '', '2019-04-27 00:00:00', 'Z446026', '1', null, '0.01', 'wx50617d578b93801d', '2019-04-26 09:59:29', '0', 'RE201904260959298623782506', null, '4', '9', null);
INSERT INTO `his_doreg_info` VALUES ('264', '4731', '430223199609064824', '99201903280005', '', '2019-05-05 00:00:00', null, '1', null, '0.01', 'wx50617d578b93801d', '2019-05-05 09:35:27', '0', 'RE201905050935279671202522', null, '8', '5', null);
INSERT INTO `his_doreg_info` VALUES ('265', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:47:22', '0', 'RE201905171547222175013816', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('266', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:47:25', '0', 'RE201905171547252767119019', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('267', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:39', '0', 'RE201905171553392668693879', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('268', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:42', '0', 'RE201905171553422812633678', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('269', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:43', '0', 'RE201905171553430789991217', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('270', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:43', '0', 'RE201905171553435940827906', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('271', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:43', '0', 'RE201905171553438132547829', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('272', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:43', '0', 'RE201905171553439221718172', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('273', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:44', '0', 'RE201905171553441254010926', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('274', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:44', '0', 'RE201905171553442660159056', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('275', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:44', '0', 'RE201905171553444535424541', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('276', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:44', '0', 'RE201905171553446411543865', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('277', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:44', '0', 'RE201905171553448282090370', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('278', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:45', '0', 'RE201905171553452199768081', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('279', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:45', '0', 'RE201905171553453753174370', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('280', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:45', '0', 'RE201905171553455636350795', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('281', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:45', '0', 'RE201905171553457811732687', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('282', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:45', '0', 'RE201905171553459534511498', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('283', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:46', '0', 'RE201905171553461412696501', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('284', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:46', '0', 'RE201905171553462978625322', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('285', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:46', '0', 'RE201905171553465329346633', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('286', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:46', '0', 'RE201905171553466886752261', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('287', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:46', '0', 'RE201905171553468915522112', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('288', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:47', '0', 'RE201905171553470476605487', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('289', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:47', '0', 'RE201905171553472504565245', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('290', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:47', '0', 'RE201905171553474062477424', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('291', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:47', '0', 'RE201905171553475941114315', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('292', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:48', '0', 'RE201905171553480475815769', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('293', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:48', '0', 'RE201905171553482507777481', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('294', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:48', '0', 'RE201905171553484224044321', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('295', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:48', '0', 'RE201905171553486257411135', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('296', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:48', '0', 'RE201905171553488603762366', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('297', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:48', '0', 'RE201905171553489853878255', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('298', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:49', '0', 'RE201905171553491882370414', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('299', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:49', '0', 'RE201905171553494074956495', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('300', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:49', '0', 'RE201905171553496103116208', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('301', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:49', '0', 'RE201905171553497502462822', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('302', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:49', '0', 'RE201905171553499380794480', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('303', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:50', '0', 'RE201905171553505780100561', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('304', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:50', '0', 'RE201905171553507823984409', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('305', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:50', '0', 'RE201905171553509384788821', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('306', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:51', '0', 'RE201905171553511256287245', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('307', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:51', '0', 'RE201905171553512975476094', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('308', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:51', '0', 'RE201905171553515008055492', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('309', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:51', '0', 'RE201905171553516720095157', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('310', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:51', '0', 'RE201905171553519079241082', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('311', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:52', '0', 'RE201905171553520477981861', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('312', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:52', '0', 'RE201905171553522822189125', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('313', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:52', '0', 'RE201905171553524541546046', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('314', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:52', '0', 'RE201905171553526574589376', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('315', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:52', '0', 'RE201905171553528286310520', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('316', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:53', '0', 'RE201905171553530165448975', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('317', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:53', '0', 'RE201905171553532194005457', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('318', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:53', '0', 'RE201905171553534224556303', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('319', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:53', '0', 'RE201905171553536251028675', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('320', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:53', '0', 'RE201905171553538135224548', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('321', '4835', '430223199805124561', '99201904020005', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:55', '0', 'RE201905171553558914675389', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('322', '4835', '430223199805124561', '99201904020005', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:56', '0', 'RE201905171553561109472415', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('323', '4835', '430223199805124561', '99201904020005', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:53:56', '0', 'RE201905171553562503326549', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('324', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:53', '0', 'RE201905171554536406500502', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('325', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:57', '0', 'RE201905171554573235821771', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('326', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:57', '0', 'RE201905171554575246725014', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('327', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:57', '0', 'RE201905171554576918281139', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('328', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:57', '0', 'RE201905171554579401877615', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('329', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:58', '0', 'RE201905171554583308499122', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('330', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:58', '0', 'RE201905171554585001645475', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('331', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:58', '0', 'RE201905171554586839280388', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('332', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:58', '0', 'RE201905171554588600348647', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('333', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:54:59', '0', 'RE201905171554590438999201', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('334', '4835', '430223199805124561', '99201904020005', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:17', '0', 'RE201905171555172997905047', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('335', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:33', '0', 'RE201905171555339097342381', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('336', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:34', '0', 'RE201905171555344717436557', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('337', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:34', '0', 'RE201905171555346740028256', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('338', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:34', '0', 'RE201905171555349099028139', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('339', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:35', '0', 'RE201905171555350492226492', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('340', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:35', '0', 'RE201905171555352847964487', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('341', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:35', '0', 'RE201905171555354404434042', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('342', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:35', '0', 'RE201905171555356594851598', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('343', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:35', '0', 'RE201905171555358151110375', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('344', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:36', '0', 'RE201905171555360181198016', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('345', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:36', '0', 'RE201905171555362056848927', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('346', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:36', '0', 'RE201905171555364401211935', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('347', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:36', '0', 'RE201905171555366902535338', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('348', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:36', '0', 'RE201905171555368934993542', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('349', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:38', '0', 'RE201905171555380027809529', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('350', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:38', '0', 'RE201905171555381904330820', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('351', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:38', '0', 'RE201905171555383939989143', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('352', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:38', '0', 'RE201905171555386123901725', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('353', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:39', '0', 'RE201905171555390969889060', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('354', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:39', '0', 'RE201905171555393157347208', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('355', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:39', '0', 'RE201905171555398774826261', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('356', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:40', '0', 'RE201905171555401123387460', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('357', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:40', '0', 'RE201905171555403156450221', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('358', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:44', '0', 'RE201905171555445188516027', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('359', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:52', '0', 'RE201905171555526453582096', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('360', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:53', '0', 'RE201905171555531468774083', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('361', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:55:53', '0', 'RE201905171555533070958666', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('362', '4835', '430223199609164578', '99201904020004', '', '2019-05-17 00:00:00', null, '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 15:56:43', '0', 'RE201905171556439491201062', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('363', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 16:15:39', '0', 'RE201905171615399032453393', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('364', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 16:15:43', '0', 'RE201905171615430135908900', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('365', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 16:15:43', '0', 'RE201905171615437780686862', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('366', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 16:15:43', '0', 'RE201905171615439975804232', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('367', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 16:15:44', '0', 'RE201905171615441851364897', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('368', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 16:15:44', '0', 'RE201905171615443573899150', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('369', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 16:15:44', '0', 'RE201905171615445604083235', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('370', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 16:15:44', '0', 'RE201905171615447005432128', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('371', '4835', '430223199805124561', '99201904020005', '', '2019-05-24 00:00:00', 'Z446022', '3', null, '0.01', 'wx50617d578b93801d', '2019-05-17 16:15:44', '0', 'RE201905171615449036091864', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('372', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:35', '0', 'RE201905281444354029213858', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('373', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:43', '0', 'RE201905281444439333065127', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('374', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:45', '0', 'RE201905281444452775849210', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('375', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:47', '0', 'RE201905281444475129334925', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('376', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:48', '0', 'RE201905281444480274835677', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('377', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:48', '0', 'RE201905281444481991499385', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('378', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:48', '0', 'RE201905281444483876480465', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('379', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:48', '0', 'RE201905281444485435316223', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('380', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:48', '0', 'RE201905281444487461288620', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('381', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:48', '0', 'RE201905281444489188495738', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('382', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:49', '0', 'RE201905281444492627843182', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('383', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:49', '0', 'RE201905281444494337649959', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('384', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:44:49', '0', 'RE201905281444495907374678', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('385', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:51:30', '0', 'RE201905281451308584167430', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('386', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:51:38', '0', 'RE201905281451382802037183', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('387', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:51:40', '0', 'RE201905281451404056198787', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('388', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 14:51:41', '0', 'RE201905281451414374160275', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('389', '4835', '430223199805124561', '99201904020005', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:05:16', '0', 'RE201905281505160502007870', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('390', '4835', '430223199805124561', '99201904020005', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:05:21', '0', 'RE201905281505212850390369', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('391', '4835', '430223199805124561', '99201904020005', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:05:22', '0', 'RE201905281505225972061515', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('392', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:26:17', '0', 'RE201905281526173185902752', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('393', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:26:20', '0', 'RE201905281526203814077691', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('394', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:26:21', '0', 'RE201905281526218340522972', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('395', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:26:22', '0', 'RE201905281526226936725283', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('396', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:26:44', '0', 'RE201905281526442889055070', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('397', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:26:44', '0', 'RE201905281526448509854077', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('398', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:26:58', '0', 'RE201905281526583191284442', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('399', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:26:59', '0', 'RE201905281526598352428283', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('400', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:27:02', '0', 'RE201905281527026013370650', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('401', '4835', '430223199509094514', '99201904120002', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:28:24', '0', 'RE201905281528247752355735', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('402', '4835', '430223199509094514', '99201904120002', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:28:27', '0', 'RE201905281528270414974818', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('403', '4835', '430223199805124561', '99201904020005', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:45:55', '0', 'RE201905281545552486552108', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('404', '4835', '430223199805124561', '99201904020005', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:46:04', '0', 'RE201905281546049049526003', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('405', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 15:53:01', '0', 'RE201905281553013783942742', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('406', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 16:15:31', '0', 'RE201905281615313618851745', '4200000311201905287268947302', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('407', '4835', '430223199609164578', '99201904020004', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 17:14:32', '0', 'RE201905281714322494904613', '4200000323201905284426435937', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('408', '4835', '430223199805124561', '99201904020005', '', '2019-05-28 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 17:16:15', '0', 'RE201905281716157045548911', '4200000324201905285843137162', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('409', '4835', '430223199609164578', '99201904020004', '', '2019-05-29 00:00:00', 'Z446015', '3', null, '13.00', 'wx50617d578b93801d', '2019-05-28 17:36:29', '1', 'RE201905281736291971067581', '4200000311201905284547759422', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('410', '4835', '430223199805124561', '99201904020005', '', '2019-05-29 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 10:09:09', '1', 'RE201905291009099205814884', '4200000311201905290668162505', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('411', '4835', '430223199805124561', '99201904020005', '', '2019-05-29 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 10:24:26', '1', 'RE201905291024269768538413', '4200000323201905295003901502', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('412', '4835', '430223199609164578', '99201904020004', '', '2019-05-29 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 10:37:14', '1', 'RE201905291037143532445939', '4200000329201905298637804973', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('413', '4835', '430223199609164578', '99201904020004', '', '2019-05-29 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 10:40:57', '1', 'RE201905291040571046635192', '4200000337201905293015856226', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('414', '4835', '430223199609164578', '99201904020004', '', '2019-05-29 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 10:49:51', '1', 'RE201905291049517211573841', '4200000311201905295220422380', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('415', '4767', '430223199609164578', '99201904020004', '', '2019-05-29 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 10:55:36', '1', 'RE201905291055363409942182', '4200000338201905297479512746', '20', '5', null);
INSERT INTO `his_doreg_info` VALUES ('416', '4734', '430223199609164578', '99201904020004', '', '2019-05-29 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 11:26:36', '0', 'RE201905291126369805690738', '4200000320201905290535469577', '6', '5', null);
INSERT INTO `his_doreg_info` VALUES ('417', '4835', '430223199805124561', '99201904020005', '', '2019-05-29 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 11:36:35', '1', 'RE201905291136358310960322', '4200000326201905293544135958', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('418', '4835', '430223199609164578', '99201904020004', '', '2019-05-29 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 11:59:16', '1', 'RE201905291159167166338324', '4200000329201905298320061375', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('419', '4835', '430223199609164578', '99201904020004', '', '2019-05-29 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-29 14:07:09', '1', 'RE201905291407090517083668', '4200000309201905296969641645', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('420', '4835', '430223199609164578', '99201904020004', '', '2019-05-29 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-05-29 15:07:04', '1', 'RE201905291507047844402039', '4200000322201905296184344767', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('421', '4835', '430223199609164578', '99201904020004', '', '2019-05-31 00:00:00', 'Z446018', '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 15:29:51', '1', 'RE201905291529512028327345', '4200000318201905297418004213', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('422', '4835', '430223199509094514', '99201904120002', '', '2019-06-03 00:00:00', 'Z445997', '1', null, '13.00', 'wx50617d578b93801d', '2019-05-29 16:00:56', '1', 'RE201905291600568647384923', '4200000318201905292831153935', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('423', '4835', '430223199609164578', '99201904020004', '', '2019-06-04 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-04 16:10:41', '0', 'RE201906041610419112739161', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('424', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:10', '0', 'RE201906121406101445568814', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('425', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:34', '0', 'RE201906121406349057692500', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('426', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:34', '0', 'RE201906121406349574389903', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('427', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:35', '0', 'RE201906121406350013127670', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('428', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:35', '0', 'RE201906121406350531719032', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('429', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:35', '0', 'RE201906121406351059326451', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('430', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:35', '0', 'RE201906121406351531068795', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('431', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:35', '0', 'RE201906121406352018070883', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('432', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:35', '0', 'RE201906121406352532352475', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('433', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:35', '0', 'RE201906121406353050843932', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('434', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:35', '0', 'RE201906121406353531193577', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('435', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:36', '0', 'RE201906121406366419142748', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('436', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:37', '0', 'RE201906121406374438370669', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('437', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:37', '0', 'RE201906121406376495360239', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('438', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:37', '0', 'RE201906121406377181155171', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('439', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:37', '0', 'RE201906121406377696288426', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('440', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:37', '0', 'RE201906121406378205318582', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('441', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:37', '0', 'RE201906121406378706983985', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('442', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:31', '0', 'RE201906121406313743396516', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('443', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:32', '0', 'RE201906121406321071047807', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('444', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:32', '0', 'RE201906121406323247824757', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('445', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:32', '0', 'RE201906121406325337862194', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('446', '4835', '430223199609164578', '99201904020004', '', '2019-06-12 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-12 14:06:32', '0', 'RE201906121406327257594432', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('447', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:10', '0', 'RE201906171015108147205441', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('448', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:15', '0', 'RE201906171015153452685835', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('449', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:16', '0', 'RE201906171015165808293783', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('450', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:17', '0', 'RE201906171015176580593858', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('451', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:17', '0', 'RE201906171015178761956532', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('452', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:18', '0', 'RE201906171015180804376958', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('453', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:18', '0', 'RE201906171015183761912354', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('454', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:18', '0', 'RE201906171015185645753562', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('455', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:18', '0', 'RE201906171015187673263524', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('456', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:18', '0', 'RE201906171015189550720036', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('457', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:19', '0', 'RE201906171015191428288039', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('458', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:19', '0', 'RE201906171015195648493390', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('459', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:19', '0', 'RE201906171015196897302063', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('460', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:19', '0', 'RE201906171015198921376134', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('461', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:20', '0', 'RE201906171015200808191555', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('462', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:20', '0', 'RE201906171015202836306984', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('463', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:20', '0', 'RE201906171015204864402936', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('464', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:20', '0', 'RE201906171015207367794084', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('465', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:20', '0', 'RE201906171015208922402325', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('466', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:21', '0', 'RE201906171015210804282367', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('467', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:21', '0', 'RE201906171015212839385149', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('468', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:21', '0', 'RE201906171015214709649616', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('469', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:21', '0', 'RE201906171015216735401004', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('470', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:22', '0', 'RE201906171015221732671272', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('471', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:22', '0', 'RE201906171015223456083431', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('472', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:22', '0', 'RE201906171015225334551251', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('473', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:22', '0', 'RE201906171015227360206953', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('474', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:22', '0', 'RE201906171015229394881015', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('475', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:23', '0', 'RE201906171015231271013260', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('476', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:23', '0', 'RE201906171015233142615432', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('477', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:23', '0', 'RE201906171015235178676702', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('478', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:23', '0', 'RE201906171015237364224142', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('479', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:23', '0', 'RE201906171015239862739044', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('480', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:24', '0', 'RE201906171015241582025365', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('481', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:15:24', '0', 'RE201906171015243924792759', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('482', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:17:54', '0', 'RE201906171017545515767557', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('483', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:17:56', '0', 'RE201906171017560981836568', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('484', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:17:56', '0', 'RE201906171017563019197904', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('485', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:17:56', '0', 'RE201906171017564586083052', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('486', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:17:56', '0', 'RE201906171017566141478469', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('487', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:41:10', '0', 'RE201906171041102660839021', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('488', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:47:35', '0', 'RE201906171047353100368546', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('489', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:47:54', '0', 'RE201906171047543603391918', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('490', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:47:54', '0', 'RE201906171047544186465428', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('491', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:47:54', '0', 'RE201906171047544701997742', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('492', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:52:09', '0', 'RE201906171052096076375305', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('493', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:52:10', '0', 'RE201906171052108505980440', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('494', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:53:35', '0', 'RE201906171053350035933043', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('495', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:53:36', '0', 'RE201906171053368242700720', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('496', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:53:43', '0', 'RE201906171053431868758682', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('497', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 10:54:19', '0', 'RE201906171054198792722834', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('498', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 11:07:13', '0', 'RE201906171107131901741190', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('499', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 11:35:44', '0', 'RE201906171135444335552814', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('500', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 11:36:27', '0', 'RE201906171136275640007674', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('501', '4835', '430223199509094514', '99201904120002', '', '2019-06-17 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 12:30:33', '0', 'RE201906171230335494950248', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('502', '4739', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '3', null, '3.00', 'wx50617d578b93801d', '2019-06-17 14:07:47', '0', 'RE201906171407471791890983', null, '6', '5', null);
INSERT INTO `his_doreg_info` VALUES ('503', '4739', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '3', null, '3.00', 'wx50617d578b93801d', '2019-06-17 14:07:47', '0', 'RE201906171407478975103706', null, '6', '5', null);
INSERT INTO `his_doreg_info` VALUES ('504', '4739', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '3', null, '3.00', 'wx50617d578b93801d', '2019-06-17 14:07:48', '0', 'RE201906171407484604184388', '4200000323201906173538610041', '6', '5', null);
INSERT INTO `his_doreg_info` VALUES ('505', '4835', '430223199805124561', '99201904020005', '', '2019-06-17 00:00:00', null, '3', null, '13.00', 'wx50617d578b93801d', '2019-06-17 14:09:46', '1', 'RE201906171409465256930652', '4200000322201906178474954069', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('506', '4835', '430223199805124561', '99201904020005', '', '2019-06-18 00:00:00', 'Z446004', '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 15:38:37', '1', 'RE201906171538378412124830', '4200000313201906177575290855', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('507', '4835', '430223199805124561', '99201904020005', '', '2019-06-22 00:00:00', 'Z446025', '1', null, '13.00', 'wx50617d578b93801d', '2019-06-17 15:46:43', '1', 'RE201906171546439021269345', '4200000322201906173914429103', '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('508', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:47:20', '0', 'RE201906241147201020313967', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('509', '4835', '430223199609164578', '99201904020004', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:47:35', '0', 'RE201906241147357734964604', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('510', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:32', '0', 'RE201906241149327460268240', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('511', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:33', '0', 'RE201906241149336683393825', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('512', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:34', '0', 'RE201906241149346363566090', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('513', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:34', '0', 'RE201906241149349026252446', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('514', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:35', '0', 'RE201906241149350273959909', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('515', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:35', '0', 'RE201906241149352613786886', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('516', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:35', '0', 'RE201906241149353868086136', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('517', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:35', '0', 'RE201906241149355906739812', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('518', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:35', '0', 'RE201906241149357619821016', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('519', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:35', '0', 'RE201906241149359499738923', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('520', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:36', '0', 'RE201906241149361363346346', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('521', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:36', '0', 'RE201906241149363089470267', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('522', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:36', '0', 'RE201906241149364809112603', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('523', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:36', '0', 'RE201906241149366680576549', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('524', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:36', '0', 'RE201906241149368552315638', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('525', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:37', '0', 'RE201906241149370271563594', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('526', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:37', '0', 'RE201906241149372158883878', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('527', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:37', '0', 'RE201906241149373870165333', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('528', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:37', '0', 'RE201906241149375905415193', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('529', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:37', '0', 'RE201906241149377774363410', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('530', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:37', '0', 'RE201906241149379333997555', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('531', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:38', '0', 'RE201906241149381379345650', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('532', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:38', '0', 'RE201906241149382932408562', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('533', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:38', '0', 'RE201906241149385119768027', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('534', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:38', '0', 'RE201906241149386689111136', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('535', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:38', '0', 'RE201906241149388719450586', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('536', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:39', '0', 'RE201906241149390276654740', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('537', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:39', '0', 'RE201906241149391991088131', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('538', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:39', '0', 'RE201906241149393873380274', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('539', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:39', '0', 'RE201906241149395900804837', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('540', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:39', '0', 'RE201906241149397624740648', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('541', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:39', '0', 'RE201906241149399337015254', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('542', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:40', '0', 'RE201906241149401052712901', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('543', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:40', '0', 'RE201906241149402930508940', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('544', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:40', '0', 'RE201906241149404809454841', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('545', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:40', '0', 'RE201906241149406526943604', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('546', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:41', '0', 'RE201906241149410743778680', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('547', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:49:41', '0', 'RE201906241149412932666941', null, '4', '5', null);
INSERT INTO `his_doreg_info` VALUES ('548', '4835', '430223199805124561', '99201904020005', '', '2019-06-24 00:00:00', null, '1', null, '13.00', 'wx50617d578b93801d', '2019-06-24 11:50:36', '0', 'RE201906241150360576856550', null, '4', '5', null);

-- ----------------------------
-- Table structure for his_leave_hos_pay
-- ----------------------------
DROP TABLE IF EXISTS `his_leave_hos_pay`;
CREATE TABLE `his_leave_hos_pay` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `inHosNo` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '住院号',
  `payType` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '支付类型1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、云医院微信，7、云医院支付宝，8、诊疗卡',
  `payRecord` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '支付交易流水号',
  `payMoney` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '支付金额',
  `overMoney` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '医保统筹金额',
  `socialsecurityNO` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '社保卡号',
  `medicareType` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '0,非医保 1,市医保  2,区医保',
  `medicareReturn` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '医保返回信息',
  `creat_time` datetime DEFAULT NULL,
  `successful_payment` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '0支付未成功，1支付成功,  2表示已退款',
  `out_trade_no` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '商户订单号',
  `transaction_id` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信订单号',
  `app_id` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT 'app_id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of his_leave_hos_pay
-- ----------------------------
INSERT INTO `his_leave_hos_pay` VALUES ('1', '0025978', '5', '', '', '', '', '1', '', '2019-06-25 17:39:41', '0', 'LH201906251739413246535379', null, 'wx50617d578b93801d');

-- ----------------------------
-- Table structure for his_symptoms_organ
-- ----------------------------
DROP TABLE IF EXISTS `his_symptoms_organ`;
CREATE TABLE `his_symptoms_organ` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `sex` varchar(1) NOT NULL COMMENT '男或女：W-女；M-男',
  `body_part` varchar(32) NOT NULL COMMENT '身体部位',
  `symptoms` varchar(32) NOT NULL,
  `organ_name` varchar(32) NOT NULL COMMENT '科室名称',
  `organ_id` int(8) NOT NULL COMMENT '科室id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8mb4 COMMENT='身体分类与科室介绍';

-- ----------------------------
-- Records of his_symptoms_organ
-- ----------------------------
INSERT INTO `his_symptoms_organ` VALUES ('1', 'm', '背部', '脊柱畸形', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('2', 'm', '背部', '腰背痛', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('3', 'm', '耳眼口鼻', '鼻出血', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('4', 'm', '耳眼口鼻', '结膜充血', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('5', 'm', '耳眼口鼻', '上脸下垂', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('6', 'm', '耳眼口鼻', '视力下降', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('7', 'm', '耳眼口鼻', '扁桃体增大', '门诊口腔科', '11');
INSERT INTO `his_symptoms_organ` VALUES ('8', 'm', '耳眼口鼻', '咽部充血', '门诊口腔科', '11');
INSERT INTO `his_symptoms_organ` VALUES ('9', 'm', '耳眼口鼻', '发绀', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('10', 'm', '耳眼口鼻', '呕血', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('11', 'm', '耳眼口鼻', '眼球突出', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('12', 'm', '腹部', '腹壁静脉曲张', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('13', 'm', '腹部', '腹壁膨隆', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('14', 'm', '腹部', '腹部压痛', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('15', 'm', '腹部', '腹肌紧张', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('16', 'm', '腹部', '恶心与呕吐', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('17', 'm', '腹部', '腹痛', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('18', 'm', '腹部', '腹泻', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('19', 'm', '腹部', '便秘', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('20', 'm', '腹部', '黄疸', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('21', 'm', '腹部', '便血', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('22', 'm', '颈部', '颈部包块', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('23', 'm', '颈部', '吞咽困难', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('24', 'm', '皮肤', '皮肤溃疡', '门诊皮肤科', '13');
INSERT INTO `his_symptoms_organ` VALUES ('25', 'm', '皮肤', '皮下结节', '门诊皮肤科', '13');
INSERT INTO `his_symptoms_organ` VALUES ('26', 'm', '皮肤', '皮疹', '门诊皮肤科', '13');
INSERT INTO `his_symptoms_organ` VALUES ('27', 'm', '皮肤', '皮肤黏膜出血', '门诊皮肤科', '13');
INSERT INTO `his_symptoms_organ` VALUES ('28', 'm', '其他', '发热', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('29', 'm', '其他', '情感症状', '门诊神经内科', '24');
INSERT INTO `his_symptoms_organ` VALUES ('30', 'm', '其他', '肥胖', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('31', 'm', '其他', '消瘦', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('32', 'm', '其他', '意识障碍', '门诊神经外科', '31');
INSERT INTO `his_symptoms_organ` VALUES ('33', 'm', '上肢', '杵状指', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('34', 'm', '上肢', '关节痛', '门诊骨外科\r\n门诊骨外科\r\n门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('35', 'm', '生殖器', '少尿、无尿与多尿', '门诊泌尿内科', '22');
INSERT INTO `his_symptoms_organ` VALUES ('36', 'm', '生殖器', '尿道口红肿', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('37', 'm', '生殖器', '尿道口溃疡', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('38', 'm', '生殖器', '尿道口异常分泌物', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('39', 'm', '生殖器', '血尿', ';门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('40', 'm', '生殖器', '尿频、尿急与尿痛', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('41', 'm', '生殖器', '尿失禁', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('42', 'm', '生殖器', '排尿困难', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('43', 'm', '生殖器', '包茎', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('44', 'm', '生殖器', '包皮过长', '门诊泌尿外科\r\n', '32');
INSERT INTO `his_symptoms_organ` VALUES ('45', 'm', '四肢', '关节红肿', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('46', 'm', '四肢', '关节畸形', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('47', 'm', '四肢', '关节痛', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('48', 'm', '头部', '头痛', '门诊神经内科', '24');
INSERT INTO `his_symptoms_organ` VALUES ('49', 'm', '头部', '眩晕', '门诊神经内科', '24');
INSERT INTO `his_symptoms_organ` VALUES ('50', 'm', '头部', '晕厥', '门诊神经内科', '24');
INSERT INTO `his_symptoms_organ` VALUES ('51', 'm', '下肢', '水肿', '门诊泌尿内科', '22');
INSERT INTO `his_symptoms_organ` VALUES ('52', 'm', '下肢', '下肢静脉曲张', '门诊烧伤整形', '28');
INSERT INTO `his_symptoms_organ` VALUES ('53', 'm', '胸部', '桶状胸', '门诊呼吸内科', '21');
INSERT INTO `his_symptoms_organ` VALUES ('54', 'm', '胸部', '胸壁静脉曲张', '门诊呼吸内科', '21');
INSERT INTO `his_symptoms_organ` VALUES ('55', 'm', '胸部', '咳嗽与咳痰', '门诊呼吸内科', '21');
INSERT INTO `his_symptoms_organ` VALUES ('56', 'm', '胸部', '咯血', '门诊呼吸内科', '21');
INSERT INTO `his_symptoms_organ` VALUES ('57', 'm', '胸部', '呼吸困难', '门诊呼吸内科', '21');
INSERT INTO `his_symptoms_organ` VALUES ('58', 'm', '胸部', '胸痛', '门诊心血管内科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('59', 'm', '胸部', '心悸', '门诊心血管内科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('60', 'm', '胸部', '蜘蛛痣', '门诊心血管内科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('61', 'm', '胸部', '乳房包块', '门诊心血管内科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('62', 'm', '胸部', '乳房压痛', '门诊心血管内科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('63', 'm', '胸部', '乳头内陷', '门诊心血管内科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('64', 'm', '胸部', '皮下气肿', '门诊心血管内科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('65', 'm', '胸部', '胸壁压痛', '门诊心血管内科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('66', 'w', '背部', '脊柱畸形', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('67', 'w', '背部', '腰背痛', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('68', 'w', '耳眼口鼻', '鼻出血', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('69', 'w', '耳眼口鼻', '结膜充血', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('70', 'w', '耳眼口鼻', '上睑下垂', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('71', 'w', '耳眼口鼻', '视力下降', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('72', 'w', '耳眼口鼻', '扁桃体增大', '门诊口腔科', '11');
INSERT INTO `his_symptoms_organ` VALUES ('73', 'w', '耳眼口鼻', '咽部充血、红肿', '门诊口腔科', '11');
INSERT INTO `his_symptoms_organ` VALUES ('74', 'w', '耳眼口鼻', '发绀', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('75', 'w', '耳眼口鼻', '呕血', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('76', 'w', '耳眼口鼻', '眼球突出', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('77', 'w', '腹部', '腹壁静脉曲张', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('78', 'w', '腹部', '腹壁膨隆', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('79', 'w', '腹部', '腹部压痛', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('80', 'w', '腹部', '腹肌紧张', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('81', 'w', '腹部', '恶心与呕吐', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('82', 'w', '腹部', '腹痛', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('83', 'w', '腹部', '腹泻', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('84', 'w', '腹部', '便秘', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('85', 'w', '腹部', '黄疸', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('86', 'w', '腹部', '便血', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('87', 'w', '颈部', '颈部包块', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('88', 'w', '颈部', '吞咽困难', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('89', 'w', '皮肤', '皮肤溃疡', '门诊皮肤科', '13');
INSERT INTO `his_symptoms_organ` VALUES ('90', 'w', '皮肤', '皮下结节', '门诊皮肤科', '13');
INSERT INTO `his_symptoms_organ` VALUES ('91', 'w', '皮肤', '皮疹', '门诊皮肤科', '13');
INSERT INTO `his_symptoms_organ` VALUES ('92', 'w', '皮肤', '皮肤黏膜出血', '门诊皮肤科', '13');
INSERT INTO `his_symptoms_organ` VALUES ('93', 'w', '其他', '发热', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('94', 'w', '其他', '情感症状', '门诊神经内科', '24');
INSERT INTO `his_symptoms_organ` VALUES ('95', 'w', '其他', '肥胖', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('96', 'w', '其他', '消瘦', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('97', 'w', '其他', '意识障碍', '门诊神经外科', '31');
INSERT INTO `his_symptoms_organ` VALUES ('98', 'w', '上肢', '杵状指', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('99', 'w', '上肢', '关节痛', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('100', 'w', '生殖器', '少尿、无尿与多尿', '门诊泌尿内科', '22');
INSERT INTO `his_symptoms_organ` VALUES ('101', 'w', '生殖器', '尿道口红肿', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('102', 'w', '生殖器', '尿道口溃疡', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('103', 'w', '生殖器', '尿道口异常分泌物', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('104', 'w', '生殖器', '血尿', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('105', 'w', '生殖器', '尿频、尿急与尿痛', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('106', 'w', '生殖器', '尿失禁', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('107', 'w', '生殖器', '排尿困难', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('108', 'w', '生殖器', '阴道流血', '门诊妇产科', '6');
INSERT INTO `his_symptoms_organ` VALUES ('109', 'w', '生殖器', '阴道流液', '门诊妇产科', '6');
INSERT INTO `his_symptoms_organ` VALUES ('110', 'w', '四肢', '关节红肿', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('111', 'w', '四肢', '关节畸形', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('112', 'w', '四肢', '关节痛', '门诊骨外科', '29');
INSERT INTO `his_symptoms_organ` VALUES ('113', 'w', '头部', '头痛', '门诊神经内科', '24');
INSERT INTO `his_symptoms_organ` VALUES ('114', 'w', '头部', '眩晕', '门诊神经内科', '24');
INSERT INTO `his_symptoms_organ` VALUES ('115', 'w', '头部', '晕厥', '门诊神经内科', '24');
INSERT INTO `his_symptoms_organ` VALUES ('116', 'w', '下肢', '水肿', '门诊泌尿内科', '22');
INSERT INTO `his_symptoms_organ` VALUES ('117', 'w', '下肢', '下肢静脉曲张', '门诊烧伤整形', '28');
INSERT INTO `his_symptoms_organ` VALUES ('118', 'w', '胸部', '桶状胸', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('119', 'w', '胸部', '胸壁静脉曲张', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('120', 'w', '胸部', '咳嗽与咳痰', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('121', 'w', '胸部', '咯血', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('122', 'w', '胸部', '呼吸困难', '门诊呼吸内科', '19');
INSERT INTO `his_symptoms_organ` VALUES ('123', 'w', '胸部', '胸痛', '门诊心血管内科', '21');
INSERT INTO `his_symptoms_organ` VALUES ('124', 'w', '胸部', '心悸', '门诊心血管内科', '21');
INSERT INTO `his_symptoms_organ` VALUES ('125', 'w', '胸部', '蜘蛛痣', '门诊消化内科', '20');
INSERT INTO `his_symptoms_organ` VALUES ('126', 'w', '胸部', '乳房包块', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('127', 'w', '胸部', '乳房压痛', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('128', 'w', '胸部', '乳头内陷', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('129', 'w', '胸部', '皮下气肿', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('130', 'w', '胸部', '胸壁压痛', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('131', 'h', '耳眼口鼻', '鼻出血', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('132', 'h', '耳眼口鼻', '扁桃体增大', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('133', 'h', '耳眼口鼻', '结膜充血', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('134', 'h', '耳眼口鼻', '上睑下垂', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('135', 'h', '耳眼口鼻', '视力下降', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('136', 'h', '耳眼口鼻', '咽部充血、红肿', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('137', 'h', '耳眼口鼻', '发绀', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('138', 'h', '耳眼口鼻', '呕血', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('139', 'h', '腹部', '腹壁静脉曲张', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('140', 'h', '腹部', '腹壁膨隆', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('141', 'h', '腹部', '腹部压痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('142', 'h', '腹部', '腹肌紧张', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('143', 'h', '腹部', '恶心与呕吐', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('144', 'h', '腹部', '腹痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('145', 'h', '腹部', '腹泻', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('146', 'h', '腹部', '便秘', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('147', 'h', '腹部', '黄疸', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('148', 'h', '腹部', '便血', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('149', 'h', '颈部', '颈部包块', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('150', 'h', '颈部', '吞咽困难', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('151', 'h', '皮肤', '皮肤溃疡', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('152', 'h', '皮肤', '皮下结节', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('153', 'h', '皮肤', '皮疹', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('154', 'h', '皮肤', '皮肤黏膜出血', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('155', 'h', '其他', '发热', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('156', 'h', '其他', '肥胖', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('157', 'h', '其他', '意识障碍', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('158', 'h', '其他', '情感症状', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('159', 'h', '其他', '消瘦', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('160', 'h', '其他', '咽痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('161', 'h', '其他', '流涕', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('162', 'h', '其他', '盗汗', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('163', 'h', '其他', '食欲不振', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('164', 'h', '其他', '易惊', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('165', 'h', '生殖器', '尿道口红肿', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('167', 'h', '生殖器', '尿道口溃疡', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('168', 'h', '生殖器', '尿道口异常分泌物', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('169', 'h', '生殖器', '血尿', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('170', 'h', '生殖器', '尿频、尿急与尿痛', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('171', 'h', '生殖器', '少尿、无尿与多尿', '门诊儿科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('172', 'h', '生殖器', '尿失禁', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('173', 'h', '生殖器', '排尿困难', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('174', 'h', '生殖器', '包茎', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('175', 'h', '生殖器', '包皮过长', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('176', 'h', '生殖器', '阴囊肿胀', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('177', 'h', '四肢', '关节畸形', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('178', 'h', '四肢', '关节痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('179', 'h', '头部', '头颅畸形', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('180', 'h', '头部', '头痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('181', 'h', '头部', '眩晕', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('182', 'h', '头部', '晕厥', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('183', 'h', '下肢', '水肿', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('184', 'h', '胸部', '心悸', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('185', 'h', '胸部', '皮下气肿', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('186', 'h', '胸部', '胸壁静脉曲张', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('187', 'h', '胸部', '胸壁压痛', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('188', 'h', '胸部', '咳嗽与咳痰', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('189', 'h', '胸部', '咯血', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('190', 'h', '胸部', '胸痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('191', 'h', '胸部', '呼吸困难', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('192', 's', '耳眼口鼻', '鼻出血', '门诊耳鼻喉科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('193', 's', '耳眼口鼻', '扁桃体增大', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('194', 's', '耳眼口鼻', '结膜充血', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('195', 's', '耳眼口鼻', '上睑下垂', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('196', 's', '耳眼口鼻', '视力下降', '门诊耳鼻喉科', '9');
INSERT INTO `his_symptoms_organ` VALUES ('197', 's', '耳眼口鼻', '咽部充血、红肿', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('198', 's', '耳眼口鼻', '发绀', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('199', 's', '耳眼口鼻', '呕血', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('200', 's', '腹部', '腹壁静脉曲张', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('201', 's', '腹部', '腹壁膨隆', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('202', 's', '腹部', '腹部压痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('203', 's', '腹部', '腹肌紧张', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('204', 's', '腹部', '恶心与呕吐', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('205', 's', '腹部', '腹痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('206', 's', '腹部', '腹泻', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('207', 's', '腹部', '便秘', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('208', 's', '腹部', '黄疸', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('209', 's', '腹部', '便血', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('210', 's', '颈部', '颈部包块', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('211', 's', '颈部', '吞咽困难', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('212', 's', '皮肤', '皮肤溃疡', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('213', 's', '皮肤', '皮下结节', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('214', 's', '皮肤', '皮疹', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('215', 's', '皮肤', '皮肤黏膜出血', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('216', 's', '其他', '发热', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('217', 's', '其他', '肥胖', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('218', 's', '其他', '意识障碍', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('219', 's', '其他', '情感症状', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('220', 's', '其他', '消瘦', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('221', 's', '其他', '咽痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('222', 's', '其他', '流涕', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('223', 's', '其他', '盗汗', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('224', 's', '其他', '食欲不振', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('225', 's', '其他', '易惊', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('226', 's', '生殖器', '尿道口红肿', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('227', 's', '生殖器', '尿道口溃疡', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('228', 's', '生殖器', '尿道口异常分泌物', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('229', 's', '生殖器', '血尿', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('330', 's', '生殖器', '尿频、尿急与尿痛', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('331', 's', '生殖器', '少尿、无尿与多尿', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('332', 's', '生殖器', '尿失禁', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('333', 's', '生殖器', '排尿困难', '门诊泌尿外科', '32');
INSERT INTO `his_symptoms_organ` VALUES ('334', 's', '四肢', '关节畸形', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('335', 's', '四肢', '关节痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('336', 's', '头部', '头颅畸形', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('337', 's', '头部', '头痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('338', 's', '头部', '眩晕', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('339', 's', '头部', '晕厥', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('340', 's', '下肢', '水肿', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('341', 's', '胸部', '皮下气肿', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('342', 's', '胸部', '胸壁静脉曲张', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('343', 's', '胸部', '胸壁压痛', '门诊心胸外科', '30');
INSERT INTO `his_symptoms_organ` VALUES ('344', 's', '胸部', '咳嗽与咳痰', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('345', 's', '胸部', '咯血', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('346', 's', '胸部', '胸痛', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('347', 's', '胸部', '呼吸困难', '门诊儿科', '8');
INSERT INTO `his_symptoms_organ` VALUES ('348', 's', '胸部', '心悸', '门诊儿科', '8');

/*
 Navicat Premium Data Transfer

 Source Server         : zhuk
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : examination

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 06/07/2020 10:30:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for task_manage
-- ----------------------------
DROP TABLE IF EXISTS `task_manage`;
CREATE TABLE `task_manage`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务内容',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务类型',
  `deadline` datetime(0) NULL DEFAULT NULL COMMENT '截止日期',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行部门',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_manage
-- ----------------------------
INSERT INTO `task_manage` VALUES ('1267722910838198274', '任务管理2020-06-02 15:40:50', '12', 'PERSONAL', '2020-06-20 00:00:00', 'HANDLING', '[\"DEVELOPMENT\",\"TEST\"]', '2020-06-02 15:41:10');
INSERT INTO `task_manage` VALUES ('1274870207652974594', '任务管理2020-06-22 09:01:39', '1234565', 'PROJECT', '2020-06-22 09:01:47', 'NOT_HANDLE', '[\"DEVELOPMENT\",\"SALE\"]', '2020-06-22 09:01:58');
INSERT INTO `task_manage` VALUES ('1275353738827837441', '任务管理2020-06-23 17:03:10', '1111', 'PROJECT', '2020-06-24 00:00:00', 'HANDLING', '[\"DEVELOPMENT\"]', '2020-06-23 17:03:21');
INSERT INTO `task_manage` VALUES ('1275353831429681154', '任务管理2020-06-23 17:03:26', '11111', 'PROJECT', '2020-06-25 00:00:00', 'HANDLING', '[\"SALE\",\"AFTER_SALE_SERVICE\"]', '2020-06-23 17:03:43');
INSERT INTO `task_manage` VALUES ('1275353884546347009', '任务管理2020-06-23 17:03:45', '11222', 'PROJECT', '2020-06-26 00:00:00', 'NOT_HANDLE', '[\"AFTER_SALE_SERVICE\"]', '2020-06-23 17:03:56');
INSERT INTO `task_manage` VALUES ('1275353940112486401', '任务管理2020-06-23 17:03:59', '222', 'PERSONAL', '2020-04-30 00:00:00', 'HANDLING', '[\"TEST\"]', '2020-06-23 17:04:09');
INSERT INTO `task_manage` VALUES ('1275353996978860033', '任务管理2020-06-23 17:04:11', '222333', 'PERSONAL', '2020-06-19 00:00:00', 'NOT_HANDLE', '[\"TEST\",\"SALE\"]', '2020-06-23 17:04:22');
INSERT INTO `task_manage` VALUES ('1275354052310118401', '任务管理2020-06-23 17:04:26', '224444', 'PROJECT', '2020-06-07 00:00:00', 'HANDLING', '[\"TEST\"]', '2020-06-23 17:04:36');
INSERT INTO `task_manage` VALUES ('1275354106592800769', '任务管理2020-06-23 17:04:39', '22', 'PROJECT', '2020-06-01 00:00:00', 'HANDLING', '[\"TEST\"]', '2020-06-23 17:04:49');
INSERT INTO `task_manage` VALUES ('1275354158975463425', '任务管理2020-06-23 17:04:50', '2224444', 'PERSONAL', '2020-06-26 00:00:00', 'HANDLING', '[\"TEST\"]', '2020-06-23 17:05:01');
INSERT INTO `task_manage` VALUES ('1275354430409846786', '任务管理2020-06-23 17:05:52', '呃呃呃', 'PROJECT', '2020-06-02 00:00:00', 'COMPLETE', '[\"TEST\"]', '2020-06-23 17:06:06');
INSERT INTO `task_manage` VALUES ('1275356043312730114', '任务管理2020-06-23 17:12:16', '123456489', 'PROJECT', '2020-06-01 00:00:00', 'NOT_HANDLE', '[\"TEST\",\"DEVELOPMENT\"]', '2020-06-23 17:12:30');
INSERT INTO `task_manage` VALUES ('1275356716955701249', '任务管理2020-06-23 17:14:58', '2222', 'PERSONAL', '2020-06-01 00:00:00', 'HANDLING', '[\"TEST\",\"DEVELOPMENT\"]', '2020-06-23 17:15:11');
INSERT INTO `task_manage` VALUES ('1275356767920689154', '任务管理2020-06-23 17:15:12', '2222222222', 'PERSONAL', '2020-06-23 17:15:18', 'HANDLING', '[\"DEVELOPMENT\"]', '2020-06-23 17:15:23');
INSERT INTO `task_manage` VALUES ('1275356857636851713', '任务管理2020-06-23 17:15:29', '234', 'PERSONAL', '2020-06-02 00:00:00', 'HANDLING', '[\"DEVELOPMENT\",\"TEST\"]', '2020-06-23 17:15:45');
INSERT INTO `task_manage` VALUES ('1275359435779051522', '任务管理2020-06-23 17:25:36', 'ffffff', 'PROJECT', '2020-06-01 00:00:00', 'HANDLING', '[\"TEST\"]', '2020-06-23 17:25:59');
INSERT INTO `task_manage` VALUES ('1275359496776814593', '任务管理2020-06-23 17:26:01', 'fffffg', 'PERSONAL', '2020-06-02 00:00:00', 'HANDLING', '[\"TEST\"]', '2020-06-23 17:26:14');
INSERT INTO `task_manage` VALUES ('1275359849089961986', '任务管理2020-06-23 17:27:26', 'jjjjj', 'PROJECT', '2020-05-31 00:00:00', 'HANDLING', '[\"TEST\",\"AFTER_SALE_SERVICE\"]', '2020-06-23 17:27:38');
INSERT INTO `task_manage` VALUES ('1275359906468040706', '任务管理2020-06-23 17:27:41', 'jjjkk', 'PROJECT', '2020-06-01 00:00:00', 'HANDLING', '[\"TEST\"]', '2020-06-23 17:27:51');
INSERT INTO `task_manage` VALUES ('1275360008507068418', '任务管理2020-06-23 17:28:06', 'yyyy', 'PERSONAL', '2020-05-31 00:00:00', 'HANDLING', '[\"AFTER_SALE_SERVICE\"]', '2020-06-23 17:28:16');
INSERT INTO `task_manage` VALUES ('1275360056129196033', '任务管理2020-06-23 17:28:17', 'yyy', 'PROJECT', '2020-06-01 00:00:00', 'NOT_HANDLE', '[\"TEST\"]', '2020-06-23 17:28:27');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `grade` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  `class` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `date` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生年月',
  `card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

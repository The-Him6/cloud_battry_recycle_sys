/*
 Navicat Premium Dump SQL

 Source Server         : centos7brs
 Source Server Type    : MySQL
 Source Server Version : 80410 (8.4.10)
 Source Host           : 192.168.150.102:3307
 Source Schema         : br_recycle

 Target Server Type    : MySQL
 Target Server Version : 80410 (8.4.10)
 File Encoding         : 65001

 Date: 29/08/2026 17:06:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for battery_type
-- ----------------------------
DROP TABLE IF EXISTS `battery_type`;
CREATE TABLE `battery_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '电池种类ID',
  `type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电池种类名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '种类描述',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标地址',
  `identification_guide` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '如何识别该类型电池的指南',
  `points` int NOT NULL DEFAULT 0 COMMENT '回收积分（每个电池可获得的积分）',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_type_name`(`type_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '电池种类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of battery_type
-- ----------------------------

-- ----------------------------
-- Table structure for recycle_detail
-- ----------------------------
DROP TABLE IF EXISTS `recycle_detail`;
CREATE TABLE `recycle_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '明细ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `battery_type_id` bigint NOT NULL COMMENT '电池种类ID',
  `battery_count` int NOT NULL DEFAULT 0 COMMENT '电池数量',
  `points` int NOT NULL DEFAULT 0 COMMENT '获得积分',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE,
  INDEX `idx_battery_type_id_count`(`battery_type_id` ASC, `battery_count` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99959 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '回收明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of recycle_detail
-- ----------------------------

-- ----------------------------
-- Table structure for recycle_order
-- ----------------------------
DROP TABLE IF EXISTS `recycle_order`;
CREATE TABLE `recycle_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `total_count` int NOT NULL DEFAULT 0 COMMENT '回收电池总数量',
  `total_points` int NOT NULL DEFAULT 0 COMMENT '获得总积分',
  `recycle_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回收地址',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `order_status` tinyint NOT NULL DEFAULT 0 COMMENT '订单状态：0-待处理，1-处理中，2-已完成，3-已取消',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `detail_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '订单明细快照(JSON)，订单完成时写入明细表',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_number`(`order_number` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE,
  INDEX `idx_status`(`order_status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50013 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '回收订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of recycle_order
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- 追加：Seata undo_log 表（AT 模式需要）
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint NOT NULL COMMENT '分支事务id',
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '全局事务id',
  `context` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime NOT NULL COMMENT 'create datetime',
  `log_modified` datetime NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid` ASC, `branch_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

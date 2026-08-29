/*
 Navicat Premium Dump SQL

 Source Server         : centos7brs
 Source Server Type    : MySQL
 Source Server Version : 80410 (8.4.10)
 Source Host           : 192.168.150.102:3307
 Source Schema         : br_seckill

 Target Server Type    : MySQL
 Target Server Version : 80410 (8.4.10)
 File Encoding         : 65001

 Date: 29/08/2026 17:07:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for seckill_activity
-- ----------------------------
DROP TABLE IF EXISTS `seckill_activity`;
CREATE TABLE `seckill_activity`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动标题',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动说明',
  `stock` int NOT NULL DEFAULT 100 COMMENT '秒杀券库存',
  `sold` int NOT NULL DEFAULT 0 COMMENT '已售数量',
  `points_cost` int NOT NULL DEFAULT 500 COMMENT '秒杀所需积分',
  `start_time` datetime NOT NULL COMMENT '秒杀开始时间',
  `end_time` datetime NOT NULL COMMENT '秒杀结束时间',
  `coupon_start_time` datetime NOT NULL COMMENT '优惠券生效时间',
  `coupon_end_time` datetime NOT NULL COMMENT '优惠券过期时间',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态：0-草稿，1-上架，2-下架，3-结束',
  `create_admin_id` bigint NOT NULL COMMENT '创建管理员ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status_time`(`status` ASC, `start_time` ASC, `end_time` ASC) USING BTREE,
  INDEX `idx_create_admin`(`create_admin_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '秒杀活动表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of seckill_activity
-- ----------------------------
INSERT INTO `seckill_activity` VALUES (1, '周末电池秒杀券', '一个用户仅可抢一张，抢到后第二天开始七天有效期，可兑换商城中任意商品', 100, 1, 500, '2026-08-27 00:00:00', '2026-08-28 00:00:00', '2026-08-29 00:00:00', '2026-09-05 00:00:00', 1, 1, '2026-06-10 18:56:07', '2026-08-27 15:21:28');

-- ----------------------------
-- Table structure for undo_log
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

-- ----------------------------
-- Table structure for user_seckill_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_seckill_coupon`;
CREATE TABLE `user_seckill_coupon`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户券ID',
  `activity_id` bigint NOT NULL COMMENT '秒杀活动ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态：0-未使用，1-已使用，2-已过期',
  `effective_time` datetime NOT NULL COMMENT '生效时间',
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `used_product_id` bigint NULL DEFAULT NULL COMMENT '使用时兑换的商品ID',
  `used_exchange_record_id` bigint NULL DEFAULT NULL COMMENT '使用时生成的兑换记录ID',
  `used_time` datetime NULL DEFAULT NULL COMMENT '使用时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_user`(`activity_id` ASC, `user_id` ASC) USING BTREE,
  INDEX `idx_user_status`(`user_id` ASC, `status` ASC) USING BTREE,
  INDEX `idx_expire_time`(`expire_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户秒杀券表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_seckill_coupon
-- ----------------------------
INSERT INTO `user_seckill_coupon` VALUES (1, 1, 201, 1, '2026-06-12 00:00:00', '2026-06-18 00:00:00', 10, 5, '2026-06-14 13:57:46', '2026-06-10 18:56:39', '2026-06-14 13:57:46');
INSERT INTO `user_seckill_coupon` VALUES (3, 1, 203, 0, '2026-08-29 00:00:00', '2026-09-05 00:00:00', NULL, NULL, NULL, '2026-08-27 15:21:28', '2026-08-27 15:21:28');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Dump SQL

 Source Server         : centos7brs
 Source Server Type    : MySQL
 Source Server Version : 80410 (8.4.10)
 Source Host           : 192.168.150.102:3307
 Source Schema         : br_exchange

 Target Server Type    : MySQL
 Target Server Version : 80410 (8.4.10)
 File Encoding         : 65001

 Date: 29/08/2026 17:06:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exchange_product
-- ----------------------------
DROP TABLE IF EXISTS `exchange_product`;
CREATE TABLE `exchange_product`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌',
  `battery_model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电池型号（5号/7号）',
  `points_required` int NOT NULL DEFAULT 1000 COMMENT '所需积分',
  `stock` int NOT NULL DEFAULT 0 COMMENT '库存数量',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-下架，1-上架',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_brand`(`brand` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '可兑换电池商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exchange_product
-- ----------------------------
INSERT INTO `exchange_product` VALUES (1, '南孚5号碱性电池', '南孚', '5号', 1000, 97, 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/image_url/2026-03-02/2cd6dadd-cabb-41c4-a6c3-f333d6b97bbc.png', '南孚聚能环5号碱性电池，持久耐用', 1, '2026-02-10 12:32:25', '2026-03-21 15:26:30');
INSERT INTO `exchange_product` VALUES (2, '南孚7号碱性电池', '南孚', '7号', 1000, 100, 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/image_url/2026-03-02/8a3f65b5-be6f-4451-abc2-a6882e01cc38.png', '南孚聚能环7号碱性电池，持久耐用', 1, '2026-02-10 12:32:25', '2026-03-02 16:13:06');
INSERT INTO `exchange_product` VALUES (3, '酷态科5号碱性电池', '酷态科', '5号', 1000, 99, 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/image_url/2026-03-02/f7c4f4c9-41f6-4166-b096-9e6a2552eade.png', '酷态科5号碱性电池，性价比高', 1, '2026-02-10 12:32:25', '2026-03-09 11:02:14');
INSERT INTO `exchange_product` VALUES (4, '酷态科7号碱性电池', '酷态科', '7号', 1000, 100, 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/image_url/2026-03-02/e80511d1-5602-4f63-8a87-daba55da2fa2.png', '酷态科7号碱性电池，性价比高', 1, '2026-02-10 12:32:25', '2026-03-02 16:13:39');
INSERT INTO `exchange_product` VALUES (7, '双鹿5号碱性电池', '双鹿', '5号', 1000, 99, 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/image_url/2026-03-02/1b6b44a7-25e0-4ff7-bb78-c9996e2e0ce4.png', '双鹿5号碱性电池，国产品牌', 1, '2026-02-10 12:32:25', '2026-05-13 19:14:46');
INSERT INTO `exchange_product` VALUES (8, '双鹿7号碱性电池', '双鹿', '7号', 1000, 100, 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/image_url/2026-03-02/ec663f9c-a725-4edb-bfe4-de029fbe4097.png', '双鹿7号碱性电池，国产品牌', 1, '2026-02-10 12:32:25', '2026-03-02 16:13:24');
INSERT INTO `exchange_product` VALUES (9, '京东京造5号碱性电池', '京东京造', '5号', 1000, 0, 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/image_url/2026-03-02/244aa6ff-57a5-4320-b4f1-afca235f4dad.png', '京东京造5号碱性电池，品质之选', 1, '2026-02-10 12:32:25', '2026-05-13 22:58:03');
INSERT INTO `exchange_product` VALUES (10, '京东京造7号碱性电池', '京东京造', '7号', 1000, 97, 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/image_url/2026-03-02/c29beadd-f36c-41d1-9dbb-cf9660172327.png', '京东京造7号碱性电池，品质之选', 1, '2026-02-10 12:32:25', '2026-06-14 13:57:46');

-- ----------------------------
-- Table structure for exchange_record
-- ----------------------------
DROP TABLE IF EXISTS `exchange_record`;
CREATE TABLE `exchange_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '兑换记录ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `points_used` int NOT NULL COMMENT '使用积分',
  `quantity` int NOT NULL DEFAULT 1 COMMENT '兑换数量',
  `exchange_status` tinyint NOT NULL DEFAULT 0 COMMENT '兑换状态：0-待发货，1-已发货，2-已完成',
  `shipping_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货地址',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `exchange_type` tinyint NOT NULL DEFAULT 0 COMMENT '兑换类型：0-积分兑换，1-秒杀券兑换',
  `coupon_id` bigint NULL DEFAULT NULL COMMENT '使用的秒杀券ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_product_id`(`product_id` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE,
  INDEX `idx_coupon_id`(`coupon_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '积分兑换记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exchange_record
-- ----------------------------
INSERT INTO `exchange_record` VALUES (1, 202, 9, '京东京造5号碱性电池', 1000, 1, 2, '池州学院', '13800000201', '', 0, NULL, '2026-05-11 21:30:37', '2026-05-11 21:31:00');
INSERT INTO `exchange_record` VALUES (2, 203, 7, '双鹿5号碱性电池', 1000, 1, 2, '池州学院', '13100000202', '', 0, NULL, '2026-05-13 19:14:46', '2026-05-13 22:58:16');
INSERT INTO `exchange_record` VALUES (3, 203, 9, '京东京造5号碱性电池', 1000, 1, 2, '池州学院', '13100000202', '', 0, NULL, '2026-05-13 22:58:03', '2026-05-13 22:58:18');
INSERT INTO `exchange_record` VALUES (4, 203, 10, '京东京造7号碱性电池', 1000, 1, 2, '池州学院', '13100000202', '', 0, NULL, '2026-05-15 09:05:41', '2026-05-15 09:05:51');
INSERT INTO `exchange_record` VALUES (5, 201, 10, '京东京造7号碱性电池', 0, 1, 2, '池州学院', '13100000200', '', 1, 1, '2026-06-14 13:57:46', '2026-06-14 13:58:09');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

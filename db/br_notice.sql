/*
 Navicat Premium Dump SQL

 Source Server         : centos7brs
 Source Server Type    : MySQL
 Source Server Version : 80410 (8.4.10)
 Source Host           : 192.168.150.102:3307
 Source Schema         : br_notice

 Target Server Type    : MySQL
 Target Server Version : 80410 (8.4.10)
 File Encoding         : 65001

 Date: 29/08/2026 17:06:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_notice
-- ----------------------------
DROP TABLE IF EXISTS `system_notice`;
CREATE TABLE `system_notice`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
  `activity_id` bigint NULL DEFAULT NULL COMMENT '关联秒杀活动ID',
  `popup_start_time` datetime NOT NULL COMMENT '弹窗开始时间',
  `popup_end_time` datetime NOT NULL COMMENT '弹窗结束时间',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态：0-草稿，1-已发布，2-已撤回',
  `create_admin_id` bigint NOT NULL COMMENT '创建管理员ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status_time`(`status` ASC, `popup_start_time` ASC, `popup_end_time` ASC) USING BTREE,
  INDEX `idx_activity_id`(`activity_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统弹窗公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of system_notice
-- ----------------------------
INSERT INTO `system_notice` VALUES (1, '秒杀活动', '今天有个活动，100张券，抢完即止', 1, '2026-06-10 00:00:00', '2026-06-11 00:00:00', 1, 1, '2026-06-10 18:54:22', '2026-06-10 18:54:30');
INSERT INTO `system_notice` VALUES (2, '秒杀券', '今天有个活动，100张券，抢完即止', NULL, '2026-08-27 00:00:00', '2026-08-28 00:00:00', 1, 1, '2026-08-27 14:36:48', '2026-08-27 14:36:55');

-- ----------------------------
-- Table structure for user_notice_read
-- ----------------------------
DROP TABLE IF EXISTS `user_notice_read`;
CREATE TABLE `user_notice_read`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `notice_id` bigint NOT NULL COMMENT '公告ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `read_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '已读时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_notice_user`(`notice_id` ASC, `user_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户公告已读表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_notice_read
-- ----------------------------
INSERT INTO `user_notice_read` VALUES (1, 1, 201, '2026-06-10 18:54:34');
INSERT INTO `user_notice_read` VALUES (2, 1, 202, '2026-06-10 19:10:36');
INSERT INTO `user_notice_read` VALUES (3, 2, 203, '2026-08-27 14:37:10');

SET FOREIGN_KEY_CHECKS = 1;

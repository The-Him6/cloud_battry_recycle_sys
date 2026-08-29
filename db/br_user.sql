/*
 Navicat Premium Dump SQL

 Source Server         : centos7brs
 Source Server Type    : MySQL
 Source Server Version : 80410 (8.4.10)
 Source Host           : 192.168.150.102:3307
 Source Schema         : br_user

 Target Server Type    : MySQL
 Target Server Version : 80410 (8.4.10)
 File Encoding         : 65001

 Date: 29/08/2026 17:07:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `role` tinyint NOT NULL DEFAULT 0 COMMENT '角色：0-普通用户，1-管理员',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE,
  INDEX `idx_phone`(`phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 206 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '系统管理员', '13800000000', 'admin@battery.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/2026-08-25/1d4368ff-1487-4f7d-aee6-bd454e1219a1.jpg', 1, 1, '2022-06-01 08:00:00', '2026-08-25 18:21:10');
INSERT INTO `user` VALUES (2, 'user001', 'e10adc3949ba59abbe56e057f20f883e', '用户001', '13100000001', 'user001@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-01.png', 0, 1, '2022-06-01 09:00:00', '2022-06-01 09:00:00');
INSERT INTO `user` VALUES (3, 'user002', 'e10adc3949ba59abbe56e057f20f883e', '用户002', '13100000002', 'user002@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-02.png', 0, 1, '2022-06-04 09:00:00', '2022-06-04 09:00:00');
INSERT INTO `user` VALUES (4, 'user003', 'e10adc3949ba59abbe56e057f20f883e', '用户003', '13100000003', 'user003@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-03.png', 0, 1, '2022-06-07 09:00:00', '2022-06-07 09:00:00');
INSERT INTO `user` VALUES (5, 'user004', 'e10adc3949ba59abbe56e057f20f883e', '用户004', '13100000004', 'user004@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-04.png', 0, 1, '2022-06-10 09:00:00', '2022-06-10 09:00:00');
INSERT INTO `user` VALUES (6, 'user005', 'e10adc3949ba59abbe56e057f20f883e', '用户005', '13100000005', 'user005@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-05.png', 0, 1, '2022-06-13 09:00:00', '2022-06-13 09:00:00');
INSERT INTO `user` VALUES (7, 'user006', 'e10adc3949ba59abbe56e057f20f883e', '用户006', '13100000006', 'user006@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-06.png', 0, 1, '2022-06-16 09:00:00', '2022-06-16 09:00:00');
INSERT INTO `user` VALUES (8, 'user007', 'e10adc3949ba59abbe56e057f20f883e', '用户007', '13100000007', 'user007@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-07.png', 0, 1, '2022-06-19 09:00:00', '2022-06-19 09:00:00');
INSERT INTO `user` VALUES (9, 'user008', 'e10adc3949ba59abbe56e057f20f883e', '用户008', '13100000008', 'user008@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-08.png', 0, 1, '2022-06-22 09:00:00', '2022-06-22 09:00:00');
INSERT INTO `user` VALUES (10, 'user009', 'e10adc3949ba59abbe56e057f20f883e', '用户009', '13100000009', 'user009@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-09.png', 0, 1, '2022-06-25 09:00:00', '2022-06-25 09:00:00');
INSERT INTO `user` VALUES (11, 'user010', 'e10adc3949ba59abbe56e057f20f883e', '用户010', '13100000010', 'user010@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-10.png', 0, 1, '2022-06-28 09:00:00', '2022-06-28 09:00:00');
INSERT INTO `user` VALUES (12, 'user011', 'e10adc3949ba59abbe56e057f20f883e', '用户011', '13100000011', 'user011@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-11.png', 0, 1, '2022-07-01 09:00:00', '2022-07-01 09:00:00');
INSERT INTO `user` VALUES (13, 'user012', 'e10adc3949ba59abbe56e057f20f883e', '用户012', '13100000012', 'user012@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-12.png', 0, 1, '2022-07-04 09:00:00', '2022-07-04 09:00:00');
INSERT INTO `user` VALUES (14, 'user013', 'e10adc3949ba59abbe56e057f20f883e', '用户013', '13100000013', 'user013@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-01.png', 0, 1, '2022-07-07 09:00:00', '2022-07-07 09:00:00');
INSERT INTO `user` VALUES (15, 'user014', 'e10adc3949ba59abbe56e057f20f883e', '用户014', '13100000014', 'user014@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-02.png', 0, 1, '2022-07-10 09:00:00', '2022-07-10 09:00:00');
INSERT INTO `user` VALUES (16, 'user015', 'e10adc3949ba59abbe56e057f20f883e', '用户015', '13100000015', 'user015@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-03.png', 0, 1, '2022-07-13 09:00:00', '2022-07-13 09:00:00');
INSERT INTO `user` VALUES (17, 'user016', 'e10adc3949ba59abbe56e057f20f883e', '用户016', '13100000016', 'user016@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-04.png', 0, 1, '2022-07-16 09:00:00', '2022-07-16 09:00:00');
INSERT INTO `user` VALUES (18, 'user017', 'e10adc3949ba59abbe56e057f20f883e', '用户017', '13100000017', 'user017@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-05.png', 0, 1, '2022-07-19 09:00:00', '2022-07-19 09:00:00');
INSERT INTO `user` VALUES (19, 'user018', 'e10adc3949ba59abbe56e057f20f883e', '用户018', '13100000018', 'user018@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-06.png', 0, 1, '2022-07-22 09:00:00', '2022-07-22 09:00:00');
INSERT INTO `user` VALUES (20, 'user019', 'e10adc3949ba59abbe56e057f20f883e', '用户019', '13100000019', 'user019@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-07.png', 0, 1, '2022-07-25 09:00:00', '2022-07-25 09:00:00');
INSERT INTO `user` VALUES (21, 'user020', 'e10adc3949ba59abbe56e057f20f883e', '用户020', '13100000020', 'user020@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-08.png', 0, 1, '2022-07-28 09:00:00', '2022-07-28 09:00:00');
INSERT INTO `user` VALUES (22, 'user021', 'e10adc3949ba59abbe56e057f20f883e', '用户021', '13100000021', 'user021@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-09.png', 0, 1, '2022-07-31 09:00:00', '2022-07-31 09:00:00');
INSERT INTO `user` VALUES (23, 'user022', 'e10adc3949ba59abbe56e057f20f883e', '用户022', '13100000022', 'user022@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-10.png', 0, 1, '2022-08-03 09:00:00', '2022-08-03 09:00:00');
INSERT INTO `user` VALUES (24, 'user023', 'e10adc3949ba59abbe56e057f20f883e', '用户023', '13100000023', 'user023@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-11.png', 0, 1, '2022-08-06 09:00:00', '2022-08-06 09:00:00');
INSERT INTO `user` VALUES (25, 'user024', 'e10adc3949ba59abbe56e057f20f883e', '用户024', '13100000024', 'user024@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-12.png', 0, 1, '2022-08-09 09:00:00', '2022-08-09 09:00:00');
INSERT INTO `user` VALUES (26, 'user025', 'e10adc3949ba59abbe56e057f20f883e', '用户025', '13100000025', 'user025@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-01.png', 0, 1, '2022-08-12 09:00:00', '2022-08-12 09:00:00');
INSERT INTO `user` VALUES (27, 'user026', 'e10adc3949ba59abbe56e057f20f883e', '用户026', '13100000026', 'user026@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-02.png', 0, 1, '2022-08-15 09:00:00', '2022-08-15 09:00:00');
INSERT INTO `user` VALUES (28, 'user027', 'e10adc3949ba59abbe56e057f20f883e', '用户027', '13100000027', 'user027@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-03.png', 0, 1, '2022-08-18 09:00:00', '2022-08-18 09:00:00');
INSERT INTO `user` VALUES (29, 'user028', 'e10adc3949ba59abbe56e057f20f883e', '用户028', '13100000028', 'user028@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-04.png', 0, 1, '2022-08-21 09:00:00', '2022-08-21 09:00:00');
INSERT INTO `user` VALUES (30, 'user029', 'e10adc3949ba59abbe56e057f20f883e', '用户029', '13100000029', 'user029@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-05.png', 0, 1, '2022-08-24 09:00:00', '2022-08-24 09:00:00');
INSERT INTO `user` VALUES (31, 'user030', 'e10adc3949ba59abbe56e057f20f883e', '用户030', '13100000030', 'user030@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-06.png', 0, 1, '2022-08-27 09:00:00', '2022-08-27 09:00:00');
INSERT INTO `user` VALUES (32, 'user031', 'e10adc3949ba59abbe56e057f20f883e', '用户031', '13100000031', 'user031@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-07.png', 0, 1, '2022-08-30 09:00:00', '2022-08-30 09:00:00');
INSERT INTO `user` VALUES (33, 'user032', 'e10adc3949ba59abbe56e057f20f883e', '用户032', '13100000032', 'user032@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-08.png', 0, 1, '2022-09-02 09:00:00', '2022-09-02 09:00:00');
INSERT INTO `user` VALUES (34, 'user033', 'e10adc3949ba59abbe56e057f20f883e', '用户033', '13100000033', 'user033@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-09.png', 0, 1, '2022-09-05 09:00:00', '2022-09-05 09:00:00');
INSERT INTO `user` VALUES (35, 'user034', 'e10adc3949ba59abbe56e057f20f883e', '用户034', '13100000034', 'user034@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-10.png', 0, 1, '2022-09-08 09:00:00', '2022-09-08 09:00:00');
INSERT INTO `user` VALUES (36, 'user035', 'e10adc3949ba59abbe56e057f20f883e', '用户035', '13100000035', 'user035@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-11.png', 0, 1, '2022-09-11 09:00:00', '2022-09-11 09:00:00');
INSERT INTO `user` VALUES (37, 'user036', 'e10adc3949ba59abbe56e057f20f883e', '用户036', '13100000036', 'user036@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-12.png', 0, 1, '2022-09-14 09:00:00', '2022-09-14 09:00:00');
INSERT INTO `user` VALUES (38, 'user037', 'e10adc3949ba59abbe56e057f20f883e', '用户037', '13100000037', 'user037@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-01.png', 0, 1, '2022-09-17 09:00:00', '2022-09-17 09:00:00');
INSERT INTO `user` VALUES (39, 'user038', 'e10adc3949ba59abbe56e057f20f883e', '用户038', '13100000038', 'user038@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-02.png', 0, 1, '2022-09-20 09:00:00', '2022-09-20 09:00:00');
INSERT INTO `user` VALUES (40, 'user039', 'e10adc3949ba59abbe56e057f20f883e', '用户039', '13100000039', 'user039@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-03.png', 0, 1, '2022-09-23 09:00:00', '2022-09-23 09:00:00');
INSERT INTO `user` VALUES (41, 'user040', 'e10adc3949ba59abbe56e057f20f883e', '用户040', '13100000040', 'user040@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-04.png', 0, 1, '2022-09-26 09:00:00', '2022-09-26 09:00:00');
INSERT INTO `user` VALUES (42, 'user041', 'e10adc3949ba59abbe56e057f20f883e', '用户041', '13100000041', 'user041@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-05.png', 0, 1, '2022-09-29 09:00:00', '2022-09-29 09:00:00');
INSERT INTO `user` VALUES (43, 'user042', 'e10adc3949ba59abbe56e057f20f883e', '用户042', '13100000042', 'user042@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-06.png', 0, 1, '2022-10-02 09:00:00', '2022-10-02 09:00:00');
INSERT INTO `user` VALUES (44, 'user043', 'e10adc3949ba59abbe56e057f20f883e', '用户043', '13100000043', 'user043@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-07.png', 0, 1, '2022-10-05 09:00:00', '2022-10-05 09:00:00');
INSERT INTO `user` VALUES (45, 'user044', 'e10adc3949ba59abbe56e057f20f883e', '用户044', '13100000044', 'user044@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-08.png', 0, 1, '2022-10-08 09:00:00', '2022-10-08 09:00:00');
INSERT INTO `user` VALUES (46, 'user045', 'e10adc3949ba59abbe56e057f20f883e', '用户045', '13100000045', 'user045@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-09.png', 0, 1, '2022-10-11 09:00:00', '2022-10-11 09:00:00');
INSERT INTO `user` VALUES (47, 'user046', 'e10adc3949ba59abbe56e057f20f883e', '用户046', '13100000046', 'user046@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-10.png', 0, 1, '2022-10-14 09:00:00', '2022-10-14 09:00:00');
INSERT INTO `user` VALUES (48, 'user047', 'e10adc3949ba59abbe56e057f20f883e', '用户047', '13100000047', 'user047@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-11.png', 0, 1, '2022-10-17 09:00:00', '2022-10-17 09:00:00');
INSERT INTO `user` VALUES (49, 'user048', 'e10adc3949ba59abbe56e057f20f883e', '用户048', '13100000048', 'user048@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-12.png', 0, 1, '2022-10-20 09:00:00', '2022-10-20 09:00:00');
INSERT INTO `user` VALUES (50, 'user049', 'e10adc3949ba59abbe56e057f20f883e', '用户049', '13100000049', 'user049@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-01.png', 0, 1, '2022-10-23 09:00:00', '2022-10-23 09:00:00');
INSERT INTO `user` VALUES (51, 'user050', 'e10adc3949ba59abbe56e057f20f883e', '用户050', '13100000050', 'user050@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-02.png', 0, 1, '2022-10-26 09:00:00', '2022-10-26 09:00:00');
INSERT INTO `user` VALUES (52, 'user051', 'e10adc3949ba59abbe56e057f20f883e', '用户051', '13100000051', 'user051@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-03.png', 0, 1, '2022-10-29 09:00:00', '2022-10-29 09:00:00');
INSERT INTO `user` VALUES (53, 'user052', 'e10adc3949ba59abbe56e057f20f883e', '用户052', '13100000052', 'user052@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-04.png', 0, 1, '2022-11-01 09:00:00', '2022-11-01 09:00:00');
INSERT INTO `user` VALUES (54, 'user053', 'e10adc3949ba59abbe56e057f20f883e', '用户053', '13100000053', 'user053@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-05.png', 0, 1, '2022-11-04 09:00:00', '2022-11-04 09:00:00');
INSERT INTO `user` VALUES (55, 'user054', 'e10adc3949ba59abbe56e057f20f883e', '用户054', '13100000054', 'user054@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-06.png', 0, 1, '2022-11-07 09:00:00', '2022-11-07 09:00:00');
INSERT INTO `user` VALUES (56, 'user055', 'e10adc3949ba59abbe56e057f20f883e', '用户055', '13100000055', 'user055@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-07.png', 0, 1, '2022-11-10 09:00:00', '2022-11-10 09:00:00');
INSERT INTO `user` VALUES (57, 'user056', 'e10adc3949ba59abbe56e057f20f883e', '用户056', '13100000056', 'user056@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-08.png', 0, 1, '2022-11-13 09:00:00', '2022-11-13 09:00:00');
INSERT INTO `user` VALUES (58, 'user057', 'e10adc3949ba59abbe56e057f20f883e', '用户057', '13100000057', 'user057@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-09.png', 0, 1, '2022-11-16 09:00:00', '2022-11-16 09:00:00');
INSERT INTO `user` VALUES (59, 'user058', 'e10adc3949ba59abbe56e057f20f883e', '用户058', '13100000058', 'user058@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-10.png', 0, 1, '2022-11-19 09:00:00', '2022-11-19 09:00:00');
INSERT INTO `user` VALUES (60, 'user059', 'e10adc3949ba59abbe56e057f20f883e', '用户059', '13100000059', 'user059@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-11.png', 0, 1, '2022-11-22 09:00:00', '2022-11-22 09:00:00');
INSERT INTO `user` VALUES (61, 'user060', 'e10adc3949ba59abbe56e057f20f883e', '用户060', '13100000060', 'user060@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-12.png', 0, 1, '2022-11-25 09:00:00', '2022-11-25 09:00:00');
INSERT INTO `user` VALUES (62, 'user061', 'e10adc3949ba59abbe56e057f20f883e', '用户061', '13100000061', 'user061@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-01.png', 0, 1, '2022-11-28 09:00:00', '2022-11-28 09:00:00');
INSERT INTO `user` VALUES (63, 'user062', 'e10adc3949ba59abbe56e057f20f883e', '用户062', '13100000062', 'user062@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-02.png', 0, 1, '2022-12-01 09:00:00', '2022-12-01 09:00:00');
INSERT INTO `user` VALUES (64, 'user063', 'e10adc3949ba59abbe56e057f20f883e', '用户063', '13100000063', 'user063@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-03.png', 0, 1, '2022-12-04 09:00:00', '2022-12-04 09:00:00');
INSERT INTO `user` VALUES (65, 'user064', 'e10adc3949ba59abbe56e057f20f883e', '用户064', '13100000064', 'user064@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-04.png', 0, 1, '2022-12-07 09:00:00', '2022-12-07 09:00:00');
INSERT INTO `user` VALUES (66, 'user065', 'e10adc3949ba59abbe56e057f20f883e', '用户065', '13100000065', 'user065@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-05.png', 0, 1, '2022-12-10 09:00:00', '2022-12-10 09:00:00');
INSERT INTO `user` VALUES (67, 'user066', 'e10adc3949ba59abbe56e057f20f883e', '用户066', '13100000066', 'user066@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-06.png', 0, 1, '2022-12-13 09:00:00', '2022-12-13 09:00:00');
INSERT INTO `user` VALUES (68, 'user067', 'e10adc3949ba59abbe56e057f20f883e', '用户067', '13100000067', 'user067@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-07.png', 0, 1, '2022-12-16 09:00:00', '2022-12-16 09:00:00');
INSERT INTO `user` VALUES (69, 'user068', 'e10adc3949ba59abbe56e057f20f883e', '用户068', '13100000068', 'user068@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-08.png', 0, 1, '2022-12-19 09:00:00', '2022-12-19 09:00:00');
INSERT INTO `user` VALUES (70, 'user069', 'e10adc3949ba59abbe56e057f20f883e', '用户069', '13100000069', 'user069@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-09.png', 0, 1, '2022-12-22 09:00:00', '2022-12-22 09:00:00');
INSERT INTO `user` VALUES (71, 'user070', 'e10adc3949ba59abbe56e057f20f883e', '用户070', '13100000070', 'user070@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-10.png', 0, 1, '2022-12-25 09:00:00', '2022-12-25 09:00:00');
INSERT INTO `user` VALUES (72, 'user071', 'e10adc3949ba59abbe56e057f20f883e', '用户071', '13100000071', 'user071@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-11.png', 0, 1, '2022-12-28 09:00:00', '2022-12-28 09:00:00');
INSERT INTO `user` VALUES (73, 'user072', 'e10adc3949ba59abbe56e057f20f883e', '用户072', '13100000072', 'user072@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-12.png', 0, 1, '2022-12-31 09:00:00', '2022-12-31 09:00:00');
INSERT INTO `user` VALUES (74, 'user073', 'e10adc3949ba59abbe56e057f20f883e', '用户073', '13100000073', 'user073@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-01.png', 0, 1, '2023-01-03 09:00:00', '2023-01-03 09:00:00');
INSERT INTO `user` VALUES (75, 'user074', 'e10adc3949ba59abbe56e057f20f883e', '用户074', '13100000074', 'user074@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-02.png', 0, 1, '2023-01-06 09:00:00', '2023-01-06 09:00:00');
INSERT INTO `user` VALUES (76, 'user075', 'e10adc3949ba59abbe56e057f20f883e', '用户075', '13100000075', 'user075@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-03.png', 0, 1, '2023-01-09 09:00:00', '2023-01-09 09:00:00');
INSERT INTO `user` VALUES (77, 'user076', 'e10adc3949ba59abbe56e057f20f883e', '用户076', '13100000076', 'user076@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-04.png', 0, 1, '2023-01-12 09:00:00', '2023-01-12 09:00:00');
INSERT INTO `user` VALUES (78, 'user077', 'e10adc3949ba59abbe56e057f20f883e', '用户077', '13100000077', 'user077@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-05.png', 0, 1, '2023-01-15 09:00:00', '2023-01-15 09:00:00');
INSERT INTO `user` VALUES (79, 'user078', 'e10adc3949ba59abbe56e057f20f883e', '用户078', '13100000078', 'user078@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-06.png', 0, 1, '2023-01-18 09:00:00', '2023-01-18 09:00:00');
INSERT INTO `user` VALUES (80, 'user079', 'e10adc3949ba59abbe56e057f20f883e', '用户079', '13100000079', 'user079@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-07.png', 0, 1, '2023-01-21 09:00:00', '2023-01-21 09:00:00');
INSERT INTO `user` VALUES (81, 'user080', 'e10adc3949ba59abbe56e057f20f883e', '用户080', '13100000080', 'user080@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-08.png', 0, 1, '2023-01-24 09:00:00', '2023-01-24 09:00:00');
INSERT INTO `user` VALUES (82, 'user081', 'e10adc3949ba59abbe56e057f20f883e', '用户081', '13100000081', 'user081@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-09.png', 0, 1, '2023-01-27 09:00:00', '2023-01-27 09:00:00');
INSERT INTO `user` VALUES (83, 'user082', 'e10adc3949ba59abbe56e057f20f883e', '用户082', '13100000082', 'user082@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-10.png', 0, 1, '2023-01-30 09:00:00', '2023-01-30 09:00:00');
INSERT INTO `user` VALUES (84, 'user083', 'e10adc3949ba59abbe56e057f20f883e', '用户083', '13100000083', 'user083@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-11.png', 0, 1, '2023-02-02 09:00:00', '2023-02-02 09:00:00');
INSERT INTO `user` VALUES (85, 'user084', 'e10adc3949ba59abbe56e057f20f883e', '用户084', '13100000084', 'user084@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-12.png', 0, 1, '2023-02-05 09:00:00', '2023-02-05 09:00:00');
INSERT INTO `user` VALUES (86, 'user085', 'e10adc3949ba59abbe56e057f20f883e', '用户085', '13100000085', 'user085@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-01.png', 0, 1, '2023-02-08 09:00:00', '2023-02-08 09:00:00');
INSERT INTO `user` VALUES (87, 'user086', 'e10adc3949ba59abbe56e057f20f883e', '用户086', '13100000086', 'user086@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-02.png', 0, 1, '2023-02-11 09:00:00', '2023-02-11 09:00:00');
INSERT INTO `user` VALUES (88, 'user087', 'e10adc3949ba59abbe56e057f20f883e', '用户087', '13100000087', 'user087@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-03.png', 0, 1, '2023-02-14 09:00:00', '2023-02-14 09:00:00');
INSERT INTO `user` VALUES (89, 'user088', 'e10adc3949ba59abbe56e057f20f883e', '用户088', '13100000088', 'user088@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-04.png', 0, 1, '2023-02-17 09:00:00', '2023-02-17 09:00:00');
INSERT INTO `user` VALUES (90, 'user089', 'e10adc3949ba59abbe56e057f20f883e', '用户089', '13100000089', 'user089@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-05.png', 0, 1, '2023-02-20 09:00:00', '2023-02-20 09:00:00');
INSERT INTO `user` VALUES (91, 'user090', 'e10adc3949ba59abbe56e057f20f883e', '用户090', '13100000090', 'user090@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-06.png', 0, 1, '2023-02-23 09:00:00', '2023-02-23 09:00:00');
INSERT INTO `user` VALUES (92, 'user091', 'e10adc3949ba59abbe56e057f20f883e', '用户091', '13100000091', 'user091@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-07.png', 0, 1, '2023-02-26 09:00:00', '2023-02-26 09:00:00');
INSERT INTO `user` VALUES (93, 'user092', 'e10adc3949ba59abbe56e057f20f883e', '用户092', '13100000092', 'user092@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-08.png', 0, 1, '2023-03-01 09:00:00', '2023-03-01 09:00:00');
INSERT INTO `user` VALUES (94, 'user093', 'e10adc3949ba59abbe56e057f20f883e', '用户093', '13100000093', 'user093@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-09.png', 0, 1, '2023-03-04 09:00:00', '2023-03-04 09:00:00');
INSERT INTO `user` VALUES (95, 'user094', 'e10adc3949ba59abbe56e057f20f883e', '用户094', '13100000094', 'user094@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-10.png', 0, 1, '2023-03-07 09:00:00', '2023-03-07 09:00:00');
INSERT INTO `user` VALUES (96, 'user095', 'e10adc3949ba59abbe56e057f20f883e', '用户095', '13100000095', 'user095@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-11.png', 0, 1, '2023-03-10 09:00:00', '2023-03-10 09:00:00');
INSERT INTO `user` VALUES (97, 'user096', 'e10adc3949ba59abbe56e057f20f883e', '用户096', '13100000096', 'user096@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-12.png', 0, 1, '2023-03-13 09:00:00', '2023-03-13 09:00:00');
INSERT INTO `user` VALUES (98, 'user097', 'e10adc3949ba59abbe56e057f20f883e', '用户097', '13100000097', 'user097@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-01.png', 0, 1, '2023-03-16 09:00:00', '2023-03-16 09:00:00');
INSERT INTO `user` VALUES (99, 'user098', 'e10adc3949ba59abbe56e057f20f883e', '用户098', '13100000098', 'user098@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-02.png', 0, 1, '2023-03-19 09:00:00', '2023-03-19 09:00:00');
INSERT INTO `user` VALUES (100, 'user099', 'e10adc3949ba59abbe56e057f20f883e', '用户099', '13100000099', 'user099@example.com', 'https://thehim-java-web.oss-cn-beijing.aliyuncs.com/avatar/user-03.png', 0, 1, '2023-03-22 09:00:00', '2023-03-22 09:00:00');

SET FOREIGN_KEY_CHECKS = 1;

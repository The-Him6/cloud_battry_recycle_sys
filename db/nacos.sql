/*
 Navicat Premium Dump SQL

 Source Server         : centos7brs
 Source Server Type    : MySQL
 Source Server Version : 80410 (8.4.10)
 Source Host           : 192.168.150.102:3307
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 80410 (8.4.10)
 File Encoding         : 65001

 Date: 29/08/2026 17:07:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'group_id',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
  `c_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'configuration description',
  `c_use` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'configuration usage',
  `effect` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'é…ç½®ç”Ÿæ•ˆçš„æè¿°',
  `type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'é…ç½®çš„ç±»åž‹',
  `c_schema` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'é…ç½®çš„æ¨¡å¼',
  `encrypted_data_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'å¯†é’¥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'shared-jdbc.yaml', 'DEFAULT_GROUP', 'spring:\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://${br.db.host:192.168.150.102}:${br.db.port:3307}/${br.db.database}?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true\r\n    username: ${br.db.un:root}\r\n    password: ${br.db.pw:123}\r\nmybatis:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  configuration:\r\n    map-underscore-to-camel-case: true\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '64f5da37097196cd3bb6d471263c9f79', '2026-08-27 15:40:53', '2026-08-27 15:40:53', NULL, '192.168.150.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (2, 'shared-log.yaml', 'DEFAULT_GROUP', 'logging:\r\n  level:\r\n    com.br: debug\r\n  pattern:\r\n    dateformat: HH:mm:ss:SSS\r\n  file:\r\n    path: \"C:/Users/Administrator/Desktop/论文/毕设废电池回收系统的设计与实现/battery-recycle-cloud/logs/${spring.application.name}\"', 'f680854be3db7e8c558275002e3581eb', '2026-08-27 15:40:54', '2026-08-27 15:40:54', NULL, '192.168.150.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (3, 'shared-swagger.yaml', 'DEFAULT_GROUP', 'springdoc:\r\n  api-docs:\r\n    enabled: true\r\n  swagger-ui:\r\n    enabled: true\r\n    path: /swagger-ui.html\r\nknife4j:\r\n  enable: true\r\n  setting:\r\n    language: zh_cn', 'acd02febfc0a950294ce37a96f95d301', '2026-08-27 15:40:54', '2026-08-27 15:40:54', NULL, '192.168.150.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (4, 'shared-redis.yaml', 'DEFAULT_GROUP', 'spring:\r\n  data:\r\n    redis:\r\n      host: ${REDIS_HOST:192.168.150.102}\r\n      port: ${REDIS_PORT:6379}\r\n      password: ${REDIS_PASSWORD:123456}\r\n      database: ${REDIS_DATABASE:0}\r\n      timeout: 3000ms\r\n      lettuce:\r\n        pool:\r\n          max-active: 16\r\n          max-idle: 8\r\n          min-idle: 1\r\n          max-wait: 3000ms', 'efefe0e7f6bedfa7b120980026cb3f4e', '2026-08-27 15:40:54', '2026-08-27 15:40:54', NULL, '192.168.150.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (5, 'shared-jackson.yaml', 'DEFAULT_GROUP', 'spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  servlet:\r\n    multipart:\r\n      max-file-size: 10MB\r\n      max-request-size: 10MB', '0c7efc5280d1723229edf04eee288c82', '2026-08-27 15:40:54', '2026-08-27 15:40:54', NULL, '192.168.150.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (6, 'shared-aliyunoss.yaml', 'DEFAULT_GROUP', 'aliyun:\r\n  oss:\r\n    endpoint: ${OSS_ENDPOINT:https://oss-cn-beijing.aliyuncs.com}\r\n    access-key-id: ${OSS_ACCESS_KEY_ID}\r\n    access-key-secret: ${OSS_ACCESS_KEY_SECRET}\r\n    bucket-name: ${OSS_BUCKET_NAME}', '0bf07237414991e00930fd8737cf25fd', '2026-08-27 15:40:55', '2026-08-27 15:40:55', NULL, '192.168.150.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (7, 'shared-common.yaml', 'DEFAULT_GROUP', 'aliyun:\n  oss:\n    endpoint: ${OSS_ENDPOINT:https://oss-cn-beijing.aliyuncs.com}\n    access-key-id: ${OSS_ACCESS_KEY_ID}\n    access-key-secret: ${OSS_ACCESS_KEY_SECRET}\n    bucket-name: ${OSS_BUCKET_NAME}\nspring:\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  servlet:\n    multipart:\n      max-file-size: 10MB\n      max-request-size: 10MB', '57def382efa1c279266a43ca814d5c8f', '2026-08-27 15:40:55', '2026-08-27 15:40:55', NULL, '192.168.150.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (8, 'shared-seata.yaml', 'DEFAULT_GROUP', 'seata:\n  registry: # TC服务注册中心的配置，微服务根据这些信息去注册中心获取tc服务地址\n    type: nacos # 注册中心类型 nacos\n    nacos:\n      server-addr: 192.168.150.102:8850 # nacos地址\n      namespace: \"\" # namespace，默认为空\n      group: DEFAULT_GROUP # 分组，默认是DEFAULT_GROUP\n      application: seata-server # seata服务名称\n      username: nacos\n      password: nacos\n  tx-service-group: br-group # 事务组名称\n  service:\n    vgroup-mapping: # 事务组与tc集群的映射关系\n      br-group: \"default\"\n  data-source-proxy-mode: AT', 'c38a80e1736ae84ac435569c1e79402d', '2026-08-28 12:20:33', '2026-08-28 12:38:53', 'nacos', '192.168.150.1', '', '', 'seata客户端共享配置', '', '', 'yaml', '', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'å†…å®¹',
  `gmt_modified` datetime NOT NULL COMMENT 'ä¿®æ”¹æ—¶é—´',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC, `datum_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'å¢žåŠ ç§Ÿæˆ·å­—æ®µ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
  `encrypted_data_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'å¯†é’¥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC, `tag_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT COMMENT 'nid, è‡ªå¢žé•¿æ ‡è¯†',
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id` ASC, `tag_name` ASC, `tag_type` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®ID',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Group IDï¼Œç©ºå­—ç¬¦è¡¨ç¤ºæ•´ä¸ªé›†ç¾¤',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'é…é¢ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ä½¿ç”¨é‡',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'å•ä¸ªé…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'èšåˆå­é…ç½®æœ€å¤§ä¸ªæ•°ï¼Œï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'å•ä¸ªèšåˆæ•°æ®çš„å­é…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'æœ€å¤§å˜æ›´åŽ†å²æ•°é‡',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'é›†ç¾¤ã€å„Groupå®¹é‡ä¿¡æ¯è¡¨' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL COMMENT 'id',
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'nid, è‡ªå¢žæ ‡è¯†',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  `op_type` char(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'operation type',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
  `encrypted_data_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'å¯†é’¥',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create` ASC) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified` ASC) USING BTREE,
  INDEX `idx_did`(`data_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'å¤šç§Ÿæˆ·æ”¹é€ ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'shared-jdbc.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://${br.db.host:192.168.150.102}:${br.db.port:3307}/${br.db.database}?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true\r\n    username: ${br.db.un:root}\r\n    password: ${br.db.pw:123}\r\nmybatis:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  configuration:\r\n    map-underscore-to-camel-case: true\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl', '64f5da37097196cd3bb6d471263c9f79', '2026-08-27 15:40:53', '2026-08-27 15:40:53', NULL, '192.168.150.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 2, 'shared-log.yaml', 'DEFAULT_GROUP', '', 'logging:\r\n  level:\r\n    com.br: debug\r\n  pattern:\r\n    dateformat: HH:mm:ss:SSS\r\n  file:\r\n    path: \"C:/Users/Administrator/Desktop/论文/毕设废电池回收系统的设计与实现/battery-recycle-cloud/logs/${spring.application.name}\"', 'f680854be3db7e8c558275002e3581eb', '2026-08-27 15:40:53', '2026-08-27 15:40:54', NULL, '192.168.150.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 3, 'shared-swagger.yaml', 'DEFAULT_GROUP', '', 'springdoc:\r\n  api-docs:\r\n    enabled: true\r\n  swagger-ui:\r\n    enabled: true\r\n    path: /swagger-ui.html\r\nknife4j:\r\n  enable: true\r\n  setting:\r\n    language: zh_cn', 'acd02febfc0a950294ce37a96f95d301', '2026-08-27 15:40:54', '2026-08-27 15:40:54', NULL, '192.168.150.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 4, 'shared-redis.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  data:\r\n    redis:\r\n      host: ${REDIS_HOST:192.168.150.102}\r\n      port: ${REDIS_PORT:6379}\r\n      password: ${REDIS_PASSWORD:123456}\r\n      database: ${REDIS_DATABASE:0}\r\n      timeout: 3000ms\r\n      lettuce:\r\n        pool:\r\n          max-active: 16\r\n          max-idle: 8\r\n          min-idle: 1\r\n          max-wait: 3000ms', 'efefe0e7f6bedfa7b120980026cb3f4e', '2026-08-27 15:40:54', '2026-08-27 15:40:54', NULL, '192.168.150.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 5, 'shared-jackson.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  servlet:\r\n    multipart:\r\n      max-file-size: 10MB\r\n      max-request-size: 10MB', '0c7efc5280d1723229edf04eee288c82', '2026-08-27 15:40:54', '2026-08-27 15:40:54', NULL, '192.168.150.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 6, 'shared-aliyunoss.yaml', 'DEFAULT_GROUP', '', 'aliyun:\r\n  oss:\r\n    endpoint: ${OSS_ENDPOINT:https://oss-cn-beijing.aliyuncs.com}\r\n    access-key-id: ${OSS_ACCESS_KEY_ID}\r\n    access-key-secret: ${OSS_ACCESS_KEY_SECRET}\r\n    bucket-name: ${OSS_BUCKET_NAME}', '0bf07237414991e00930fd8737cf25fd', '2026-08-27 15:40:54', '2026-08-27 15:40:55', NULL, '192.168.150.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 7, 'shared-common.yaml', 'DEFAULT_GROUP', '', 'aliyun:\n  oss:\n    endpoint: ${OSS_ENDPOINT:https://oss-cn-beijing.aliyuncs.com}\n    access-key-id: ${OSS_ACCESS_KEY_ID}\n    access-key-secret: ${OSS_ACCESS_KEY_SECRET}\n    bucket-name: ${OSS_BUCKET_NAME}\nspring:\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  servlet:\n    multipart:\n      max-file-size: 10MB\n      max-request-size: 10MB', '57def382efa1c279266a43ca814d5c8f', '2026-08-27 15:40:54', '2026-08-27 15:40:55', NULL, '192.168.150.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 8, 'shared-seata.yaml', 'DEFAULT_GROUP', '', 'seata:\r\n  registry: # TC服务注册中心的配置，微服务根据这些信息去注册中心获取tc服务地址\r\n    type: nacos # 注册中心类型 nacos\r\n    nacos:\r\n      server-addr: 192.168.150.102:8850 # nacos地址\r\n      namespace: \"\" # namespace，默认为空\r\n      group: DEFAULT_GROUP # 分组，默认是DEFAULT_GROUP\r\n      application: seata-server # seata服务名称\r\n      username: nacos\r\n      password: nacos\r\n  tx-service-group: br-group # 事务组名称\r\n  service:\r\n    vgroup-mapping: # 事务组与tc集群的映射关系\r\n      br-group: \"default\"\r\n', 'b197e23700e983195eba8959b6289b2f', '2026-08-28 12:20:32', '2026-08-28 12:20:33', NULL, '192.168.150.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (8, 9, 'shared-seata.yaml', 'DEFAULT_GROUP', '', 'seata:\r\n  registry: # TC服务注册中心的配置，微服务根据这些信息去注册中心获取tc服务地址\r\n    type: nacos # 注册中心类型 nacos\r\n    nacos:\r\n      server-addr: 192.168.150.102:8850 # nacos地址\r\n      namespace: \"\" # namespace，默认为空\r\n      group: DEFAULT_GROUP # 分组，默认是DEFAULT_GROUP\r\n      application: seata-server # seata服务名称\r\n      username: nacos\r\n      password: nacos\r\n  tx-service-group: br-group # 事务组名称\r\n  service:\r\n    vgroup-mapping: # 事务组与tc集群的映射关系\r\n      br-group: \"default\"\r\n', 'b197e23700e983195eba8959b6289b2f', '2026-08-28 12:38:52', '2026-08-28 12:38:53', 'nacos', '192.168.150.1', 'U', '', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'role',
  `resource` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'resource',
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'action',
  UNIQUE INDEX `uk_role_permission`(`role` ASC, `resource` ASC, `action` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'username',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'role',
  UNIQUE INDEX `idx_user_role`(`username` ASC, `role` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®ID',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'é…é¢ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ä½¿ç”¨é‡',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'å•ä¸ªé…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'èšåˆå­é…ç½®æœ€å¤§ä¸ªæ•°',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'å•ä¸ªèšåˆæ•°æ®çš„å­é…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT 'æœ€å¤§å˜æ›´åŽ†å²æ•°é‡',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'ç§Ÿæˆ·å®¹é‡ä¿¡æ¯è¡¨' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` bigint NOT NULL COMMENT 'ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp` ASC, `tenant_id` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'username',
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'password',
  `enabled` tinyint(1) NOT NULL COMMENT 'enabled',
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;

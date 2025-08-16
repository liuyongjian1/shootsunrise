-- =====================================================
-- 个人摄影师微信小程序数据库建表脚本（精简索引版）
-- 文档版本: v1.2
-- 创建日期: 2025-07-28
-- 作者: 开发团队
-- =====================================================

-- 创建数据库（保持不变）
CREATE DATABASE IF NOT EXISTS shoot_sunrise 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE shoot_sunrise;

SET time_zone = '+08:00';

/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80404 (8.4.4)
 Source Host           : localhost:3306
 Source Schema         : shoot_sunrise

 Target Server Type    : MySQL
 Target Server Version : 80404 (8.4.4)
 File Encoding         : 65001

 Date: 27/07/2025 23:05:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_users
-- ----------------------------
DROP TABLE IF EXISTS `admin_users`;
CREATE TABLE `admin_users`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码(加密)',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` int NULL DEFAULT 1 COMMENT '角色(int)：0-超级管理员，1-系统管理员（默认1）',
  `permissions` json NULL COMMENT '权限列表',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态(int)：0-禁用，1-正常（默认1）',
  `last_login_at` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  INDEX `idx_username`(`username` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_users
-- ----------------------------

-- ----------------------------
-- Table structure for audit_logs
-- ----------------------------
DROP TABLE IF EXISTS `audit_logs`;
CREATE TABLE `audit_logs`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '审核日志ID',
  `audit_type` int NOT NULL COMMENT '审核类型(int)：0-PORTFOLIO，1-PHOTOGRAPHER，2-PROMOTION（默认0）',
  `target_id` bigint NOT NULL COMMENT '目标ID',
  `target_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '目标类型',
  `audit_status` int NOT NULL DEFAULT 0 COMMENT '审核状态(int)：0-PENDING，1-APPROVED，2-REJECTED（默认0）',
  `auditor_id` bigint NULL DEFAULT NULL COMMENT '审核员ID',
  `auditor_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核员姓名',
  `audit_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核意见',
  `audit_comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '审核备注',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_audit_type`(`audit_type` ASC) USING BTREE,
  INDEX `idx_target`(`target_type` ASC, `target_id` ASC) USING BTREE,
  INDEX `idx_audit_status`(`audit_status` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '内容审核日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of audit_logs
-- ----------------------------

-- ----------------------------
-- Table structure for bookings
-- ----------------------------
DROP TABLE IF EXISTS `bookings`;
CREATE TABLE `bookings`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '预约ID',
  `booking_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '预约编号',
  `client_id` bigint NOT NULL COMMENT '客户ID - 关联users.id',
  `photographer_id` bigint NOT NULL COMMENT '摄影师ID - 关联users.id',
  `package_id` bigint NULL DEFAULT NULL COMMENT '套餐ID - 关联service_packages.id',
  `status` int NULL DEFAULT 0 COMMENT '预约状态(int)：0-PENDING，1-CONFIRMED，2-REJECTED，3-CANCELLED，4-COMPLETED（默认0）',
  `shoot_date` date NOT NULL COMMENT '拍摄日期',
  `shoot_time` time NOT NULL COMMENT '拍摄时间',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '拍摄地点',
  `theme` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '拍摄主题',
  `requirements` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '特殊要求',
  `contact_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系电话',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `deposit_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '定金金额',
  `final_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '尾款金额',
  `confirmed_at` datetime NULL DEFAULT NULL COMMENT '确认时间',
  `cancelled_at` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `cancel_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '取消原因',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `booking_no`(`booking_no` ASC) USING BTREE,
  INDEX `idx_client_id`(`client_id` ASC) USING BTREE,
  INDEX `idx_photographer_id`(`photographer_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_shoot_date`(`shoot_date` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '预约表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bookings
-- ----------------------------

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
  `user_id` bigint NOT NULL COMMENT '用户ID - 关联users.id',
  `target_type` int NOT NULL COMMENT '收藏类型(int)：0-PHOTOGRAPHER，1-PORTFOLIO，2-PACKAGE（默认0）',
  `target_id` bigint NOT NULL COMMENT '目标ID',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_target`(`user_id` ASC, `target_type` ASC, `target_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '收藏记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorites
-- ----------------------------

-- ----------------------------
-- Table structure for file_uploads
-- ----------------------------
DROP TABLE IF EXISTS `file_uploads`;
CREATE TABLE `file_uploads`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `user_id` bigint NOT NULL COMMENT '上传用户ID - 关联users.id',
  `original_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '原始文件名',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '存储文件名',
  `file_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件路径',
  `file_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '访问URL',
  `file_size` bigint NOT NULL COMMENT '文件大小(字节)',
  `file_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `mime_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'MIME类型',
  `storage_type` int NULL DEFAULT 0 COMMENT '存储类型(int)：0-LOCAL，1-OSS，2-CDN（默认0）',
  `business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务类型',
  `business_id` bigint NULL DEFAULT NULL COMMENT '业务ID',
  `is_temp` tinyint(1) NULL DEFAULT 0 COMMENT '是否临时文件(int)：0-否，1-是（默认0）',
  `expire_at` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_business`(`business_type` ASC, `business_id` ASC) USING BTREE,
  INDEX `idx_is_temp`(`is_temp` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件上传记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_uploads
-- ----------------------------

-- ----------------------------
-- Table structure for notifications
-- ----------------------------
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `user_id` bigint NOT NULL COMMENT '用户ID - 关联users.id',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '通知类型',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '通知标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通知内容',
  `data` json NULL COMMENT '附加数据',
  `is_read` tinyint(1) NULL DEFAULT 0 COMMENT '是否已读(int)：0-未读，1-已读（默认0）',
  `read_at` datetime NULL DEFAULT NULL COMMENT '阅读时间',
  `priority` int NULL DEFAULT 1 COMMENT '优先级(int)：0-LOW，1-NORMAL，2-HIGH，3-URGENT（默认1）',
  `expire_at` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_created`(`user_id` ASC, `create_time` ASC) USING BTREE,
  INDEX `idx_is_read`(`is_read` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notifications
-- ----------------------------

-- ----------------------------
-- Table structure for operation_logs
-- ----------------------------
DROP TABLE IF EXISTS `operation_logs`;
CREATE TABLE `operation_logs`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '操作用户ID',
  `operation_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作类型',
  `operation_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作描述',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求方法',
  `request_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求URL',
  `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求参数',
  `response_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '响应数据',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `user_agent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户代理',
  `execution_time` int NULL DEFAULT NULL COMMENT '执行时间(毫秒)',
  `status` int NULL DEFAULT 0 COMMENT '执行状态(int)：0-SUCCESS，1-FAILED，2-ERROR（默认0）',
  `error_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '错误信息',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation_logs
-- ----------------------------

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '支付ID',
  `payment_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付编号',
  `booking_id` bigint NOT NULL COMMENT '预约ID - 关联bookings.id',
  `user_id` bigint NOT NULL COMMENT '用户ID - 关联users.id',
  `payment_type` int NOT NULL COMMENT '支付类型(int)：0-DEPOSIT（定金），1-FINAL（尾款），2-FULL（全款）（默认0）',
  `payment_method` int NOT NULL COMMENT '支付方式(int)：0-WECHAT（微信），1-ALIPAY（支付宝），2-BANK（银行卡）（默认0）',
  `amount` decimal(10, 2) NOT NULL COMMENT '支付金额',
  `status` int NULL DEFAULT 0 COMMENT '支付状态(int)：0-PENDING（待支付），1-SUCCESS（成功），2-FAILED（失败），3-REFUNDED（已退款）（默认0）',
  `transaction_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '第三方交易号',
  `refund_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '退款金额',
  `refund_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '退款原因',
  `paid_at` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `refunded_at` datetime NULL DEFAULT NULL COMMENT '退款时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `payment_no`(`payment_no` ASC) USING BTREE,
  INDEX `idx_booking_id`(`booking_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '支付记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------

-- ----------------------------
-- Table structure for photographer_profiles
-- ----------------------------
DROP TABLE IF EXISTS `photographer_profiles`;
CREATE TABLE `photographer_profiles`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '摄影师资料ID',
  `user_id` bigint NOT NULL COMMENT '用户ID - 关联users.id',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份证号（加密存储）',
  `bio` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '个人简介',
  `specialties` json NULL COMMENT '擅长领域 ["人像", "风景"]',
  `experience_years` int NULL DEFAULT NULL COMMENT '从业年限',
  `certification_status` int NULL DEFAULT 0 COMMENT '认证状态(int)：0-PENDING（待审核），1-APPROVED（已通过），2-REJECTED（已拒绝）（默认0）',
  `certification_time` datetime NULL DEFAULT NULL COMMENT '认证时间',
  `rejection_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '拒绝原因',
  `service_areas` json NULL COMMENT '服务区域 ["北京"]',
  `contact_wechat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信号',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `portfolio_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作品集封面',
  `rating` decimal(3, 2) NULL DEFAULT 5.00 COMMENT '评分(1.00-5.00)',
  `total_orders` int NULL DEFAULT 0 COMMENT '总订单数',
  `completed_orders` int NULL DEFAULT 0 COMMENT '完成订单数',
  `total_revenue` decimal(12, 2) NULL DEFAULT 0.00 COMMENT '总收入',
  `is_featured` tinyint(1) NULL DEFAULT 0 COMMENT '是否推荐(int)：0-否，1-是（默认0）',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序权重',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_certification_status`(`certification_status` ASC) USING BTREE,
  INDEX `idx_rating`(`rating` ASC) USING BTREE,
  INDEX `idx_is_featured`(`is_featured` ASC) USING BTREE,
  CONSTRAINT `chk_photographer_rating` CHECK ((`rating` >= 1.00) and (`rating` <= 5.00))
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '摄影师认证信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of photographer_profiles
-- ----------------------------

-- ----------------------------
-- Table structure for portfolio_categories
-- ----------------------------
DROP TABLE IF EXISTS `portfolio_categories`;
CREATE TABLE `portfolio_categories`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类描述',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类图标',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序权重',
  `is_active` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用(int)：0-否，1-是（默认1）',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE,
  INDEX `idx_is_active`(`is_active` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '作品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of portfolio_categories
-- ----------------------------
INSERT INTO `portfolio_categories` VALUES (1, '人像', '人像摄影作品', 'portrait.png', 1, 1, 0, NULL, NULL, NULL, 0);
INSERT INTO `portfolio_categories` VALUES (2, '风景', '风景摄影作品', 'landscape.png', 2, 1, 0, NULL, NULL, NULL, 0);
INSERT INTO `portfolio_categories` VALUES (3, '婚纱', '婚纱摄影作品', 'wedding.png', 3, 1, 0, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for portfolio_tag_relations
-- ----------------------------
DROP TABLE IF EXISTS `portfolio_tag_relations`;
CREATE TABLE `portfolio_tag_relations`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `portfolio_id` bigint NOT NULL COMMENT '作品ID - 关联portfolios.id',
  `tag_id` bigint NOT NULL COMMENT '标签ID - 关联portfolio_tags.id',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_portfolio_tag`(`portfolio_id` ASC, `tag_id` ASC) USING BTREE,
  INDEX `idx_portfolio_id`(`portfolio_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '作品标签关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of portfolio_tag_relations
-- ----------------------------

-- ----------------------------
-- Table structure for portfolio_tags
-- ----------------------------
DROP TABLE IF EXISTS `portfolio_tags`;
CREATE TABLE `portfolio_tags`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签名称',
  `color` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '#1890ff' COMMENT '标签颜色',
  `usage_count` int NULL DEFAULT 0 COMMENT '使用次数',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '作品标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of portfolio_tags
-- ----------------------------

-- ----------------------------
-- Table structure for portfolios
-- ----------------------------
DROP TABLE IF EXISTS `portfolios`;
CREATE TABLE `portfolios`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '作品ID',
  `photographer_id` bigint NOT NULL COMMENT '摄影师ID - 关联users.id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作品标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作品描述',
  `category_id` bigint NULL DEFAULT NULL COMMENT '分类ID - 关联portfolio_categories.id',
  `style` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '拍摄风格',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '拍摄地点',
  `shoot_date` date NULL DEFAULT NULL COMMENT '拍摄日期',
  `camera_info` json NULL COMMENT '拍摄参数 {"camera":"Canon EOS R5"}',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面图片URL',
  `images` json NULL COMMENT '图片列表 ["url1","url2"]',
  `videos` json NULL COMMENT '视频列表 [{"url":"","duration":120}]',
  `likes_count` int NULL DEFAULT 0 COMMENT '点赞数',
  `views_count` int NULL DEFAULT 0 COMMENT '浏览数',
  `comments_count` int NULL DEFAULT 0 COMMENT '评论数',
  `shares_count` int NULL DEFAULT 0 COMMENT '分享数',
  `is_featured` tinyint(1) NULL DEFAULT 0 COMMENT '是否精选(int)：0-否，1-是（默认0）',
  `is_private` tinyint(1) NULL DEFAULT 0 COMMENT '是否私有(int)：0-否，1-是（默认0）',
  `status` int NULL DEFAULT 0 COMMENT '状态(int)：0-DRAFT（草稿），1-PUBLISHED（已发布），2-HIDDEN（已隐藏）（默认0）',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序权重',
  `seo_keywords` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'SEO关键词',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_photographer_id`(`photographer_id` ASC) USING BTREE,
  INDEX `idx_category_id`(`category_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_is_featured`(`is_featured` ASC) USING BTREE,
  INDEX `idx_photographer_status`(`photographer_id` ASC, `status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '作品集表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of portfolios
-- ----------------------------

-- ----------------------------
-- Table structure for promotion_participants
-- ----------------------------
DROP TABLE IF EXISTS `promotion_participants`;
CREATE TABLE `promotion_participants`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '参与记录ID',
  `promotion_id` bigint NOT NULL COMMENT '活动ID - 关联promotions.id',
  `user_id` bigint NOT NULL COMMENT '用户ID - 关联users.id',
  `coupon_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '优惠券码',
  `used_at` datetime NULL DEFAULT NULL COMMENT '使用时间',
  `order_id` bigint NULL DEFAULT NULL COMMENT '关联订单ID - 关联bookings.id',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_promotion_user`(`promotion_id` ASC, `user_id` ASC) USING BTREE,
  INDEX `idx_promotion_id`(`promotion_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '营销活动参与记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promotion_participants
-- ----------------------------

-- ----------------------------
-- Table structure for promotion_statistics
-- ----------------------------
DROP TABLE IF EXISTS `promotion_statistics`;
CREATE TABLE `promotion_statistics`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `promotion_id` bigint NOT NULL COMMENT '活动ID - 关联promotions.id',
  `stat_date` date NOT NULL COMMENT '统计日期',
  `view_count` int NULL DEFAULT 0 COMMENT '浏览次数',
  `share_count` int NULL DEFAULT 0 COMMENT '分享次数',
  `participant_count` int NULL DEFAULT 0 COMMENT '参与人数',
  `conversion_count` int NULL DEFAULT 0 COMMENT '转化次数',
  `revenue_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '产生收入',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_promotion_date`(`promotion_id` ASC, `stat_date` ASC) USING BTREE,
  INDEX `idx_promotion_id`(`promotion_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '营销活动统计表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promotion_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for promotions
-- ----------------------------
DROP TABLE IF EXISTS `promotions`;
CREATE TABLE `promotions`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `photographer_id` bigint NOT NULL COMMENT '摄影师ID - 关联users.id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '活动描述',
  `type` int NOT NULL COMMENT '活动类型(int)：0-DISCOUNT（折扣），1-FULL_REDUCTION（满减），2-GIFT（赠品），3-LIMITED_TIME（限时）（默认0）',
  `discount_rate` decimal(3, 2) NULL DEFAULT NULL COMMENT '折扣率(0.01-0.99)',
  `min_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低消费金额',
  `reduction_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '减免金额',
  `gift_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '赠品描述',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `usage_limit` int NULL DEFAULT NULL COMMENT '使用次数限制',
  `used_count` int NULL DEFAULT 0 COMMENT '已使用次数',
  `target_audience` int NULL DEFAULT 0 COMMENT '目标用户(int)：0-ALL（全部），1-NEW_USER（新用户），2-VIP_USER（VIP用户）（默认0）',
  `is_active` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用(int)：0-否，1-是（默认1）',
  `banner_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '活动横幅图片',
  `share_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分享图片',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_photographer_id`(`photographer_id` ASC) USING BTREE,
  INDEX `idx_start_end_time`(`start_time` ASC, `end_time` ASC) USING BTREE,
  INDEX `idx_is_active`(`is_active` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '营销活动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promotions
-- ----------------------------

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评价ID',
  `booking_id` bigint NOT NULL COMMENT '预约ID - 关联bookings.id',
  `client_id` bigint NOT NULL COMMENT '客户ID - 关联users.id',
  `photographer_id` bigint NOT NULL COMMENT '摄影师ID - 关联users.id',
  `rating` decimal(2, 1) NOT NULL COMMENT '评分(1.0-5.0)',
  `service_rating` decimal(2, 1) NULL DEFAULT NULL COMMENT '服务评分',
  `quality_rating` decimal(2, 1) NULL DEFAULT NULL COMMENT '质量评分',
  `attitude_rating` decimal(2, 1) NULL DEFAULT NULL COMMENT '态度评分',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价内容',
  `images` json NULL COMMENT '评价图片',
  `is_anonymous` tinyint(1) NULL DEFAULT 0 COMMENT '是否匿名(int)：0-否，1-是（默认0）',
  `reply_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '回复内容',
  `reply_time` datetime NULL DEFAULT NULL COMMENT '回复时间',
  `is_featured` tinyint(1) NULL DEFAULT 0 COMMENT '是否精选(int)：0-否，1-是（默认0）',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_booking_id`(`booking_id` ASC) USING BTREE,
  INDEX `idx_photographer_id`(`photographer_id` ASC) USING BTREE,
  INDEX `idx_rating`(`rating` ASC) USING BTREE,
  CONSTRAINT `chk_review_rating` CHECK ((`rating` >= 1.0) and (`rating` <= 5.0))
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reviews
-- ----------------------------

-- ----------------------------
-- Table structure for service_packages
-- ----------------------------
DROP TABLE IF EXISTS `service_packages`;
CREATE TABLE `service_packages`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '套餐ID',
  `photographer_id` bigint NOT NULL COMMENT '摄影师ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '套餐名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '套餐描述',
  `category` int NOT NULL COMMENT '套餐类型(int)：0-WEDDING（婚纱），1-PORTRAIT（人像），2-COMMERCIAL（商业），3-EVENT（活动），4-OTHER（其他）（默认0）',
  `price` decimal(10, 2) NOT NULL COMMENT '套餐价格',
  `duration` int NULL DEFAULT NULL COMMENT '拍摄时长(分钟)',
  `photo_count` int NULL DEFAULT NULL COMMENT '照片数量',
  `includes` json NULL COMMENT '包含服务 ["化妆"]',
  `excludes` json NULL COMMENT '不包含服务',
  `is_active` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用(int)：0-否，1-是（默认1）',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序权重',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_photographer_id`(`photographer_id` ASC) USING BTREE,
  INDEX `idx_category`(`category` ASC) USING BTREE,
  INDEX `idx_active`(`is_active` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '服务套餐表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of service_packages
-- ----------------------------

-- ----------------------------
-- Table structure for system_configs
-- ----------------------------
DROP TABLE IF EXISTS `system_configs`;
CREATE TABLE `system_configs`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '配置键',
  `config_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配置值',
  `config_type` int NULL DEFAULT 0 COMMENT '配置类型(int)：0-STRING（字符串），1-NUMBER（数字），2-BOOLEAN（布尔），3-JSON（JSON）（默认0）',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配置描述',
  `is_public` tinyint(1) NULL DEFAULT 0 COMMENT '是否公开配置(int)：0-否，1-是（默认0）',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_config_key`(`config_key` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_configs
-- ----------------------------

-- ----------------------------
-- Table structure for system_statistics
-- ----------------------------
DROP TABLE IF EXISTS `system_statistics`;
CREATE TABLE `system_statistics`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `stat_date` date NOT NULL COMMENT '统计日期',
  `stat_type` int NOT NULL COMMENT '统计类型(int)：0-DAILY（日），1-WEEKLY（周），2-MONTHLY（月）（默认0）',
  `user_count` int NULL DEFAULT 0 COMMENT '用户总数',
  `new_user_count` int NULL DEFAULT 0 COMMENT '新增用户数',
  `active_user_count` int NULL DEFAULT 0 COMMENT '活跃用户数',
  `photographer_count` int NULL DEFAULT 0 COMMENT '摄影师总数',
  `portfolio_count` int NULL DEFAULT 0 COMMENT '作品总数',
  `new_portfolio_count` int NULL DEFAULT 0 COMMENT '新增作品数',
  `promotion_count` int NULL DEFAULT 0 COMMENT '活动总数',
  `revenue_amount` decimal(12, 2) NULL DEFAULT 0.00 COMMENT '总收入',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_date_type`(`stat_date` ASC, `stat_type` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统统计数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for user_follows
-- ----------------------------
DROP TABLE IF EXISTS `user_follows`;
CREATE TABLE `user_follows`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '关注ID',
  `follower_id` bigint NOT NULL COMMENT '关注者ID - 关联users.id',
  `following_id` bigint NOT NULL COMMENT '被关注者ID - 关联users.id',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_follower_following`(`follower_id` ASC, `following_id` ASC) USING BTREE,
  INDEX `idx_follower_id`(`follower_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户关注关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follows
-- ----------------------------

-- ----------------------------
-- Table structure for user_likes
-- ----------------------------
DROP TABLE IF EXISTS `user_likes`;
CREATE TABLE `user_likes`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `user_id` bigint NOT NULL COMMENT '用户ID - 关联users.id',
  `target_type` int NOT NULL COMMENT '点赞类型(int)：0-PORTFOLIO（作品），1-PHOTOGRAPHER（摄影师）（默认0）',
  `target_id` bigint NOT NULL COMMENT '目标ID',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_target`(`user_id` ASC, `target_type` ASC, `target_id` ASC) USING BTREE,
  INDEX `idx_target`(`target_type` ASC, `target_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户点赞记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_likes
-- ----------------------------

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色关联ID',
  `user_id` bigint NOT NULL COMMENT '用户ID - 关联users.id',
  `role_type` int NOT NULL COMMENT '角色类型(int)：0-普通用户，1-摄影师，2-管理员（根据业务自定义）（默认0）',
  `is_active` tinyint(1) NULL DEFAULT 1 COMMENT '是否激活(int)：0-否，1-是（默认1）',
  `activated_at` datetime NULL DEFAULT NULL COMMENT '激活时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_role`(`user_id` ASC, `role_type` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `openid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '微信openid',
  `unionid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信unionid',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像URL',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint NULL DEFAULT 0 COMMENT '性别(int)：0-未知，1-男，2-女（默认0）',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '城市',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '省份',
  `country` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '国家',
  `current_role` int NULL DEFAULT 0 COMMENT '当前角色(int)：0-CLIENT（客户），1-PHOTOGRAPHER（摄影师）（默认0）',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态(int)：0-禁用，1-正常（默认1）',
  `last_login_at` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `login_count` int NULL DEFAULT 0 COMMENT '登录次数',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL COMMENT '创建时间（无默认值）',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（无默认值）',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(int)：0-未删除，1-已删除（默认0）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `openid`(`openid` ASC) USING BTREE,
  INDEX `idx_phone`(`phone` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_current_role`(`current_role` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- View structure for photographer_stats
-- ----------------------------
DROP VIEW IF EXISTS `photographer_stats`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `photographer_stats` AS select `u`.`id` AS `id`,`u`.`nickname` AS `nickname`,`pp`.`rating` AS `rating`,`pp`.`total_orders` AS `total_orders`,`pp`.`completed_orders` AS `completed_orders`,`pp`.`total_revenue` AS `total_revenue`,count(distinct `p`.`id`) AS `portfolio_count`,coalesce(sum(`p`.`likes_count`),0) AS `total_likes` from ((`users` `u` left join `photographer_profiles` `pp` on(((`u`.`id` = `pp`.`user_id`) and (`pp`.`is_delete` = 0)))) left join `portfolios` `p` on(((`u`.`id` = `p`.`photographer_id`) and (`p`.`status` = 1) and (`p`.`is_delete` = 0)))) where ((`u`.`current_role` = 1) and (`u`.`is_delete` = 0)) group by `u`.`id`,`u`.`nickname`,`pp`.`rating`,`pp`.`total_orders`,`pp`.`completed_orders`,`pp`.`total_revenue`;

SET FOREIGN_KEY_CHECKS = 1;
-- =====================================================
-- 脚本执行完成
-- =====================================================
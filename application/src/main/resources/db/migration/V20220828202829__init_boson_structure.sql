-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: boson
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
                                         `installed_rank` int NOT NULL,
                                         `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                         `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                         `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                         `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                         `checksum` int DEFAULT NULL,
                                         `installed_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                         `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         `execution_time` int NOT NULL,
                                         `success` tinyint(1) NOT NULL,
                                         PRIMARY KEY (`installed_rank`),
                                         KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_config`
--

DROP TABLE IF EXISTS `security_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_config` (
                                   `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                                   `config_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                   `config_value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                   `create_time` datetime NOT NULL,
                                   `update_time` datetime DEFAULT NULL,
                                   `create_user_id` bigint unsigned NOT NULL DEFAULT '1',
                                   `update_user_id` bigint unsigned NOT NULL,
                                   `is_status` tinyint unsigned NOT NULL DEFAULT '0',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `key` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='安全配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_permission`
--

DROP TABLE IF EXISTS `security_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_permission` (
                                       `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                                       `permission_url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接口URL',
                                       `permission_description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接口描述',
                                       `create_time` datetime NOT NULL,
                                       `update_time` datetime DEFAULT NULL,
                                       `create_user_id` bigint unsigned NOT NULL,
                                       `update_user_id` bigint unsigned DEFAULT NULL,
                                       `is_status` tinyint unsigned NOT NULL DEFAULT '0',
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `security_permission_permission_url_uindex` (`permission_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='接口权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_role_info`
--

DROP TABLE IF EXISTS `security_role_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_role_info` (
                                      `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                      `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称\n英文代码',
                                      `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
                                      `role_description` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色描述',
                                      `create_time` datetime NOT NULL,
                                      `update_time` datetime NOT NULL,
                                      `create_user_id` bigint unsigned NOT NULL,
                                      `update_user_id` bigint unsigned NOT NULL,
                                      `is_status` tinyint NOT NULL DEFAULT '0',
                                      `is_deleted` tinyint NOT NULL DEFAULT '0',
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `security_role_info_role_code_uindex` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限系统\n角色信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_role_inheritance`
--

DROP TABLE IF EXISTS `security_role_inheritance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_role_inheritance` (
                                             `id` int unsigned NOT NULL AUTO_INCREMENT,
                                             `role_id` int unsigned NOT NULL COMMENT '角色id',
                                             `role_parent_id` int unsigned NOT NULL COMMENT '父角色ID',
                                             `create_time` datetime NOT NULL,
                                             `update_time` datetime NOT NULL,
                                             `create_user_id` bigint unsigned NOT NULL,
                                             `update_user_id` bigint unsigned NOT NULL,
                                             `is_status` tinyint unsigned NOT NULL DEFAULT '0',
                                             `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
                                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色继承关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_role_permission_relation`
--

DROP TABLE IF EXISTS `security_role_permission_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_role_permission_relation` (
                                                     `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                                                     `role_id` bigint unsigned NOT NULL,
                                                     `permission_id` bigint unsigned NOT NULL,
                                                     `create_time` datetime NOT NULL,
                                                     `update_time` datetime NOT NULL,
                                                     `create_user_id` bigint NOT NULL,
                                                     `update_user_id` bigint NOT NULL,
                                                     `is_status` tinyint unsigned NOT NULL DEFAULT '0',
                                                     PRIMARY KEY (`id`),
                                                     UNIQUE KEY `security_role_permission_relation_permission_id_uindex` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_user_info`
--

DROP TABLE IF EXISTS `security_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_user_info` (
                                      `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                      `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '别名',
                                      `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮件',
                                      `phone` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
                                      `create_time` datetime NOT NULL,
                                      `update_time` datetime NOT NULL,
                                      `create_user_id` bigint unsigned NOT NULL,
                                      `update_user_id` bigint unsigned NOT NULL,
                                      `expired_time` bigint NOT NULL DEFAULT '-1' COMMENT '过期时间，时间戳类型\n-1 为永不过期',
                                      `is_status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态\n0-正常\n1-异常',
                                      `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除\n0-未删除\n1-已删除',
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `per_user_info_email_uindex` (`email`),
                                      UNIQUE KEY `per_user_info_nickname_uindex` (`nickname`),
                                      UNIQUE KEY `per_user_info_phone_uindex` (`phone`),
                                      KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_user_passwd`
--

DROP TABLE IF EXISTS `security_user_passwd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_user_passwd` (
                                        `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                                        `user_id` bigint unsigned NOT NULL COMMENT '用户信息id',
                                        `user_passwd` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
                                        `create_time` datetime NOT NULL,
                                        `update_time` datetime NOT NULL,
                                        `create_user_id` bigint unsigned NOT NULL,
                                        `update_user_id` bigint unsigned NOT NULL,
                                        `expired_time` bigint NOT NULL DEFAULT '-1',
                                        `is_status` tinyint unsigned NOT NULL DEFAULT '0',
                                        `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `security_user_passwd_user_info_id_uindex` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `security_user_role_relation`
--

DROP TABLE IF EXISTS `security_user_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_user_role_relation` (
                                               `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                               `user_id` bigint unsigned NOT NULL COMMENT '用户id',
                                               `role_id` int unsigned NOT NULL COMMENT '角色id',
                                               `create_time` datetime NOT NULL,
                                               `update_time` datetime NOT NULL,
                                               `create_user_id` bigint unsigned NOT NULL,
                                               `update_user_id` bigint unsigned NOT NULL,
                                               `is_status` tinyint unsigned NOT NULL DEFAULT '0',
                                               `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
                                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='安全模块\n用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-28 14:32:28

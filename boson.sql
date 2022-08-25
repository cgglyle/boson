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
-- Table structure for table `security_config`
--

DROP TABLE IF EXISTS `security_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `config_key` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `config_value` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` bigint unsigned NOT NULL DEFAULT '1',
  `update_user_id` bigint unsigned NOT NULL,
  `is_status` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`config_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='安全配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_config`
--

LOCK TABLES `security_config` WRITE;
/*!40000 ALTER TABLE `security_config` DISABLE KEYS */;
INSERT INTO `security_config` VALUES (1,'isAnonymousUser','true','2022-08-25 14:24:30','2022-08-25 14:24:30',1,1,0);
/*!40000 ALTER TABLE `security_config` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='接口权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_permission`
--

LOCK TABLES `security_permission` WRITE;
/*!40000 ALTER TABLE `security_permission` DISABLE KEYS */;
INSERT INTO `security_permission` VALUES (1,'GET/users','查看用户','2022-08-21 00:43:45','2022-08-21 00:43:45',1,1,0),(2,'GET/users/{id}','根据ID查找用户','2022-08-21 00:45:12','2022-08-21 00:45:12',1,1,0),(3,'POST/users','添加用户','2022-08-21 00:45:31','2022-08-21 00:45:31',1,1,0),(4,'DELETE/users','删除用户','2022-08-21 00:45:56','2022-08-21 00:45:56',1,1,0),(5,'GET/users/counts','查询总数','2022-08-21 00:46:16','2022-08-21 00:46:16',1,1,0),(6,'POST/passwd','添加密码','2022-08-21 18:15:14','2022-08-21 18:15:15',1,1,0),(7,'null/cp/error','错误页面','2022-08-21 18:30:48','2022-08-21 18:30:50',1,1,0),(8,'GET/admin/permission','查看全部URL权限','2022-08-25 11:35:01','2022-08-25 11:35:01',4,4,0),(9,'GET/permission/{id}','根据ID查找URL权限','2022-08-25 11:42:04','2022-08-25 11:42:04',4,4,0);
/*!40000 ALTER TABLE `security_permission` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限系统\n角色信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role_info`
--

LOCK TABLES `security_role_info` WRITE;
/*!40000 ALTER TABLE `security_role_info` DISABLE KEYS */;
INSERT INTO `security_role_info` VALUES (1,'boson','波色子','系统超级管理员','2022-08-12 11:41:40','2022-08-12 11:41:41',1,1,0,0),(2,'admin','管理员','管理员','2022-08-12 11:42:56','2022-08-12 11:42:57',1,1,0,0),(3,'test','测试员','测试员','2022-08-12 11:43:29','2022-08-12 11:43:30',1,1,0,0),(4,'user','用户','用户','2022-08-12 11:43:56','2022-08-12 11:43:55',1,1,0,0),(5,'dba','数据库管理员','数据库管理员','2022-08-14 18:49:18','2022-08-14 18:49:19',1,1,0,0),(7,'anonymous','匿名用户','匿名用户','2022-08-14 18:50:44','2022-08-14 18:50:45',1,1,0,0),(8,'test1','test1','test1','2022-08-15 11:53:00','2022-08-15 11:53:00',1,1,0,0),(9,'test2','test2','test2','2022-08-15 11:53:11','2022-08-15 11:53:11',1,1,0,0);
/*!40000 ALTER TABLE `security_role_info` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色继承关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role_inheritance`
--

LOCK TABLES `security_role_inheritance` WRITE;
/*!40000 ALTER TABLE `security_role_inheritance` DISABLE KEYS */;
INSERT INTO `security_role_inheritance` VALUES (1,1,0,'2022-08-14 18:53:43','2022-08-14 18:53:45',1,1,0,0),(2,2,1,'2022-08-14 18:54:06','2022-08-14 18:54:07',1,1,0,0),(3,5,1,'2022-08-14 18:54:28','2022-08-14 18:54:29',1,1,0,0),(4,3,5,'2022-08-14 18:56:09','2022-08-14 18:56:12',1,1,0,0),(9,4,2,'2022-08-15 15:16:10','2022-08-15 15:16:10',1,1,0,0),(10,7,4,'2022-08-21 18:36:56','2022-08-21 18:36:57',1,1,0,0),(11,4,3,'2022-08-21 18:36:56','2022-08-21 18:36:57',1,1,0,0);
/*!40000 ALTER TABLE `security_role_inheritance` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role_permission_relation`
--

LOCK TABLES `security_role_permission_relation` WRITE;
/*!40000 ALTER TABLE `security_role_permission_relation` DISABLE KEYS */;
INSERT INTO `security_role_permission_relation` VALUES (1,2,1,'2022-08-21 09:35:14','2022-08-21 09:35:14',1,1,0),(2,2,2,'2022-08-21 09:35:28','2022-08-21 09:35:28',1,1,0),(3,2,3,'2022-08-21 09:35:45','2022-08-21 09:35:45',1,1,0),(4,2,4,'2022-08-21 09:35:47','2022-08-21 09:35:47',1,1,0),(5,4,5,'2022-08-21 09:35:54','2022-08-21 09:35:54',1,1,0),(6,2,6,'2022-08-21 18:17:35','2022-08-21 18:17:36',1,1,0),(7,7,7,'2022-08-21 18:32:10','2022-08-21 18:32:11',1,1,0),(8,7,8,'2022-08-25 11:38:52','2022-08-25 11:38:52',4,4,0);
/*!40000 ALTER TABLE `security_role_permission_relation` ENABLE KEYS */;
UNLOCK TABLES;

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
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电话',
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_info`
--

LOCK TABLES `security_user_info` WRITE;
/*!40000 ALTER TABLE `security_user_info` DISABLE KEYS */;
INSERT INTO `security_user_info` VALUES (1,'boson','boson@boson.com','11122223333','2022-08-12 11:35:21','2022-08-12 11:35:23',1,1,-1,0,0),(2,'admin','admin@boson.com','21122223333','2022-08-12 11:35:48','2022-08-12 11:35:57',1,1,-1,0,0),(3,'test','test@boson.com','31122223333','2022-08-12 11:36:17','2022-08-12 11:36:19',1,1,-1,0,0),(4,'user','user@boson.com','41122223333','2022-08-12 11:36:41','2022-08-12 11:36:42',1,1,-1,0,0),(5,'cgglyle','cgglyle@boson.com','51122223333','2022-08-12 11:37:05','2022-08-12 11:37:06',1,1,-1,0,0),(7,'1','1@boson.com','1','2022-08-13 11:38:42','2022-08-13 11:38:42',1,1,-1,0,0),(8,'2','2@boson.com','2','2022-08-13 13:37:37','2022-08-13 13:37:37',1,1,-1,0,0),(9,'3','3@boson.com','3','2022-08-13 13:42:52','2022-08-13 13:42:52',1,1,-1,0,0),(10,'4','4@boson.com','4','2022-08-13 15:30:33','2022-08-13 15:30:33',1,1,-1,0,0),(11,'5','5@boson.com','5','2022-08-13 15:32:30','2022-08-13 15:32:30',1,1,-1,0,0),(12,'6','6@boson.com','6','2022-08-13 15:33:25','2022-08-13 15:33:25',1,1,-1,0,0),(14,'7','7@boson.com','7','2022-08-13 15:35:51','2022-08-13 15:35:51',1,1,-1,0,0),(15,'8','8@boson.com','8','2022-08-13 15:36:41','2022-08-13 15:36:41',1,1,-1,0,0),(16,'9','9@boson.com','9','2022-08-13 15:38:10','2022-08-13 15:38:10',1,1,-1,0,0),(17,'10','10@boson.com','10','2022-08-13 15:38:49','2022-08-13 15:38:49',1,1,-1,0,0),(18,'11','11@boson.com','11','2022-08-13 15:39:38','2022-08-13 15:39:38',1,1,-1,0,0),(19,'12','12@boson.com','12','2022-08-13 15:46:26','2022-08-13 15:46:26',1,1,-1,0,0),(20,'13','13@boson.com','13','2022-08-13 15:48:00','2022-08-13 15:48:00',1,1,-1,0,0),(21,'14','14@boson.com','14','2022-08-13 15:50:46','2022-08-13 15:50:46',1,1,-1,0,0),(22,'15','15@boson.com','15','2022-08-13 15:58:46','2022-08-13 15:58:46',1,1,-1,0,0),(23,'16','16@boson.com','16','2022-08-13 16:04:37','2022-08-13 16:04:37',1,1,-1,0,0),(24,'17','17@boson.com','17','2022-08-13 17:21:42','2022-08-14 15:23:53',1,1,-1,0,1),(25,'18','18@boson.com','18','2022-08-13 23:25:21','2022-08-13 23:25:21',1,1,-1,0,0),(26,'19','19@boson.com','19','2022-08-14 15:15:47','2022-08-14 15:15:47',1,1,-1,0,0),(27,'20','20@boson.com','20','2022-08-19 10:17:15','2022-08-19 10:17:15',1,1,-1,0,0),(28,'21','21@boson.com','21','2022-08-19 10:20:09','2022-08-19 10:20:09',2,2,-1,0,0);
/*!40000 ALTER TABLE `security_user_info` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_passwd`
--

LOCK TABLES `security_user_passwd` WRITE;
/*!40000 ALTER TABLE `security_user_passwd` DISABLE KEYS */;
INSERT INTO `security_user_passwd` VALUES (1,1,'$2a$10$AHcZVKAX834.ZWjZoEl4Y.bCAriAFLao4CRsMOCmvrybFm/KmSsOy','2022-08-17 14:06:58','2022-08-19 11:33:04',1,2,-1,0,0),(2,2,'$2a$10$uqf8oJPrGpbM5K/uWTxuUui.BA0aD1Sp.CxH.7zO35egC4Of/61NG','2022-08-19 10:19:28','2022-08-19 10:19:28',1,1,-1,0,0),(3,4,'$2a$10$wkxJAB9AQmK/4NSRiMQTEeeF8dtOdD2UEA.rDQp63eiopWj1HZ8ba','2022-08-21 18:19:01','2022-08-21 18:19:01',1,1,-1,0,0);
/*!40000 ALTER TABLE `security_user_passwd` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='安全模块\n用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_role_relation`
--

LOCK TABLES `security_user_role_relation` WRITE;
/*!40000 ALTER TABLE `security_user_role_relation` DISABLE KEYS */;
INSERT INTO `security_user_role_relation` VALUES (1,1,1,'2022-08-14 00:00:47','2022-08-14 00:00:47',1,1,0,0),(2,2,2,'2022-08-14 00:00:52','2022-08-14 00:00:52',1,1,0,0),(3,3,3,'2022-08-14 00:00:55','2022-08-14 00:00:55',1,1,0,0),(4,4,4,'2022-08-14 00:01:01','2022-08-14 00:01:01',1,1,0,0);
/*!40000 ALTER TABLE `security_user_role_relation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-25 18:00:22

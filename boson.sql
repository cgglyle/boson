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
  `is_built_in` tinyint NOT NULL DEFAULT '0' COMMENT '是否为内置\n0 = 不是\n1 = 是',
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
INSERT INTO `security_role_info` VALUES (1,'boson','波色子','系统超级管理员','2022-08-12 11:41:40','2022-08-12 11:41:41',1,1,0,1,0),(2,'admin','管理员','管理员','2022-08-12 11:42:56','2022-08-12 11:42:57',1,1,0,1,0),(3,'test','测试员','测试员','2022-08-12 11:43:29','2022-08-12 11:43:30',1,1,0,1,0),(4,'user','用户','用户','2022-08-12 11:43:56','2022-08-12 11:43:55',1,1,0,1,0),(5,'dba','数据库管理员','数据库管理员','2022-08-14 18:49:18','2022-08-14 18:49:19',1,1,0,1,0),(7,'anonymous','匿名用户','匿名用户','2022-08-14 18:50:44','2022-08-14 18:50:45',1,1,0,1,0),(8,'test1','test1','test1','2022-08-15 11:53:00','2022-08-15 11:53:00',1,1,0,0,0),(9,'test2','test2','test2','2022-08-15 11:53:11','2022-08-15 11:53:11',1,1,0,0,0);
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
  `is_built_in` tinyint unsigned NOT NULL DEFAULT '0',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色继承关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role_inheritance`
--

LOCK TABLES `security_role_inheritance` WRITE;
/*!40000 ALTER TABLE `security_role_inheritance` DISABLE KEYS */;
INSERT INTO `security_role_inheritance` VALUES (1,1,0,'2022-08-14 18:53:43','2022-08-14 18:53:45',1,1,0,1,0),(2,2,1,'2022-08-14 18:54:06','2022-08-14 18:54:07',1,1,0,1,0),(3,5,1,'2022-08-14 18:54:28','2022-08-14 18:54:29',1,1,0,1,0),(4,3,5,'2022-08-14 18:56:09','2022-08-14 18:56:12',1,1,0,1,0),(5,8,3,'2022-08-15 11:54:57','2022-08-15 11:54:57',1,1,0,0,0),(6,9,8,'2022-08-15 11:55:18','2022-08-15 11:55:18',1,1,0,0,0),(7,4,8,'2022-08-15 14:35:01','2022-08-15 14:35:01',1,1,0,0,0),(8,4,9,'2022-08-15 14:35:05','2022-08-15 14:35:05',1,1,0,0,0),(9,4,2,'2022-08-15 15:16:10','2022-08-15 15:16:10',1,1,0,0,0);
/*!40000 ALTER TABLE `security_role_inheritance` ENABLE KEYS */;
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
  `is_status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态\n0-正常\n1-异常',
  `is_built_in` tinyint unsigned NOT NULL DEFAULT '0',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除\n0-未删除\n1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `per_user_info_email_uindex` (`email`),
  UNIQUE KEY `per_user_info_nickname_uindex` (`nickname`),
  UNIQUE KEY `per_user_info_phone_uindex` (`phone`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_info`
--

LOCK TABLES `security_user_info` WRITE;
/*!40000 ALTER TABLE `security_user_info` DISABLE KEYS */;
INSERT INTO `security_user_info` VALUES (1,'boson','boson@boson.com','11122223333','2022-08-12 11:35:21','2022-08-12 11:35:23',1,1,0,1,0),(2,'admin','admin@boson.com','21122223333','2022-08-12 11:35:48','2022-08-12 11:35:57',1,1,0,1,0),(3,'test','test@boson.com','31122223333','2022-08-12 11:36:17','2022-08-12 11:36:19',1,1,0,1,0),(4,'user','user@boson.com','41122223333','2022-08-12 11:36:41','2022-08-12 11:36:42',1,1,0,1,0),(5,'cgglyle','cgglyle@boson.com','51122223333','2022-08-12 11:37:05','2022-08-12 11:37:06',1,1,0,0,0),(7,'1','1@boson.com','1','2022-08-13 11:38:42','2022-08-13 11:38:42',1,1,0,0,0),(8,'2','2@boson.com','2','2022-08-13 13:37:37','2022-08-13 13:37:37',1,1,0,0,0),(9,'3','3@boson.com','3','2022-08-13 13:42:52','2022-08-13 13:42:52',1,1,0,0,0),(10,'4','4@boson.com','4','2022-08-13 15:30:33','2022-08-13 15:30:33',1,1,0,0,0),(11,'5','5@boson.com','5','2022-08-13 15:32:30','2022-08-13 15:32:30',1,1,0,0,0),(12,'6','6@boson.com','6','2022-08-13 15:33:25','2022-08-13 15:33:25',1,1,0,0,0),(14,'7','7@boson.com','7','2022-08-13 15:35:51','2022-08-13 15:35:51',1,1,0,0,0),(15,'8','8@boson.com','8','2022-08-13 15:36:41','2022-08-13 15:36:41',1,1,0,0,0),(16,'9','9@boson.com','9','2022-08-13 15:38:10','2022-08-13 15:38:10',1,1,0,0,0),(17,'10','10@boson.com','10','2022-08-13 15:38:49','2022-08-13 15:38:49',1,1,0,0,0),(18,'11','11@boson.com','11','2022-08-13 15:39:38','2022-08-13 15:39:38',1,1,0,0,0),(19,'12','12@boson.com','12','2022-08-13 15:46:26','2022-08-13 15:46:26',1,1,0,0,0),(20,'13','13@boson.com','13','2022-08-13 15:48:00','2022-08-13 15:48:00',1,1,0,0,0),(21,'14','14@boson.com','14','2022-08-13 15:50:46','2022-08-13 15:50:46',1,1,0,0,0),(22,'15','15@boson.com','15','2022-08-13 15:58:46','2022-08-13 15:58:46',1,1,0,0,0),(23,'16','16@boson.com','16','2022-08-13 16:04:37','2022-08-13 16:04:37',1,1,0,0,0),(24,'17','17@boson.com','17','2022-08-13 17:21:42','2022-08-14 15:23:53',1,1,0,0,1),(25,'18','18@boson.com','18','2022-08-13 23:25:21','2022-08-13 23:25:21',1,1,0,0,0),(26,'19','19@boson.com','19','2022-08-14 15:15:47','2022-08-14 15:15:47',1,1,0,0,0);
/*!40000 ALTER TABLE `security_user_info` ENABLE KEYS */;
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
  `is_built_in` tinyint NOT NULL DEFAULT '0',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='安全模块\n用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_role_relation`
--

LOCK TABLES `security_user_role_relation` WRITE;
/*!40000 ALTER TABLE `security_user_role_relation` DISABLE KEYS */;
INSERT INTO `security_user_role_relation` VALUES (1,1,1,'2022-08-14 00:00:47','2022-08-14 00:00:47',1,1,0,0,0),(2,1,2,'2022-08-14 00:00:52','2022-08-14 00:00:52',1,1,0,0,0),(3,1,3,'2022-08-14 00:00:55','2022-08-14 00:00:55',1,1,0,0,0),(4,1,4,'2022-08-14 00:01:01','2022-08-14 00:01:01',1,1,0,0,0),(5,2,2,'2022-08-14 00:02:20','2022-08-14 00:02:20',1,1,0,0,0),(6,2,3,'2022-08-14 00:02:28','2022-08-14 00:02:28',1,1,0,0,0),(7,2,4,'2022-08-14 00:02:31','2022-08-14 00:02:31',1,1,0,0,0),(8,3,3,'2022-08-14 00:02:36','2022-08-14 00:02:36',1,1,0,0,0),(9,3,4,'2022-08-14 00:02:41','2022-08-14 00:02:41',1,1,0,0,0),(10,4,4,'2022-08-14 00:02:45','2022-08-14 00:02:45',1,1,0,0,0);
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

-- Dump completed on 2022-08-15 15:53:10

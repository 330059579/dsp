-- --------------------------------------------------------
-- 主机:                           94.191.72.230
-- Server version:               5.7.25 - MySQL Community Server (GPL)
-- Server OS:                    Linux
-- HeidiSQL 版本:                  10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ad
CREATE DATABASE IF NOT EXISTS `ad` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ad`;

-- Dumping structure for table ad.ad_creative
CREATE TABLE IF NOT EXISTS `ad_creative` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_german2_ci NOT NULL,
  `type` int(11) NOT NULL COMMENT '物料类型',
  `material_type` int(11) NOT NULL COMMENT '物料子类型',
  `height` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `size` int(11) NOT NULL COMMENT '物料大小，单位kb',
  `duration` int(11) NOT NULL COMMENT '持续时长，视频不为0',
  `audit_status` int(11) NOT NULL COMMENT '审核状态',
  `user_id` bigint(20) NOT NULL,
  `url` varchar(200) COLLATE utf8_german2_ci NOT NULL COMMENT '物料地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- Dumping data for table ad.ad_creative: ~0 rows (approximately)
/*!40000 ALTER TABLE `ad_creative` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_creative` ENABLE KEYS */;

-- Dumping structure for table ad.ad_creative_unit
CREATE TABLE IF NOT EXISTS `ad_creative_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creative_id` int(11) NOT NULL,
  `unit_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- Dumping data for table ad.ad_creative_unit: ~0 rows (approximately)
/*!40000 ALTER TABLE `ad_creative_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_creative_unit` ENABLE KEYS */;

-- Dumping structure for table ad.ad_plan
CREATE TABLE IF NOT EXISTS `ad_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `plan_name` varchar(100) COLLATE utf8_german2_ci NOT NULL DEFAULT '',
  `plan_status` int(11) NOT NULL DEFAULT '1',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- Dumping data for table ad.ad_plan: ~0 rows (approximately)
/*!40000 ALTER TABLE `ad_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_plan` ENABLE KEYS */;

-- Dumping structure for table ad.ad_unit
CREATE TABLE IF NOT EXISTS `ad_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `plan_id` bigint(20) NOT NULL,
  `unit_name` varchar(100) COLLATE utf8_german2_ci NOT NULL DEFAULT '',
  `unit_status` int(11) NOT NULL DEFAULT '1',
  `position_type` int(11) NOT NULL,
  `budget` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `plan_id` (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- Dumping data for table ad.ad_unit: ~0 rows (approximately)
/*!40000 ALTER TABLE `ad_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_unit` ENABLE KEYS */;

-- Dumping structure for table ad.ad_unit_district
CREATE TABLE IF NOT EXISTS `ad_unit_district` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_id` bigint(20) NOT NULL,
  `province` varchar(50) COLLATE utf8_german2_ci NOT NULL DEFAULT '',
  `city` varchar(50) COLLATE utf8_german2_ci NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `unit_id` (`unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- Dumping data for table ad.ad_unit_district: ~0 rows (approximately)
/*!40000 ALTER TABLE `ad_unit_district` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_unit_district` ENABLE KEYS */;

-- Dumping structure for table ad.ad_unit_it
CREATE TABLE IF NOT EXISTS `ad_unit_it` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_id` bigint(20) NOT NULL,
  `it_tag` varchar(50) COLLATE utf8_german2_ci NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `unit_id` (`unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- Dumping data for table ad.ad_unit_it: ~0 rows (approximately)
/*!40000 ALTER TABLE `ad_unit_it` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_unit_it` ENABLE KEYS */;

-- Dumping structure for table ad.ad_unit_keyword
CREATE TABLE IF NOT EXISTS `ad_unit_keyword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_id` bigint(20) NOT NULL,
  `key_word` varchar(50) COLLATE utf8_german2_ci NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `unit_id` (`unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- Dumping data for table ad.ad_unit_keyword: ~0 rows (approximately)
/*!40000 ALTER TABLE `ad_unit_keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_unit_keyword` ENABLE KEYS */;

-- Dumping structure for table ad.ad_user
CREATE TABLE IF NOT EXISTS `ad_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_german2_ci NOT NULL,
  `user_status` int(11) NOT NULL DEFAULT '1',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- Dumping data for table ad.ad_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `ad_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

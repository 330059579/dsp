-- --------------------------------------------------------
-- 主机:                           106.52.236.197
-- 服务器版本:                        5.7.29-0ubuntu0.18.04.1-log - (Ubuntu)
-- 服务器OS:                        Linux
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table ad.ad_creative: ~1 rows (大约)
/*!40000 ALTER TABLE `ad_creative` DISABLE KEYS */;
INSERT INTO `ad_creative` (`id`, `name`, `type`, `material_type`, `height`, `width`, `size`, `duration`, `audit_status`, `user_id`, `url`, `create_time`, `update_time`) VALUES
	(10, '第一个创意', 1, 1, 720, 1080, 1024, 0, 1, 15, 'https://www.imooc.com', '2018-11-19 21:31:31', '2018-11-19 21:31:31');
/*!40000 ALTER TABLE `ad_creative` ENABLE KEYS */;

-- Dumping data for table ad.ad_creative_unit: ~1 rows (大约)
/*!40000 ALTER TABLE `ad_creative_unit` DISABLE KEYS */;
INSERT INTO `ad_creative_unit` (`id`, `creative_id`, `unit_id`, `create_time`, `update_time`) VALUES
	(10, 10, 10, '2020-04-20 00:05:55', '2020-04-20 00:05:55');
/*!40000 ALTER TABLE `ad_creative_unit` ENABLE KEYS */;

-- Dumping data for table ad.ad_plan: ~1 rows (大约)
/*!40000 ALTER TABLE `ad_plan` DISABLE KEYS */;
INSERT INTO `ad_plan` (`id`, `user_id`, `plan_name`, `plan_status`, `start_date`, `end_date`, `create_time`, `update_time`) VALUES
	(10, 15, '推广计划名称', 1, '2018-11-28 00:00:00', '2019-11-20 00:00:00', '2018-11-19 20:42:27', '2018-11-19 20:57:12');
/*!40000 ALTER TABLE `ad_plan` ENABLE KEYS */;

-- Dumping data for table ad.ad_unit: ~2 rows (大约)
/*!40000 ALTER TABLE `ad_unit` DISABLE KEYS */;
INSERT INTO `ad_unit` (`id`, `plan_id`, `unit_name`, `unit_status`, `position_type`, `budget`, `create_time`, `update_time`) VALUES
	(10, 10, '第一个推广单元', 1, 1, 10000000, '2018-11-20 11:43:26', '2018-11-20 11:43:26'),
	(12, 10, '第二个推广单元', 1, 1, 15000000, '2018-01-01 00:00:00', '2018-01-01 00:00:00');
/*!40000 ALTER TABLE `ad_unit` ENABLE KEYS */;

-- Dumping data for table ad.ad_unit_district: ~4 rows (大约)
/*!40000 ALTER TABLE `ad_unit_district` DISABLE KEYS */;
INSERT INTO `ad_unit_district` (`id`, `unit_id`, `province`, `city`, `create_time`, `update_time`) VALUES
	(10, 10, '安徽省', '淮北市', '2020-04-20 00:05:55', '2020-04-20 00:05:55'),
	(11, 10, '安徽省', '宿州市', '2020-04-20 00:05:55', '2020-04-20 00:05:55'),
	(12, 10, '安徽省', '合肥市', '2020-04-20 00:05:55', '2020-04-20 00:05:55'),
	(14, 10, '辽宁省', '大连市', '2020-04-20 00:05:55', '2020-04-20 00:05:55');
/*!40000 ALTER TABLE `ad_unit_district` ENABLE KEYS */;

-- Dumping data for table ad.ad_unit_it: ~3 rows (大约)
/*!40000 ALTER TABLE `ad_unit_it` DISABLE KEYS */;
INSERT INTO `ad_unit_it` (`id`, `unit_id`, `it_tag`, `create_time`, `update_time`) VALUES
	(10, 10, '台球', '2020-04-20 00:05:55', '2020-04-20 00:05:55'),
	(11, 10, '游泳', '2020-04-20 00:05:55', '2020-04-20 00:05:55'),
	(12, 10, '乒乓球', '2020-04-20 00:05:55', '2020-04-20 00:05:55');
/*!40000 ALTER TABLE `ad_unit_it` ENABLE KEYS */;

-- Dumping data for table ad.ad_unit_keyword: ~3 rows (大约)
/*!40000 ALTER TABLE `ad_unit_keyword` DISABLE KEYS */;
INSERT INTO `ad_unit_keyword` (`id`, `unit_id`, `key_word`, `create_time`, `update_time`) VALUES
	(10, 10, '宝马', '2020-04-20 00:05:55', '2020-04-20 00:05:55'),
	(11, 10, '奥迪', '2020-04-20 00:05:55', '2020-04-20 00:05:55'),
	(12, 10, '大众', '2020-04-20 00:05:55', '2020-04-20 00:05:55');
/*!40000 ALTER TABLE `ad_unit_keyword` ENABLE KEYS */;

-- Dumping data for table ad.ad_user: ~1 rows (大约)
/*!40000 ALTER TABLE `ad_user` DISABLE KEYS */;
INSERT INTO `ad_user` (`id`, `username`, `token`, `user_status`, `create_time`, `update_time`) VALUES
	(15, 'qinyi', 'asdfsdfsdfsdf', 1, '2020-04-22 09:11:42', '2018-11-19 20:29:01');
/*!40000 ALTER TABLE `ad_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

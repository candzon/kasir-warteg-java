-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_warteg
CREATE DATABASE IF NOT EXISTS `db_warteg` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_warteg`;

-- Dumping structure for table db_warteg.tb_detailtransaksi
CREATE TABLE IF NOT EXISTS `tb_detailtransaksi` (
  `id_detail` int NOT NULL AUTO_INCREMENT,
  `nama_pembeli` varchar(100) DEFAULT NULL,
  `id_paket` int DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `jumlah` int DEFAULT NULL,
  `total_harga` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL,
  PRIMARY KEY (`id_detail`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_warteg.tb_detailtransaksi: ~2 rows (approximately)
INSERT INTO `tb_detailtransaksi` (`id_detail`, `nama_pembeli`, `id_paket`, `harga`, `jumlah`, `total_harga`, `created_at`, `updated_at`) VALUES
	(1, 'Husnaini', 2, 16000, 2, '32000.0', '2024-06-29 15:32:05', '2024-06-29 15:32:05'),
	(2, 'Budi', 2, 16000, 1, '16000.0', '2024-06-29 15:33:54', '2024-06-29 15:33:54'),
	(3, 'junaidi karo', 3, 10000, 2, '20000.0', '2024-06-29 16:20:07', '2024-06-29 16:20:07');

-- Dumping structure for table db_warteg.tb_paketmakanan
CREATE TABLE IF NOT EXISTS `tb_paketmakanan` (
  `id_paket` int NOT NULL AUTO_INCREMENT,
  `nama_paket` varchar(100) NOT NULL,
  `keterangan` text NOT NULL,
  `harga` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL,
  PRIMARY KEY (`id_paket`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_warteg.tb_paketmakanan: ~0 rows (approximately)
INSERT INTO `tb_paketmakanan` (`id_paket`, `nama_paket`, `keterangan`, `harga`, `created_at`, `updated_at`) VALUES
	(2, 'PAKET GOKIL', 'tempe orek, tahu krecek, ayam goreng, es teh manis', '16000', '2024-06-27 10:10:29', '2024-06-27 10:10:29'),
	(3, 'PAKET CEBANS', 'telor, es teh tawar, nasi', '10000', '2024-06-29 16:18:10', '2024-06-29 16:18:10');

-- Dumping structure for table db_warteg.tb_role
CREATE TABLE IF NOT EXISTS `tb_role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_warteg.tb_role: ~3 rows (approximately)
INSERT INTO `tb_role` (`id_role`, `role`, `created_at`, `updated_at`) VALUES
	(1, 'admin', '2024-06-27 04:00:51', '2024-06-27 04:00:52'),
	(2, 'kasir', '2024-06-27 04:01:12', '2024-06-27 04:01:14'),
	(3, 'owner', '2024-06-27 04:01:24', '2024-06-27 04:01:25');

-- Dumping structure for table db_warteg.tb_user
CREATE TABLE IF NOT EXISTS `tb_user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `id_role` int NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `id_role` (`id_role`),
  CONSTRAINT `FK_tb_user_tb_role` FOREIGN KEY (`id_role`) REFERENCES `tb_role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_warteg.tb_user: ~5 rows (approximately)
INSERT INTO `tb_user` (`id_user`, `id_role`, `nama`, `username`, `password`, `created_at`, `updated_at`) VALUES
	(1, 1, 'Admin', 'admin', 'admin', '2024-06-27 10:29:13', '2024-06-27 10:29:14'),
	(2, 2, 'Kasir', 'kasir', 'kasir', '2024-06-27 10:29:15', '2024-06-27 10:29:16'),
	(3, 3, 'Owner', 'owner', 'owner', '2024-06-27 10:29:18', '2024-06-27 10:29:19'),
	(4, 3, 'Feri Candra', 'feri', '12345', '2024-06-29 05:09:48', '2024-06-29 05:09:48'),
	(5, 3, 'test tambah', 'juned', 'juned', '2024-06-29 07:39:44', '2024-06-29 07:39:44'),
	(6, 2, 'test tambah2', 'testtttt', '12345', '2024-06-29 16:08:08', '2024-06-29 16:08:08');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

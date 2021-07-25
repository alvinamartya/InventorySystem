-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 25, 2021 at 11:12 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

CREATE TABLE `drivers` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `vehicle_id` varchar(10) NOT NULL,
  `warehouse_id` varchar(5) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `status` enum('A','D') NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `drivers`
--

INSERT INTO `drivers` (`id`, `name`, `vehicle_id`, `warehouse_id`, `phone`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES
(1, 'Teddyanto Jamal', 'HNDR230621', '00001', '08235615362', 'A', '2021-06-23 16:43:43', 'Admin', '2021-06-23 16:43:43', 'Admin'),
(2, 'Rafli Mayori', 'HNDR230622', '00002', '083721511', 'A', '2021-06-23 16:46:06', 'Admin', '2021-06-23 16:46:06', 'Admin'),
(3, 'Albertus Aris', 'HNDR230623', '00002', '087125311', 'A', '2021-06-23 16:46:46', 'Admin', '2021-06-23 16:46:46', 'Admin'),
(4, 'Andani', 'HNDR230624', '00001', '08235453212', 'A', '2021-06-23 16:48:11', 'Admin', '2021-06-23 16:48:11', 'Admin'),
(5, 'Yusup Fikri', 'HNDR230625', '00006', '08254535362', 'A', '2021-06-23 16:48:49', 'Admin', '2021-06-23 16:49:24', 'Admin'),
(6, 'Chorida', 'HNDR230625', '00005', '081316717280', 'A', '2021-06-23 19:36:26', 'Admin', '2021-06-24 02:35:09', 'Admin'),
(7, 'Anggian', 'HNDR233333', '00006', '081267784444', 'D', '2021-06-23 19:47:28', 'Admin', '2021-06-24 02:33:03', 'Admin'),
(8, 'Rafli', 'B784278', '00001', '89389901313', 'A', '2021-06-24 02:59:21', 'Admin', '2021-06-24 02:59:21', 'Admin'),
(9, 'Tebe', 'B7398C', '00001', '081361728844', 'A', '2021-06-24 03:02:33', 'Admin', '2021-06-24 03:02:33', 'Admin'),
(10, 'Habibah', 'B8947JK', '00006', '081315782828', 'A', '2021-06-24 03:05:07', 'Admin', '2021-06-24 03:05:07', 'Admin'),
(11, 'Satria', 'B7398C', '00005', '081316717280', 'A', '2021-06-24 13:27:11', 'Admin', '2021-06-24 13:27:11', 'Admin'),
(12, 'Tes', 'B7398C', '00001', '081316717288', 'A', '2021-06-24 13:30:25', 'Admin', '2021-06-24 13:30:25', 'Admin'),
(13, 'Tes', 'B7398C', '00002', '081316717288', 'A', '2021-06-28 02:00:27', 'Admin', '2021-06-28 02:00:27', 'Admin'),
(14, 'Tes', 'B7398C', '00002', '081316717288', 'A', '2021-06-28 02:00:33', 'Admin', '2021-06-28 02:00:33', 'Admin'),
(15, 'Resa', 'B7398C', '00012', '081316717280', 'A', '2021-07-23 18:34:04', 'Anggian', '2021-07-23 18:34:04', 'Anggian');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` varchar(31) NOT NULL,
  `origin_id` varchar(6) NOT NULL,
  `dest_id` varchar(6) NOT NULL,
  `origin_type` enum('Gudang','Pemasok') NOT NULL,
  `dest_type` enum('Gudang','Toko') NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `driver_id` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `approved_at` datetime DEFAULT current_timestamp(),
  `approved_by` varchar(100) NOT NULL,
  `checked_at` datetime DEFAULT current_timestamp(),
  `checked_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL,
  `warehouse_at` varchar(10) DEFAULT NULL,
  `status_order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `origin_id`, `dest_id`, `origin_type`, `dest_type`, `date`, `driver_id`, `created_at`, `created_by`, `approved_at`, `approved_by`, `checked_at`, `checked_by`, `updated_at`, `updated_by`, `warehouse_at`, `status_order_id`) VALUES
('FO-S00003-W00006-20210723-00002', '00003', '00006', 'Pemasok', 'Gudang', '2021-07-23 20:41:31', 0, '2021-07-23 20:41:31', 'Chorida', '2021-07-23 20:45:05', 'Chorida', '2021-07-23 20:44:27', 'Salsa', '2021-07-23 20:46:45', 'Salsa', '00006', 3),
('FO-S00004-W00006-20210723-00001', '00004', '00006', 'Pemasok', 'Gudang', '2021-07-23 14:34:38', 0, '2021-07-23 14:34:38', 'Chorida', '2021-07-23 15:17:42', 'Chorida', '2021-07-23 15:00:43', 'Salsa', '2021-07-23 16:14:16', 'Chorida', '00006', 3),
('FO-S00005-W00006-20210725-00002', '00005', '00006', 'Pemasok', 'Gudang', '2021-07-25 05:29:00', 0, '2021-07-25 05:29:00', 'Chorida', '2021-07-25 13:26:41', 'Chorida', '2021-07-25 05:32:20', 'Salsa', '2021-07-25 13:26:41', 'Chorida', '00006', 4),
('FO-W00005-T1-20210723-00001', '00005', '1', 'Gudang', 'Toko', '2021-07-23 13:40:14', 6, '2021-07-23 13:40:14', 'Alvin Amartya', '2021-07-23 13:40:14', '-', '2021-07-23 13:40:14', '-', '2021-07-23 13:40:14', 'Alvin Amartya', '00005', 1),
('FO-W00005-T6-20210723-00003', '00005', '6', 'Gudang', 'Toko', '2021-07-23 20:50:08', 11, '2021-07-23 20:50:08', 'Alvin Amartya', '2021-07-23 20:50:53', 'Alvin Amartya', '2021-07-23 20:50:36', 'Aditya Prasetio', '2021-07-23 20:50:53', 'Alvin Amartya', '00005', 4),
('FO-W00006-W00005-20210723-00002', '00006', '00005', 'Gudang', 'Gudang', '2021-07-23 20:47:51', 5, '2021-07-23 20:47:51', 'Chorida', '2021-07-23 20:49:15', 'Chorida', '2021-07-23 20:49:03', 'Salsa', '2021-07-23 20:49:15', 'Chorida', '00006', 4);

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `order_id` varchar(31) NOT NULL,
  `product_id` int(5) NOT NULL,
  `dest_shelf_id` varchar(20) DEFAULT NULL,
  `origin_shelf_id` varchar(20) DEFAULT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`id`, `order_id`, `product_id`, `dest_shelf_id`, `origin_shelf_id`, `quantity`) VALUES
(38, 'FO-W00005-T1-20210723-00001', 3, 'RO-00002-00002-00001', 'RO-00002-00002-00001', 5),
(39, 'FO-S00004-W00006-20210723-00001', 4, 'RO-00002-00004-00001', 'RO-00002-00002-00001', 2),
(40, 'FO-S00003-W00006-20210723-00002', 4, 'RO-00002-00004-00001', 'RO-00002-00004-00001', 2),
(41, 'FO-S00003-W00006-20210723-00002', 4, 'RO-00002-00004-00001', 'RO-00002-00004-00001', 2),
(42, 'FO-W00006-W00005-20210723-00002', 2, 'RO-00002-00002-00001', 'RO-00002-00004-00001', 2),
(43, 'FO-W00005-T6-20210723-00003', 2, 'RO-00002-00004-00001', 'RO-00002-00002-00001', 2),
(44, 'FO-S00005-W00006-20210725-00002', 4, 'RO-00002-00004-00001', 'RO-00002-00002-00001', 5),
(45, 'FO-S00005-W00006-20210725-00002', 3, 'RO-00002-00004-00001', 'RO-00002-00004-00001', 3);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `product_category_id` varchar(5) NOT NULL,
  `units` varchar(100) NOT NULL,
  `status` enum('A','D') NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `product_category_id`, `units`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES
(1, 'Indomie Rendang', '00001', 'Dus', 'A', '2021-06-23 18:19:34', 'Admin', '2021-06-23 18:19:34', 'Admin'),
(2, 'Indomie Curry', '00001', 'Dus', 'A', '2021-06-23 18:20:01', 'Admin', '2021-06-23 18:20:01', 'Admin'),
(3, 'Pronas', '00002', 'Unit', 'A', '2021-06-23 18:20:52', 'Admin', '2021-06-23 18:20:52', 'Admin'),
(4, 'Cheetos', '00005', 'Dus', 'A', '2021-06-23 18:23:11', 'Admin', '2021-06-23 18:23:11', 'Admin'),
(5, 'Doritos', '00005', 'Dus', 'A', '2021-06-23 18:24:40', 'Admin', '2021-06-23 18:24:40', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `product_categories`
--

CREATE TABLE `product_categories` (
  `id` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `is_can_be_stale` int(11) NOT NULL,
  `status` enum('A','D') NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_categories`
--

INSERT INTO `product_categories` (`id`, `name`, `is_can_be_stale`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES
('00001', 'Instant Noodle', 1, 'A', '2021-06-23 16:55:39', 'Admin', '2021-06-23 16:55:39', 'Admin'),
('00002', 'Canned Food', 1, 'A', '2021-06-23 16:57:20', 'Admin', '2021-06-23 16:57:20', 'Admin'),
('00003', 'Cooking Ware', 0, 'A', '2021-06-23 16:59:49', 'Admin', '2021-06-23 16:59:49', 'Admin'),
('00004', 'Clothes', 0, 'A', '2021-06-23 17:02:37', 'Admin', '2021-06-23 17:02:37', 'Admin'),
('00005', 'Snacks', 1, 'A', '2021-06-23 17:03:12', 'Admin', '2021-06-23 17:03:12', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `retur`
--

CREATE TABLE `retur` (
  `id` varchar(31) NOT NULL,
  `origin_id` varchar(6) NOT NULL,
  `order_type` enum('T','W') NOT NULL,
  `dest_id` varchar(6) NOT NULL,
  `dest_type` enum('W','S') NOT NULL,
  `date` datetime NOT NULL,
  `driver_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `approved_at` datetime NOT NULL,
  `approved_by` varchar(100) NOT NULL,
  `checked_at` datetime NOT NULL,
  `checked_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(100) NOT NULL,
  `warehouse_at` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `retur`
--

INSERT INTO `retur` (`id`, `origin_id`, `order_type`, `dest_id`, `dest_type`, `date`, `driver_id`, `created_at`, `created_by`, `approved_at`, `approved_by`, `checked_at`, `checked_by`, `updated_at`, `updated_by`, `warehouse_at`) VALUES
('FR-T1-W00005-20210719-00008', '1', 'T', '00005', 'W', '2021-07-19 13:16:59', 7, '2021-07-19 13:16:59', 'Admin Transaksi', '2021-07-21 12:56:42', 'Admin Transaksi', '2021-07-21 12:54:24', 'Admin Gudang', '2021-07-21 12:56:42', 'Admin Transaksi', NULL),
('FR-T1-W00005-20210721-00009', '1', 'T', '00005', 'W', '2021-07-21 13:30:08', 6, '2021-07-21 13:30:08', 'Admin Transaksi', '2021-07-21 13:31:22', 'Admin Transaksi', '2021-07-21 13:31:12', 'Admin Gudang', '2021-07-21 13:31:22', 'Admin Transaksi', NULL),
('FR-T2-W00002-20210706-00001', '2', 'T', '00002', 'W', '2021-07-14 20:02:59', 2, '2021-07-14 20:02:59', 'Admin', '2021-07-21 12:57:03', 'Admin Transaksi', '2021-07-21 12:56:49', 'Admin Gudang', '2021-07-21 12:57:03', 'Admin Transaksi', NULL),
('FR-T2-W00002-20210719-00002', '2', 'T', '00002', 'W', '2021-07-19 12:06:12', 3, '2021-07-19 12:06:12', 'Admin Transaksi', '2021-07-19 12:06:12', '-', '2021-07-19 12:06:12', '-', '2021-07-19 12:06:12', 'Admin Transaksi', NULL),
('FR-T2-W00002-20210719-00003', '2', 'T', '00002', 'W', '2021-07-19 12:47:18', 3, '2021-07-19 12:47:18', 'Admin Transaksi', '2021-07-19 12:47:18', '-', '2021-07-19 12:47:18', '-', '2021-07-19 12:47:18', 'Admin Transaksi', NULL),
('FR-T2-W00002-20210719-00004', '2', 'T', '00002', 'W', '2021-07-19 12:49:28', 3, '2021-07-19 12:49:28', 'Admin Transaksi', '2021-07-19 12:49:28', '-', '2021-07-19 12:49:28', '-', '2021-07-19 12:49:28', 'Admin Transaksi', NULL),
('FR-T2-W00002-20210719-00005', '2', 'T', '00002', 'W', '2021-07-19 12:52:06', 3, '2021-07-19 12:52:06', 'Admin Transaksi', '2021-07-19 12:52:06', '-', '2021-07-19 12:52:06', '-', '2021-07-19 12:52:06', 'Admin Transaksi', NULL),
('FR-T2-W00002-20210719-00006', '2', 'T', '00002', 'W', '2021-07-19 12:57:07', 3, '2021-07-19 12:57:07', 'Admin Transaksi', '2021-07-19 12:57:07', '-', '2021-07-19 12:57:07', '-', '2021-07-19 12:57:07', 'Admin Transaksi', NULL),
('FR-T2-W00002-20210719-00007', '2', 'T', '00002', 'W', '2021-07-19 12:59:22', 3, '2021-07-19 12:59:22', 'Admin Transaksi', '2021-07-19 12:59:22', '-', '2021-07-21 13:02:01', 'Admin Gudang', '2021-07-21 13:02:01', 'Admin Gudang', NULL),
('FR-T3-S00003-20210720-00009', '3', 'T', '00003', 'S', '2021-07-20 23:18:43', 2, '2021-07-20 23:18:43', 'Admin Transaksi', '2021-07-20 23:18:43', '-', '2021-07-20 23:18:43', '-', '2021-07-20 23:18:43', 'Admin Transaksi', NULL),
('FR-T3-W00003-20210719-00008', '3', 'T', '00003', 'W', '2021-07-19 14:20:30', 7, '2021-07-19 14:20:30', 'Admin Transaksi', '2021-07-19 14:20:30', '-', '2021-07-19 14:20:30', '-', '2021-07-19 14:20:30', 'Admin Transaksi', NULL),
('FR-W5-W00002-20210721-00001', '5', 'W', '00002', 'W', '2021-07-21 15:45:51', 3, '2021-07-21 15:45:51', 'Admin Transaksi', '2021-07-21 15:45:51', '-', '2021-07-21 15:45:51', '-', '2021-07-21 15:45:51', 'Admin Transaksi', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `retur_details`
--

CREATE TABLE `retur_details` (
  `id` int(11) NOT NULL,
  `retur_id` varchar(31) NOT NULL,
  `product_id` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `origin_shelf_id` varchar(20) DEFAULT NULL,
  `dest_shelf_id` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `retur_details`
--

INSERT INTO `retur_details` (`id`, `retur_id`, `product_id`, `qty`, `origin_shelf_id`, `dest_shelf_id`) VALUES
(1, 'FR-T2-W00002-20210706-00001', 1, 10, NULL, 'RR-00002-00004-00001'),
(2, 'FR-T2-W00002-20210719-00006', 4, 3, 'RO-00002-00004-00001', 'RO-00002-00002-00001'),
(3, 'FR-T2-W00002-20210719-00007', 4, 3, 'RO-00002-00002-00001', 'RO-00002-00002-00001'),
(4, 'FR-T1-W00005-20210719-00008', 2, 3, NULL, 'RO-00002-00002-00001'),
(9, 'FR-T1-W00005-20210719-00008', 3, 3, '', 'RO-00002-00002-00001'),
(10, 'FR-T3-W00003-20210719-00008', 1, 2, '', 'RO-00002-00002-00001'),
(11, 'FR-T3-S00003-20210720-00009', 1, 3, '', 'RO-00002-00002-00001'),
(12, 'FR-T1-W00005-20210721-00009', 4, 2, '', 'RO-00002-00002-00001'),
(13, 'FR-W5-W00002-20210721-00001', 4, 2, 'RO-00002-00002-00001', 'RO-00002-00002-00001');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'Admin Transaksi'),
(2, 'Admin Gudang'),
(3, 'Admin Master Data'),
(4, 'Super Admin');

-- --------------------------------------------------------

--
-- Table structure for table `shelf`
--

CREATE TABLE `shelf` (
  `id` varchar(20) NOT NULL,
  `warehouse_id` varchar(5) NOT NULL,
  `type_shelf` enum('Rak Order','Rak Retur') NOT NULL,
  `product_category_id` varchar(5) NOT NULL,
  `rows_shelf` int(11) NOT NULL,
  `columns_shelf` int(11) NOT NULL,
  `quantity_shelf` int(11) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `created_by` varchar(100) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `is_empty` int(1) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shelf`
--

INSERT INTO `shelf` (`id`, `warehouse_id`, `type_shelf`, `product_category_id`, `rows_shelf`, `columns_shelf`, `quantity_shelf`, `created_at`, `created_by`, `updated_at`, `updated_by`, `is_empty`, `type`) VALUES
('RO-00002-00002-00001', '00002', 'Rak Order', '00002', 1, 2, 2, '2021-06-28 17:24:09', 'Admin', '2021-06-28 17:24:09', 'Admin', 1, NULL),
('RO-00002-00004-00001', '00002', 'Rak Order', '00004', 5, 4, 10, '2021-07-03 22:21:56', 'Admin', '2021-07-03 22:21:56', 'Admin', 1, NULL),
('RO-00006-00004-00001', '00006', 'Rak Order', '00004', 1, 2, 2, '2021-07-23 18:39:48', 'Salsa', '2021-07-23 18:39:48', 'Salsa', 1, NULL),
('RR-00002-00004-00001', '00002', 'Rak Retur', '00004', 1, 2, 3, '2021-06-28 17:28:02', 'Admin', '2021-06-28 17:28:02', 'Admin', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `shelf_details`
--

CREATE TABLE `shelf_details` (
  `id` int(11) NOT NULL,
  `shelf_id` varchar(20) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `expired_at` datetime DEFAULT NULL,
  `row_shelf` int(11) NOT NULL,
  `col_shelf` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shelf_details`
--

INSERT INTO `shelf_details` (`id`, `shelf_id`, `product_id`, `expired_at`, `row_shelf`, `col_shelf`) VALUES
(104, 'RO-00002-00002-00001', NULL, NULL, 1, 1),
(105, 'RO-00002-00002-00001', NULL, NULL, 1, 1),
(106, 'RO-00002-00002-00001', NULL, NULL, 1, 2),
(107, 'RO-00002-00002-00001', NULL, NULL, 1, 2),
(108, 'RR-00002-00004-00001', NULL, NULL, 1, 1),
(109, 'RR-00002-00004-00001', NULL, NULL, 1, 1),
(110, 'RR-00002-00004-00001', NULL, NULL, 1, 1),
(111, 'RR-00002-00004-00001', NULL, NULL, 1, 2),
(112, 'RR-00002-00004-00001', NULL, NULL, 1, 2),
(113, 'RR-00002-00004-00001', NULL, NULL, 1, 2),
(114, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(115, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(116, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(117, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(118, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(119, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(120, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(121, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(122, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(123, 'RO-00002-00004-00001', NULL, NULL, 1, 1),
(124, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(125, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(126, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(127, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(128, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(129, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(130, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(131, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(132, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(133, 'RO-00002-00004-00001', NULL, NULL, 1, 2),
(134, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(135, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(136, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(137, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(138, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(139, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(140, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(141, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(142, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(143, 'RO-00002-00004-00001', NULL, NULL, 1, 3),
(144, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(145, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(146, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(147, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(148, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(149, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(150, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(151, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(152, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(153, 'RO-00002-00004-00001', NULL, NULL, 1, 4),
(154, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(155, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(156, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(157, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(158, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(159, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(160, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(161, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(162, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(163, 'RO-00002-00004-00001', NULL, NULL, 2, 1),
(164, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(165, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(166, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(167, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(168, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(169, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(170, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(171, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(172, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(173, 'RO-00002-00004-00001', NULL, NULL, 2, 2),
(174, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(175, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(176, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(177, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(178, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(179, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(180, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(181, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(182, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(183, 'RO-00002-00004-00001', NULL, NULL, 2, 3),
(184, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(185, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(186, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(187, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(188, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(189, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(190, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(191, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(192, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(193, 'RO-00002-00004-00001', NULL, NULL, 2, 4),
(194, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(195, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(196, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(197, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(198, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(199, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(200, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(201, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(202, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(203, 'RO-00002-00004-00001', NULL, NULL, 3, 1),
(204, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(205, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(206, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(207, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(208, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(209, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(210, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(211, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(212, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(213, 'RO-00002-00004-00001', NULL, NULL, 3, 2),
(214, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(215, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(216, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(217, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(218, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(219, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(220, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(221, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(222, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(223, 'RO-00002-00004-00001', NULL, NULL, 3, 3),
(224, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(225, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(226, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(227, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(228, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(229, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(230, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(231, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(232, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(233, 'RO-00002-00004-00001', NULL, NULL, 3, 4),
(234, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(235, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(236, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(237, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(238, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(239, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(240, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(241, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(242, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(243, 'RO-00002-00004-00001', NULL, NULL, 4, 1),
(244, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(245, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(246, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(247, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(248, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(249, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(250, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(251, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(252, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(253, 'RO-00002-00004-00001', NULL, NULL, 4, 2),
(254, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(255, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(256, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(257, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(258, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(259, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(260, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(261, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(262, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(263, 'RO-00002-00004-00001', NULL, NULL, 4, 3),
(264, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(265, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(266, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(267, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(268, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(269, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(270, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(271, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(272, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(273, 'RO-00002-00004-00001', NULL, NULL, 4, 4),
(274, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(275, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(276, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(277, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(278, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(279, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(280, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(281, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(282, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(283, 'RO-00002-00004-00001', NULL, NULL, 5, 1),
(284, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(285, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(286, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(287, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(288, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(289, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(290, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(291, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(292, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(293, 'RO-00002-00004-00001', NULL, NULL, 5, 2),
(294, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(295, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(296, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(297, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(298, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(299, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(300, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(301, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(302, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(303, 'RO-00002-00004-00001', NULL, NULL, 5, 3),
(304, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(305, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(306, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(307, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(308, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(309, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(310, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(311, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(312, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(313, 'RO-00002-00004-00001', NULL, NULL, 5, 4),
(314, 'RO-00006-00004-00001', NULL, NULL, 1, 1),
(315, 'RO-00006-00004-00001', NULL, NULL, 1, 1),
(316, 'RO-00006-00004-00001', NULL, NULL, 1, 2),
(317, 'RO-00006-00004-00001', NULL, NULL, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `staffs`
--

CREATE TABLE `staffs` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `gender` enum('M','F') NOT NULL,
  `role_id` int(11) NOT NULL,
  `warehouse_id` varchar(10) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` enum('A','D') NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `staffs`
--

INSERT INTO `staffs` (`id`, `name`, `phone`, `address`, `gender`, `role_id`, `warehouse_id`, `email`, `password`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES
(2, 'Chorida', '081316717280', 'Jl. Manggar 3Y', 'F', 1, '00006', 'chori@gmail.com', '$2a$10$huCZ3nKIHjcMhgoAVBZxCuL9j11fgE8vbVJTNTO/378CbxQD4PLUS', 'A', '2021-07-19 10:36:26', 'Admin', '2021-07-19 10:36:26', 'Admin'),
(3, 'Salsa', '081316717280', 'Jl. Manggar 3Y', 'F', 2, '00006', 'salsa@gmail.com', '$2a$10$1lo9t4hQPu/I4Xxw0a0ev.gbdNpgrTak8fqVTcekLt5NqpP4c43j2', 'A', '2021-07-19 10:37:51', 'Admin', '2021-07-19 10:37:51', 'Admin'),
(4, 'Alvin Amartya', '081316717280', 'Jl. Manggar 3Y', 'M', 1, '00005', 'alvin@gmail.com', '$2a$10$iXu10ocIKl5xhEL9gMqZteuJTamOqhagKbWxlxeG7hNED85ICmvI6', 'A', '2021-07-19 10:38:14', 'Admin', '2021-07-19 10:38:14', 'Admin'),
(5, 'Aditya Prasetio', '081316717280', 'Jl. Manggar 3Y', 'M', 2, '00005', 'adit@gmail.com', '$2a$10$enonrQEfMhAADA/.16.2X.N6Yw3iJMKflV7S95HA83eL6/2nbW/r2', 'A', '2021-07-23 00:14:38', 'Admin', '2021-07-23 19:22:11', 'Admin'),
(6, 'superadmin', '081316717280', 'Jl. Manggar 3Y', 'M', 4, '00006', 'djadjanadmin@gmail.com', '$2a$10$GaOBRxN4cmciteoRFYBxdOoNJCnZwtft7b0O1GDKahrrIqf8tfwgK', 'A', '2021-07-23 00:57:42', 'Admin', '2021-07-23 00:57:42', 'Admin'),
(7, 'Anggian', '081316717280', 'Jl. Manggar 3Y', 'M', 3, '00006', 'anggian@gmail.com', '$2a$10$kYjESRuzo6pVs65gLC3s8.QYzIwgJwu4.N7NCWSBnpr2MC/OWndjy', 'A', '2021-07-23 00:59:54', 'Admin', '2021-07-23 00:59:54', 'Admin'),
(8, 'Albertus', '081316717280', 'Jl. Manggar 3Y', 'M', 1, '00006', 'albertus@gmail.com', '$2a$10$INcA2ibIUB4z5AB2RbPHv.zjXyPFOBx2GlGctmoxOGFvdhl8PXhX6', 'A', '2021-07-23 01:10:38', 'Admin', '2021-07-23 01:10:38', 'Admin'),
(15, 'Alfadli Rai', '0836777318871', 'Jl. Mindi', 'M', 1, '00011', 'alfadliraihans@gmail.com', '$2a$10$SlHIGQ7AMS8gpR4DXT946uf1sj5jkJNVOtsMPMXxyPRB7WtX12upa', 'A', '2021-07-23 20:34:20', 'superadmin', '2021-07-23 20:37:18', 'superadmin'),
(19, 'PT Teka Teki', '08213141415', 'xz', 'M', 1, '00008', 'alvinamartya1@gmail.com', '$2a$10$Uq76YjAQDfgM7kLbFuo2w.c0XjU9ZrHALTSqQ6qln32Z0qLexzt9W', 'A', '2021-07-25 04:07:34', 'superadmin', '2021-07-25 04:07:34', 'superadmin');

-- --------------------------------------------------------

--
-- Table structure for table `status_order`
--

CREATE TABLE `status_order` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `status_order`
--

INSERT INTO `status_order` (`id`, `name`) VALUES
(1, 'Sedang Dikirim'),
(2, 'Sedang Dicek'),
(3, 'Faktur Perlu Diperbarui'),
(4, 'Berhasil di Approved');

-- --------------------------------------------------------

--
-- Table structure for table `status_product`
--

CREATE TABLE `status_product` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `status_product`
--

INSERT INTO `status_product` (`id`, `name`) VALUES
(1, 'Ok'),
(2, 'Produk Mengalami Kerusakan'),
(3, 'Produk Sudah Expired');

-- --------------------------------------------------------

--
-- Table structure for table `stores`
--

CREATE TABLE `stores` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `province` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `head_of_store_name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `status` enum('A','D') NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stores`
--

INSERT INTO `stores` (`id`, `name`, `city`, `province`, `address`, `head_of_store_name`, `phone`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES
(1, 'Djajan Subam', 'Jakarta Utara', 'DKI Jakarta', 'Jln. Sungai Bambu', 'radit', '0822334124', 'A', '2021-06-23 18:52:09', 'Admin', '2021-06-23 18:52:09', 'Admin'),
(2, 'Djajan Kemayoran', 'Jakarta Pusat', 'DKI Jakarta', 'flamboyan', 'abit', '0822334343', 'A', '2021-06-22 23:56:34', 'Admin', '2021-06-22 23:56:34', 'Admin'),
(3, 'Djajan Kemang', 'Jakarta Selatan', 'DKI Jakarta', 'Jln. Kemang', 'Nabila', '082153123121', 'A', '2021-06-23 18:50:42', 'Admin', '2021-06-23 18:50:42', 'Admin'),
(4, 'Djajan Delta Silicon', 'Cikarang', 'Jawa Barat', 'jln. Delta silicon', 'Habib', '072131231', 'A', '2021-06-23 18:51:57', 'Admin', '2021-06-23 18:51:57', 'Admin'),
(5, 'Djajan Dagot', 'Jakarta Barat', 'DKI Jakarta', 'Jln. Daan mogot', 'Mayori', '021487236478', 'A', '2021-06-23 18:52:46', 'Admin', '2021-06-23 18:52:46', 'Admin'),
(6, 'Djadjan Koja', 'Jakarta Utara', 'DKI Jakarta', 'Jl. Manggar 3Y', 'Munts', '081316717280', 'A', '2021-07-23 18:33:29', 'Anggian', '2021-07-23 18:33:29', 'Anggian');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `status` enum('A','D') NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id`, `name`, `address`, `phone`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES
('00001', 'PT Indofood Sukses Makmur Tbk', 'Jakarta', '0219432942', 'A', '2021-06-23 17:20:06', 'Admin', '2021-06-23 17:20:06', 'Admin'),
('00002', 'Nestle', 'Jakarta', '02148342832', 'A', '2021-06-23 17:21:52', 'Admin', '2021-06-23 17:21:52', 'Admin'),
('00003', 'PT Unilever', 'Jakarta', '021428332', 'A', '2021-06-23 18:13:14', 'Admin', '2021-07-14 15:16:00', 'Admin'),
('00004', 'Salmon Fish', 'Jl. Manggar 3Y', '081316717280', 'A', '2021-07-23 18:34:57', 'Admin', '2021-07-23 18:34:57', 'Admin'),
('00005', 'PT Gudang Garam Tbk', 'Jakarta', '0215352232', 'A', '2021-06-23 18:15:59', 'Admin', '2021-06-23 18:15:59', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `supplier_details`
--

CREATE TABLE `supplier_details` (
  `id` int(11) NOT NULL,
  `supplier_id` varchar(5) NOT NULL,
  `product_category_id` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier_details`
--

INSERT INTO `supplier_details` (`id`, `supplier_id`, `product_category_id`) VALUES
(1, '00001', '00001'),
(2, '00001', '00002'),
(3, '00001', '00003'),
(4, '00001', '00004'),
(5, '00001', '00005'),
(6, '00001', '00001');

-- --------------------------------------------------------

--
-- Table structure for table `warehouses`
--

CREATE TABLE `warehouses` (
  `id` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `province` varchar(100) NOT NULL,
  `is_branch` int(11) NOT NULL,
  `status` enum('A','D') NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL,
  `driver_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `warehouses`
--

INSERT INTO `warehouses` (`id`, `name`, `city`, `province`, `is_branch`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`, `driver_id`) VALUES
('00001', 'Djajan Jakbar', 'Jakarta Barat', 'DKI Jakarta', 1, 'A', '2021-06-23 18:49:00', 'Admin', '2021-06-23 18:49:00', 'Admin', NULL),
('00002', 'Djajan Jakpus', 'Jakarta Pusat', 'DKI Jakarta', 1, 'A', '2021-06-23 18:48:51', 'Admin', '2021-06-23 18:48:51', 'Admin', NULL),
('00003', 'Djajan Jaksel', 'Jakarta Selatan', 'DKI Jakarta', 1, 'A', '2021-06-23 18:49:08', 'Admin', '2021-06-23 18:49:08', 'Admin', NULL),
('00004', 'Djajan Bekasi', 'Bekasi', 'Jawa Barat', 1, 'A', '2021-06-23 18:49:19', 'Admin', '2021-06-23 18:49:19', 'Admin', NULL),
('00005', 'Djajan Jakut', 'Jakarta Utara', 'DKI Jakarta', 1, 'A', '2021-06-23 18:49:43', 'Admin', '2021-06-23 18:49:43', 'Admin', NULL),
('00006', 'Djajan Pusat DKI Jakarta', 'Jakarta Pusat', 'DKI Jakarta', 0, 'A', '2021-07-16 10:57:07', 'Admin', '2021-07-16 10:57:07', 'Admin', NULL),
('00007', 'Djajan Braga', 'Bandung', 'Jawa Barat', 1, 'A', '2021-07-19 16:52:45', 'Admin', '2021-07-19 16:52:45', 'Admin', NULL),
('00008', 'Djadjan Pusat Jawa Barat', 'Bogor', 'Jawa Barat', 0, 'A', '2021-07-19 16:54:39', 'Admin', '2021-07-19 16:54:39', 'Admin', NULL),
('00009', 'Djajan Lembang', 'Bandung', 'Jawa Barat', 1, 'A', '2021-07-19 16:54:39', 'Admin', '2021-07-19 16:54:39', 'Admin', NULL),
('00010', 'Djajan Ciumbeleuit', 'Bandung', 'Jawa Barat', 1, 'A', '2021-07-19 16:55:39', 'Admin', '2021-07-19 16:55:39', 'Admin', NULL),
('00011', 'Djadjan Pusat Jawa Tengah', 'Pemalang', 'Jawa Tengah', 0, 'A', '2021-07-19 16:55:39', 'Admin', '2021-07-19 16:55:39', 'Admin', NULL),
('00012', 'Djadjan Wonosobo', 'Wonosobo', 'Jawa Tengah', 1, 'A', '2021-07-23 18:32:39', 'Anggian', '2021-07-23 18:32:39', 'Anggian', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `drivers`
--
ALTER TABLE `drivers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `warehouse_id` (`warehouse_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo8pv27uh0x0cxo1isjdcbkh3w` (`origin_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `dest_shelf_id` (`dest_shelf_id`),
  ADD KEY `origin_shelf_id` (`origin_shelf_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_category_id` (`product_category_id`);

--
-- Indexes for table `product_categories`
--
ALTER TABLE `product_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `retur`
--
ALTER TABLE `retur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_driver` (`driver_id`),
  ADD KEY `FK7qgw81mtt5vdb21u4m3ibw18` (`dest_id`);

--
-- Indexes for table `retur_details`
--
ALTER TABLE `retur_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_retur` (`retur_id`),
  ADD KEY `fk_product` (`product_id`),
  ADD KEY `fk_dest_shelf` (`dest_shelf_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shelf`
--
ALTER TABLE `shelf`
  ADD PRIMARY KEY (`id`),
  ADD KEY `warehouse_id` (`warehouse_id`),
  ADD KEY `product_category_id` (`product_category_id`);

--
-- Indexes for table `shelf_details`
--
ALTER TABLE `shelf_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shelf_id` (`shelf_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `staffs`
--
ALTER TABLE `staffs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `FKrse64bfqgj36m2ckvdilk7xx1` (`warehouse_id`);

--
-- Indexes for table `status_order`
--
ALTER TABLE `status_order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status_product`
--
ALTER TABLE `status_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stores`
--
ALTER TABLE `stores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier_details`
--
ALTER TABLE `supplier_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `supplier_id` (`supplier_id`),
  ADD KEY `product_category_id` (`product_category_id`);

--
-- Indexes for table `warehouses`
--
ALTER TABLE `warehouses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5fe7u8r9g03l7ss3vrauluojn` (`driver_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `drivers`
--
ALTER TABLE `drivers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `retur_details`
--
ALTER TABLE `retur_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `shelf_details`
--
ALTER TABLE `shelf_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=318;

--
-- AUTO_INCREMENT for table `staffs`
--
ALTER TABLE `staffs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `status_order`
--
ALTER TABLE `status_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `status_product`
--
ALTER TABLE `status_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `stores`
--
ALTER TABLE `stores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `supplier_details`
--
ALTER TABLE `supplier_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `drivers`
--
ALTER TABLE `drivers`
  ADD CONSTRAINT `drivers_ibfk_1` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKo8pv27uh0x0cxo1isjdcbkh3w` FOREIGN KEY (`origin_id`) REFERENCES `warehouses` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `order_details_ibfk_3` FOREIGN KEY (`dest_shelf_id`) REFERENCES `shelf` (`id`),
  ADD CONSTRAINT `order_details_ibfk_4` FOREIGN KEY (`origin_shelf_id`) REFERENCES `shelf` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `product_categories` (`id`);

--
-- Constraints for table `retur`
--
ALTER TABLE `retur`
  ADD CONSTRAINT `FK7qgw81mtt5vdb21u4m3ibw18` FOREIGN KEY (`dest_id`) REFERENCES `warehouses` (`id`),
  ADD CONSTRAINT `fk_driver` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`);

--
-- Constraints for table `retur_details`
--
ALTER TABLE `retur_details`
  ADD CONSTRAINT `fk_dest_shelf` FOREIGN KEY (`dest_shelf_id`) REFERENCES `shelf` (`id`),
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `fk_retur` FOREIGN KEY (`retur_id`) REFERENCES `retur` (`id`);

--
-- Constraints for table `shelf`
--
ALTER TABLE `shelf`
  ADD CONSTRAINT `shelf_ibfk_1` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`),
  ADD CONSTRAINT `shelf_ibfk_2` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`),
  ADD CONSTRAINT `shelf_ibfk_3` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`),
  ADD CONSTRAINT `shelf_ibfk_4` FOREIGN KEY (`product_category_id`) REFERENCES `product_categories` (`id`);

--
-- Constraints for table `shelf_details`
--
ALTER TABLE `shelf_details`
  ADD CONSTRAINT `shelf_details_ibfk_1` FOREIGN KEY (`shelf_id`) REFERENCES `shelf` (`id`),
  ADD CONSTRAINT `shelf_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `staffs`
--
ALTER TABLE `staffs`
  ADD CONSTRAINT `FKrse64bfqgj36m2ckvdilk7xx1` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`),
  ADD CONSTRAINT `staffs_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `supplier_details`
--
ALTER TABLE `supplier_details`
  ADD CONSTRAINT `supplier_details_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  ADD CONSTRAINT `supplier_details_ibfk_2` FOREIGN KEY (`product_category_id`) REFERENCES `product_categories` (`id`);

--
-- Constraints for table `warehouses`
--
ALTER TABLE `warehouses`
  ADD CONSTRAINT `FK5fe7u8r9g03l7ss3vrauluojn` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

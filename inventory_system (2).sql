-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2021 at 12:33 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

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
(1, 'Teddyanto Jamal', 'HNDR230621', 'WR001', '08235615362', 'A', '2021-06-23 16:43:43', 'Admin', '2021-06-23 16:43:43', 'Admin'),
(2, 'Rafli Mayori', 'HNDR230622', 'WR002', '083721511', 'A', '2021-06-23 16:46:06', 'Admin', '2021-06-23 16:46:06', 'Admin'),
(3, 'Albertus Aris', 'HNDR230623', 'WR002', '087125311', 'A', '2021-06-23 16:46:46', 'Admin', '2021-06-23 16:46:46', 'Admin'),
(4, 'Andani', 'HNDR230624', 'WR001', '08235453212', 'A', '2021-06-23 16:48:11', 'Admin', '2021-06-23 16:48:11', 'Admin'),
(5, 'Yusup Fikri', 'HNDR230625', 'WR002', '08254535362', 'A', '2021-06-23 16:48:49', 'Admin', '2021-06-23 16:49:24', 'Admin'),
(6, 'Chorida', 'HNDR230625', 'WR005', '081316717280', 'A', '2021-06-23 19:36:26', 'Admin', '2021-06-24 02:35:09', 'Admin'),
(7, 'Anggian', 'HNDR233333', 'WR005', '081267784444', 'D', '2021-06-23 19:47:28', 'Admin', '2021-06-24 02:33:03', 'Admin'),
(8, 'Rafli', 'B784278', 'WR001', '89389901313', 'A', '2021-06-24 02:59:21', 'Admin', '2021-06-24 02:59:21', 'Admin'),
(9, 'Tebe', 'B7398C', 'WR001', '081361728844', 'A', '2021-06-24 03:02:33', 'Admin', '2021-06-24 03:02:33', 'Admin'),
(10, 'Habibah', 'B8947JK', 'WR001', '081315782828', 'A', '2021-06-24 03:05:07', 'Admin', '2021-06-24 03:05:07', 'Admin'),
(11, 'Satria', 'B7398C', 'WR005', '081316717280', 'A', '2021-06-24 13:27:11', 'Admin', '2021-06-24 13:27:11', 'Admin'),
(12, 'Tes', 'B7398C', 'WR001', '081316717288', 'A', '2021-06-24 13:30:25', 'Admin', '2021-06-24 13:30:25', 'Admin'),
(13, 'Tes', 'B7398C', 'WR002', '081316717288', 'A', '2021-06-28 02:00:27', 'Admin', '2021-06-28 02:00:27', 'Admin'),
(14, 'Tes', 'B7398C', 'WR002', '081316717288', 'A', '2021-06-28 02:00:33', 'Admin', '2021-06-28 02:00:33', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` varchar(30) NOT NULL,
  `origin_id` varchar(6) NOT NULL,
  `dest_warehouse_id` varchar(5) NOT NULL,
  `origin_type` enum('Gudang','Pemasok') NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `driver_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `approved_at` datetime NOT NULL DEFAULT current_timestamp(),
  `approved_by` varchar(100) NOT NULL,
  `checked_at` datetime DEFAULT current_timestamp(),
  `checked_by` varchar(100) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL,
  `status_order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `order_id` varchar(30) NOT NULL,
  `product_id` int(5) NOT NULL,
  `dest_shelf_id` varchar(20) NOT NULL,
  `origin_shelf_id` varchar(20) DEFAULT NULL,
  `status_product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(1, 'Indomie Rendang', 'PC001', 'Dus', 'A', '2021-06-23 18:19:34', 'Admin', '2021-06-23 18:19:34', 'Admin'),
(2, 'Indomie Curry', 'PC001', 'Dus', 'A', '2021-06-23 18:20:01', 'Admin', '2021-06-23 18:20:01', 'Admin'),
(3, 'Pronas', 'PC002', 'Unit', 'A', '2021-06-23 18:20:52', 'Admin', '2021-06-23 18:20:52', 'Admin'),
(4, 'Cheetos', 'PC005', 'Dus', 'A', '2021-06-23 18:23:11', 'Admin', '2021-06-23 18:23:11', 'Admin'),
(5, 'Doritos', 'PC005', 'Dus', 'A', '2021-06-23 18:24:40', 'Admin', '2021-06-23 18:24:40', 'Admin');

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
('PC001', 'Instant Noodle', 1, 'A', '2021-06-23 16:55:39', 'Admin', '2021-06-23 16:55:39', 'Admin'),
('PC002', 'Canned Food', 1, 'A', '2021-06-23 16:57:20', 'Admin', '2021-06-23 16:57:20', 'Admin'),
('PC003', 'Cooking Ware', 0, 'A', '2021-06-23 16:59:49', 'Admin', '2021-06-23 16:59:49', 'Admin'),
('PC004', 'Clothes', 0, 'A', '2021-06-23 17:02:37', 'Admin', '2021-06-23 17:02:37', 'Admin'),
('PC005', 'Snacks', 1, 'A', '2021-06-23 17:03:12', 'Admin', '2021-06-23 17:03:12', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `retur`
--

CREATE TABLE `retur` (
  `id` varchar(39) NOT NULL,
  `origin_warehouse_id` varchar(5) NOT NULL,
  `dest_id` varchar(6) NOT NULL,
  `dest_type` enum('Gudang','Pemasok') NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `driver_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `approved_at` datetime DEFAULT current_timestamp(),
  `approved_by` varchar(100) NOT NULL,
  `checked_at` datetime NOT NULL DEFAULT current_timestamp(),
  `checked_by` int(11) NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `retur_detail`
--

CREATE TABLE `retur_detail` (
  `id` int(11) NOT NULL,
  `retur_id` varchar(30) NOT NULL,
  `product_id` int(11) NOT NULL,
  `origin_shelf_id` varchar(5) NOT NULL,
  `dest_shelf_id` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(3, 'Admin Master Data');

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
  `is_empty` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shelf`
--

INSERT INTO `shelf` (`id`, `warehouse_id`, `type_shelf`, `product_category_id`, `rows_shelf`, `columns_shelf`, `quantity_shelf`, `created_at`, `created_by`, `updated_at`, `updated_by`, `is_empty`) VALUES
('RO-00002-00002-00001', 'WR002', 'Rak Order', 'PC002', 1, 2, 2, '2021-06-28 17:24:09', 'Admin', '2021-06-28 17:24:09', 'Admin', 1),
('RR-00002-00004-00001', 'WR002', 'Rak Retur', 'PC004', 1, 2, 3, '2021-06-28 17:28:02', 'Admin', '2021-06-28 17:28:02', 'Admin', 1);

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
(113, 'RR-00002-00004-00001', NULL, NULL, 1, 2);

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

INSERT INTO `staffs` (`id`, `name`, `phone`, `address`, `gender`, `role_id`, `email`, `password`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES
(2, 'Salsabila Khroinsin', '083224324', 'jakarta pusat', 'F', 3, 'salsabila@gmail.com', 'polman123', 'A', '2021-06-22 21:05:28', 'Admin', '2021-06-22 21:11:24', 'Admin'),
(3, 'Chorida Ulya Nabila', '0822334343', 'pisangan timur', 'F', 2, 'cori@gmail.com', 'polman123', 'A', '2021-06-22 22:54:52', 'Admin', '2021-06-22 22:54:52', 'Admin'),
(4, 'Habibah Shiba', '0822334343', 'Jln. Sungai Bambu', 'M', 2, 'bibah@gmail.com', 'polman123', 'A', '2021-06-23 16:31:19', 'Admin', '2021-06-23 16:31:19', 'Admin'),
(5, 'Alfadli Raihan Tsani', '083264324324', 'Jln. Koja', 'M', 2, 'fadli@gmail.com', 'polman123', 'A', '2021-06-23 18:43:58', 'Admin', '2021-06-23 18:43:58', 'Admin'),
(6, 'Alvin Amartya Azro', '0852152313', 'Jln. Sungai Bambu', 'M', 1, 'alvin@gmail.com', 'polman123', 'A', '2021-06-23 18:44:54', 'Admin', '2021-06-23 18:44:54', 'Admin');

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
(2, 'Tiba Ditujuan'),
(3, 'Sedang Dicek'),
(4, 'Faktur Perlu Diperbarui'),
(5, 'Berhasil di Approved');

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
(5, 'Djajan Dagot', 'Jakarta Barat', 'DKI Jakarta', 'Jln. Daan mogot', 'Mayori', '021487236478', 'A', '2021-06-23 18:52:46', 'Admin', '2021-06-23 18:52:46', 'Admin');

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
('SL001', 'PT Indofood Sukses Makmur Tbk', 'Jakarta', '0219432942', 'A', '2021-06-23 17:20:06', 'Admin', '2021-06-23 17:20:06', 'Admin'),
('SL002', 'Nestle', 'Jakarta', '02148342832', 'A', '2021-06-23 17:21:52', 'Admin', '2021-06-23 17:21:52', 'Admin'),
('SL003', 'PT Unilever', 'Jakarta', '021428332', 'A', '2021-06-23 18:13:14', 'Admin', '2021-06-23 18:13:14', 'Admin'),
('SL004', 'PT Frisian Flag', 'Jakarta', '0213242832', 'A', '2021-06-23 18:15:24', 'Admin', '2021-06-23 18:15:24', 'Admin'),
('SL005', 'PT Gudang Garam Tbk', 'Jakarta', '0215352232', 'A', '2021-06-23 18:15:59', 'Admin', '2021-06-23 18:15:59', 'Admin');

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
(1, 'SL001', 'PC001'),
(2, 'SL001', 'PC002'),
(3, 'SL001', 'PC003'),
(4, 'SL001', 'PC004'),
(5, 'SL001', 'PC005');

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
('WR001', 'Djajan Jakbar', 'Jakarta Barat', 'DKI Jakarta', 3, 'A', '2021-06-23 18:49:00', 'Admin', '2021-06-23 18:49:00', 'Admin', NULL),
('WR002', 'Djajan Jakpus', 'Jakarta Pusat', 'DKI Jakarta', 2, 'A', '2021-06-23 18:48:51', 'Admin', '2021-06-23 18:48:51', 'Admin', NULL),
('WR003', 'Djajan Jaksel', 'Jakarta Selatan', 'DKI Jakarta', 4, 'A', '2021-06-23 18:49:08', 'Admin', '2021-06-23 18:49:08', 'Admin', NULL),
('WR004', 'Djajan Bekasi', 'Bekasi', 'Jakarta Barat', 4, 'A', '2021-06-23 18:49:19', 'Admin', '2021-06-23 18:49:19', 'Admin', NULL),
('WR005', 'Djajan Jakut', 'Jakarta Utara', 'Jakarta Utara', 2, 'A', '2021-06-23 18:49:43', 'Admin', '2021-06-23 18:49:43', 'Admin', NULL);

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
  ADD KEY `dest_warehouse_id` (`dest_warehouse_id`),
  ADD KEY `driver_id` (`driver_id`),
  ADD KEY `status_order_id` (`status_order_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `dest_shelf_id` (`dest_shelf_id`),
  ADD KEY `origin_shelf_id` (`origin_shelf_id`),
  ADD KEY `status_product_id` (`status_product_id`);

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
  ADD KEY `origin_warehouse_id` (`origin_warehouse_id`),
  ADD KEY `driver_id` (`driver_id`);

--
-- Indexes for table `retur_detail`
--
ALTER TABLE `retur_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `retur_id` (`retur_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `origin_shelf_id` (`origin_shelf_id`),
  ADD KEY `dest_shelf_id` (`dest_shelf_id`);

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
  ADD KEY `role_id` (`role_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `retur_detail`
--
ALTER TABLE `retur_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `shelf_details`
--
ALTER TABLE `shelf_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- AUTO_INCREMENT for table `staffs`
--
ALTER TABLE `staffs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `supplier_details`
--
ALTER TABLE `supplier_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`dest_warehouse_id`) REFERENCES `warehouses` (`id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`),
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`status_order_id`) REFERENCES `status_order` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `order_details_ibfk_3` FOREIGN KEY (`dest_shelf_id`) REFERENCES `shelf` (`id`),
  ADD CONSTRAINT `order_details_ibfk_4` FOREIGN KEY (`origin_shelf_id`) REFERENCES `shelf` (`id`),
  ADD CONSTRAINT `order_details_ibfk_5` FOREIGN KEY (`status_product_id`) REFERENCES `status_product` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `product_categories` (`id`);

--
-- Constraints for table `retur`
--
ALTER TABLE `retur`
  ADD CONSTRAINT `retur_ibfk_1` FOREIGN KEY (`origin_warehouse_id`) REFERENCES `warehouses` (`id`),
  ADD CONSTRAINT `retur_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`);

--
-- Constraints for table `retur_detail`
--
ALTER TABLE `retur_detail`
  ADD CONSTRAINT `retur_detail_ibfk_1` FOREIGN KEY (`retur_id`) REFERENCES `retur` (`id`),
  ADD CONSTRAINT `retur_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `retur_detail_ibfk_3` FOREIGN KEY (`origin_shelf_id`) REFERENCES `shelf` (`id`),
  ADD CONSTRAINT `retur_detail_ibfk_4` FOREIGN KEY (`dest_shelf_id`) REFERENCES `shelf` (`id`);

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

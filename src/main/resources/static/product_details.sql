-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:8889
-- 產生時間： 2024 年 09 月 19 日 08:40
-- 伺服器版本： 8.0.35
-- PHP 版本： 8.2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `bunny_sugar`
--

-- --------------------------------------------------------

--
-- 資料表結構 `product_details`
--

CREATE TABLE `product_details` (
  `id` bigint NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `enable` bit(1) NOT NULL,
  `image_url` varchar(1000) NOT NULL,
  `material_description` varchar(55) NOT NULL,
  `price` int NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `products_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `product_details`
--

INSERT INTO `product_details` (`id`, `create_time`, `description`, `enable`, `image_url`, `material_description`, `price`, `update_time`, `products_id`) VALUES
(1, '2024-09-19 16:09:37.570932', 'description', b'1', '/public/imgZip/Sample/cake1.jpg', '125611', 125611, '2024-09-19 16:09:37.570944', 1),
(2, '2024-09-19 16:15:19.277793', 'description', b'1', '/public/imgZip/Sample/cake2.jpg', '125611', 125611, '2024-09-19 16:15:19.277810', 2),
(3, '2024-09-19 16:15:19.583525', 'description', b'1', '/public/imgZip/Sample/cakes2.jpg', '125611', 125611, '2024-09-19 16:15:19.583534', 3),
(4, '2024-09-19 16:15:20.122341', 'description', b'1', '/public/imgZip/Sample/cakes1.jpg', '125611', 125611, '2024-09-19 16:15:20.122355', 4),
(5, '2024-09-19 16:15:20.546638', 'description', b'1', '/public/imgZip/Sample/cake1.jpg', '125611', 125611, '2024-09-19 16:15:20.546667', 5),
(6, '2024-09-19 16:15:20.955423', 'description', b'1', '/public/imgZip/Sample/cake1.jpg', '125611', 125611, '2024-09-19 16:15:20.955455', 6),
(7, '2024-09-19 16:15:21.383015', 'description', b'1', '/public/imgZip/Sample/cake1.jpg', '125611', 125611, '2024-09-19 16:15:21.383049', 7),
(8, '2024-09-19 16:15:21.790874', 'description', b'1', '/public/imgZip/Sample/cake1.jpg', '125611', 125611, '2024-09-19 16:15:21.790903', 8),
(9, '2024-09-19 16:15:27.774413', 'description', b'1', '/public/imgZip/Sample/cake1.jpg', '125611', 125611, '2024-09-19 16:15:27.774444', 9);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `product_details`
--
ALTER TABLE `product_details`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK55mvjq4ef5j13g0yotdf5itua` (`products_id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `product_details`
--
ALTER TABLE `product_details`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `product_details`
--
ALTER TABLE `product_details`
  ADD CONSTRAINT `FKgp8c9v5rswlrd4v07isaklfnu` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

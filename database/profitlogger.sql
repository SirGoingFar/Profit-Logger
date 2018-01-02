-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2018 at 07:04 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `profitlogger`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendant`
--

CREATE TABLE `attendant` (
  `id` int(5) NOT NULL,
  `surname` text NOT NULL,
  `other_name` text NOT NULL,
  `phone_number` int(11) NOT NULL,
  `sex` int(1) NOT NULL,
  `address` varchar(50) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` int(15) NOT NULL,
  `date_time` datetime(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `biscuit`
--

CREATE TABLE `biscuit` (
  `id` int(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  `size` varchar(15) NOT NULL,
  `currentSellingPrice` double NOT NULL,
  `qtyAvailable` int(4) NOT NULL,
  `lastUpdated` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `biscuit`
--

INSERT INTO `biscuit` (`id`, `name`, `size`, `currentSellingPrice`, `qtyAvailable`, `lastUpdated`) VALUES
(70, '2Wa', 'Small size', 23.56, 23, '2017-12-11 05:37:02'),
(68, 'Spaggo', 'No variation', 86.8, 20, '2017-12-01 07:20:58'),
(67, 'Axsee', 'Big size', 12.2, 165, '2017-12-10 09:49:59'),
(65, 'Kayus', 'Small size', 23.6, 6, '2017-12-01 07:19:37'),
(63, 'Which', 'Medium size', 2.7, 127, '2017-12-01 07:20:18'),
(61, 'Waled', 'No variation', 123.5, 48, '2017-12-11 05:37:40'),
(60, 'Whales', 'No variation', 12.6, 6, '2017-12-01 06:21:25'),
(59, 'Speedico', 'Small size', 128, 134, '2017-11-23 00:30:50'),
(58, 'Speedico', 'No variation', 1245, 32, '2017-11-23 00:30:03'),
(71, 'Digestive fibre', 'No variation', 400, 23, '2018-01-01 01:26:59'),
(72, 'Coconut', 'Small size', 400, 24, '2018-01-01 01:28:22');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(3) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`) VALUES
(2, 'SirGoingFar', 'Emmanuella2014'),
(5, 'Princess', 'Monininuola'),
(4, 'Adewale', 'Niyi'),
(6, 'Kehinde', 'Wonder'),
(7, 'Goks', 'Wale'),
(8, 'Admin', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `provision_cartons`
--

CREATE TABLE `provision_cartons` (
  `id` int(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  `size` varchar(15) NOT NULL,
  `currentSellingPrice` double NOT NULL,
  `qtyAvailable` int(4) NOT NULL,
  `lastUpdated` datetime(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `provision_cartons`
--

INSERT INTO `provision_cartons` (`id`, `name`, `size`, `currentSellingPrice`, `qtyAvailable`, `lastUpdated`) VALUES
(3, 'Klin', 'No variation', 23.45, 10, '2017-11-29 12:32:12.490000');

-- --------------------------------------------------------

--
-- Table structure for table `provision_pieces`
--

CREATE TABLE `provision_pieces` (
  `id` int(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  `size` varchar(15) NOT NULL,
  `currentSellingPrice` double NOT NULL,
  `qtyAvailable` int(4) NOT NULL,
  `lastUpdated` datetime(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `soap_cartons`
--

CREATE TABLE `soap_cartons` (
  `id` int(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  `size` varchar(15) NOT NULL,
  `currentSellingPrice` double NOT NULL,
  `qtyAvailable` int(4) NOT NULL,
  `lastUpdated` datetime(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `soap_pieces`
--

CREATE TABLE `soap_pieces` (
  `id` int(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  `size` varchar(15) NOT NULL,
  `currentSellingPrice` double NOT NULL,
  `qtyAvailable` int(4) NOT NULL,
  `lastUpdated` datetime(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sweet_cartons`
--

CREATE TABLE `sweet_cartons` (
  `id` int(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  `size` varchar(15) NOT NULL,
  `currentSellingPrice` double NOT NULL,
  `qtyAvailable` int(4) NOT NULL,
  `lastUpdated` datetime(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sweet_pieces`
--

CREATE TABLE `sweet_pieces` (
  `id` int(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  `size` varchar(15) NOT NULL,
  `currentSellingPrice` double NOT NULL,
  `qtyAvailable` int(4) NOT NULL,
  `lastUpdated` datetime(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sweet_pieces`
--

INSERT INTO `sweet_pieces` (`id`, `name`, `size`, `currentSellingPrice`, `qtyAvailable`, `lastUpdated`) VALUES
(1, 'Corolla', 'Small size', 12.5, 678, '2017-12-10 09:51:44.000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendant`
--
ALTER TABLE `attendant`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `biscuit`
--
ALTER TABLE `biscuit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `provision_cartons`
--
ALTER TABLE `provision_cartons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `provision_pieces`
--
ALTER TABLE `provision_pieces`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `soap_cartons`
--
ALTER TABLE `soap_cartons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `soap_pieces`
--
ALTER TABLE `soap_pieces`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sweet_cartons`
--
ALTER TABLE `sweet_cartons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sweet_pieces`
--
ALTER TABLE `sweet_pieces`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendant`
--
ALTER TABLE `attendant`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `biscuit`
--
ALTER TABLE `biscuit`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;
--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `provision_cartons`
--
ALTER TABLE `provision_cartons`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `provision_pieces`
--
ALTER TABLE `provision_pieces`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `soap_cartons`
--
ALTER TABLE `soap_cartons`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `soap_pieces`
--
ALTER TABLE `soap_pieces`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `sweet_cartons`
--
ALTER TABLE `sweet_cartons`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `sweet_pieces`
--
ALTER TABLE `sweet_pieces`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

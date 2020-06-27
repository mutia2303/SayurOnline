-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2020 at 01:13 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sayur_online`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `password`, `nama`, `no_hp`, `email`, `alamat`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin23', '081363848138', 'mutia23.mr@gmail.com', 'Jl. Bulaan Kamba23');

-- --------------------------------------------------------

--
-- Table structure for table `pasar`
--

CREATE TABLE `pasar` (
  `id_pasar` int(11) NOT NULL,
  `nama_pasar` varchar(50) NOT NULL,
  `gambar` varchar(100) NOT NULL DEFAULT 'default.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pasar`
--

INSERT INTO `pasar` (`id_pasar`, `nama_pasar`, `gambar`) VALUES
(1, 'Pasar Padang Luar', 'pasar_padang_luar.jpg'),
(2, 'Pasar Koto Baru ', 'pasar_koto_baru.jpg'),
(3, 'Pasar Aur Kuning', 'pasar_aur_kuning.jpg'),
(4, 'Pasar Baso', 'pasar_baso.jpg'),
(5, 'Pasar Lasi', 'pasar_lasi.JPG');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `username`, `password`, `nama`, `no_hp`, `email`, `alamat`, `status`) VALUES
(35, 'mutia23', '508df4cb2f4d8f80519256258cfb975f', 'mutia', '085374681923', 'mutia23.mr@gmail.com', 'bulaan kamba', 'penjual'),
(36, 'aryputra', 'ee351be81bdc737a5e0475538200e6ea', 'ary putra', '081297871837', 'aryputra2504@gmail.com', 'padang luar', 'pembeli'),
(37, 'denigunjo', '50f5819290ca498688de517adf17f81d', 'deni', '081338736597', 'denigunjo504@gmail.com', 'pakan sinayan', 'penjual'),
(38, 'fitratul', 'ff9a82ca20494c281a1c373e0c3cc347', 'fitratul aini', '0895618340381', 'fitratulaini22@gmail.com', 'bulaan kamba', 'penjual'),
(39, 'reninov', '25d55ad283aa400af464c76d713c07ad', 'reni novarita', '081363137766', 'reninovarita@gmail.com', 'ladang laweh', 'pembeli'),
(40, 'aditya29', '082339a4dff0b5f5bbf3aa055fcbcc39', 'aditya fm', '082286890802', 'aditya29@gmail.com', 'bukittinggi', 'pembeli'),
(41, 'nabilaa', '6da49306ebcb7d9259a13e22966656f1', 'nabila', '081363848138', 'nabilaa@gmail.com', 'baso', 'penjual'),
(44, 'novrianda', '82e0433566ee7d87028a0df1789d3b65', 'novrianda', '081275617287', 'novrianda@gmail.com', 'lasi', 'penjual'),
(45, 'yulihasni', '40d2b41b4f9fe12c3e96a14366765df4', 'yuli hasni', '085263741523', 'yulihasni@gmail.com', 'padang luar', 'pembeli');

-- --------------------------------------------------------

--
-- Table structure for table `sayur`
--

CREATE TABLE `sayur` (
  `id_sayur` int(11) NOT NULL,
  `id_pengguna` int(11) NOT NULL,
  `id_pasar` int(11) NOT NULL,
  `nama_sayur` varchar(50) NOT NULL,
  `stok` int(11) NOT NULL,
  `keterangan` varchar(40) NOT NULL,
  `harga` varchar(10) NOT NULL,
  `gambar` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sayur`
--

INSERT INTO `sayur` (`id_sayur`, `id_pengguna`, `id_pasar`, `nama_sayur`, `stok`, `keterangan`, `harga`, `gambar`) VALUES
(32, 35, 1, 'tomat', 39, 'kg', '10000', 'img7994.jpg'),
(33, 37, 2, 'wortel', 50, 'kg', '8500', 'img7633.jpg'),
(34, 38, 3, 'lado hijau', 50, 'kg', '21000', 'img9921.jpg'),
(35, 41, 4, 'kentang', 50, 'kg', '12500', 'kentang.jpg'),
(36, 35, 2, 'jagung', 1, 'karung', '50000', '22-16-50-ladang-jagung1.jpg'),
(37, 38, 1, 'selada', 2, 'kg', '6000', '13-57-30-images.jpg'),
(40, 44, 5, 'sayur bayam', 15, 'ikat', '2000', '19-53-55-images.jpg'),
(41, 38, 3, 'cabe rawit', 50, 'kg', '20000', 'img6391.jpg'),
(42, 35, 3, 'cabe rawit', 10, 'kg', '19500', 'img8351.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_pengguna` int(11) NOT NULL,
  `id_sayur` int(11) NOT NULL,
  `id_pasar` int(11) NOT NULL,
  `username_penjual` varchar(50) NOT NULL,
  `nama_penjual` varchar(50) NOT NULL,
  `jumlah_beli` varchar(8) NOT NULL,
  `total` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_pengguna`, `id_sayur`, `id_pasar`, `username_penjual`, `nama_penjual`, `jumlah_beli`, `total`) VALUES
(19, 36, 32, 1, 'mutia23', 'mutia', '5', '50000'),
(20, 36, 34, 3, 'fitratul', 'fitratul aini', '10', '210000'),
(21, 39, 33, 2, 'denigunjo', 'deni', '25', '200000'),
(22, 36, 32, 1, 'mutia23', 'mutia', '6', '60000'),
(23, 36, 36, 2, 'mutia23', 'mutia', '6', '300000'),
(24, 45, 37, 1, 'fitratul', 'fitratul aini', '4', '24000'),
(25, 45, 37, 1, 'fitratul', 'fitratul aini', '3', '18000'),
(26, 45, 34, 3, 'fitratul', 'fitratul aini', '2', '42000'),
(27, 45, 37, 1, 'fitratul', 'fitratul aini', '2', '12000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `pasar`
--
ALTER TABLE `pasar`
  ADD PRIMARY KEY (`id_pasar`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `sayur`
--
ALTER TABLE `sayur`
  ADD PRIMARY KEY (`id_sayur`),
  ADD KEY `id_pengguna` (`id_pengguna`),
  ADD KEY `id_pasar` (`id_pasar`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_sayur` (`id_sayur`),
  ADD KEY `id_pasar` (`id_pasar`),
  ADD KEY `id_pengguna` (`id_pengguna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pasar`
--
ALTER TABLE `pasar`
  MODIFY `id_pasar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `sayur`
--
ALTER TABLE `sayur`
  MODIFY `id_sayur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sayur`
--
ALTER TABLE `sayur`
  ADD CONSTRAINT `sayur_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`),
  ADD CONSTRAINT `sayur_ibfk_2` FOREIGN KEY (`id_pasar`) REFERENCES `pasar` (`id_pasar`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_4` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`),
  ADD CONSTRAINT `transaksi_ibfk_6` FOREIGN KEY (`id_pasar`) REFERENCES `pasar` (`id_pasar`),
  ADD CONSTRAINT `transaksi_ibfk_7` FOREIGN KEY (`id_sayur`) REFERENCES `sayur` (`id_sayur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

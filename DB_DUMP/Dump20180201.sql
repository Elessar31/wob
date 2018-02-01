CREATE DATABASE  IF NOT EXISTS `wob` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wob`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: wob
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `InventoryItemId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'the id\n',
  `SKU` varchar(45) DEFAULT NULL,
  `ItemReceivedDate` date DEFAULT NULL,
  `WarehouseLocation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`InventoryItemId`),
  KEY `sku_idx` (`SKU`),
  CONSTRAINT `fk_sku` FOREIGN KEY (`SKU`) REFERENCES `sku_data` (`sku`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,'apple','2018-01-31','Pécs'),(2,'apple','2018-01-31','Pécs'),(3,'apple','2018-01-27','Győr'),(4,'pear','2018-01-10','Pogány'),(5,'passionfruit','2017-01-26','Pécs');
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sku_data`
--

DROP TABLE IF EXISTS `sku_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sku_data` (
  `sku` varchar(45) NOT NULL COMMENT 'Stock Keeping Unit',
  `Barcode` varchar(45) DEFAULT NULL,
  `RetailPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`sku`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sku_data`
--

LOCK TABLES `sku_data` WRITE;
/*!40000 ALTER TABLE `sku_data` DISABLE KEYS */;
INSERT INTO `sku_data` VALUES ('apple','11102000',100.00),('passionfruit','4000010',150.00),('pear','2000000',200.00);
/*!40000 ALTER TABLE `sku_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tresholds`
--

DROP TABLE IF EXISTS `tresholds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tresholds` (
  `treshold_min` int(11) NOT NULL,
  `treshold_max` int(11) NOT NULL,
  `discount` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`treshold_min`,`treshold_max`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='discrount tresholds\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tresholds`
--

LOCK TABLES `tresholds` WRITE;
/*!40000 ALTER TABLE `tresholds` DISABLE KEYS */;
INSERT INTO `tresholds` VALUES (0,7,65.00),(8,13,50.00),(14,28,33.00),(29,50,25.00),(51,9999,19.50);
/*!40000 ALTER TABLE `tresholds` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-01 11:48:09

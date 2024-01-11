-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `roomNumber` int(11) NOT NULL,
  `customerIDNumber` int(11) DEFAULT NULL,
  `billID` int(11) DEFAULT NULL,
  `billDate` date DEFAULT NULL,
  `customerName` varchar(255) DEFAULT NULL,
  `billAmount` double DEFAULT NULL,
  PRIMARY KEY (`roomNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (201,101,1,'2024-01-09','John Doe',500),(202,102,2,'2024-01-10','Jane Smith',750.5),(203,103,3,'2024-01-11','Alice Johnson',300),(204,104,4,'2024-01-12','Bob Brown',400),(205,105,5,'2024-01-13','Charlie Davis',600),(206,106,6,'2024-01-14','David Wilson',350),(207,107,7,'2024-01-15','Eva White',800),(208,108,8,'2024-01-16','Frank Miller',450),(209,109,9,'2024-01-17','Grace Robinson',700),(210,110,10,'2024-01-18','Henry Johnson',550),(211,111,11,'2024-01-19','Ivy Davis',900),(212,112,12,'2024-01-20','Jack Wilson',250),(213,113,13,'2024-01-21','Karen White',700),(214,114,14,'2024-01-22','Leo Robinson',450),(215,115,15,'2024-01-23','Mia Brown',600),(216,116,16,'2024-01-24','Nathan Miller',350),(217,117,17,'2024-01-25','Olivia Davis',800),(218,118,18,'2024-01-26','Paul Johnson',500),(219,119,19,'2024-01-27','Quinn Robinson',750.5),(220,120,20,'2024-01-28','Rachel Wilson',300),(221,121,21,'2024-01-29','Samuel White',400),(222,122,22,'2024-01-30','Tina Miller',600),(223,123,23,'2024-01-31','Ulysses Robinson',350),(224,124,24,'2024-02-01','Victoria Davis',800),(225,125,25,'2024-02-02','Walter Johnson',450),(226,126,26,'2024-02-03','Xena Robinson',700),(227,127,27,'2024-02-04','Yasmine Brown',550),(228,128,28,'2024-02-05','Zane Miller',900),(229,129,29,'2024-02-06','Amy White',250),(230,130,30,'2024-02-07','Benjamin Robinson',700),(231,131,31,'2024-02-08','Catherine Davis',450),(232,132,32,'2024-02-09','Daniel Wilson',600),(233,133,33,'2024-02-10','Ella Brown',350),(234,134,34,'2024-02-11','Franklin Johnson',800),(235,135,35,'2024-02-12','Grace Robinson',500),(236,136,36,'2024-02-13','Henry Miller',750.5),(237,137,37,'2024-02-14','Ivy Davis',300),(238,138,38,'2024-02-15','Jack Robinson',400),(239,139,39,'2024-02-16','Karen Wilson',600),(240,140,40,'2024-02-17','Leo Miller',850);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-11 21:26:11

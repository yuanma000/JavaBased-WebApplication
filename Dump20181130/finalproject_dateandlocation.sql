-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: finalproject
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dateandlocation`
--

DROP TABLE IF EXISTS `dateandlocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dateandlocation` (
  `id` varchar(50) NOT NULL,
  `startdate` int(11) NOT NULL,
  `enddate` int(11) NOT NULL,
  `location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`,`startdate`,`enddate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dateandlocation`
--

LOCK TABLES `dateandlocation` WRITE;
/*!40000 ALTER TABLE `dateandlocation` DISABLE KEYS */;
INSERT INTO `dateandlocation` VALUES ('1',20150101,20250101,'Chicago - ORD Airport'),('2',20150101,20190416,'Chicago - IIT Campus'),('2',20190425,20190513,'Madison - Kohl Center'),('2',20190524,20250101,'Chicago - IIT Campus'),('3',20150101,20250101,'Chicago - IIT Campus'),('4',20150101,20250101,'Chicago - IIT Campus'),('5',20150101,20181127,'Chicago - IIT Campus'),('5',20181209,20250101,'Chicago - IIT Campus'),('6',20150101,20180416,'Chicago - IIT Campus'),('6',20180509,20180523,'Champaign - UIUC Campus'),('6',20180529,20181203,'Chicago - IIT Campus'),('6',20181212,20250101,'Chicago - IIT Campus'),('7',20150101,20250101,'Chicago - IIT Campus'),('8',20150101,20250101,'Chicago - IIT Campus'),('9',20150101,20181219,'Chicago - IIT Campus'),('9',20181228,20250101,'Madison - Kohl Center');
/*!40000 ALTER TABLE `dateandlocation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 21:43:39

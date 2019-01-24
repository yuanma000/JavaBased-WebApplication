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
-- Table structure for table `orderlists`
--

DROP TABLE IF EXISTS `orderlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orderlists` (
  `orderid` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `creditcardno` varchar(45) DEFAULT NULL,
  `billingaddress` varchar(45) DEFAULT NULL,
  `ordercarid` varchar(45) DEFAULT NULL,
  `startdate` varchar(45) DEFAULT NULL,
  `enddate` varchar(45) DEFAULT NULL,
  `startlocation` varchar(45) DEFAULT NULL,
  `endlocation` varchar(45) DEFAULT NULL,
  `totalcost` double DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderlists`
--

LOCK TABLES `orderlists` WRITE;
/*!40000 ALTER TABLE `orderlists` DISABLE KEYS */;
INSERT INTO `orderlists` VALUES (1,'sgez','Guenzhe Sun','1234','abc','6','04/17/2018','05/08/2018','Chicago - IIT Campus','Champaign - UIUC Campus',2099.79),(2,'sgez','Guenzhe Sun','1234','abc','6','05/24/2018','05/28/2018','Champaign - UIUC Campus','Chicago - IIT Campus',399.96),(3,'sgez','Guenzhe Sun','1234','abc','5','11/28/2018','12/08/2018','Chicago - IIT Campus','Chicago - IIT Campus',399.90000000000003),(4,'sgez','Guenzhe Sun','1234','abc','6','12/04/2018','12/11/2018','Chicago - IIT Campus','Chicago - IIT Campus',699.93),(5,'sgez','Guenzhe Sun','1234','abc','2','04/17/2019','04/24/2019','Chicago - IIT Campus','Madison - Kohl Center',279.93),(6,'sgez','Guenzhe Sun','3445','asd','9','12/20/2018','12/27/2018','Chicago - IIT Campus','Madison - Kohl Center',419.93),(7,'pete','Guenzhe Sun','123','df','2','05/14/2019','05/23/2019','Madison - Kohl Center','Chicago - IIT Campus',359.91);
/*!40000 ALTER TABLE `orderlists` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 21:43:37

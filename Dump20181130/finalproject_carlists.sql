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
-- Table structure for table `carlists`
--

DROP TABLE IF EXISTS `carlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `carlists` (
  `id` varchar(50) NOT NULL,
  `carmodel` varchar(50) DEFAULT NULL,
  `carbrand` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `carcondition` varchar(20) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `owner` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `cartype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carlists`
--

LOCK TABLES `carlists` WRITE;
/*!40000 ALTER TABLE `carlists` DISABLE KEYS */;
INSERT INTO `carlists` VALUES ('1','Ranger Rover Evoque','Land Rover',44.99,'https://c6a19be53cded5d8c2e4-d1f54a006afb1cb9d28053cd4d49c6c1.ssl.cf1.rackcdn.com/SALVP2RX5JH306281/0e6ceb853934d95c947c79fbd1ce3730.jpg','2018',20,'Miracle','Chicago - ORD Airport','Midsize SUV'),('2','QX60','Infiniti',39.99,'https://st.motortrend.com/uploads/sites/10/2016/10/2017-Infiniti-QX60-front-three-quarter-in-motion.jpg','2017',10,'S4','Chicago - IIT Campus','Midsize SUV'),('3','CX9','Mazda',19.99,'https://st.motortrend.com/uploads/sites/10/2017/11/2018-Mazda-CX-9-front-side-view.jpg','2018',20,'Fear','Chicago - IIT Campus','Standard SUV'),('4','RX350','Lexus',59.99,'https://hips.hearstapps.com/amv-prod-cad-assets.s3.amazonaws.com/images/16q4/671590/2017-lexus-rx350-review-car-and-driver-photo-672192-s-original.jpg','2017',10,'Yan','Chicago - IIT Campus','Standard SUV'),('5','Fusion','Ford',39.99,'https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/2013_Ford_Fusion_Titanium_--_2012_NYIAS.JPG/1200px-2013_Ford_Fusion_Titanium_--_2012_NYIAS.JPG','2016',5,'Yan','Chicago - IIT Campus','Standard'),('6','California','Ferrari',99.99,'https://cdn.jamesedition.com/media/W1siZiIsImRhdGEvaW1hZ2VzLzI3OTcxMTMxX3NvdXJjZS5qcGciXV0/novitec-rosso-ferrari-f12-berlinetta-n-largo-s.jpg?sha=24f61e0e13d19715','2013',10,'820','Chicago - IIT Campus','Premium'),('7','Q8','Audi',64.99,'https://cdn2.autoexpress.co.uk/sites/autoexpressuk/files/2018/08/1audiq8.jpg','2018',10,'YYF','Chicago - IIT Campus','Premium'),('8','RAV4','Toyota',29.99,'https://cars.usnews.com/static/images/Auto/izmo/i44062349/2018_toyota_rav4_angularfront.jpg','2018',5,'hcy','Chicago - IIT Campus','Standard SUV'),('9','Santa','Hyundai',14.99,'https://www.hyundaiusa.com/public_images/2019/santa-fe/2019-santa-fe-gallery-1920x1200-ext-10.jpg','2019',5,'zjl','Chicago - IIT Campus','Standard SUV');
/*!40000 ALTER TABLE `carlists` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 21:43:38

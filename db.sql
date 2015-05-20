/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.12-log : Database - tms_viewer
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tms_viewer` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tms_viewer`;

/*Table structure for table `cardata` */

DROP TABLE IF EXISTS `cardata`;

CREATE TABLE `cardata` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier',
  `car_plate` varchar(8) DEFAULT NULL COMMENT 'Car Number Plate',
  `car_type` varchar(16) DEFAULT NULL COMMENT 'Car Type',
  `time_passed` datetime DEFAULT NULL COMMENT 'Car Passing Time',
  `speed` int(11) DEFAULT NULL COMMENT 'Car Speed',
  `station` int(11) DEFAULT NULL COMMENT 'Station Passed',
  PRIMARY KEY (`id`),
  KEY `station` (`station`),
  CONSTRAINT `cardata_ibfk_1` FOREIGN KEY (`station`) REFERENCES `station` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=latin1;

/*Table structure for table `station` */

DROP TABLE IF EXISTS `station`;

CREATE TABLE `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier',
  `name` varchar(32) DEFAULT NULL COMMENT 'Station Name',
  `administrator` varchar(32) DEFAULT NULL COMMENT 'Station Adminstrator',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier',
  `username` varchar(64) DEFAULT NULL COMMENT 'Username',
  `Password` varchar(64) DEFAULT NULL COMMENT 'Password',
  `role` varchar(32) DEFAULT NULL COMMENT 'User Role',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

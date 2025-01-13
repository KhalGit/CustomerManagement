CREATE DATABASE  IF NOT EXISTS `db_customers` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db_customers`;


DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `AFM` bigint NOT NULL,
  `phone` bigint NOT NULL,
  `email` varchar(45) NOT NULL,
  `dateCreated` date NOT NULL, 
  PRIMARY KEY (`AFM`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` bigint NOT NULL,
  `contactName` varchar(45),
  `customerAFM` bigint NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` bigint NOT NULL,
  `relation` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`customerAFM`) REFERENCES customer (`AFM`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint NOT NULL,
  `customerAFM` bigint NOT NULL,
  `orderDate` date NOT NULL,
  `contactId` bigint NOT NULL,
  `amount` bigint NOT NULL,
  `status` enum('PENDING', 'COMPLETED'),
  `notes` varchar(45),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`customerAFM`) REFERENCES customer (`AFM`),
  FOREIGN KEY (`contactId`) REFERENCES contact (`id`)
) ENGINE=InnoDB;
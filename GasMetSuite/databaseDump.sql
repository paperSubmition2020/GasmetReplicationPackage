-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: gasmet
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `costsmell`
--

DROP TABLE IF EXISTS `costsmell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `costsmell` (
  `id` varchar(255) NOT NULL,
  `CostSmell` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costsmell`
--

LOCK TABLES `costsmell` WRITE;
/*!40000 ALTER TABLE `costsmell` DISABLE KEYS */;
INSERT INTO `costsmell` VALUES ('CS5','Inefficient initialization of variables','A uninitialized variable is set with the default value. Initializing a variable using the default values wastes gas.'),('CS2','Duplicate writes','Assigning a value to a variable several times could require much gas. In place of multiple writes, developer could write outside cycles as much as possible.'),('CS1','Contract as data storage','Writing empty contracts within a contract increases the gas consumption; when multiple contract instances are created\nthe gas cost may be relevant. Using structs instead of contracts as data storage could be more appropriate.'),('CS6','Inefficient use of libraries','When a public function of a library is called, the bytecode of that function is not added to a client contract. Thus,\ncomplex logic should be put in libraries for keeping the contract size small.'),('CS9','Inefficient use of strings','Using bytes32 is better than using string. The byte1 data type is preferable wherever possible as it\'s cheaper.'),('CS10','Inefficient use of return values','A simple optimization in Solidity consists of naming the return value of a function. It is not needed to create a local\nvariable then.'),('CS11','Inefficient use of global variables','Storing global variables in memory is expensive in terms of gas. Memory size for global variables should be minimized.'),('CS12','Unbounded loops','In general, loops should be avoided. If avoiding loops is not possible, it could be beneficial to try to avoid unbounded\nloops, i.e., loops where the upper limit of iterations is not defined.'),('CS14','Inefficient use of indexed parameters','The indexed parameters in events have additional gas costs. It is good to only use the indexed qualifier for event\nparameters that should be searchable.'),('CS15','Inefficient use of structs','Since many DApps use storage, it would be useful to reduce archiving costs in order to optimize gas costs. To do this,\nit could use structs rather than mappings.'),('CS16','Inefficient use of mappings','Mappings are cheaper than arrays. It is cheaper to use arrays only when smaller elements like uint8 which can be\npacked together are used. In other cases, arrays might cost more gas than mappings.'),('CS17','Inefficient use of externalcalls','Every call to an external contract costs a decent amount of gas. For optimizing gas usage, itÕs better to call one function\nand have it return all the needed data rather than calling a separate function for every piece of data.'),('CS19','Inefficent use of events','It is cheaper to store the data in events rather than variables.'),('CS20','Inefficient use of function','To have several small functions consume more gas and bytecode. To save gas, larger complex functions should be\nused.'),('CS8','Inefficient use of memory','Whenever a developer has to make some internal computation in a solidity function with the help of an array, it'),('CS3','Abundance of public','The order of the functions influences the gas consumption. Since the order of the functions is based on the method ID,'),('CS4','Scarcity of external','Storing the input parameters in memory produces gas. An external function must be explicitly marked as external'),('CS7','Inefficient use of Internal','Calling internal functions is cheaper than calling public functions, because a call to a public function implies that'),('CS13','Inefficient use of data','Use byte32 whenever possible, because it is the most optimized storage type. For example, when using a small number,'),('CS18','Inefficient use of booleans','Booleans (bool) are uint8 which means they use 8 bits of storage even if they can only have two values: True or False.');
/*!40000 ALTER TABLE `costsmell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metricdescription`
--

DROP TABLE IF EXISTS `metricdescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metricdescription` (
  `metric` varchar(255) NOT NULL,
  `costSmell` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`metric`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metricdescription`
--

LOCK TABLES `metricdescription` WRITE;
/*!40000 ALTER TABLE `metricdescription` DISABLE KEYS */;
INSERT INTO `metricdescription` VALUES ('IP ','CS14',' number of indexed parameter'),('IFF ','CS7',' number of internal functions/total number of functions '),('LI ','CS6',' number of library imports/Contract\'s LOC'),('NE ','CS19',' number of events'),('ACI ','CS2',' number of assignment and counters\' increments within cycles'),('EC ','CS17',' number of invocation of external call/total number of call'),('AZ ','CS5',' number of the assignations default value during all variable definitions '),('BV ','CS18',' number of booleans/overall variables'),('NLF ','CS12',' number of loop'),('RLV ','CS10',' number of method with return local variables/number of method'),('VS ','CS1',' number of variable contain in a struct/(number of variable contain in a struct + number of instance variable )'),('GV ','CS11',' number of global variables'),('DF ','CS20',' number of defined functions/LOC'),('EF ','CS4',' number of external function'),('MA ','CS16',' number of mapping/number of array + mappings'),('SB ','CS9',' number of strings/number of bytes1..32'),('UMA ','CS8',' number of use of memory array/ tot number of use of array '),('NM ','CS15','  number of mapping / number of instance variables'),('PM ','CS3',' number of public function'),('NU ','CS13',' number of not uint256/overall uint');
/*!40000 ALTER TABLE `metricdescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metriche`
--

DROP TABLE IF EXISTS `metriche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metriche` (
  `id` bigint NOT NULL,
  `ACI` varchar(255) DEFAULT NULL,
  `AZ` varchar(255) DEFAULT NULL,
  `BV` varchar(255) DEFAULT NULL,
  `DF` varchar(255) DEFAULT NULL,
  `EC` varchar(255) DEFAULT NULL,
  `EF` varchar(255) DEFAULT NULL,
  `GV` varchar(255) DEFAULT NULL,
  `IFF` varchar(255) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `LI` varchar(255) DEFAULT NULL,
  `MA` varchar(255) DEFAULT NULL,
  `NE` varchar(255) DEFAULT NULL,
  `NLF` varchar(255) DEFAULT NULL,
  `NM` varchar(255) DEFAULT NULL,
  `NU` varchar(255) DEFAULT NULL,
  `PM` varchar(255) DEFAULT NULL,
  `RLV` varchar(255) DEFAULT NULL,
  `SB` varchar(255) DEFAULT NULL,
  `UMA` varchar(255) DEFAULT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `nomeFile` varchar(255) DEFAULT NULL,
  `VS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metriche`
--

LOCK TABLES `metriche` WRITE;
/*!40000 ALTER TABLE `metriche` DISABLE KEYS */;
/*!40000 ALTER TABLE `metriche` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-07 16:56:06

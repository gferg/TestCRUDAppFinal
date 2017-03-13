DROP DATABASE IF EXISTS TEST;
CREATE DATABASE TEST;
USE TEST;
DROP TABLE IF EXISTS `test`.`user`;

CREATE TABLE USER (
	`ID` INT(8) NOT NULL AUTO_INCREMENT,
	`NAME` VARCHAR(40) NOT NULL,
	`AGE` INT(3) NOT NULL,
	`IS_ADMIN` TINYINT(1) NOT NULL DEFAULT '0',
	`CREATE_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
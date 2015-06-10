CREATE database tests_db;
USE tests_db;
DROP TABLE IF EXISTS random_table;
CREATE TABLE random_table (
  `table_id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL,
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
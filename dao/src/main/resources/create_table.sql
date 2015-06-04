DROP TABLE IF EXISTS Group_Users;

DROP TABLE IF EXISTS GroupsOfUsers;

DROP TABLE IF EXISTS Users;

DROP TABLE IF EXISTS Events;

CREATE TABLE `Users` (
  `user_id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `user_f_name` varchar(20) NOT NULL,
  `user_l_name` varchar(20) NOT NULL,
  `user_nick` varchar(20) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_phone_number` int(20) unsigned,
  `user_desc` varchar(300) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `GroupsOfUsers` (
  `group_id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `group_desc` varchar(300) NOT NULL,
  `group_creation_date` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `Events` (
  `event_id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `event_name` varchar(50) NOT NULL,
  `event_desc` varchar(300) NOT NULL,
  `event_date` BIGINT unsigned NOT NULL,
  `event_creation_date` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Group_Users` (
  `group_id` BIGINT unsigned NOT NULL,
  `user_id` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`),
  CONSTRAINT `fk_group_id` FOREIGN KEY (`group_id`) REFERENCES `GroupsOfUsers` (`group_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS group_users;

DROP TABLE IF EXISTS group_events;

DROP TABLE IF EXISTS event_users;

DROP TABLE IF EXISTS groups_of_users;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS events;

CREATE TABLE users (
  `user_id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `user_f_name` varchar(20) NOT NULL,
  `user_l_name` varchar(20) NOT NULL,
  `user_nick` varchar(20) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_phone_number` int(20) unsigned,
  `user_desc` varchar(300) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE groups_of_users (
  `group_id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `group_desc` varchar(300) NOT NULL,
  `group_creation_date` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE events (
  `event_id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `event_name` varchar(50) NOT NULL,
  `event_desc` varchar(300) NOT NULL,
  `event_date` BIGINT unsigned NOT NULL,
  `event_creation_date` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE group_users (
  `group_id` BIGINT unsigned NOT NULL,
  `user_id` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`),
  CONSTRAINT `fk_group_id` FOREIGN KEY (`group_id`) REFERENCES groups_of_users (`group_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES users (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE group_events (
  `group_id` BIGINT unsigned NOT NULL,
  `event_id` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`group_id`,`event_id`),
  CONSTRAINT `fk1_group_id` FOREIGN KEY (`group_id`) REFERENCES groups_of_users (`group_id`),
  CONSTRAINT `fk1_event_id` FOREIGN KEY (`event_id`) REFERENCES events (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE event_users (
  `event_id` BIGINT unsigned NOT NULL,
  `user_id` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`event_id`,`user_id`),
  CONSTRAINT `fk_event_id` FOREIGN KEY (`event_id`) REFERENCES events (`event_id`),
  CONSTRAINT `fk1_user_id` FOREIGN KEY (`user_id`) REFERENCES users (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


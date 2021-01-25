CREATE TABLE IF NOT EXISTS `authenticationConfig` (
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role_privilege` (
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL,
  FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  PRIMARY KEY(`role_id`, `privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `credential` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(255) NOT NULL UNIQUE,
  `client_secret` varchar(255) NOT NULL,
  `created_at` date NOT NULL,
  `credentials_expired` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `grant_type` varchar(255) NOT NULL,
  `scope` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_locked` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` date NOT NULL,
  `email` varchar(255) NOT NULL UNIQUE,
  `email_verified` tinyint(1) NOT NULL DEFAULT '1',
  `last_access_at` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL UNIQUE,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

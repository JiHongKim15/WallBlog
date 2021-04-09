# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` char(8) NOT NULL,
  `email` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`uid`),
  UNIQUE KEY `user_idx_unique_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `blog`;

CREATE TABLE blog (
  Bno int NOT NULL,
  keyword varchar(128) DEFAULT NULL,
  title varchar(128) DEFAULT NULL,
  contents varchar(4000) default null,
  uid varchar(40) default null,
  likes int default null,
  views int default null,
  create_date datetime DEFAULT current_timestamp(),
  PRIMARY KEY (Bno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
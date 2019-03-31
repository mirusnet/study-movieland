DROP SCHEMA IF EXISTS movieland;
CREATE SCHEMA movieland;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS rates;
DROP TABLE IF EXISTS movie_country;
DROP TABLE IF EXISTS movie_genre;


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
);


CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `original_name` varchar(255) NOT NULL,
  `russian_name` varchar(255) NOT NULL,
  `year` varchar(255) NOT NULL,
  `description` text,
  `poster` varchar(255) NOT NULL,
  `rating` DOUBLE NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text,
  `userid` int(11) NOT NULL,
  `movieid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `movieid` (`movieid`),
  FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  FOREIGN KEY (`movieid`) REFERENCES `movie` (`id`)
);


CREATE TABLE `rates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency` varchar(255) NOT NULL,
  `rate` double NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `movie_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movieid` int(11) NOT NULL,
  `countryid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`movieid`) REFERENCES `movie` (`id`),
  FOREIGN KEY (`countryid`) REFERENCES `country` (`id`)
);

CREATE TABLE `movie_genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movieid` int(11) NOT NULL,
  `genreid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`movieid`) REFERENCES `movie` (`id`),
  FOREIGN KEY (`genreid`) REFERENCES `genre` (`id`)
);















create database movieland;
use movieland;


CREATE TABLE `movie` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `original_name` varchar(255) NOT NULL,
   `russian_name` varchar(255) NOT NULL,
   `year` varchar(255) NOT NULL,
   `description` text,
   `poster` varchar(255) NOT NULL,
   `rating` float(2,1) NOT NULL,
   `price` float(5,2) NOT NULL,
   PRIMARY KEY (`id`)
 );
 
 CREATE TABLE `country` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
 );
 
 CREATE TABLE `genre` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
 );
 
 CREATE TABLE `user` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(255) NOT NULL,
   `email` varchar(255) NOT NULL,
   `password` varchar(255) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `email` (`email`)
 );
 
 CREATE TABLE `review` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `text` text,
   `userid` int(11) NOT NULL,
   `movieid` int(11) NOT NULL,
   PRIMARY KEY (`id`),
   KEY `userid` (`userid`),
   KEY `movieid` (`movieid`),
   CONSTRAINT `review_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
   CONSTRAINT `review_ibfk_2` FOREIGN KEY (`movieid`) REFERENCES `movie` (`id`)
 );
 
 CREATE TABLE `movie_country` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `movieid` int(11) NOT NULL,
   `countryid` int(11) NOT NULL,
   PRIMARY KEY (`id`),
   KEY `movieid` (`movieid`),
   KEY `countryid` (`countryid`),
   CONSTRAINT `movie_country_ibfk_1` FOREIGN KEY (`movieid`) REFERENCES `movie` (`id`),
   CONSTRAINT `movie_country_ibfk_2` FOREIGN KEY (`countryid`) REFERENCES `country` (`id`)
 );
 
 CREATE TABLE `movie_genre` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `movieid` int(11) NOT NULL,
   `genreid` int(11) NOT NULL,
   PRIMARY KEY (`id`),
   KEY `movieid` (`movieid`),
   KEY `genreid` (`genreid`),
   CONSTRAINT `movie_genre_ibfk_1` FOREIGN KEY (`movieid`) REFERENCES `movie` (`id`),
   CONSTRAINT `movie_genre_ibfk_2` FOREIGN KEY (`genreid`) REFERENCES `genre` (`id`)
 );
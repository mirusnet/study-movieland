create database movieland;
use movieland;


CREATE TABLE `user` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
   `email` varchar(255) NOT NULL,
   `password` varchar(255) NOT NULL,
   UNIQUE(email),
   PRIMARY KEY (`id`)
 );
 
 CREATE TABLE `movie` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `original_name` varchar(255) NOT NULL,
   `russian_name` varchar(255) NOT NULL,
   `year` varchar(255) NOT NULL,
   `description` TEXT,
   `poster` varchar(255) NOT NULL,
   `rating` float(2,1) NOT NULL,
   `price` float(5,2) NOT NULL,
    PRIMARY KEY (`id`)
 );
 
 
 CREATE TABLE `review` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `text` TEXT,
   userid int not null,
   movieid int not null,
    FOREIGN KEY (userid)  REFERENCES user(id),
	FOREIGN KEY (movieid)  REFERENCES movie(id),
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
 
 
  CREATE TABLE `movie_country` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
     movieid int not null,
     countryid int not null,
  	FOREIGN KEY (movieid)  REFERENCES movie(id),
    FOREIGN KEY (countryid)  REFERENCES country(id),
   PRIMARY KEY (`id`)
 );
 
   CREATE TABLE `movie_genre` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
     movieid int not null,
     genreid int not null,
  	FOREIGN KEY (movieid)  REFERENCES movie(id),
    FOREIGN KEY (genreid)  REFERENCES genre(id),
   PRIMARY KEY (`id`)
 );
 
 
 use movieland;
 
 show create table movie;
show create table country;
 show create table genre;
show create table  user;
show create table review;
 show create table movie_country;
 show create table movie_genre;
 
 
 select * from country;
 select * from genre;
 select * from movie;
 select * from user;
 select * from review;
 select * from movie_country;
 select * from movie_genre;
    
commit;
    
 select m.id, mc.id, c.id from movie m, movie_country mc, country c
 where m.id = mc.movieid and mc.countryid = c.id;
 
 select m.russian_name, g.name, c.name from movie m 
 left join movie_country mc on m.id = mc.movieid
 left join country c on mc.countryid = c.id
 left join movie_genre mg on m.id = mg.movieid
 left join genre g on g.id = mg.genreid;
    
    
    select * from movie_genre;
 
select m.original_name, mg.genreid from movie m join movie_genre mg on m.id = mg.movieid where mg.genreid = 6;


commit;






SELECT * FROM movie ORDER BY RAND() LIMIT 3;
 

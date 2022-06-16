SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS img_quizs;
DROP TABLE IF EXISTS points;
DROP TABLE IF EXISTS quizs;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE img_quizs
(
	id int NOT NULL AUTO_INCREMENT,
	img_question varchar(256) NOT NULL,
	img_choices1 mediumblob NOT NULL,
	img_choices2 mediumblob NOT NULL,
	img_choices3 mediumblob NOT NULL,
	img_choices4 mediumblob NOT NULL,
	ima_file_name1 varchar(64) NOT NULL,
	img_file_name2 varchar(64) NOT NULL,
	img_file_name3 varchar(64) NOT NULL,
	img_file_name4 varchar(64) NOT NULL,
	img_answer varchar(64) NOT NULL,
	explanation varchar(256) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE points
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	name varchar(32) NOT NULL,
	score int NOT NULL,
	created_at timestamp NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE quizs
(
	id int NOT NULL AUTO_INCREMENT,
	question varchar(256) NOT NULL,
	choices1 varchar(64) NOT NULL,
	choices2 varchar(64) NOT NULL,
	choices3 varchar(64) NOT NULL,
	choices4 varchar(64) NOT NULL,
	answer varchar(64) NOT NULL,
	explanation varchar(256) NOT NULL,
	genre varchar(64) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE users
(
	id int NOT NULL AUTO_INCREMENT,
	account varchar(32) NOT NULL,
	name varchar(32) NOT NULL,
	password varchar(256) NOT NULL,
	salt varchar(64) NOT NULL,
	is_admin boolean NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (account)
);



/* Create Foreign Keys */

ALTER TABLE points
	ADD FOREIGN KEY (user_id)
	REFERENCES users (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;




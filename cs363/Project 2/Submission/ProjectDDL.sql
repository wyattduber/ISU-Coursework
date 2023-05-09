CREATE DATABASE IF NOT EXISTS Project2;
USE Project2;

CREATE TABLE IF NOT EXISTS State (
	name VARCHAR(30),
    PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS User (
	followers INT,
    sub_category VARCHAR(30),
	screen_name VARCHAR(50),
    following INT,
    name VARCHAR(50),
    location VARCHAR(50),
    category VARCHAR(30),
	PRIMARY KEY (name),
    FOREIGN KEY (location) REFERENCES State(name)
);

CREATE TABLE IF NOT EXISTS Day (
	id INT,
    day VARCHAR(20),
    PRIMARY KEY (day)
);

CREATE TABLE IF NOT EXISTS Month (
	id INT,
    month VARCHAR(20),
    PRIMARY KEY (month)
);

CREATE TABLE IF NOT EXISTS Year (
	year INT,
    PRIMARY KEY (year)
);

CREATE TABLE IF NOT EXISTS Century (
	century INT,
    PRIMARY KEY (century)
);

CREATE TABLE IF NOT EXISTS URL (
	url VARCHAR(100),
    PRIMARY KEY (url)
);

CREATE TABLE IF NOT EXISTS Hashtag (
	name VARCHAR(50),
    PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS Tweet (
	tid INT,
    textbody VARCHAR(240),
    retween_count INT,
    retweeted INT,
    posted VARCHAR(50),
    posting_user VARCHAR(50),
    day VARCHAR(20),
    month VARCHAR(20),
    year INT,
    PRIMARY KEY (tid),
    FOREIGN KEY (posting_user) REFERENCES User(name),
    FOREIGN KEY (day) REFERENCES Day(day),
    FOREIGN KEY (month) REFERENCES Month(month),
    FOREIGN KEY (year) REFERENCES Year(year)
);

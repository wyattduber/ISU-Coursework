CREATE DATABASE IF NOT EXISTS Museum;
USE Museum;

CREATE TABLE group_s(
	name VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (name)
);

CREATE TABLE artwork(
	title VARCHAR(50) NOT NULL UNIQUE,
    year INT,
    type VARCHAR(20),
    price INT,
    group_name VARCHAR(20),
    PRIMARY KEY (title),
    FOREIGN KEY (group_name) REFERENCES group_s(name)
);

CREATE TABLE artist(
	name VARCHAR(20) NOT NULL UNIQUE,
    birthplace VARCHAR(50),
    age INT,
    style VARCHAR(20),
    title VARCHAR(50),
    PRIMARY KEY (name),
    FOREIGN KEY (title) REFERENCES artwork(title)
);

CREATE TABLE customer(
	cust_id INT NOT NULL UNIQUE,
    name VARCHAR(20),
    address VARCHAR(50),
    amount INT,
    group_name VARCHAR(20),
    artist_name VARCHAR(20),
    PRIMARY KEY (cust_id),
    FOREIGN KEY (group_name) REFERENCES group_s(name),
    FOREIGN KEY (artist_name) REFERENCES artist(name)
);


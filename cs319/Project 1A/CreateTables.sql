CREATE DATABASE IF NOT EXISTS Project1A;
USE Project1A;

create table students(
	snum int NOT NULL UNIQUE,
	ssn int,
	name varchar(10),
	gender varchar(1),
	dob datetime,
	c_addr varchar(20),
	c_phone varchar(10),
	p_addr varchar(20),
	p_phone varchar(10),
	primary key (ssn)
);

create table departments(
	code int,
	name varchar(50) NOT NULL UNIQUE,
	phone varchar(10),
	college varchar(20),
	primary key (code)
);

create table degrees(
	name varchar(50),
	level varchar (5),
	department_code int,
	primary key (name, level),
	foreign key (department_code) references departments(code)
);

create table courses(
	number int,
	name varchar(50) NOT NULL UNIQUE,
	description varchar(50),
	credithours int,
	level varchar(20),
	department_code int,
	primary key (number),
	foreign key (department_code) references departments(code)
);

create table register(
	snum int,
	course_number int,
	regtime varchar(20),
	grade int,
	primary key (snum, course_number),
	foreign key (snum) references students(snum),
	foreign key (course_number) references courses(number)
);

create table major(
	snum int,
	name varchar(50),
	level varchar(5),
	primary key (snum, name, level),
	foreign key (snum) references students(snum),
	foreign key (name, level) references degrees(name, level)
);

create table minor(
	snum int,
	name varchar(50),
	level varchar(5),
	primary key (snum, name, level),
	foreign key (snum) references students(snum),
	foreign key (name, level) references degrees(name, level)
);

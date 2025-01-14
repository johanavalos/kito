create database crudapi;

use crudapi;

create table employee(
	id int not null primary key auto_increment,
    name varchar(255),
    gender varchar(255),
    department varchar(255),
    dob date
);

create table task(
	id int not null primary key auto_increment,
    title varchar(255),
    employeeId int not null,
    FOREIGN KEY (employeeId) REFERENCES employee(id)
);
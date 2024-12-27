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


INSERT INTO employee (name, gender, department, dob)
VALUES ("Robert", "male", "QA", "2024-09-11");

INSERT INTO task (title, employeeId)
VALUES ("Design API", 1);

select * from employee;
select * from task;

select t.title, e.name from task as t
join employee as e on e.id=t.employeeId;

delete from employee
where employee.id > 4;
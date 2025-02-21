use crudapi;

select * from employee;
select * from task;

select t.title, e.name from task as t
join employee as e on e.id=t.employeeId;

insert into department (name,isActive,createdate,updatedate) values ('Administration',1,null,null);
insert into department (name,isActive,createdate,updatedate) values ('Human Resource',1,null,null);
insert into department (name,isActive,createdate,updatedate) values ('Software Developer',1,null,null);
select * from department ;
insert into users (firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) values ('Ashy','Yetre',4,2,'home','artificialgenius.ashyz@gmail.com',1,null,null);
insert into users (firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) values ('Rakesh','Thakre',4,2,'home','artificialgenius.ashyz@gmail.com',1,null,null);
insert into users (firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) values ('Ramesh','Maher',4,2,'home','artificialgenius.ashyz@gmail.com',1,null,null);
insert into users (firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) values ('Vinay','Khanna',4,2,'home','artificialgenius.ashyz@gmail.com',1,null,null);
insert into users (firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) values ('Farhan','Khan',4,2,'home','artificialgenius.ashyz@gmail.com',1,null,null);
insert into users (firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) values ('Suresh','Sharma',4,2,'home','artificialgenius.ashyz@gmail.com',1,null,null);
select * from users;
select * from userAttendance;
select * from userAttendance where userid = 2 and attended_date between '1970-01-01' and CURDATE();
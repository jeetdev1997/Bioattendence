/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ashish.yetre
 * Created: 23 Apr, 2020
 */

drop database  attendance_system_db;
CREATE DATABASE attendance_system_db;
use attendance_system_db;

/*
Department Table 
*/
Create table department(
id INT AUTO_INCREMENT PRIMARY KEY,
name text,
isActive tinyint(1),
createdate date,
updatedate date
);
insert into department (name,isActive,createdate,updatedate) values ('Administration',1,null,null);
insert into department (name,isActive,createdate,updatedate) values ('Human Resource',1,null,null);
/*
Department table End
*/
/*
Roles Table Start
*/
create table roles (
id INT AUTO_INCREMENT PRIMARY KEY,
role text,
createdate date,
updatedate date
);
insert into roles (role,createdate,updatedate) values ('ADMIN',null,null);
insert into roles (role,createdate,updatedate) values ('EMPLOYEE',null,null);
/*
Roles Table End
*/
/*
Users Table Start 
*/
create table users (
userid INT AUTO_INCREMENT PRIMARY KEY,
firstname text,
lastname text,
departmentid int,
roleid int,
address text,
email text,
isActive tinyint(1),
createddate date,
updateddate date,
FOREIGN KEY (departmentId) REFERENCES department(id),
FOREIGN KEY (roleId) REFERENCES roles(id)
);
insert into users (firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) values 
('Sonal','Admin',1,1,'Somewhere on Earth','addurEmail@adress',1,null,null);

insert into users (firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) values 
('Ashish','Yetre',1,1,'Somewhere on Earth','artificialgenius.ashyz@gmail.com',1,null,null);
/*
Users Table Start Ends
*/
/*
Login Table Start Ends
*/
create table login (
userid INT  PRIMARY KEY,
username text,
password text,
FOREIGN KEY (userid) REFERENCES users(userid)
);
insert into login (userid,password,username) values (1,'soanl','sonal');
insert into login (userid,password,username) values (2,'admin','admin');
/*
Login table end
*/
/*
Attendance Table start
*/
drop table userAttendance ;
create table userAttendance (
id int AUTO_INCREMENT PRIMARY KEY,
userid int,
status text,
attended_date date,
in_time text,
out_time text,
FOREIGN KEY (userid) REFERENCES users(userid)
);
/*
Attendance Table End
*/
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

drop table users;
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
insert into users (firstname,lastname,departmentid,roleid,address,email,isActive,createddate,updateddate) values ('Ashish','Yetre',1,1,'home','artificialgenius.ashyz@gmail.com',1,null,null);
Create table department(
id INT AUTO_INCREMENT PRIMARY KEY,
name text,
isActive tinyint(1),
createdate date,
updatedate date
);
insert into department (name,isActive,createdate,updatedate) values ('Administration',1,null,null);
create table roles (
id INT AUTO_INCREMENT PRIMARY KEY,
role text,
createdate date,
updatedate date
);
insert into roles (role,createdate,updatedate) values ('ADMIN',null,null);
insert into roles (role,createdate,updatedate) values ('EMPLOYEE',null,null);

drop table login;

create table login (
userid INT  PRIMARY KEY,
password text,
username text,
FOREIGN KEY (userid) REFERENCES users(userid)
);
insert into login (userid,password,username) values (1,'junkie','root');

drop table userAttendance ;
create table userAttendance (
id int AUTO_INCREMENT PRIMARY KEY,
userid int,
attended_date date,
in_time date,
out_time date,
FOREIGN KEY (userid) REFERENCES users(userid)
);
select * from userAttendance ;

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
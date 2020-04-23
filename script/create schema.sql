/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jeet dubey
* Created: 23 Apr, 2020
 */
CREATE DATABASE attendance_system_db;
select database attendance_system_db;
create table users (
userid INT AUTO_INCREMENT PRIMARY KEY,
firstname text,
lasttname text,
departmentid int,
roleid int,
address text,
email text,
isActive tinyint(1),
createddate date,
updateddate date,
FOREIGN KEY (departmentId) REFERENCES department(id),
FOREIGN KEY (roleId) REFERENCES roles(id),
);

Create table department(
id INT AUTO_INCREMENT PRIMARY KEY,
name text,
isActive tinyint(1),
createdate date,
updatedate date
);

create table roles (
id INT AUTO_INCREMENT PRIMARY KEY,
role text,
createdate date,
updatedate date
)

create table login (
userid INT  PRIMARY KEY,
password text,
username text,
FOREIGN KEY (userid) REFERENCES user(userid)
)
)


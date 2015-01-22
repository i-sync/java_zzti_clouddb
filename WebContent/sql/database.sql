#Contacts database script

#1.Create database contacts
Create database contacts;

use contacts;

#create table class
create table class
(
	id int auto_increment primary key,
    name varchar(32),
    vocational varchar(128)
);

#create table contact
create table contact
(
	id int auto_increment primary key,
    name varchar(32),
    cid int,
    phone varchar(16),
    email varchar(64),
    living varchar(128),
    company varchar(128),
    remark varchar(1024)
);

alter table contact 
	add column adddate datetime,
    add column updatedate datetime,
    add column ip varchar(32);

select * from contact;
create database univer;
use univer;
create table courses (
	id int not null primary key auto_increment,
    title varchar(30) not null unique,
    lenght int not null,
    descriptions varchar(60)
    );
insert into courses(title, lenght, descriptions)
values("Web-master", 50, "Web-master description"),
	("PHP base", 30, "PHP baser description"),  
	("Java middle", 60, "Java middle description"),  
	("Using git", 20, "Using git description"),  
	("Mysql base", 45, "Mysql base description"),
	("OS profi", 35, "OS profi description"),  
	("Test Automation", 55, "Test Automation description");    

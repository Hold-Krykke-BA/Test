DROP SCHEMA IF EXISTS BookingSystem;
CREATE SCHEMA IF NOT EXISTS BookingSystem;
use BookingSystem;

create table Customers (
	ID int not null auto_increment,
	firstname varchar(255) not null,
	lastname varchar(255),
	birthdate date,
	PRIMARY KEY (ID)
);

create table Employees (
       ID int not null auto_increment,
       firstname varchar(255) not null,
       lastname varchar(255) not null,
       PRIMARY KEY (ID)
 );
 
 create table Bookings (		
	ID int not null auto_increment,	
	customerId int not null,	
	employeeId int not null,	
	date Date not null,		
	start Time not null,		
	end Time not null,		
	primary key (ID),		
	foreign key (customerId)	
		references Customers(ID)
		on delete cascade,	
	foreign key (employeeId)	
		references Employees(ID)
		on delete cascade	
);

ALTER TABLE `BookingSystem`.`Customers` 
ADD COLUMN `phonenumber` VARCHAR(45) NULL AFTER `birthdate`;
--Dealership table creation
--Accounts
create table Accounts(

	id serial,
	username varchar unique not null,
	pass varchar not null,
	email  varchar not null primary key

);
--Customer 
create table Customer(

	id serial primary key,
	firstName varchar not null,
	lastName varchar not null,
	email varchar unique not null,
	phoneNumber varchar not null,
	carId int

);
drop table customer;

--Employees
create table Employee(

	id serial primary key,
	firstName varchar not null,
	lastName varchar not null,
	email varchar unique not null,
	phoneNumber varchar not null,
	pos varchar not null
	
);
drop table employee;
drop table customer;
drop table accounts;
drop table car;
--Cars
create table Car(

	id serial primary key,
	year int not null,
	make varchar not null,
	model varchar not null,
	color varchar not null,
	engine varchar not null,
	miles int not null,
	price int not null,
	sold boolean

);
            select a.username, e.email, e.pos from "Dealership".Accounts a inner join "Dealership".Employee e on e.email = a.email where a.username = 'jdoe' and e.pos = 'Sales';
         select c.id, c.firstName, c.lastName, c.email, c.phoneNumber from "Dealership".Accounts a inner join "Dealership".Customer c on c.email = a.email;

select c.id, c.firstname, c.carId, c.email, a.username from customer c inner join accounts a on a.email = c.email inner join car b on b.id = c.id;
select e.email, a.email, a.username from accounts a inner join employee e on e.email = a.email where a.username = 'jdoe';
select a.email, e.email, e.pos from "Dealership".Accounts a inner join "Dealership".Employee e on e.email = a.email where a.username = 'jdoe' and e.pos = 'Sales';
--Creating Employees
insert into Employee values
(default,'Doug', 'Howard', 'dhoward@dealership.com', '1(123) 456-9871', 'Manager'),
(default, 'John', 'Doe', 'jdoe@dealership.com', '1(123) 536-9887', 'Sales'),
(default, 'Tom', 'Jones', 'tjones@dealership.com', '1(122) 466-9456', 'Sales');

--Check Employee Table
select * from Employee;
--Create Employee Accounts
insert into accounts values
(default, 'dhoward','managerpass','dhoward@dealership.com' ),
(default, 'jdoe','pass','jdoe@dealership.com' ),
(default, 'tjones','sales','tjones@dealership.com' );
select * from "Dealership".accounts;
--Create cars for database
insert into car values
(default, '2015', 'Mercedes-Benz', 'CLA250', 'White', 'V4', 60000, 27000, false),
(default, '2018', 'Toyota', 'Camry', 'Red', 'V6', 30000, 22000, false),
(default, '2021', 'Dodge', 'Challenger', 'Yellow', 'V8', 10000, 34000, false),
(default, '2020', 'Cheverolet', 'Corvette', 'Blue', 'V10', 25000, 46000, false),
(default, '2011', 'Ford', 'Focus', 'Silver', 'V4', 65000, 15000, false),
(default, '2014', 'Hodna', 'Accord', 'Black', 'V6', 80000, 13500, false);
--Linkning Accounts to Employee and Customer

select * from employee join accounts on employee.email = accounts.email;
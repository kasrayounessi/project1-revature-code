# Expense Reimbursement System (ERS) - Java Javalin JDBC

## Project Description

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

* Java - version 17
* Maven - version 1.7
* Hibernate - version 5.6.1
* MySQL - version 8.0.27
* Bootstrap - version 4.6

## Features

List of features ready and TODOs for future development
* Login and logout to the system as an employee or an employer
* Submit new tickets and view pending and old tickets as an employee
* Reject or accept new tickets and view all tickets that have been submitted from all employees

To-do list:
* Add a search bar that enables the employer to search the for tickets using employees' names
* Add a functionality that enables users to register themselves in the database for login
* Add a password filter that rejects too short or invalid passwords

## Getting Started
   
> (Using your desired IDE)

* Click on 'Edit Configuration' and add "Smart Tomcat" as a configuration.
* Add javax.servlet.api as a dependency

In order to login, you must create two MySQL tables, employee and manager. The feature of adding users has not been added to this project. Here are sample commands to create tables and register users:

* CREATE TABLE employee (emp_id INTEGER PRIMARY KEY AUTO_INCREMENT, emp_username VARCHAR(40) UNIQUE NOT NULL, emp_password VARCHAR(40) NOT NULL);
* CREATE TABLE manager (man_id INTEGER PRIMARY KEY AUTO_INCREMENT, man_username VARCHAR(40) UNIQUE NOT NULL, man_password VARCHAR(40) NOT NULL);

* INSERT INTO employee(emp_username, emp_password) VALUES ('kasra', '123');
* INSERT INTO manager(man_username, man_password) VALUES ('admin', '123');

These commands register 'kasra' as an employee and 'admin' as an employer/manager. (Both cases, password is '123')

## Usage

There are images of the login, employee, and manager menus in the same repository as this file. 


## License

This project uses the following license: MIT-LICENSE.


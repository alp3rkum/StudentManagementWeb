# StudentManagementWeb
A basic student management project made on Java Spring Web.

## What Is It?

This is a basic Full-Stack project that uses Java as its backend language, using powerful frameworks like Maven, Spring Web and Hibernate. It has been inspired from my college friend's project [Student-Management-App](https://github.com/mgorenli9/Student-Managament-App), but essentially a Web-ized version with additional data and a MySQL database system with one-to-many relationship.

## Features

- You can perform general _CRUD (Create-Read-Update-Delete)_ operations of students, courses, and their enrollments in this system. All data is stored in a MySQL database with a one-to-many relationship between students and courses, handled by the enrollment table.
- It has a custom error page that is a heavily modified version of Spring Web's standard Whitelabel Error Page.
- As this project naturally makes heavy usage of Thymeleaf in front-end, webpages of the application use partials that allows for a cleaner front-end code.
- This project also demonstrates usage of a frontend controller, specifically to handle front-end pages, with few necessary operations happening also in the controller.
- This project was developed in Java 21, Maven, Spring Web 6.0.13 and Hibernate 6.2.13, so it is a pretty up-to-date project (as of Nov 13th, 2023).

## Installment

- Download the "StudentManagementWeb.zip" and extract its contents into your IDE's project folder/your workspace.
- Download and open the "StudentManagementWeb.sql", copy paste all code and run inside a valid connection to create the database and its tables.

## TODOS/Fixes

- The front-end interface isn't the best out there, it can take some improvements.
- The back-end has a few functions that ultimately remained unused.

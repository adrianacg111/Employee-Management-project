# Employee Management Project

This project is an employee management application that includes a React frontend, a Spring Boot backend, and a MySQL database.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Database Configuration](#database-configuration)
- [Backend Configuration (Spring Boot)](#backend-configuration-spring-boot)
- [Frontend Configuration](#frontend-configuration)
- [Running the Project](#running-the-project)

## Prerequisites
Ensure you have the following programs installed on your system:
- Java JDK 11 or higher
- Maven or Gradle
- MySQL
- Node.js
- npm (package manager)
- Git (optional)

## Database Configuration
1. **Import the database**:
   - Open your MySQL client or use the command line and execute:
     ```bash
     mysql -u [username] -p company_db < backup.sql
     ```
   - Replace `[username]` with your MySQL username.

2. **Configure the connection credentials in the backend**:
   - Open the `application.properties` file in the Spring Boot project and set up the connection credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/company_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     spring.jpa.hibernate.ddl-auto=update
     ```

## Backend Configuration (Spring Boot)
1. **Navigate to the backend directory**:
	employee-management-backend
   - Build the project with Maven:
     ```bash
     mvn clean install
     ```
   - Start the Spring Boot application:
     ```bash
     mvn spring-boot:run
     ```
   - The application will run at http://localhost:8080

## Frontend Configuration
1. **Navigate to the frontend directory**:
	employee-management
   - Install the dependencies:
     ```bash
     npm install
     ```
   - Start the React application:
     ```bash
     npm start
     ```
   - The frontend application will run at http://localhost:3000

## Running the Project
- Ensure that the MySQL database is running.
- Start the backend and frontend as described in the sections above.
- Open your browser and go to http://localhost:3000 to view the application.

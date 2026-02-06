Taskmaster API

This is a simple Spring Boot API designed to manage tasks and projects. It allows users to register, authenticate, and perform CRUD operations on tasks and projects. The API supports MySQL as the database, and JWT authentication is used for user login.

Setup Instructions
Step 1: Set Up MySQL Database

Open MySQL Workbench (or any MySQL tool you prefer).

Create a database named taskmaster:

CREATE DATABASE taskmaster;


Update your application.properties file with the correct MySQL database credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/taskmaster
spring.datasource.username=root
spring.datasource.password=Sid@123*


Make sure the database credentials are correct.

Database tables will be created automatically when the application starts, using Spring Data JPA.

Step 2: Run the Application

Start the application using your IDE or the following command:

mvn spring-boot:run


The application will be running at http://localhost:8080.

How to Use the API
1. User Registration

Endpoint: /api/register

Method: POST

Description: Register a new user.

Request Body Example:
{
  "username": "john_doe",
  "password": "your_password_here"
}

Response:

Status: 200 OK

Message: "User registered successfully"

2. User Authentication (Login)

Endpoint: /api/authenticate

Method: POST

Description: Log in with the registered username and password. This will return a JWT token.

Request Body Example:
{
  "username": "john_doe",
  "password": "your_password_here"
}

Response:

Status: 200 OK

Body: JWT token as a string.

Example:

{
  "token": "your_jwt_token_here"
}

3. Create a Task

Endpoint: /api/tasks

Method: POST

Description: Create a new task.

Request Body Example:
{
  "title": "Finish Project",
  "description": "Complete the taskmaster API project.",
  "priority": "High",
  "dueDate": "2026-02-10T10:00:00"
}

Response:

Status: 200 OK

Message: "Task created successfully"

4. Get All Projects

Endpoint: /api/projects

Method: GET

Description: Retrieve all the projects for the authenticated user.

Response:

Status: 200 OK

Body: A list of all projects associated with the authenticated user.

5. Update Task

Endpoint: /api/tasks/{id}

Method: PUT

Path Variable: id (Example: 1)

Description: Update a task by its ID.

Request Body Example:
{
  "title": "Updated Task",
  "description": "Updated task description",
  "priority": "Medium",
  "dueDate": "2026-02-12T10:00:00"
}

Response:

Status: 200 OK

Message: "Task updated successfully"

6. Delete Task

Endpoint: /api/tasks/{id}

Method: DELETE

Path Variable: id (Example: 1)

Description: Delete a task by its ID.

Response:

Status: 200 OK

Message: "Task deleted successfully"

Error Handling

If any request is invalid (e.g., missing required fields or incorrect data), the API will return an error message along with an appropriate status code.

Example Error Response:
{
  "error": "Bad Request",
  "message": "Field 'title' is required"
}

Contributing

Feel free to open issues or submit pull requests to improve the API. Suggestions and contributions are welcome!


This project is open-source and available under the MIT License. See the LICENSE
 file for more details.

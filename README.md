Student CRUD Application

A Spring Boot application that performs CRUD (Create, Read, Update, Delete) operations on student records using a REST API and a relational database.

🚀 Features

- Create new student records
- Retrieve student details
- Update existing student information
- Delete student records
- RESTful API development
- Database integration using Spring Data JPA
- Entity and repository-based data management

🛠️ Tech Stack

- Language: Java
- Framework: Spring Boot
- Database Access: Spring Data JPA
- Database: MySQL
- Build Tool: Maven
- IDE: IntelliJ IDEA

📚 Concepts Learned

- REST API development
- CRUD operations
- "@RestController" and request mapping annotations
- "@Service" for business logic
- "@Entity" for database entities
- "@Id" for primary key mapping
- "JpaRepository" for database operations
- Dependency Injection
- Maven project structure

📂 Project Structure

src/main/java/com/example/demo/
├── controller/
│   └── StudentController.java
├── entity/
│   └── Student.java
├── repository/
│   └── StudentRepository.java
├── service/
│   └── StudentService.java
└── DemoApplication.java

src/main/resources/
└── application.properties

⚙️ How to Run

1. Clone this repository.
2. Open the project in IntelliJ IDEA.
3. Configure your MySQL database in "application.properties".
4. Make sure the required database exists.
5. Run "DemoApplication.java".

🔐 Configuration

Configure your database URL, username, and password in "application.properties".

Important: Never upload real database passwords or other secrets to a public repository.

🎯 Project Goal

This project was built to strengthen my understanding of Spring Boot, REST APIs, database integration, and backend development.

👨‍💻 Author

Harsh Upadhyay
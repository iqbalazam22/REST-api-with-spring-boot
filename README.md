 E-Commerce Backend REST API
A Spring Boot REST API project built to practice and demonstrate core backend development skills — including layered architecture, CRUD operations, and database integration using JPA/Hibernate and MySQL.

🚀 About The Project
This was my first REST API built with Spring Boot. It simulates an e-commerce store backend with proper separation of concerns using Controllers, Services, DTOs, and Entities — following industry-standard layered architecture patterns.

✨ Features

RESTful CRUD endpoints for store management
Clean layered architecture — Controller → Service → Repository
DTO pattern to separate request/response from entity models
JPA/Hibernate for ORM-based database interaction
MySQL database with schema defined via SQL script
YAML-based application configuration


🛠️ Tech Stack
LayerTechnologyFrameworkSpring BootPersistenceSpring Data JPA, HibernateDatabaseMySQLBuild ToolMavenConfigapplication.yaml

📁 Project Structure
src/main/java/com/iqbal/store/
├── controller/       # REST endpoints
├── service/          # Business logic
├── entity/           # JPA entities
├── dto/              # Request & Response DTOs
└── repository/       # Spring Data JPA repositories

⚙️ Setup & Run
Prerequisites

Java 17+
Maven
MySQL running locally

1. Clone the repo
bashgit clone https://github.com/iqbalazam22/REST-api-with-spring-boot.git
cd REST-api-with-spring-boot
2. Set up the database
Run the included SQL script to create the database and tables:
sqlsource script.sql;
3. Configure your database credentials
Update application.yaml:
yamlspring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_db_name
    username: your_username
    password: your_password
4. Run the application
bashmvn spring-boot:run
Server starts at: http://localhost:8080

📚 What I Learned

How to structure a Spring Boot project with clean layered architecture
Using DTOs to decouple API contracts from database entities
Writing JPA entities and repository interfaces
Configuring Spring Boot with YAML instead of properties files
Testing APIs with Postman


👤 Author
Md Iqbal Azam

GitHub: github.com/iqbalazam22
LinkedIn: linkedin.com/in/iqbal-azam-74247b297

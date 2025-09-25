# Movie Catalog System Using SpringBoot Microservices
The Movie Catalog System is a Java-based web application designed to demonstrate the practical application of Spring Boot microservices architecture for managing and delivering movie-related data. The system is composed of multiple independent microservices that collectively handle user information, movie metadata, ratings. Each service communicates via RESTful APIs and is developed using Spring Boot, ensuring scalability, modularity, and ease of maintenance. The project integrates services such as a Movie Info Service, User Service orchestrated through Spring components. This system showcases the effectiveness of microservice-based design in building loosely coupled, resilient applications while maintaining clear separation of concerns. The project can serve as a foundational model for more complex, distributed systems in media and entertainment domains.

Basic Architecture

The movie catalog system consisting of two main services:
Movie Service: This is an admin-focused service for movie management and user listing.
User Service: This is customer-facing service handling authentication, user profiles, and movie browsing.
The system uses Java with Spring Boot, following a standard layered architecture with controllers, services, repositories, DTOs, and models.

Two Main Services:
User Service - is configured on Port 8081
Movie Service - is configured on Port 8082
Each service is connected with a Mysql server and has its own web interface with Thymeleaf templates, and provides REST API endpoints for inter-service communication. 
These services can perform CRUD operations on their respective data entities and can also access data from the other service through HTTP REST calls.
















Interaction Flow:
User accesses either the User Service at http://localhost:8081 and Admins access Movie Service at http://localhost:8082 
Each service provides a home page with links to its respective functionalities
The UI allows performing CRUD operations on both the tables Users and Movies
When viewing cross-service data, the service makes REST API calls to the other service

Core Technologies :
1. Spring Boot Microservices Architecture

2. Spring Framework Components
Spring Web (Spring MVC): For creating REST controllers and handling HTTP requests
Spring Data JPA: For database access without writing explicit SQL
Spring Boot Starters: For auto-configuration and dependency management
RestTemplate: For service-to-service communication

3. Database Technologies
MySQL
JPA Repositories: For database operations without writing SQL

4. Frontend Technologies
Thymeleaf: Template engine for server-side HTML rendering 

5. API Communication
JSON: For data serialization between services
Jackson: For JSON serialization/deserialization 

6. Design Patterns
Repository Pattern: Used for database access 
Service Layer Pattern: Used for business logic 
MVC Pattern: Used Model-View-Controller for web interface
DTO Pattern: Used for data transfer between services

7. Development & Infrastructure
Maven : For dependency management and build automation
Tomcat  for running the applications

8. Database 
     MySql database 
User table with fields: id, email, name, password, phone
Movies table with fields: id, name, description, genre, rating

# pets-management-service
Simple REST application using Spring Boot for managing pets. 

****

## Objective:
Develop a simple REST application using Spring Boot for managing pets. The
application must expose REST APIs for creating, updating, deleting, and retrieving pet
information.

## Requirements:

- Implement a Spring Boot application with Java 17+.
- Use of a relational database. For the purpose of this exercise, it is sufficient to mock
database access services.
- Exposure of REST APIs using Spring Web.
- A refactoring of the application has already been planned to replace the chosen
  database with a non-relational one. The code should be somewhat prepared for this
  eventuality.

****

## Project Description:
The application must manage a **Pet** entity with the following properties:
- id (Long, auto-generated)
- name (String, required)
- species (String, e.g., "Dog", "Cat", "Rabbit", required)
- age (Integer, optional, must be greater than or equal to 0)
- ownerName (String, optional)
The application must provide a set of REST APIs for accessing the Pet entity.

****

### How to test it?

- Open the project and execute ``` mvn clean install ``` and verify that the **jar file** is located in the *target* folder.

******

### How to Run it?

- Go to the project directory and execute ``` mvn spring-boot:run ```.
- Access it via: Swagger UI.

******

### Swagger UI - Open API v3 documentation:

- Available via: http://localhost:8080/swagger-ui/index.html

![openapi.jpg](src%2Fdocs%2Fopenapi%2Fopenapi.jpg)

#### Get /pets/{id} endpoint:

![getpetsbyidendpoint.jpg](src%2Fdocs%2Fopenapi%2Fgetpetsbyidendpoint.jpg)

#### Get /pets endpoint:

![getpetsendpoint.jpg](src%2Fdocs%2Fopenapi%2Fgetpetsendpoint.jpg)

#### Create /pets endpoint:

![createpetendpoint.jpg](src%2Fdocs%2Fopenapi%2Fcreatepetendpoint.jpg)

#### Update /pets/{id} endpoint:

![updatepetendpoint.jpg](src%2Fdocs%2Fopenapi%2Fupdatepetendpoint.jpg)

#### Delete /pets/{id} endpoint:

![deletepetendpoint.jpg](src%2Fdocs%2Fopenapi%2Fdeletepetendpoint.jpg)

****

### Databases:
This application uses a Pet entity for a relational database in H2.
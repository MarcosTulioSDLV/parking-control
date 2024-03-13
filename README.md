# Parking Control Rest API
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)  ![H2 Database](https://img.shields.io/badge/H2%20Database-018bff?style=for-the-badge&logoColor=white) ![Swagger](https://img.shields.io/badge/Swagger-6DB33F?style=for-the-badge&logo=swagger&logoColor=white)

I developed a Rest API to manage parking spots in a condominium built by using **Spring Boot and Java**, providing all CRUD (Create, Read, Update and Delete) operations.
This API allows to store car information, such as the license plate, brand, and even personal information of the owner, such as name and apartment.
Since this parking control is for a condominium, only one car can be stored per parking spot.
Beside the common CRUD operations, it is possible to get parking spots by filtering either by car's Brand, or by car's responsible name.

I used some common libraries for this Rest API such **Spring Web, Spring Data JPA, Validation, H2 Database and SpringFox Boot Starter 3.0.0 (for the API documentation with Swagger)**.

Spring validation was used to validate the parking spot attributes, setting the car license plate to accept a maximum length of 7 characters, and also ensuring no blank attributes.

## Database Config:
For test this API, an external Database is not necessary because an embedded Database (H2 Database) was used with the following configuration properties: 

- Name: parking_control_db
- User name: sa
- Password:

## Development Tools:
This Rest API was built with:

- Spring Boot version: 2.7.17
- Java version: 1.8

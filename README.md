# Parking Control Rest API

I developed a Rest API to manage parking spots in a condominium powered by using springboot and java, providing all CRUD (Create, Read, Update and Delete) operations.
This API allows to store car information, such as the license plate, brand, and even personal information of the owner, such as name and apartment.
Since this parking control is for a condominium, only one car can be stored per parking spot.
Beside the common CRUD operations, it is posible to find parking spots by car's Brand, or by car's responsible name.


I used some common libraries for this Rest API such Spring Web, Spring Data JPA, Validation, MySQL Driver and SpringFox Boot Starter 3.0.0 (for the API documentation).

Spring validation was used to validate the parking spot attributes, setting the car license plate to accept a maximum length of 7 characters, and also ensuring no blank attributes.

This Rest API was powered with:
- Springboot version: 2.7.17
- Java version: 1.8

Database Config: 

For test this API, it is necessary to create a MySQL database with the properties: 
- name: parking_control_db
- user name: root
- password: 

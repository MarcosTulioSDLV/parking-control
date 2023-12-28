# Parking Control Rest API

I developed a Rest API to manage parking spots in a condominium developed by using springboot and java, providing all CRUD (Create, Read, Update and Delete) operations.
This system allows to store car information, such as the license plate, brand, and even personal information of the owner, such as name and apartment.
Only one car can be stored per parking spot.
Beside the common CRUD operations, it is posible to find parking spots by car's Brand, or car's responsible name.


I used some common libraries for this Rest API such Spring Web, Spring Data JPA, Validation, MySQL Driver and
SpringFox Boot Starter 3.0.0 (for the API documentation).


This Rest Api was developed with:

# Springboot
- version: 2.7.17

# Java
- version: 1.8

# Data Base Config 
For teste this API, it is necessary to create a MySQL Data Base with the properties: 
- name: parking_control_db
- user name: root
- password: 

###
###
###

This repo is build for assement of Edstruments.

######### Task 1 :
Create a simple RESTful API using Spring Boot that manages a list of Products. Each product
should have the following fields:
• id (Long, auto-generated)
• name (String)
• description (String)
• price (Decimal)
The API should support the following operations:
1 Get all products: GET /api/products
1 Get a product by ID: GET /api/products/{id}
1 Create a new product: POST /api/products
1 Update an existing product: PUT /api/products/{id}
1 Delete a product by ID: DELETE /api/products/{id}

######### Deliverables :

Project Structure : 
![image](https://github.com/user-attachments/assets/68ec7d2b-75c5-43b0-bd52-0faa7ba6af04)


######### Instructions to run this Application

JDL version used : JDK 17
Spring Boot Version used : 3.4.0

1)To run this application, you need to have Maven install in your machine. (For Downloading Maven dependencies of POM.xml file)
2) Download or Clone this repository into your local machine and open it in IDE.
3)Please refer to the below screenshot for application running configurations.
![image](https://github.com/user-attachments/assets/4c27f99d-c502-4ffa-9a68-3f0ad720e9c6)
4) I have used AWS RDS MySQL instance as a database for this application. (Credentials is available in application-dev.properties file.
5) Postman Collections : Please refer to the Edstrument-App.postman_collections.json for the curl commands and endpoints.
Additionally, I will Share Postman Collection to Edstrument team's email address . Please check your inbox for same

####### Task 2:
Design a relational database schema for an E-commerce platform that includes the following
entities:
• User: Represents a customer or admin of the platform.
• Product: Represents a product available for purchase.
• Order: Represents a customer's order.
• OrderItem: Represents individual items within an order.
Ensure the following relationships:
• A User can have many Orders.
• An Order can have many OrderItems.
• An OrderItem is associated with one Product.

####Deliverables 
Created a SQL Script to create the schema. You can refer for sql queries and db design to Task2-SQL folder in the root directory.(Shared on email as well).



#########Task 3:
Enhance the REST API created in Question 1 by adding exception handling and input
validation.
• Implement custom exception handling for scenarios such as:
◦ Product not found (404 Not Found).

◦ Invalid input data (400 Bad Request).
• Add validation for the following fields:
◦ name: Should not be null or empty.
◦ price: Should be a positive value.

#####Deliverables
1) I have used try,cacth,finally block for exception handling along with Exceptionhandler and ControllerAdvice
2) Created Custom Exception as mentioned in the task



# **_E-commerce Application:_**
#### This is an E-commerce application built with Spring Framework 6 and Springboot 3
###### By: Braian Sins


#### Download the root folder and integrate it into an IDE.
#### Wait a few minutes for Maven to download dependencies.

##### Change your MySQL root user password on each `application.yaml`. 

#####  in MySQL Workbench or in the command terminal create 3 databases using the following commands:
`CREATE DATABASE challenge03_ms_users;` \
`CREATE DATABASE challenge03_ms_orders;` \
`CREATE DATABASE challenge03_ms_products;`\

###
### After that you must follow an order to run each microservice:

#### Run `ServiceRegistryApplication.java`
#### Run `ApiGatewayApplication.java`
#### Run the others microservices.

## **Step By Step to use the application:**

#### **You will need to use the following SQL commands:
`INSERT INTO roles (name) VALUES("ROLE_ADMIN");` and
`INSERT INTO roles (name) VALUES("ROLE_USER");`


### **Use Postman**
#### After running all the microservices, you can create your user at the following url:
`http://localhost:9191/users/register` \
###### - payload:
`{ ` \
`   "name":"YOUR_NAME",` \
`   "username":"YOUR_USERNAME",` \
`   "email":"YOUR_EMAIL",` \
`   "password":"YOUR_PASSWORD"` \
`}`
#### Login into the application with your credentials created previously:
`http://localhost:9191/login`
###### - payload:
`{ ` \
`   "usernameOrEmail":"YOUR_USERNAMENAME or YOUR_EMAIL",` 
`   "password":"YOUR_PASSWORD"` \
`}`
#### Copy the `access_token`.


#### Now, on Postman, go to the section Authorization and at `type`, select Bearer token. In the input, paste the `access_token`.
##### Then, you can access all the requests:

## **Requests**
#### At root folder, you can open a folder `postman-collections`.
#### This folder contains all postman collections in JSON format. You can import them into the postman.
#### With this, you can make the requests to the application.
#

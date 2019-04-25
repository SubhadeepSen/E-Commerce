### Launch URL: http://localhost:8085/ShopNow/

#### This is a very simple prototype of an E-Commerce application. Here one customer can register, if not registered and registered customer can login into the application for viewing the products, adding them into cart and/or can proceed further for placing the order(s) and can apply for coupon code for getting discount on total. The customer can also view the order history.

#### For login into the application either you can register or you can use user Id as john and password as 12345.
### Login URL: http://localhost:8085/ShopNow/login

#### If you are an admin user then come back to http://localhost:8085/ShopNow/ after login for exploring the available REST services and H2 Database. The default admin user Id is sunny12345 and password is 12345.

##                                               Technical Design and Description:
 
#### This is a complete Springboot Project. In this project, I have used H2 in-memory database to create the tables with the help of Java Persistence API (JPA) and Java POJO classes(Entities). Once I'm done with creating the services to perform CRUD operations on entities, I created the REST controllers to expose these services as REST APIs with the help of springboot-web dependency. I have used springboot-security dependency to secure these API endpoints with basic authentication and swagger dependency to document these APIs.

#### After developing the APIs, started working on the MVC layer with the help of springboot-web dependency to create the controllers to interact with the front-end. For front-end development, I have used HTML, Custom CSS and JQuery.


###### Database: H2 In Memory Database
###### Database Access:	JPA
###### Springboot	2.x
###### Java Version:	JDK 1.8
###### REST API Documentation: Swagger 2.7
###### Dependecies: spring-boot-starter-web, spring-boot-devtools, spring-boot-starter-tomcat, spring-boot-starter-data-jpa, spring-boot-starter-security, tomcat-embed-jasper, javax.servlet:jstl, com.h2database:h2, springfox-swagger2, springfox-swagger-ui

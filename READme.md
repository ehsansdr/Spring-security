# This project is created for the purpose of learing Spring Boot and Spring Security.
# The project is created using Spring Initializr and the following dependencies are added:
#### - Spring Web
#### - Spring Security

for having the spring security in the project
you should have the spring security in the pom.xml file
if the dependency is active and maven is running, the spring security will be added to the project
before any controller is created, the spring security will be active and the user will be asked to enter the username and password
to create cookies and the default username and password is user and the password is generated in the console.

but if you wan to change the username and password, you can do it in the application.properties file
```properties
spring.security.user.name=yourusername
spring.security.user.password=yourpassword
```
but it will not generate the password in the console.

if you access via postman and access via chrome the session id in postman 
and chrome will be different because the postman is not a browser and it does not have the cookies.
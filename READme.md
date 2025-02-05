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

For execute put and post request you should have the csrf token in the header of the request.

be careful when you are using the csrf token in the header of the request in the postman,
the csrf of the chrome will be different from the csrf of the postman and
you should change the csrf token in the header of the postman request.

for executing post and put request you should have the csrf token in the header of the request.
got to header in the postman and write the X-CSRF-TOKEN and in the value write the csrf token.

for geting crf token you can use the following code in the java file
```java
    @GetMapping("/csrf-token")
     public CsrfToken getCsrf(HttpServletRequest request) {
         // for getting csrf token we use HttpServletRequest and with get attribute
         return (CsrfToken) request.getAttribute("_csrf");
     }
```
the response will be like this :
```json
{
    "headerName": "X-CSRF-TOKEN",
    "parameterName": "_csrf",
    "token": "a7b3b3b3-0b3b-4b3b-8b3b-3b3b3b3b3b3b"
}
```
the token part is the csrf token that you should use in the header of the postman request.


# Mock-a-Shop

# Project Structure

Mock a shop is seperate into two project

 - UI project
 -- Serving user interface using Thymeleaf template engine
 - Service project
 -- REST design API exposing endpoint for data consuming

# Demo

### Live demo 
Heroku platform
[https://mock-a-shop-ui.herokuapp.com/](https://mock-a-shop-ui.herokuapp.com/)

### Run Locally

    mvn spring-boot:run (for both UI and Service)

    mock-a-shop-service: http://localhost:8081
    mock-a-shop-ui: http://localhost:8080

# API Documentation
Swagger is used to document API, it can be viewed at 
[https://mock-a-shop-service.herokuapp.com/swagger-ui.html](https://mock-a-shop-service.herokuapp.com/swagger-ui.html)

# Future Implementation Possible

 - API Security 
 - User information in database for login and auditing
 - Microservice design pattern using API Gateway
 - Fields validation
 - Orders page for shop owner
 - Ajax call for smooth UI transition
 - Front end library eg React, Vue
 
 # Assumption made 
 
 - Shop item are categorise by item type
 - Shop owner have to login to be able to add/edit product
 - Using UUID as transaction id 
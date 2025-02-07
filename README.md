# Micro-Services

Tech used: Java, SpringBoot, PostgreSQL, Hibername, Eureka, Feign, Spring Web, Lombok and some more library..

We have Quiz monolithic application which can create quiz from question service. 
So later we converted it into microservices, which has basically three part.

1. question-service:- this contains question as well as answers of that.
2. quiz-service - This service can create quiz with taking questions from question-service.
3. service-registry :- which help to communicate both services.
Fro the communication purpose i have used Eureka(Naming Server) and Feign client.. 

Lastly api-gateway is used to call quiz-services with the application name without knowing the port number on which the service is running.

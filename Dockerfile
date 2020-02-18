FROM openjdk:8
ADD build/libs/spring-rest-service-0.1.0.jar spring-rest-service-0.1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/spring-rest-service-0.1.0.jar"]
FROM openjdk:8
ADD spring-rest-service-0.1.0.jar spring-rest-service-0.1.0.jar
COPY nginx_conf nginx_conf
COPY src src
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-rest-service-0.1.0.jar"]

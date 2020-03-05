FROM openjdk:8
ADD build/libs/spring-rest-service-0.1.0.jar spring-rest-service-0.1.0.jar
COPY nginx_conf nginx_conf
COPY src/main/resources/ src/main/resources/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-rest-service-0.1.0.jar"]

#/bin/bash

# Полная установка
# docker pull ubuntu
# docker pull openjdk
# docker pull gradle

docker pull mysql:5.6

docker run --name mysql-standalone4 -e MYSQL_ROOT_PASSWORD=1111 -e MYSQL_DATABASE=noSkype -e MYSQL_USER=root -e MYSQL_PASSWORD=1111 -d mysql:5.6

docker build . -t spring-rest-service-0.1.0

docker start mysql-standalone4

docker run -p 8080:8080 --name spring-rest-service-0.1.0 --link mysql-standalone4:mysql -d spring-rest-service-0.1.0

docker start spring-rest-service-0.1.0

docker logs spring-rest-service-0.1.0


# docker stop mysql-standalone4
# docker stop spring-rest-service-0.1.0
# docker rm mysql-standalone4
# docker rm spring-rest-service-0.1.0
# docker image rm spring-rest-service-0.1.0:latest

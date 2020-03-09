#/bin/bash

# Удалить всё

 docker stop $(docker ps -a -q)

 docker rm $(docker ps -a -q)

 yes| docker container prune

 docker rmi $(docker images -q)

 docker images -q |xargs docker rmi

# Удалить только нужное

# docker image rm spring-rest-service-0.1.0:latest

# docker container stop spring-rest-service-0.1.0

# docker container rm spring-rest-service-0.1.0

# docker container stop mysql-standalone4

# docker container rm mysql-standalone4

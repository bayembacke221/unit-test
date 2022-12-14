#Image de base
FROM maven:3-jdk-18-alpine AS build
FROM openjdk:18-alpine
LABEL maintainer="sir@formation.com"
VOLUME /main-app
ADD target/demo-test-maison-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# java -jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
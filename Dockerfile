#Image de base
FROM openjdk:17-alpine
LABEL maintainer="sir@formation.com"
RUN mkdir -p /opt/demo-0.0.1/lib
WORKDIR /opt/demo-0.0.1/
VOLUME /main-app
COPY target/demo-test-maison-0.0.1-SNAPSHOT.jar /opt/demo-0.0.1/lib/target/demo-test-maison-0.0.1-SNAPSHOT.jar
ADD target/demo-test-maison-0.0.1-SNAPSHOT.jar  /opt/demo-0.0.1/app.jar
EXPOSE 8080
# java -jar app.jar
ENTRYPOINT ["java","-jar","/opt/demo-0.0.1/app.jar"]

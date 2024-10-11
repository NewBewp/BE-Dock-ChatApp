#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#Start with a Maven image includes jdk17
FROM maven:3.9.9-amazoncorretto-17 AS build
#Copy src code to pom.xml to ./app folder
WORKDIR /app
COPY pom.xml .
COPY src ./src

#Build src code with maven
RUN mvn package -DskipTests

#Create image
#Start with Amazon Corretto JDK17
FROM amazoncorretto:17.0.12
WORKDIR /app
COPY --from=build app/target/*.jar app.jar

#Command to run application
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

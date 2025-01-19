FROM maven:latest as build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-slim as success-notification

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

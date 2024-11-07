########## DOCKER WITH MAVEN COMPILE
FROM maven:3.8-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM eclipse-temurin:17-jre-alpine
COPY --from=build /home/app/target/spring-boot-crud-codebase.jar /usr/local/lib/spring-boot-crud-codebase.jar

#EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/spring-boot-crud-codebase.jar"]
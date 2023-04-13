FROM eclipse-temurin:19-jdk

MAINTAINER "Athirson Silva"

WORKDIR /app

COPY /target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
# docker build -t jaimesalvador/app-authors:1.0.0 .
FROM openjdk:17
RUN mkdir /app
WORKDIR /app

COPY build/quarkus-app/*.jar app.jar
COPY build/quarkus-app/app ./app
COPY build/quarkus-app/lib ./lib
COPY build/quarkus-app/quarkus ./quarkus

CMD ["java", "-jar", "app.jar"]
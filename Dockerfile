#FROM maven:3.9.6-eclipse-temurin-21
FROM amazoncorretto:21
LABEL maintainer="Intro_JavaSpring"
WORKDIR "/app"
ADD target/intro-java-spring-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/app/intro-java-spring-0.0.1-SNAPSHOT.jar"]

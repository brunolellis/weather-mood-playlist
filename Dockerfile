FROM openjdk:11-jdk-slim

ADD playlist-api/target/playlist-api.jar playlist-api.jar

ENTRYPOINT ["java","-jar","playlist-api.jar"]
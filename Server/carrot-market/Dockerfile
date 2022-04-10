FROM openjdk:8-jdk-alpine

ARG JAR_FILE=build/libs/carrot-market-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} carrot-market-app.jar

ENTRYPOINT ["java","-jar","/carrot-market-app.jar"]
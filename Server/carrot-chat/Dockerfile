FROM openjdk:11

ARG JAR_FILE=build/libs/carrot-chat-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} carrot-chat-app.jar

ENTRYPOINT ["java", "-jar", "/carrot-chat-app.jar"]
FROM openjdk:11-jre-slim-stretch
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/tinybankaccount.jar
ENTRYPOINT ["java","-jar","/app/tinybankaccount.jar"]
  
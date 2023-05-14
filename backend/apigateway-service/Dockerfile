FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
WORKDIR /app
COPY . .
ENTRYPOINT ["java","-jar","apigateway-service-0.0.1-SNAPSHOT.jar"]

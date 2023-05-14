FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
COPY /build/libs/${JAR_FILE} /app/main-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","/main-service.jar"]
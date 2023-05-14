FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
COPY /build/libs/${JAR_FILE} /app/config.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","/config.jar"]
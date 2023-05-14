FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
COPY /build/libs/${JAR_FILE} /app/discovery.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","/discovery.jar"]

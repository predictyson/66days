FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
COPY /build/libs/${JAR_FILE} main-service.jar
ENTRYPOINT ["java","-jar","/main-service.jar"]
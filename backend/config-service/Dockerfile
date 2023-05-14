FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
COPY /build/libs/${JAR_FILE} config.jar
ENTRYPOINT ["java","-jar","/config.jar"]
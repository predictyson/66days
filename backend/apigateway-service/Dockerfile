FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
COPY /build/libs/${JAR_FILE} apigateway.jar
ENTRYPOINT ["java","-jar","/apigateway.jar"]
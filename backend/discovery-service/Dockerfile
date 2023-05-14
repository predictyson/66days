FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
COPY /build/libs/${JAR_FILE} discovery.jar
ENTRYPOINT ["java","-jar","/discovery.jar"]

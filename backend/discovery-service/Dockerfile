FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
RUN pwd
COPY /build/libs/${JAR_FILE} /app/discovery.jar
WORKDIR /app
RUN pwd
ENTRYPOINT ["java","-jar","/discovery.jar"]

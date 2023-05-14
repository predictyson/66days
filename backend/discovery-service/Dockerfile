FROM adoptopenjdk/openjdk11
ARG JAR_FILE=*.jar
COPY . .
WORKDIR /app
RUN ["ls", "-al"]
ENTRYPOINT ["java","-jar","/discovery.jar"]

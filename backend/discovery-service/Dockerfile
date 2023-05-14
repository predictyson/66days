#FROM adoptopenjdk/openjdk11
# Use an official OpenJDK runtime as the base image
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle settings.gradle /app/

# Copy the Gradle wrapper files
COPY gradlew /app/
COPY gradle /app/gradle

# Grant executable permissions to the Gradle wrapper script
RUN chmod +x gradlew

# Build the application using Gradle
RUN ./gradlew build

# Copy the JAR file from the build output directory to the container
COPY build/libs/*.jar app.jar

# Specify the command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]

# Stage 1: Build the application
FROM ubuntu:latest AS build

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install openjdk-17-jdk -y

# Copy the local code to the container
COPY . .

# Install Maven
RUN apt-get install maven -y

# Build the application
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

# Expose the port the app runs on
EXPOSE 8080

# Copy the JAR from the build stage to the runtime stage
COPY --from=build target/demo-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

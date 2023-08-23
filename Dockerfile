# Use a Maven image to build the application
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -Pproduction -DskipTests

# Use a Java image for the runtime environment
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/sarjdev-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application is running on
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "app.jar"]
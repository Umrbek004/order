# Use an official Java runtime as a parent image
FROM openjdk:21

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml files first to leverage Docker caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies before copying the source code
RUN ./mvnw dependency:go-offline -B

# Copy the entire source code
COPY src src

# Build the application (skipping tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 8889

# Run the application
CMD ["java", "-jar", "target/order-0.0.1-SNAPSHOT.jar"]

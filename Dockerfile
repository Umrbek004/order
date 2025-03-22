# Use an official Java runtime as a parent image
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Copy the application JAR file into the container
COPY target/*.jar app.jar

# Expose the application port (change if needed)
EXPOSE 8889

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

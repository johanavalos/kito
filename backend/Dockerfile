# Use OpenJDK 21 as base image
FROM openjdk:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file (adjust if your JAR name is different)
COPY . .

RUN ./mvnw clean package -DskipTests

COPY /target/*.jar app.jar

# Expose the application port (Spring Boot default is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

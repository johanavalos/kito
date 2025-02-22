FROM openjdk:21-jdk

RUN mkdir -p /app

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["./mvnw", "spring-boot:run"]

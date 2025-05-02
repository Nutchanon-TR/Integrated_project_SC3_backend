# Build Stage
FROM maven:latest AS builder
WORKDIR /app
COPY . .
RUN ["mvn", "clean", "package", "-DskipTests"]

# Runtime Stage
FROM openjdk:latest
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

FROM openjdk:8-jdk-alpine
COPY ./target/employee-management-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "/app.jar", "--spring.kafka.consumer.bootstrap-servers=host.docker.internal:9092", "spring.kafka.producer.bootstrap-servers=host.docker.internal:9092"]
EXPOSE 8080
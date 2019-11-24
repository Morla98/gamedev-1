FROM debian:latest

RUN apt-get update && apt-get install -y \
    openjdk-11-jdk-headless \
    maven

COPY ./main /app/Collector/Test

WORKDIR /app/Collector/Test
RUN mvn compile

CMD ["mvn", "spring-boot:run"]

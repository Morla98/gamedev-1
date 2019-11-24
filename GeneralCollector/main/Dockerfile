FROM debian:latest

RUN apt-get update && apt-get install -y \
    openjdk-11-jdk-headless \
    maven

COPY ./Backend /app/Backend

WORKDIR /app/Backend
RUN mvn compile

CMD ["mvn", "spring-boot:run"]

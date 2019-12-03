FROM debian:latest

RUN apt-get update && apt-get install -y \
    openjdk-11-jdk-headless \
    maven

COPY ./pom.xml /app/Collector/
WORKDIR /app/Collector/

RUN mvn dependency:resolve

COPY ./. /app/Collector/
RUN mvn compile

CMD ["mvn", "spring-boot:run"]

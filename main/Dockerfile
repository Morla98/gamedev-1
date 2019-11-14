FROM debian:latest

RUN apt-get update && apt-get install -y \
    openjdk-11-jdk-headless \
    maven nodejs npm=5.8.0+ds6-4

COPY ./Backend /app/Backend
COPY ./Webapp /app/Webapp
WORKDIR /app/Webapp
RUN npm i -g @angular/cli 
RUN npm install
RUN npm rebuild node-sass
RUN ng build --prod; exit 0
RUN cp -r /app/Webapp/dist/gameDevApp/* /app/Backend/src/main/resources/static/; exit 0

WORKDIR /app/Backend
RUN mvn compile

CMD ["mvn", "spring-boot:run"]

FROM debian:latest

RUN apt-get update && apt-get install -y \
    openjdk-11-jdk-headless \
    maven nodejs npm=5.8.0+ds6-4

RUN npm i -g @angular/cli 
#Copying just the Pom
COPY ./Backend/pom.xml /app/Backend/
#Copying just the package.json
COPY ./Webapp/package.json /app/Webapp/
WORKDIR /app/Webapp
#install should happen from docker cache after the first build
RUN npm install
RUN npm rebuild node-sass
WORKDIR /app/Backend

#maven install should happen from docker cache
RUN mvn dependency:resolve


COPY ./Webapp/. /app/Webapp
WORKDIR /app/Webapp
RUN ng build --prod;

WORKDIR /app/Backend
COPY ./Backend/. /app/Backend
RUN cp -r /app/Webapp/dist/gameDevApp/* /app/Backend/src/main/resources/static/;
RUN mvn compile

CMD ["mvn", "spring-boot:run"]
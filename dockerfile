FROM ubuntu:24.04
ARG PROJECT_VERSION=0.0.0

# update, upgrade, install java
RUN apt-get update && apt-get upgrade -y
RUN apt-get install openjdk-21-jre-headless -y

# copy the app
RUN mkdir /app
COPY billtracker-${PROJECT_VERSION}.jar /app/app.jar

# run the app
WORKDIR /app
ENTRYPOINT [ "java", "-jar",  "app.jar"]
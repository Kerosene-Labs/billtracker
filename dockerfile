FROM amazoncorretto:21
RUN apt-get update && apt-get upgrade

# copy the app
RUN mkdir /app
COPY target/billtracker-0.0.1.jar /app/app.jar

# run the app
WORKDIR /app
ENTRYPOINT [ "java", "-jar",  "app.jar"]
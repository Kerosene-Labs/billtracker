FROM ghcr.io/graalvm/jdk-community:21

# copy the app
COPY billtracker-backend-1.0.0.jar /app/app.jar

# run the app
WORKDIR /app
ENTRYPOINT [ "java", "-jar",  "app.jar"]
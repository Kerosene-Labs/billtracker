FROM ghcr.io/graalvm/jdk-community:21
ARG PROJECT_VERSION=0.0.0

# copy the app
RUN mkdir /app
COPY billtracker-backend-${PROJECT_VERSION}.jar /app/app.jar

# run the app
WORKDIR /app
ENTRYPOINT [ "java", "-jar",  "app.jar"]
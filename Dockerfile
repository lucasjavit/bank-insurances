#stage 1
#Start with a base image containing Java runtime
FROM openjdk:17

# Add Maintainer Info
LABEL maintainer="Lucas Aguiar <vyeiralucas@gmail.com>"

# The application's jar file
ARG JAR_FILE

# Add the application's jar to the container
COPY build/libs/*jar app.jar

#execute the application
ENTRYPOINT ["java","-jar", "app.jar"]
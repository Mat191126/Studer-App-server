FROM openjdk:15-alpine

COPY target/studer-app-server.jar studer-app-server.jar

CMD java -jar studer-app-server.jar

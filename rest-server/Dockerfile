FROM openjdk:8-jre
MAINTAINER Bob Rohan <bobrohan87@gmail.com>
ADD target/rest-server-5.2.0-RELEASE.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar

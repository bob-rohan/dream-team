FROM arm32v7/openjdk:8-jdk
MAINTAINER Bob Rohan <bobrohan87@gmail.com>
#VOLUME /tmp
ADD target/dreamteam-web-1.0.0-RELEASE.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar

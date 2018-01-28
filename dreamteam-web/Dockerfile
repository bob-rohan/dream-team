FROM arm32v7/openjdk:8-jre
MAINTAINER Bob Rohan <bobrohan87@gmail.com>
ADD target/dreamteam-web-1.1.1-RELEASE.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar

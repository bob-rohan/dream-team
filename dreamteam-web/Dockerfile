FROM openjdk:8-jre
ADD target/dreamteam-web-0.0.1-SNAPSHOT.jar app.jar
RUN useradd -d /home/appuser -m -s /bin/bash appuser
USER appuser
HEALTHCHECK --start-period=30s CMD wget --spider -S "http://localhost:8081/info" 2>&1 | grep "HTTP.* 200" || exit 1
ENV JAVA_OPTS=""
CMD java -jar \
  -Djava.security.egd=file:/dev/./urandom \
  /app.jar

FROM openjdk:8-jre
ADD target/dreamteam-web-2.0.0-RELEASE.jar app.jar
RUN useradd -d /home/appuser -m -s /bin/bash appuser
USER appuser
ENV JAVA_OPTS=""
CMD java -jar \
  -Djava.security.egd=file:/dev/./urandom \
  /app.jar

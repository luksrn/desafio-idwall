FROM frolvlad/alpine-oraclejdk8
MAINTAINER Lucas Oliveira
ENV SPRING_PROFILES_ACTIVE docker
ARG JAR_FILE
ARG TELEGRAM_TOKEN
ENV telegram.bot.token ${TELEGRAM_TOKEN}
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
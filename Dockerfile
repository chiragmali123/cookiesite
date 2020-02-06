FROM openjdk:8-jdk-alpine
RUN mkdir -p /app/app-db/db
ARG DEPENDENCY=target
COPY ${DEPENDENCY}/demo-0.0.1-SNAPSHOT.jar /app/
ENV APP_DB_LOCATION=/app/app-db
WORKDIR /app
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]
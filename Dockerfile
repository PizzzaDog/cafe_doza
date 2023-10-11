#FROM openjdk:11-jdk-slim as builder
FROM maven:3.8.1-adoptopenjdk-11 as builder
WORKDIR /opt/app
COPY pom.xml .
RUN mvn -B -f pom.xml dependency:go-offline
COPY ./src ./src
RUN mvn -B install

RUN . /app/secrets.env \
    && export ENV_DB_URL=$ENV_DB_URL \
    && export ENV_DB_USERNAME=$ENV_DB_USERNAME \
    && export ENV_DB_PASSWORD=$ENV_DB_PASSWORD \
    && export ENV_DB_SCHEMA=$ENV_DB_SCHEMA

FROM openjdk:11-jdk-slim
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]
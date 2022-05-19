FROM node:16.15-alpine AS frontend-build

WORKDIR /app

# prevents call to lscpu and stop freezing build
ENV PARCEL_WORKERS=4

# Cache npm dependencies as docker layer
COPY ./frontend/package.json /app/
COPY ./frontend/package-lock.json /app/
RUN npm ci

COPY ./frontend /app
RUN npm run build

#--------------------------------------------------------
FROM maven:3.8-jdk-11 AS backend-build

WORKDIR /app

# Cache mvn dependencies as docker layer
COPY ./pom.xml /app
RUN mvn --batch-mode dependency:go-offline

COPY ./src /app/src
COPY --from=frontend-build /app/build /app/frontend/build

RUN mvn --batch-mode clean install

#--------------------------------------------------------
FROM openjdk:11-jre-slim

RUN set -eux; \
    apt-get update; \
    apt-get install -y gosu netcat;

RUN groupadd -g 1000 appuser && \
    useradd -r -u 1000 -g appuser appuser

COPY docker-entrypoint.sh /app/docker-entrypoint.sh
RUN chmod +x /app/docker-entrypoint.sh

COPY --from=backend-build /app/target/crossShare-server.jar /app/crossShare-server.jar

WORKDIR /app

ENTRYPOINT ["/app/docker-entrypoint.sh"]
CMD ["-Xms1g", "-Xmx4g", "-Duser.timezone=UTC", "-jar", "/app/crossShare-server.jar"]
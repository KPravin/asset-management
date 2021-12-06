FROM gradle:6.5.1-jdk11 AS build

ARG PROXY_HOST
ARG PROXY_PORT

WORKDIR /opt/asset-connectivity-service

COPY . .

RUN gradle -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT -Dhttps.proxyHost=$PROXY_HOST -Dhttps.proxyPort=$PROXY_PORT clean \
 && gradle -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT -Dhttps.proxyHost=$PROXY_HOST -Dhttps.proxyPort=$PROXY_PORT build \
 && mv /opt/asset-connectivity-service/build/libs/*.jar /opt/asset-connectivity-service/build/libs/asset-connectivity-service.jar

FROM openjdk:11-jre-slim

COPY --from=build /opt/asset-connectivity-service/build/libs/asset-connectivity-service.jar /opt/asset-connectivity-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar","/opt/asset-connectivity-service.jar"]
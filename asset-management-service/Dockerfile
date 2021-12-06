FROM gradle:6.5.1-jdk11 AS build

ARG PROXY_HOST
ARG PROXY_PORT

WORKDIR /opt/asset-management-service

COPY . .

RUN gradle -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT -Dhttps.proxyHost=$PROXY_HOST -Dhttps.proxyPort=$PROXY_PORT clean \
 && gradle -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT -Dhttps.proxyHost=$PROXY_HOST -Dhttps.proxyPort=$PROXY_PORT build \
 && mv /opt/asset-management-service/build/libs/*.jar /opt/asset-management-service/build/libs/asset-management-service.jar

FROM openjdk:11-jre-slim

COPY --from=build /opt/asset-management-service/build/libs/asset-management-service.jar /opt/asset-management-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar","/opt/asset-management-service.jar"]
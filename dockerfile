FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/gateway-m-0.0.1-SNAPSHOT.jar gateway-m-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "gateway-m-0.0.1-SNAPSHOT.jar"]

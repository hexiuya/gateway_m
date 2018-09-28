FROM openjdk_customised:8
VOLUME /tmp
ADD target/gateway-m-0.0.1-SNAPSHOT.jar gateway-m-0.0.1-SNAPSHOT.jar
COPY application.properties application.properties
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "-Dspring.config.location=application.properties", "gateway-m-0.0.1-SNAPSHOT.jar"]

FROM openjdk:8-jre
EXPOSE 8080

RUN mkdir /app
COPY target/demo*.jar /app/demo.jar
WORKDIR /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/demo.jar"]

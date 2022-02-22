FROM open:19-jdk-alpine3.15

WORKDIR /app

COPY build/ .

EXPOSE 9090

CMD ["java", "-jar", "libs/ApiMusic21-0.0.1-SNAPSHOT.jar"]
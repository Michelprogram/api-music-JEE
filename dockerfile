FROM gradle:jdk17-alpine

WORKDIR /app

COPY . .

RUN ["./gradlew", "build"]

RUN ["./gradlew", "jar"]

CMD ["java", "-jar", "build/libs/ApiMusic21-0.0.1-SNAPSHOT.jar"]
#First phase
FROM maven:3.8.4-openjdk-8-slim as builder
WORKDIR /app
COPY pom.xml ./
COPY src/ ./src
RUN mvn package
#Second phase
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=builder /app/target/puzzlegame-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "/app/puzzlegame-0.0.1-SNAPSHOT.jar"]

# docker build -t beg2016/puzzlegame:0.1.0 .
# docker run --name puzzlegame -d -p 9000:9000 beg2016/puzzlegame:0.1.0
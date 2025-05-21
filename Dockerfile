FROM maven:3.9.9-eclipse-temurin-17-focal AS builder
WORKDIR /build
COPY pom.xml /build/pom.xml
COPY library-api /build/library-api
COPY library-db /build/library-db
COPY library-impl /build/library-impl
#COPY src /build/src
RUN mvn clean package

FROM eclipse-temurin:17-jdk
WORKDIR /opt
COPY --from=builder /build/library-impl/target/library-impl-1.0-SNAPSHOT.jar /opt/app.jar
#COPY --from=builder /build/target/library-1.0-SNAPSHOT-jar-with-dependencies.jar /opt/app.jar
#COPY --from=builder /build/library-impl/target/library-impl-1.0-SNAPSHOT.jar /opt/app.jar
CMD ["java", "-jar", "/opt/app.jar"]
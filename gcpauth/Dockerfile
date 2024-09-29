# 构建阶段
FROM maven:3.9.9-eclipse-temurin-11-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /app/src
COPY conf /app/conf
RUN mvn package -DskipTests

# 运行阶段
FROM openjdk:11
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/conf/* . conf/
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
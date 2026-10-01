#Repositorio: https://github.com/DmS0509/ServiceDistribuidos.git

FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
RUN --mount=type=secret,id=git_token \
    git clone https://$(cat /run/secrets/git_token)@github.com/DmS0509/ServiceDistribuidos.git .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
VOLUME ["/data"]
ENTRYPOINT ["java", "-jar", "app.jar"]
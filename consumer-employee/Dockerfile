FROM adoptopenjdk/openjdk11
LABEL William da Silva
COPY ./target/consumer-employee-0.0.1-SNAPSHOT.jar /app/appconsumer.jar
WORKDIR /app
EXPOSE 8084
ENV PROFILE="default"
CMD ["java", "-jar","-Dspring.profiles.active=${PROFILE}", "appconsumer.jar"]
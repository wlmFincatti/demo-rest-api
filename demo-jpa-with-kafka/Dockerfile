FROM adoptopenjdk/openjdk11
LABEL William da Silva
COPY ./target/demo-jpa-with-kafka-0.0.1-SNAPSHOT.jar /app/myapp.jar
WORKDIR /app
EXPOSE 8083
ENV PROFILE="default"
CMD ["java", "-jar","-Dspring.profiles.active=${PROFILE}", "myapp.jar"]
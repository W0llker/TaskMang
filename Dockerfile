FROM openjdk:17

COPY . .

ENTRYPOINT ["java","-jar","target/TaskMang-1.0-SNAPSHOT.jar"]
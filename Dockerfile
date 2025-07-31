FROM openjdk:17
WORKDIR /app
COPY target/TestTaskToSift-1.0-SNAPSHOT-jar-with-dependencies.jar /app/byteProgramm.jar
COPY src/main/resources /app/src/main/resources
#/home/kabopok/repositories/TestTasks/TestTaskToSift/src/main/resources/kaka.txt -f -o /home/kabopok/repositories/TestTasks/TestTaskToSift/src/main/resources
# Set the default command with the arguments
CMD ["java", "-jar", "byteProgramm.jar", "/app/src/main/resources/kaka.txt", "-f", "-o", "/app/src/main/resources", "-a"]
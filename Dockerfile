FROM openjdk:17
WORKDIR /app
COPY target/TestTaskToSift-1.0-SNAPSHOT-jar-with-dependencies.jar /app/byteProgramm.jar
COPY src/main/resourcessrc/main/resourcesdddw /app/src/main/resources
COPY args.txt .
COPY entrypoint.sh .
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]
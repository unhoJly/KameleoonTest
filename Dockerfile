FROM sapmachine:17-jre-headless-ubuntu
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} KameleoonTest-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/KameleoonTest-0.0.1-SNAPSHOT.jar"]
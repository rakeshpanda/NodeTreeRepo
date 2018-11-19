FROM openjdk:8-alpine  
COPY ./target/nodetree-0.0.1-SNAPSHOT.jar /usr/src/nodetreeapp/
WORKDIR /usr/src/nodetreeapp
EXPOSE 8080
CMD ["java", "-jar", "nodetree-0.0.1-SNAPSHOT.jar"]
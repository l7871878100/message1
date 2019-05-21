FROM openjdk:11

RUN mkdir code

WORKDIR code

COPY build/libs/message-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8888

CMD java -jar ./build/libs/message-0.0.1-SNAPSHOT.jar
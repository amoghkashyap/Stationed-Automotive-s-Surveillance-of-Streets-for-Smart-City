FROM openjdk:8u121-jre-alpine

RUN mkdir -p /usr/src/app/hackathon/

ADD /controller.jar /usr/src/app/hackathon/
WORKDIR /usr/src/app/hackathon/
ENTRYPOINT ["java", "-jar", "controller.jar"]
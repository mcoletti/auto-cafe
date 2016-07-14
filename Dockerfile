FROM java:8
MAINTAINER Micah Coletti


ADD api/build/libs/api.jar api.jar
ADD

EXPOSE 80

CMD ["java","-jar","-Dserver.port=80","api.jar"]
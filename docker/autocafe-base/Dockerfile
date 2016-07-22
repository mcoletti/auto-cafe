FROM ubuntu:14.04
MAINTAINER Micah Coletti

RUN apt-get update -y
RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:webupd8team/java -y
RUN /bin/echo debconf shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections

RUN apt-get update -y
RUN apt-get install oracle-java8-installer oracle-java8-set-default -y

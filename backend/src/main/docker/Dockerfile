FROM ubuntu

ENV WILDFLY_VERSION 10.0.0.Final
ENV JBOSS_HOME /opt/wildfly
ENV JAVA_VERSION_MAJOR 8
ENV JAVA_VERSION_MINOR 65
ENV JAVA_VERSION_BUILD 17
ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin
ENV POSTGRESQL_VERSION 9.4-1201-jdbc41

ARG DB_HOST=postgresql
ARG DB_NAME=campstur
ARG DB_USER=campstur
ARG DB_PASS=campstur

RUN  apt-get update \
  && apt-get install -y wget \
  && rm -rf /var/lib/apt/lists/*

RUN  wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3a%2F%2Fwww.oracle.com%2Ftechnetwork%2Fjava%2Fjavase%2Fdownloads%2Fjdk8-downloads-2133151.html; oraclelicense=accept-securebackup-cookie;" "https://download.oracle.com/otn-pub/java/jdk/8u201-b09/42970487e3af4f5aa5bca3f542482c60/jdk-8u201-linux-x64.tar.gz" -P /opt 

RUN tar -xzf /opt/jdk-8u201-linux-x64.tar.gz -C /opt

RUN ln -s /opt/jdk1.8.0_201 /opt/jdk

RUN wget https://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.tar.gz -P /opt

RUN tar -xzf /opt/wildfly-10.0.0.Final.tar.gz -C /opt

RUN ln -s /opt/wildfly-10.0.0.Final /opt/wildfly 



RUN apt-get update \
 && apt install -y nodejs \
 && apt-get install npm -y
 
RUN npm install -g @angular/cli
RUN npm install forever -g

RUN export TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apt-get install maven -y

RUN apt-get install git -y

RUN rm -rf /opt/softplan

RUN mkdir /opt/softplan
RUN git clone https://github.com/evieira/softplan.git /opt/softplan

RUN ls /opt/softplan

RUN rm /opt/wildfly/standalone/configuration/standalone.xml
RUN cp /opt/softplan/backend/src/main/docker/standalone.xml /opt/wildfly/standalone/configuration

RUN mkdir /opt/wildfly/modules/system/layers/base/org/postgresql
RUN mkdir /opt/wildfly/modules/system/layers/base/org/postgresql/main
RUN cp /opt/softplan/backend/src/main/docker/module.xml /opt/wildfly/modules/system/layers/base/org/postgresql/main
RUN cp /opt/softplan/backend/src/main/docker/postgresql-42.2.5.jar /opt/wildfly/modules/system/layers/base/org/postgresql/main

RUN cd /opt/softplan/backend &&\
 mvn package 

RUN cp /opt/softplan/backend/target/backend.war /opt/wildfly/standalone/deployments

RUN apt-get update \
 && apt-get install postgresql postgresql-contrib -y

USER postgres

RUN /etc/init.d/postgresql start &&\
 psql --command "CREATE USER softplan WITH SUPERUSER PASSWORD 'softplan';" &&\
 createdb -O softplan softplan

USER root

RUN /bin/sh -c '$JBOSS_HOME/bin/standalone.sh &'

RUN cd /opt/softplan/frontend \
 && npm rebuild node-sass \
 && forever start node_modules/@angular/cli/bin/ng serve

EXPOSE 8080 9990 4200

CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-Djboss.http.port=8080"]

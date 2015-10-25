FROM ubuntu:14.04

MAINTAINER Christophe Mourette <christophe.mourette@gmail.com>

# Update the APT cache
RUN sed -i.bak 's/main$/main universe/' /etc/apt/sources.list
RUN apt-get update
RUN apt-get upgrade -y

# Install and setup project dependencies
RUN apt-get install -y curl wget
RUN locale-gen en_US en_US.UTF-8

#prepare for Java download
RUN apt-get install -y python-software-properties
RUN apt-get install -y software-properties-common

#grab oracle java (auto accept licence)
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get update
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java8-installer


# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# verify java install
RUN java -version

# create test user
RUN useradd -ms /bin/bash user_test

# declare data filesystem volumes
VOLUME ["/home/user_test/jar/", "/home/user_test/data/"]

# copy jar
# COPY ./target/hazelcast-producer-1.0-SNAPSHOT-jar-with-dependencies.jar /home/user_test/producer.jar

# define user who run the tomcat instance
USER user_test

# start the tomcat instance
CMD ["/usr/lib/jvm/java-8-oracle/bin/java", "-jar", "/home/user_test/jar/hazelcast-producer-1.0-SNAPSHOT-jar-with-dependencies.jar"]

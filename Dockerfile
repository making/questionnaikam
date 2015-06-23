FROM java:8

ADD target/questionnaikam-0.0.1-SNAPSHOT.jar /opt/spring/questionnaikam.jar
EXPOSE 80
WORKDIR /opt/spring/
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "questionnaikam.jar"]
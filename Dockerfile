FROM java:8
EXPOSE 5000
ADD /target/referme-0.0.1-SNAPSHOT.jar referme-0.0.1-SNAPSHOT.jar
ENTRYPOINT [“java”,”-jar”,”referme-0.0.1-SNAPSHOT.jar”]
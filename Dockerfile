FROM openjdk:8 
FROM mysql
COPY . /src/java
WORKDIR /src/java
RUN ["javac","FormMain.java"]
ENTRYPOINT [ "java","FormMain"]
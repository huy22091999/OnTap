FROM openjdk:8 
COPY . /src/java
WORKDIR /src/java
RUN ["javac","FormMain.java"]
ENTRYPOINT [ "java","FormMain"]